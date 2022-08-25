package com.example.home_doctor.Storages;

public class AppointmentDetails {
    String name;
    String time;
    int img;


    public AppointmentDetails(String name, String time, int img) {
        this.name = name;
        this.time = time;
        this.img =img;
    }



    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
