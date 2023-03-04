package com.example.travel.Models;

public class HotelItem {

    private int imageResourse;
    private String title;
    private String key_id;
    private String favStatus;
    private String hoteldesc;

    //constructor
    public HotelItem() {
    }

    public HotelItem(int imageResourse, String title, String key_id, String favStatus, String hoteldesc) {
        this.imageResourse = imageResourse;
        this.title = title;
        this.key_id = key_id;
        this.favStatus = favStatus;
        this.hoteldesc =hoteldesc;
    }

    public int getImageResourse() {
        return imageResourse;
    }

    public void setImageResourse(int imageResourse) {
        this.imageResourse = imageResourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public String getHoteldesc() {
        return hoteldesc;
    }

    public void setHoteldesc(String hoteldesc) {
        this.hoteldesc = hoteldesc;
    }

}
