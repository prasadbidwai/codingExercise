package com.sertifiquiz.domain;

import java.util.HashMap;

/**
 * Created by prasad on 5/9/2017.
 * Domain Class to model entire student data in memory using HashMap
 */
public class StudentRepo {

    private int id;
    private HashMap<Integer, Student> studentMap = new HashMap<Integer, Student>();

    public HashMap<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public void saveStudentInMap(Student newStudent){
        int id = newStudent.getId();
        studentMap.put(id, newStudent);
    }
}
