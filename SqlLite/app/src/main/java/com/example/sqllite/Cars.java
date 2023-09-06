package com.example.sqllite;


import android.os.Parcel;
import android.os.Parcelable;

public class Cars implements Parcelable{
    private int ID_Car;
    private String Model_Car;
    private String Opisanie_Car;

    public Cars(int ID_Car, String model_Car, String opisanie_Car) {
        this.ID_Car = ID_Car;
        Model_Car = model_Car;
        Opisanie_Car = opisanie_Car;
    }

    protected Cars(Parcel in) {
        ID_Car = in.readInt();
        Model_Car = in.readString();
        Opisanie_Car = in.readString();
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

    public int getID_Car() {
        return ID_Car;
    }

    public void setID_Car(int ID_Car) {
        this.ID_Car = ID_Car;
    }

    public String getModel_Car() {
        return Model_Car;
    }

    public void setModel_Car(String model_Car) {
        Model_Car = model_Car;
    }

    public String getOpisanie_Car() {
        return Opisanie_Car;
    }

    public void setOpisanie_Car(String opisanie_Car) {
        Opisanie_Car = opisanie_Car;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Model_Car);
        parcel.writeInt(ID_Car);
        parcel.writeString(Opisanie_Car);
    }
}
