package com.sertifiquiz.restServices;

import com.sertifiquiz.domain.Student;

import java.util.HashMap;
import java.util.List;

/**
 * Created by prasad on 5/12/2017.
 */
public interface ClientRestInterface {

    HashMap<Integer, Student> readAndParseJSON(String urlToRead) throws Exception;
    void putResponse(int maxAttnYear, int maxGPAYear, List<Integer> highGPAStudents, int mostInconsitentId) throws Exception;
}
