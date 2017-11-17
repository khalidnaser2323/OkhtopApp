package com.example.toto.okhtobapp;



public class RowItems {
    private String name,city;
    private String userPic;
    public RowItems(String name, String city, String userPic) {
        this.name = name;
        this.city = city;
        this.userPic = userPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}