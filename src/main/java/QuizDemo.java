import com.sertifiquiz.domain.Student;
import com.sertifiquiz.restServices.ClientREST;
import com.sertifiquiz.restServices.ClientRestInterface;
import com.sertifiquiz.services.StudentValuation;
import com.sertifiquiz.services.StudentValuationInterface;
import com.sertifiquiz.services.YearInfoValuationInterface;
import com.sertifiquiz.services.YearInfoValuation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by prasad on 5/8/2017.
 */
public class QuizDemo {

    public static void main(String[] args) throws Exception {

        //accepting the data Endpoint URL through commandline argument
        String dataEndpoint = args[0];

        //retrieve the JSON data from URL and store it in memory for operations
        ClientRestInterface cl = new ClientREST();
        HashMap<Integer, Student> studentMap = cl.readAndParseJSON(dataEndpoint);

        //calling API Methods to find max attendence year & max GPA year
        YearInfoValuationInterface yv = new YearInfoValuation();
        int yearWthMaxAttn = yv.yearWithMaxAttendence(studentMap);
        int yearWthMaxGPA = yv.yearWithHighestGPA(studentMap);

        //calling API methods to find top 10 GPA students & most inconsitent student
        StudentValuationInterface sv = new StudentValuation();
        List<Integer> topTenGpaStudents = sv.highestOverallGPAStudent(studentMap);
        int mostInconsitentStudent = sv.mostInconsitentStudent(studentMap);

        //calling API to PUT(submit) the result on provided URL
        try {
            cl.putResponse(yearWthMaxAttn, yearWthMaxGPA, topTenGpaStudents, mostInconsitentStudent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
