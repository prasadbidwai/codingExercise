package com.sertifiquiz.services;

import com.sertifiquiz.domain.Student;

import java.util.Map;

/**
 * Created by prasad on 5/10/2017.
 */
public interface YearInfoValuationInterface {

    //method to find the year with the max GPA scored in:
    int yearWithHighestGPA(Map<Integer, Student> studentsMap);

    //method to find the year with max attendence:
    int yearWithMaxAttendence(Map<Integer, Student> studentsMap);
}
