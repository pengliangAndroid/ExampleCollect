package com.wstro.examplecollect.presenter;

import com.wstro.app.common.mvp.BasePresenter;
import com.wstro.app.common.utils.rx.RxUtils;
import com.wstro.examplecollect.Constants;
import com.wstro.examplecollect.view.CommonListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class CommonListPresenter extends BasePresenter<CommonListView> {
    //private static final int PAGE_SIZE = 10;

    private int curPage = 1;

    private boolean hasMoreData = true;

    public CommonListPresenter() {
    }

    public void getData(){
        hasMoreData = true;

        subscription.add(Observable.just("")

                .map(new Func1<String, List<String>>() {
                    @Override
                    public List<String> call(String s) {
                        try {
                            Thread.sleep((long) (1500*Math.random()));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(curPage % 4 == 3){
                            curPage++;
                            //throw new Exception("xxx");
                            String ss = null;
                            ss.toString();
                        }else if(curPage == 6){
                            return null;
                        }

                        int pageIndex = (curPage -1)* Constants.PAGE_SIZE;
                        List<String> tmpList = new ArrayList<String>();
                        for (int i = pageIndex; i < pageIndex+Constants.PAGE_SIZE; i++) {
                            tmpList.add("New Item " + i);
                        }
                        return tmpList;
                    }
                })
                .compose(RxUtils.<List<String>>applyIOToMainThreadSchedulers())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        subscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().onGetDataFail("加载失败");
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        if(strings == null || strings.size() < Constants.PAGE_SIZE)
                            hasMoreData = false;
                        curPage++;

                        getMvpView().onGetDataSuccess(strings);

                    }
                })
        );
    }


    public boolean isHasMoreData() {
        return hasMoreData;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
}