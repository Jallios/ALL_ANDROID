package com.example.a2weeks;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Cars implements Parcelable {
    private String modelCars;
    private int imgCars;
    private String opisanie;

    public Cars(String modelCars, int imgCars, String opisanie){
        this.modelCars = modelCars;
        this.imgCars = imgCars;
        this.opisanie = opisanie;
    }

    protected Cars(Parcel in) {
        modelCars = in.readString();
        imgCars = in.readInt();
        opisanie = in.readString();
    }

    public static final Creator<Cars> CREATOR = new Creator<Cars>() {
        @Override
        public Cars createFromParcel(Parcel in) {
            return new Cars(in);
        }

        @Override
        public Cars[] newArray(int size) {
            return new Cars[size];
        }
    };

    public String getOpisanie() {
        return opisanie;
    }

    public void setOpisanie(String opisanie) {
        this.opisanie = opisanie;
    }

    public String getModelCars() {
        return modelCars;
    }

    public void setModelCars(String modelCars) {
        this.modelCars = modelCars;
    }

    public int getImgCars() {
        return imgCars;
    }

    public void setImgCars(int imgCars) {
        this.imgCars = imgCars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(modelCars);
        parcel.writeInt(imgCars);
        parcel.writeString(opisanie);
    }
}
