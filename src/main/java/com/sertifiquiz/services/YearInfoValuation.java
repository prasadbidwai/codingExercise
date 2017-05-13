package com.sertifiquiz.services;

import com.sertifiquiz.domain.Student;

import java.util.*;

/**
 * Created by prasad on 5/10/2017.
 */
public class YearInfoValuation implements YearInfoValuationInterface {

    Map<Integer, Integer> yearAndAttendenceMap = new HashMap<Integer, Integer>();

    /*
    * method to calculate year with highest GPA:
    * */
    public int yearWithHighestGPA(Map<Integer, Student> studentsMap){

        Map<Integer, List> yearAndGPAListMap = getYearAndInfoMap(studentsMap);
        Map<Integer, Double> yearAndAvgGPAMap = getYearAndAvgMap(yearAndGPAListMap);
        Map<Integer, Double> sortedMap = sortMapByValue(yearAndAvgGPAMap);

        System.out.println("year with max overall GPA:: "+sortedMap.keySet().toArray()[0]);
//        System.out.println("year with max overall GPA has GPA:: "+sortedMap.values().toArray()[0]);

        return (int)sortedMap.keySet().toArray()[0];
    }

    /*
    * method to calculate year with Max attendence:
    * */
    public int yearWithMaxAttendence(Map<Integer, Student> studentsMap){

        Map<Integer, List> yearAndGPAListMap = getYearAndInfoMap(studentsMap);

        int maxSize = 0;
        int maxAttendenceYear = 0;

        for (Map.Entry <Integer, List> it: yearAndGPAListMap.entrySet()){
            int currentYear = it.getKey();
            List<Double> currenList = it.getValue();
            int currentListSize = currenList.size();

            if (currentListSize > maxSize){
                maxSize = currentListSize;
                maxAttendenceYear = currentYear;
            }
        }
        System.out.println("Year with the max attendence:: "+maxAttendenceYear);
        return maxAttendenceYear;
    }

    public Map<Integer, Double> getYearAndAvgMap(Map<Integer, List> yearGPAsMap){

        Map<Integer, Double> yearAndAvgGPAMap = new HashMap<Integer, Double>();
        for (Map.Entry<Integer, List> it: yearGPAsMap.entrySet()){
            int currentYear = it.getKey();
            List<Double> currentYearList = it.getValue();
            Double sum = 0.0;
            Double currentYearAvg = 0.0;

            for (Double currentGPA : currentYearList){
                sum += currentGPA;
            }
            currentYearAvg = sum / currentYearList.size();

            yearAndAvgGPAMap.put(currentYear, currentYearAvg);
            yearAndAttendenceMap.put(currentYear, currentYearList.size());
        }

        return yearAndAvgGPAMap;
    }

    public Map getYearAndInfoMap(Map<Integer, Student> studentMap){

        HashMap<Integer, List> YearAndAvgGPAMap = new HashMap<Integer, List>();

        for (Map.Entry<Integer, Student> it: studentMap.entrySet()){
            Student currentStudent = it.getValue();
            int startYear = currentStudent.getStartYear();
            List<Double> gpaList = currentStudent.getGpaRecord();

            for (Double gpaScore: gpaList){
                List<Double> currentYearList = YearAndAvgGPAMap.get(startYear);
                if (currentYearList==null) {
                    currentYearList = new ArrayList<Double>();
                    currentYearList.add(gpaScore);
                    YearAndAvgGPAMap.put(startYear, currentYearList);
                }else
                    currentYearList.add(gpaScore);
                startYear++;
            }
        }
    return YearAndAvgGPAMap;
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
