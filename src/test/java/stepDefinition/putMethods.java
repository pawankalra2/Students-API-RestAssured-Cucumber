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

//Step definition class for all PUT operations
public class putMethods {
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^I perform PUT operation to update an existing Student with body$")
    @Given("^I perform PUT operation to for same id which does not exist")
    public void put_Operation_To_Update_Student_with_Id(DataTable dtStudentDetails) {
        List<Map<String, String>> studentDetails = dtStudentDetails.asMaps(String.class, String.class);
        String expectedResponse = studentDetails.get(0).get("expectedResponse");
        studentDetails studentData = new studentDetails(dtStudentDetails);

        //Creating Update student EndPoint
        String updateStudentUrl = util.commonFunctions.make_End_Point_With_BaseUrl("BaseUrl", "UpdateStudentURL");

        //studentDetails Object is converted to JSON string and passed as body to PUT operation.
        Response putCallResponse = util.commonFunctions.perform_Operation_Put(updateStudentUrl, util.commonFunctions.convertStudentDetailsJavaOBJtoJSONstring(studentData));
        ResponseBody bodyOfResponse = putCallResponse.getBody();
        Assert.assertEquals(Integer.toString(putCallResponse.statusCode()), expectedResponse);

        //Custom logging to cucumber report.
        if (expectedResponse.equals("200")) {
            scenario.write("Student " + bodyOfResponse.jsonPath().getString("id") + " is updated with values :- firstName : " +
                    bodyOfResponse.jsonPath().getString("firstName") +
                    " - lastName : " + bodyOfResponse.jsonPath().getString("lastName") +
                    " - nationality : " + bodyOfResponse.jsonPath().getString("nationality") +
                    " - studentClass : " + bodyOfResponse.jsonPath().getString("studentClass"));
        } else if (expectedResponse.equals("500")) {
            scenario.write(bodyOfResponse.asString());
        }
    }

    @Given("^I get (.*) response if I update a student with invalid json body consisting of only (.*)$")
    public void put_Operation_invalid(String expectedResponse, String body) {
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

