package com.sertifiquiz.services;

import com.sertifiquiz.domain.Student;
import java.util.*;

/**
 * Created by prasad on 5/9/2017.
 */

public class StudentValuation implements StudentValuationInterface{

/*
* method to calculate top 10 scoring students
*/
public List highestOverallGPAStudent(Map<Integer, Student> studentsMap){

    int count = 10;
    int i = 0;
    List<Integer> topTenScoreres = new ArrayList<Integer>();
    Map<Integer, Double> studentGPAMap = getStudentGPAMap(studentsMap);
    Map<Integer, Double> sortedMap = sortMapByValue(studentGPAMap);

    for (Map.Entry<Integer, Double> it: sortedMap.entrySet()) {
        topTenScoreres.add(it.getKey());
        i++;
        if (i>=count)
            break;
    }
    System.out.println("\nTop 10 Students with the highest GPAs::"+topTenScoreres);
    return topTenScoreres;
}

/*
* method to calculate the most inconsitent student
* in terms of difference between min & max GPA
*/
public int mostInconsitentStudent(Map<Integer, Student> studentsMap){

    List<Double> currentStudentScores;
    Double currentMinMax = 0.0;
    int mostInconsitentStudentId = 1;
    Double biggestMinMax = 0.0;

    for (Map.Entry<Integer, Student> it: studentsMap.entrySet()){

        double[] scoresArray;
        int currentStudentId = it.getKey();
        currentStudentScores = studentsMap.get(currentStudentId).getGpaRecord();
        int currentListSize = currentStudentScores.size();
        scoresArray = currentStudentScores.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        Arrays.sort(scoresArray);
        currentMinMax = scoresArray[currentListSize-1] - scoresArray[0];

        if(currentMinMax > biggestMinMax){
            biggestMinMax = currentMinMax;
            mostInconsitentStudentId = currentStudentId;
        }
    }

    System.out.println("ID of Student with the most inconsitent GPAs: "+mostInconsitentStudentId);
    return mostInconsitentStudentId;
}

public Map getStudentGPAMap(Map<Integer, Student> studentsMap){

    HashMap<Integer, Double> studentAvgGPAMap = new HashMap<Integer, Double>();

    for (Map.Entry<Integer, Student> it: studentsMap.entrySet()){
        Student currentStudent = it.getValue();
        List<Double> GPAs = currentStudent.getGpaRecord();
        Double averageGPA = 0.0;
        Double totalGPA = 0.0;

        for (Double value: GPAs){
            totalGPA += value;
        }
        averageGPA = totalGPA/GPAs.size();
        studentAvgGPAMap.put(currentStudent.getId(), averageGPA);
    }
    return studentAvgGPAMap;
}

    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map )
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<K, V> sortedResultantMap = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            sortedResultantMap.put( entry.getKey(), entry.getValue() );
        }
        return sortedResultantMap;
    }
}
