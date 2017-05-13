package com.sertifiquiz.domain;

import java.util.List;

/**
 * Created by prasad on 5/9/2017.
 * Domain Class to model the Student details
 */
public class Student {

    private int id;
    private String name;
    private int startYear;
    private int endYear;
    private List gpaRecord;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public List getGpaRecord() {
        return gpaRecord;
    }

    public void setGpaRecord(List gpaRecord) {
        this.gpaRecord = gpaRecord;
    }

}
