package com.example.michaelaki.teamjon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * File made by michaelaki on 10/10/17.
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
    private long compareDate;

    public RatSighting() {

    }

    /**
     * Getter for compare date
     * @return Rat sighting's compare date
     */
    public long getCompareDate() {
        return compareDate;
    }

    /**
     * Setter for compare date used when displaying RatSighting on map and graph
     * @param compareDate new compareDate for User
     */
    public void setCompareDate(long compareDate) {
        this.compareDate = compareDate;
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
    /**
     * Setter for borough
     * @param borough new name for User
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }
    /**
     * Setter for city
     * @param city new name for User
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Setter for date
     * @param date new name for User
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * Setter for incident address
     * @param incidentAddress new name for User
     */
    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }
    /**
     * Setter for incident zip code
     * @param incidentZip new name for User
     */
    public void setIncidentZip(String incidentZip) {
        this.incidentZip = incidentZip;
    }
    /**
     * Setter for key
     * @param key new name for User
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * Setter for latitude
     * @param latitude new name for User
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    /**
     * Setter for location type
     * @param locationType new name for User
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
    /**
     * Setter for longitude
     * @param longitude new name for User
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}


