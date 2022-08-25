package com.example.home_doctor.Storages;

import java.io.Serializable;

public class DoctorDetails implements Serializable {

    public String name;
    public String location;
    public int image;
    public int ratings;
    public String interest = "";
    String working_days;
    String working_hours;
    String amount;
    String availability;
    String speciality;

    public DoctorDetails(String name, String location, int image, int ratings, String interest, String working_days, String working_hours, String amount, String availability, String speciality) {
        this.name = name;
        this.location = location;
        this.image = image;
        this.ratings = ratings;
        this.interest = interest;
        this.working_days = working_days;
        this.working_hours = working_hours;
        this.amount = amount;
        this.availability = availability;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getWorking_days() {
        return working_days;
    }

    public void setWorking_days(String working_days) {
        this.working_days = working_days;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

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


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
