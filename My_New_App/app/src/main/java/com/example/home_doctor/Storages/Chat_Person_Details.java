package com.example.home_doctor.Storages;

public class Chat_Person_Details {
    String name;
    int img;
    int badgeNum;

    public Chat_Person_Details(String name, int img, int badgeNum) {
        this.name = name;
        this.img = img;
        this.badgeNum = badgeNum;

    }

    public int getBadgeNum() {
        return badgeNum;
    }

    public void setBadgeNum(int badgeNum) {
        this.badgeNum = badgeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
