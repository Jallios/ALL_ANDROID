package com.example.api;

public class Car {

    private int id_Car;
    private String car_Name;
    private String car_Detail;
    private String car_Img;
    private String student_FIO;
    private  int student_Score;

    public Car(int id_Car, String car_Name, String car_Detail, String car_Img, String student_FIO, int student_Score) {
        this.id_Car = id_Car;
        this.car_Name = car_Name;
        this.car_Detail = car_Detail;
        this.car_Img = car_Img;
        this.student_FIO = student_FIO;
        this.student_Score = student_Score;
    }

    public int getId_Car() {
        return id_Car;
    }

    public void setId_Car(int id_Car) {
        this.id_Car = id_Car;
    }

    public String getCar_Name() {
        return car_Name;
    }

    public void setCar_Name(String car_Name) {
        this.car_Name = car_Name;
    }

    public String getCar_Detail() {
        return car_Detail;
    }

    public void setCar_Detail(String car_Detail) {
        this.car_Detail = car_Detail;
    }

    public String getCar_Img() {
        return car_Img;
    }

    public void setCar_Img(String car_Img) {
        this.car_Img = car_Img;
    }

    public String getStudent_FIO() {
        return student_FIO;
    }

    public void setStudent_FIO(String student_FIO) {
        this.student_FIO = student_FIO;
    }

    public int getStudent_Score() {
        return student_Score;
    }

    public void setStudent_Score(int student_Score) {
        this.student_Score = student_Score;
    }
}
