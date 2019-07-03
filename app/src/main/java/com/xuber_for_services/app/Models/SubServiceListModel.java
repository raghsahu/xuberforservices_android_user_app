package com.xuber_for_services.app.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class SubServiceListModel implements Parcelable {

    String name  = "";
    String image = "";
    //String id = "";
    int id;
    int img;
    private boolean isSelected;


    public SubServiceListModel(String name, int img) {
        this.name = name;
        this.img = img;
    }
    public SubServiceListModel() {
    }

    public SubServiceListModel(String name, String img) {
        this.name = name;
        this.image = img;
    }

    protected SubServiceListModel(Parcel in) {
        name = in.readString();
        image = in.readString();
        img = in.readInt();
        id = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<SubServiceListModel> CREATOR = new Creator<SubServiceListModel>() {
        @Override
        public SubServiceListModel createFromParcel(Parcel in) {
            return new SubServiceListModel(in);
        }

        @Override
        public SubServiceListModel[] newArray(int size) {
            return new SubServiceListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
