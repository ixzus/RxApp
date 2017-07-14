package com.ixzus.rxapp;

import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.ixzus.rxapp.constant.ViewStatus;

public class Main2Activity extends AppCompatActivity implements IActivity {


    SparseArray<View> layoutSparseArray = new SparseArray();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        toolbar("切换", true, "状态页");
//
//    }

    private void toolbar(String backStr, boolean isback, String title) {
        if (isback) {
            findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
            findViewById(R.id.toolbar_line).setVisibility(View.VISIBLE);
            findViewById(R.id.toolbar_back).setOnClickListener(view -> showLoading(true));
        }
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
        ((TextView) findViewById(R.id.toolbar_back_text)).setText(backStr);
//        addLayoutResId(R.layout.view_no_data,0);
//        addLayoutResId(R.layout.view_loading,1);

    }

    private void showLoading(boolean flag) {
        if (flag)
            findViewById(R.id.viewLoading).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.viewLoading).setVisibility(View.GONE);
    }


    @Override
    public int initLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {
        toolbar("切换", true, "状态页");
    }

    @Override
    public void initData() {
        ViewStatus bean = getIntent().getParcelableExtra("viewStatus");
        bean.showStatus(this, ViewStatus.ViewStatus.STATUS_LOADING);
    }
}
