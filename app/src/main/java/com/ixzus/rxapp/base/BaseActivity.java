package com.ixzus.rxapp.base;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ixzus.rxapp.MethodInfo;
import com.ixzus.rxapp.R;
import com.ixzus.rxapp.constant.ViewStatus;
import com.ixzus.rxapp.widget.FactoryDialog;

/**
 * 功能描述:
 * Created by ixzus on 2017/7/14.
 */

public class BaseActivity extends AppCompatActivity {

    protected Context mContext = this;

    /**
     * 标题
     *
     * @param backStr
     * @param isback
     * @param title
     */
    @MethodInfo(date = "2017/7/17", author = "ixzus", version = 1, desc = "标题信息 p1=是否有返回, p2返回文字, p3标题")
    protected void toolbar(boolean isback, String backStr, String title) {
        if (isback) {
            findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
            findViewById(R.id.toolbar_line).setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(title))
            ((TextView) findViewById(R.id.toolbar_title)).setText(title);
        if (!TextUtils.isEmpty(backStr))
            ((TextView) findViewById(R.id.toolbar_back_text)).setText(backStr);

    }

    /**
     * 加载
     *
     * @param activity
     * @return
     */
    protected FactoryDialog showDialog(final Activity activity) {
        FactoryDialog dialog = FactoryDialog.create(activity.getFragmentManager());
        dialog.setLayoutRes(R.layout.dialog_net)
                .setmOutsideCancel(false)
                .setmBackCancel(false)
                .setmTag("netdialog");
        return dialog;
    }

    /**
     * 状态
     *
     * @param activity
     * @param status
     */
    protected void showStatus(final Activity activity, final int status) {
        switch (status) {
            case ViewStatus.STATUS:
                if (activity.findViewById(R.id.viewErr) != null) {
                    activity.findViewById(R.id.viewErr).setVisibility(View.GONE);
                }
                if (activity.findViewById(R.id.viewLoading) != null) {
                    activity.findViewById(R.id.viewLoading).setVisibility(View.GONE);
                }
                if (activity.findViewById(R.id.viewNoData) != null) {
                    activity.findViewById(R.id.viewNoData).setVisibility(View.GONE);
                }
                if (activity.findViewById(R.id.viewNoNet) != null) {
                    activity.findViewById(R.id.viewNoNet).setVisibility(View.GONE);
                }
                break;
            case ViewStatus.STATUS_ERR:
                if (activity.findViewById(R.id.viewErr) != null) {
                    activity.findViewById(R.id.viewErr).setVisibility(View.VISIBLE);
                }

                break;
            case ViewStatus.STATUS_LOADING:
                if (activity.findViewById(R.id.viewLoading) != null) {
                    activity.findViewById(R.id.viewLoading).setVisibility(View.VISIBLE);
                }
                break;
            case ViewStatus.STATUS_NO_DATA:
                if (activity.findViewById(R.id.viewNoData) != null) {
                    activity.findViewById(R.id.viewNoData).setVisibility(View.VISIBLE);
                }
                break;
            case ViewStatus.STATUS_NO_NET:
                if (activity.findViewById(R.id.viewNoNet) != null) {
                    activity.findViewById(R.id.viewNoNet).setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
