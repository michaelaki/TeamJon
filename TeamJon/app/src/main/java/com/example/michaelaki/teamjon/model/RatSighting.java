package com.example.michaelaki.teamjon.model;

import java.io.Serializable;

/**
 * Created by michaelaki on 10/10/17.
 */

public class RatSighting implements Serializable{
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

    /**
     * Returns a string with the unique key and the date of each rat sighting
     * @return string
     */
    public String toString() {
        return "Key: " + key + "\nDate: " + date;
    }

    /**
     * Getter for latitude
     * @return Rat sighting's latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Getter for longitude
     * @return Rat sighting's longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Getter for borough
     * @return Rat sighting's borough
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Getter for city
     * @return Rat sighting's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for date
     * @return Rat sighting's date
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for address
     * @return Rat sighting's address
     */
    public String getIncidentAddress() {
        return incidentAddress;
    }

    /**
     * Getter for zip code
     * @return Rat sighting's zip code
     */
    public String getIncidentZip() {
        return incidentZip;
    }

    /**
     * Getter for unique key
     * @return Rat sighting's key
     */
    public String getKey() {
        return key;
    }

    /**
     * Getter for location type
     * @return Rat sighting's location type
     */
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


