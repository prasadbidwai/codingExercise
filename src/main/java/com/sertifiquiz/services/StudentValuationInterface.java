package com.sertifiquiz.services;

import com.sertifiquiz.domain.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by prasad on 5/10/2017.
 */
public interface StudentValuationInterface {

    //method to get list of top 10 students with highest GPA:
    List highestOverallGPAStudent(Map<Integer, Student> studentsMap);

    //method to find the most inconsitenet student in terms of min & max GPA
    int mostInconsitentStudent(Map<Integer, Student> studentsMap);
}
