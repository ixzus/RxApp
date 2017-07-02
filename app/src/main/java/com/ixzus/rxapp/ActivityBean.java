package com.ixzus.rxapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xoutl on 2017/7/2.
 */

public class ActivityBean implements Parcelable {
    private BaseDialog dialog;

    public BaseDialog getDialog() {
        return dialog;
    }

    public void setDialog(BaseDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.dialog, flags);
    }

    public ActivityBean() {
    }

    protected ActivityBean(Parcel in) {
        this.dialog = in.readParcelable(BaseDialog.class.getClassLoader());
    }

    public static final Creator<ActivityBean> CREATOR = new Creator<ActivityBean>() {
        @Override
        public ActivityBean createFromParcel(Parcel source) {
            return new ActivityBean(source);
        }

        @Override
        public ActivityBean[] newArray(int size) {
            return new ActivityBean[size];
        }
    };
}
