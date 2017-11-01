package com.example.michaelaki.teamjon.model;

import java.io.Serializable;

/**
 * Created by michaelaki on 10/31/17.
 */

public class Filter implements Serializable {
    private long startDate;
    private long endDate;

    public Filter() {
        startDate = 0;
        endDate = 0;
    }

    public long getEndDate() {
        return endDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
}
