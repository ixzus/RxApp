package com.ixzus.rxapp.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ixzus.rxapp.R;
import com.ixzus.rxapp.constant.ViewStatus;
import com.ixzus.rxapp.widget.FactoryDialog;

/**
 * 功能描述:
 * Created by ixzus on 2017/7/14.
 */

public class BaseActivity extends AppCompatActivity {

    protected FactoryDialog showDialog(final Activity activity) {
        FactoryDialog dialog = FactoryDialog.create(activity.getFragmentManager());
        dialog.setLayoutRes(R.layout.dialog_net)
                .setmOutsideCancel(false)
                .setmBackCancel(false)
                .setmTag("netdialog");
        return dialog;
    }


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
