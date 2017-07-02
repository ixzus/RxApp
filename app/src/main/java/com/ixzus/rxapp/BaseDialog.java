package com.ixzus.rxapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseDialog extends DialogFragment implements Parcelable {

    /**
     * tag
     */
    private static final String TAG = "BaseDialog";

    /**
     * FragmentManager
     */
    public FragmentManager mFragmentManager;

    /**
     * dialog操作监听回调
     */
    private DialogViewListener mDialogViewListener;

    /**
     * 是否点击其他区域关闭 默认关闭
     */
    private boolean mOutsideCancel = true;

    /**
     * 返回键是否能撤销dialog
     */
    private boolean mBackCancel = true;

    /**
     * tag标记
     */
    private String mTag = "BaseDialog";

    /**
     * 布局文件
     */
    public int mLayoutRes;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


    public interface DialogViewListener {

        void bindView(View v);

        void dismiss();

    }

    public BaseDialog() {

    }

    public BaseDialog setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.Theme_dialogLayout);
        setRetainInstance(true);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT <= 10) {
            return super.onCreateDialog(savedInstanceState);
        }
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View body = inflater.inflate(getLayoutRes(), null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(body);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(mOutsideCancel);
        setCancelable(mBackCancel);
        bindView(body);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        if (Build.VERSION.SDK_INT > 10) {
            return super.onCreateView(inflater, view, bundle);
        }
        View body = inflater.inflate(getLayoutRes(), null, false);
        bindView(body);
        return body;
    }

    public void bindView(View v) {
        if (mDialogViewListener != null) {
            mDialogViewListener.bindView(v);
        }
    }


    /**
     * show显示dialog
     *
     * @param fragmentManager
     */
    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }

    public BaseDialog show() {
        show(mFragmentManager);
        return this;
    }

    @LayoutRes
    public int getLayoutRes() {
        return mLayoutRes;
    }

    public BaseDialog setDialogViewListener(DialogViewListener listener) {
        mDialogViewListener = listener;
        return this;
    }

    public BaseDialog setmOutsideCancel(boolean mOutsideCancel) {
        this.mOutsideCancel = mOutsideCancel;
        return this;
    }

    public BaseDialog setmBackCancel(boolean mBackCancel) {
        this.mBackCancel = mBackCancel;
        return this;
    }

    public BaseDialog setmTag(String mTag) {
        this.mTag = mTag;
        return this;
    }

    public BaseDialog setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDialogViewListener != null) {
            mDialogViewListener.dismiss();
        }
    }
}