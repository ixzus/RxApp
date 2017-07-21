package com.ixzus.rxapp;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ixzus.rxapp.base.BaseActivity;
import com.ixzus.rxapp.constant.ViewStatus;

public class Main2Activity extends BaseActivity implements IActivity {



    @Override
    public int initLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {
        toolbar(true, null, "状态页");
    }

    @Override
    public void initData() {
        showStatus((Activity) mContext, ViewStatus.STATUS_LOADING);
    }
}
