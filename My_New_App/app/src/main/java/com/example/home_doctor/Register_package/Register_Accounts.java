package com.example.home_doctor.Register_package;

public class Register_Accounts {

    // string variable for
    // storing employee name.
    private String fullName;

    // string variable for storing
    // employee contact number
    private String email;

    // string variable for storing
    // employee address.
    private String phone;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.

    private String country;
    
    private String address;

    private  String occupation;

    private String userType;

    private String password;


    public Register_Accounts() {

    }
    public Register_Accounts(String fullName,String email,String phone,String country,String occupation,String address,String password) {
        this.fullName=fullName;
        this.email=email;
        this.phone=phone;
        this.country=country;
        this.address=address;
        this.occupation=occupation;
        this.occupation=occupation;
        this.password=password;

    }
    // created getter and setter methods
    // for all our variables.
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public  void setEmail(String email){
        this.email=email;
    }
    public  String getEmail(){
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone =phone;
    }

    public  void setCountry(String country){
        this.country=country;
    }
    public  String getCountry(){
        return country;
    }


    public String getLocation() {
        return address;
    }

    public void setLocation(String location) {
        this.address = location;
    }

    public void setUserType(String user) {
        this.userType = user;
    }
    public String getUserType(){
        return userType;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }

    public void setOccupation(String occupation) {
    }
    public  String getOccupation(){
        return occupation;

    }
}