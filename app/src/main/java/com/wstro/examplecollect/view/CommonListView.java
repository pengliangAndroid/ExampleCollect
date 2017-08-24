package com.wstro.examplecollect.view;

import com.wstro.app.common.mvp.MvpView;

import java.util.List;

public interface CommonListView extends MvpView {
    void onGetDataSuccess(List<String> list);

    void onGetDataFail(String error);
}