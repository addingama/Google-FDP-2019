package com.google.fdp.destination;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gama on 2019-08-16.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class Destination implements Parcelable {
    public static String EXTRA_NAME = "Destination";
    private String name;
    private String location;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeString(this.image);
    }

    public Destination() {
    }

    protected Destination(Parcel in) {
        this.name = in.readString();
        this.location = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Destination> CREATOR = new Parcelable.Creator<Destination>() {
        @Override
        public Destination createFromParcel(Parcel source) {
            return new Destination(source);
        }

        @Override
        public Destination[] newArray(int size) {
            return new Destination[size];
        }
    };
}
