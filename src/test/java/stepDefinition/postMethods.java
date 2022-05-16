package stepDefinition;

import data.studentDetails;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

//Step definition class for all POST operations
public class postMethods {
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    /*Student details from datable is stored to studentDetails POJO class, and then converted to JSON string.*/

    @Given("^I perform POST operation to add a new Student with body$")
    public void post_Operation_Add_New_Student(DataTable dtStudentDetails) {
        List<Map<String, String>> studentDetails = dtStudentDetails.asMaps(String.class, String.class);
        studentDetails studentData = new studentDetails(dtStudentDetails);
        String expectedResponse = studentDetails.get(0).get("expectedResponse");
        //Creating Add student EndPoint
        String addStudentUrl = util.commonFunctions.make_End_Point_With_BaseUrl("BaseUrl", "AddStudentUrl");
        //studentDetails Object is converted to JSON string and passed as body to POST operation.
        Response postCallResponse = util.commonFunctions.perform_Operation_Post(addStudentUrl, util.commonFunctions.convertStudentDetailsJavaOBJtoJSONstring(studentData));
        ResponseBody bodyOfResponse = postCallResponse.getBody();
        Assert.assertEquals(Integer.toString(postCallResponse.statusCode()), expectedResponse);
        Assert.assertEquals(postCallResponse.statusLine(), "HTTP/1.1 200 ", "Verifying post request returns status Line HTTP/1.1 200 OK");
        Assert.assertTrue(bodyOfResponse.asString().contains("New student enrolled with student id : "));
        //Custom logging to cucumber report.
        scenario.write(bodyOfResponse.asString());
    }

    @Given("^I get (.*) response if I add a student with invalid json body consisting of only (.*)$")
    public void post_Operation_invalid(String expectedResponse, String body) {
        //Creating Add student EndPoint
        String addStudentUrl = util.commonFunctions.make_End_Point_With_BaseUrl("BaseUrl", "AddStudentUrl");
        //studentDetails Object is converted to JSON string and passed as body to POST operation.
        Response postCallResponse = util.commonFunctions.perform_Operation_Post_Invalid(addStudentUrl, body);
        ResponseBody bodyOfResponse = postCallResponse.getBody();
        Assert.assertEquals(Integer.toString(postCallResponse.statusCode()), expectedResponse);
        Assert.assertTrue(bodyOfResponse.asString().contains("JSON parse error:"));
        //Custom logging to cucumber report.
        scenario.write(bodyOfResponse.asString());
    }

}
