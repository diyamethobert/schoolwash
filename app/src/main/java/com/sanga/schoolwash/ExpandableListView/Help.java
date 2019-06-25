package com.sanga.schoolwash.ExpandableListView;

import android.os.Parcel;
import android.os.Parcelable;

public class Help implements Parcelable {

    public final String name;

    public Help(String name) {
        this.name = name;
    }

    protected Help(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Help> CREATOR = new Creator<Help>() {
        @Override
        public Help createFromParcel(Parcel in) {
            return new Help(in);
        }

        @Override
        public Help[] newArray(int size) {
            return new Help[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
