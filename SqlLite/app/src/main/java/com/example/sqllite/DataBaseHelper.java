package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "cars.db";
    private  static  final  int SCHEMA = 1;
    static final String TABLE_NAME = "Car";

    private static  final  String COLUMN_ID = "id_car";
    private static  final  String COLUMN_MODEL = "model_car";
    private static  final  String COLUMN_OPISANIE = "opisanie_car";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (" + COLUMN_ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MODEL
        + " TEXT, " + COLUMN_OPISANIE + " INTEGER); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addCar (Cars car){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MODEL, car.getModel_Car());
        contentValues.put(COLUMN_OPISANIE, car.getOpisanie_Car());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }
    public void updateCar(Cars car) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MODEL, car.getModel_Car());
        contentValues.put(COLUMN_OPISANIE, car.getOpisanie_Car());

        sqLiteDatabase.update(TABLE_NAME, contentValues,COLUMN_ID + "=" + car.getID_Car(),  null);
    }

    public void deleteCar(Cars car)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=" + car.getID_Car(), null);
    }

    public ArrayList<Cars> getCarsList()
    {
        ArrayList<Cars> carsArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if (result.moveToFirst()){
            while (result.moveToNext()){
                int id = result.getInt(0);
                String modelCar = result.getString(1);
                String opisanie = result.getString(2);

                Cars cars = new Cars(id, modelCar, opisanie);
                carsArrayList.add(cars);
            }
        }
        result.close();
        return carsArrayList;
    }

}
