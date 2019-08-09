package com.example.allergy;

public class StoreInfo {
    String name;
    String addres_rd;
    double latitude, longitude;

    public StoreInfo(String name, String addres_rd, double latitude, double longitude) {
        this.name = name;
        this.addres_rd = addres_rd;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getAddres_rd() {
        return addres_rd;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddres_rd(String addres_rd) {
        this.addres_rd = addres_rd;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
