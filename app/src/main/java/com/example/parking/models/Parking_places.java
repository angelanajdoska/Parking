package com.example.parking.models;

public class Parking_places {
    private int id;
    private String parking_name;
    private String city_name;
    private int total;
    private String latitude;
    private String longitude;
    public Parking_places(int id, String parking_name, String city_name,int total, String latitude, String longitude){
        this.id=id;
        this.parking_name=parking_name;
        this.city_name=city_name;
        this.total=total;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getParkingName() {
        return parking_name;
    }
    public void setParkingName(String parking_name) {
        this.parking_name = parking_name;
    }
    public String getCityName() {
        return city_name;
    }
    public void setCityName(String city_name) {
        this.city_name = city_name;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}

