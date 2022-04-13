package com.example.materialtest;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

//public class Fruit implements Serializable {
public class Fruit implements Parcelable {

    private String name;
    private int imageId;

    public Fruit(){

    }

    public Fruit(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return this.name;
    }
    public int getImageId(){
        return this.imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageId);
    }
    public static final Parcelable.Creator<Fruit> CREATOR =new Parcelable.Creator<Fruit>(){
        @Override
        public Fruit createFromParcel(Parcel source) {
            Fruit fruit=new Fruit();
            fruit.name=source.readString();
            fruit.imageId=source.readInt();
            return fruit;
        }

        @Override
        public Fruit[] newArray(int size) {
            return new Fruit[size];
        }
    };
}
