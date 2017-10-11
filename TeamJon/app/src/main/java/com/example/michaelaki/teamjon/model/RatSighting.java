package com.example.michaelaki.teamjon.model;

/**
 * Created by michaelaki on 10/10/17.
 */

public class RatSighting {
    private String key;
    private String date;
    private String locationType;
    private String incidentZip;
    private String incidentAddress;
    private String city;
    private String borough;
    private Double latitude;
    private Double longitude;

    public RatSighting() {

    }

    public String toString() {
        return "Key: " + key + "\nDate: " + date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getBorough() {
        return borough;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public String getIncidentZip() {
        return incidentZip;
    }

    public String getKey() {
        return key;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public void setIncidentZip(String incidentZip) {
        this.incidentZip = incidentZip;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}


