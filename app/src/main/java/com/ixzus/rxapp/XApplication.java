package com.ixzus.rxapp;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ixzus.rxapp.impl.ISwipeBack;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by xoutl on 2017/7/1.
 */

public class XApplication extends Application {
    private static final String TAG = "XApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @MethodInfo(author = "ixzus", date = "2017/7/3", version = 2)
    private void init() {
        initLeakCanary();
        initLifecycle();
    }


    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    private void initLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
               /* ActivityBean bean = new ActivityBean();
                bean.setDialog(showDialog(activity));
                activity.getIntent().putExtra("ActivityBean", bean);*/

                if (activity instanceof IActivity) {
                    activity.setContentView(((IActivity) activity).initLayout());
                    ((IActivity) activity).initView();
                    ((IActivity) activity).initData();
                }

                if (activity instanceof IToolbar) {
                    initToolbar(activity);
                }

                if (activity instanceof ISwipeBack) {
                    SwipeBackHelper.onCreate(activity);
                }

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activity instanceof ISwipeBack) {
                    SwipeBackHelper.onPostCreate(activity);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activity instanceof ISwipeBack) {
                    SwipeBackHelper.onDestroy(activity);
                }
              /*  ActivityBean bean = activity.getIntent().getParcelableExtra("ActivityBean");
                bean.setDialog(null);*/

            }
        });
    }

    private void initToolbar(final Activity activity) {
        if (activity.findViewById(R.id.toolbar) != null) {
            if (activity instanceof AppCompatActivity) {
                ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                    activity.getActionBar().setDisplayShowTitleEnabled(false);
                }
            }
        }

//        if (activity.findViewById(R.id.toolbar_title) != null) {
//            ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
//        }
        if (activity.findViewById(R.id.toolbar_back) != null) {
            activity.findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onBackPressed();
                }
            });
        }

    }

/*    private FactoryDialog showDialog(final Activity activity) {
        FactoryDialog dialog = FactoryDialog.create(activity.getFragmentManager());
        dialog.setLayoutRes(R.layout.dialog_net)
                .setmOutsideCancel(false)
                .setmBackCancel(false)
                .setmTag("netdialog");
        return dialog;
    }*/

}
