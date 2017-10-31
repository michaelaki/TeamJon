package com.example.michaelaki.teamjon.model;

import java.io.Serializable;

/**
 * Created by michaelaki on 10/31/17.
 */

public class Filter implements Serializable {
    private int startDate;
    private int endDate;

    public Filter() {
        startDate = 0;
        endDate = 0;
    }

    public int getEndDate() {
        return endDate;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
}
