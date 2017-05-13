import com.sertifiquiz.domain.Student;
import com.sertifiquiz.restServices.ClientREST;
import com.sertifiquiz.restServices.ClientRestInterface;
import com.sertifiquiz.services.StudentValuation;
import com.sertifiquiz.services.StudentValuationInterface;
import com.sertifiquiz.services.YearInfoValuation;
import com.sertifiquiz.services.YearInfoValuationInterface;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by prasad on 5/12/2017.
 */
public class QuizDemoTest {

    @Test
    public void checkCorrectMaxAttendenceYear(){

        String dataEndpoint = "http://apitest.sertifi.net/api/Students";
        ClientRestInterface cl = new ClientREST();
        Map<Integer, Student> studentMap = null;
        try {
            studentMap = cl.readAndParseJSON(dataEndpoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        YearInfoValuationInterface yv = new YearInfoValuation();
        int yearWthMaxAttn = yv.yearWithMaxAttendence(studentMap);

        //assertion Condition::
        assertEquals(yearWthMaxAttn, 2011);
}

    @Test
    public void checkMaxGPAYear(){

        String dataEndpoint = "http://apitest.sertifi.net/api/Students";
        ClientRestInterface cl = new ClientREST();
        HashMap<Integer, Student> studentMap = null;
        try {
            studentMap = cl.readAndParseJSON(dataEndpoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        YearInfoValuationInterface yv = new YearInfoValuation();
        int yearWthMaxGPA = yv.yearWithHighestGPA(studentMap);

        //assertion Condition::
        assertEquals(yearWthMaxGPA, 2016);
    }

    @Test
    public void checkTopTenList(){
        Integer [] topTenIdArray = {4, 10, 11, 18, 20, 13, 24, 6, 22, 9};
        String dataEndpoint = "http://apitest.sertifi.net/api/Students";
        ClientRestInterface cl = new ClientREST();
        HashMap<Integer, Student> studentMap = null;

        try {
            studentMap = cl.readAndParseJSON(dataEndpoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentValuationInterface sv = new StudentValuation();
        List<Integer> topTenGpaStudents = sv.highestOverallGPAStudent(studentMap);

        //assertion Condition:
        assertTrue(topTenGpaStudents.containsAll(Arrays.asList(topTenIdArray)));
    }

    @Test
    public void checkMostInconsitent(){

        String dataEndpoint = "http://apitest.sertifi.net/api/Students";
        ClientRestInterface cl = new ClientREST();
        HashMap<Integer, Student> studentMap = null;

        try {
            studentMap = cl.readAndParseJSON(dataEndpoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StudentValuationInterface sv = new StudentValuation();

        //assertion Condition:
        assertEquals(sv.mostInconsitentStudent(studentMap), 15);
    }
}
