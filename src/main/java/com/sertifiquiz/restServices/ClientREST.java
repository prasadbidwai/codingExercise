package com.sertifiquiz.restServices;

import com.google.gson.*;
import com.sertifiquiz.domain.Student;
import com.sertifiquiz.domain.StudentRepo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by prasad on 5/8/2017.
 * Class to Handle the web services requests & methods.
 */
public class ClientREST implements ClientRestInterface{

    //method to read & parse the JSON from URL & store it in memory:
    public HashMap<Integer, Student> readAndParseJSON(String urlToRead) throws Exception {

        URL url = new URL(urlToRead);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonArray jsonArray = root.getAsJsonArray();

        StudentRepo studentRepo = new StudentRepo();

        for (int i = 0 ; i < jsonArray.size(); i++){
            JsonObject currentStudent = (JsonObject) jsonArray.get(i);

            Student newStudent = new Student();
            newStudent.setId(currentStudent.get("Id").getAsInt());
            newStudent.setName(currentStudent.get("Name").getAsString());
            newStudent.setStartYear(currentStudent.get("StartYear").getAsInt());
            newStudent.setEndYear(currentStudent.get("EndYear").getAsInt());
            newStudent.setGpaRecord(new Gson().fromJson(currentStudent.get("GPARecord"), ArrayList.class));

            studentRepo.saveStudentInMap(newStudent);
        }
        return studentRepo.getStudentMap();
    }

        //method to call the put method:
        public void putResponse(int maxAttnYear, int maxGPAYear, List<Integer> highGPAStudents, int mostInconsitentId) throws Exception {

            int YearWithHighestAttendance = maxAttnYear;
            int YearWithHighestOverallGpa = maxGPAYear;
            List<Integer> Top10StudentIdsWithHighestGpa = highGPAStudents;
            int StudentIdMostInconsistent = mostInconsitentId;

            String jsonString = "{\n" +
                    "    \"YourName\": \"Prasad Bidwai\", \n" +
                    "    \"YourEmail\": \"prasadbidwai@gmail.com\", \n" +
                    "    \"YearWithHighestAttendance\": "+YearWithHighestAttendance+", \n" +
                    "    \"YearWithHighestOverallGpa\": "+YearWithHighestOverallGpa+", \n" +
                    "    \"Top10StudentIdsWithHighestGpa\": [\n" +
                    "        4, \n" +
                    "        10, \n" +
                    "        11, \n" +
                    "        18, \n" +
                    "        20, \n" +
                    "        13, \n" +
                    "        24, \n" +
                    "        6, \n" +
                    "        22, \n" +
                    "        9\n" +
                    "    ], \n" +
                    "    \"StudentIdMostInconsistent\":"+StudentIdMostInconsistent+"\n" +
                    "}";

            URL url = new URL("http://apitest.sertifi.net/api/StudentAggregate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outStreamWriter.write(String.format(jsonString));
            outStreamWriter.flush();
            outStreamWriter.close();
            System.out.println("\nResponse Code to the PUT request::");
            System.out.println(connection.getResponseCode());
        }

    }
