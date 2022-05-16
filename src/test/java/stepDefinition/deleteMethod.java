package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
//Step definition class for all DELETE operations
public class deleteMethod {

    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Then("^I perform a DELETE operation for student ID (.*) and verify (.*) response.$")
    public void delete_Operation_Student_Id(String id, String expectedResponse) {
        Integer i = Integer.parseInt(id);
        JSONObject jsonObj = new JSONObject().put("id", i);
        //Creating Delete student EndPoint
        String deleteStudentUrl = util.commonFunctions.make_End_Point_With_BaseUrl("BaseUrl", "DeleteStudentURL");
        Response deleteCallResponse = util.commonFunctions.perform_Operation_Delete(deleteStudentUrl, jsonObj.toString(), expectedResponse, id);
        Assert.assertEquals(Integer.toString(deleteCallResponse.statusCode()), expectedResponse);
        //Custom logging to cucumber report.
        if (expectedResponse.equals("200")) {
            Assert.assertEquals(deleteCallResponse.statusLine(), "HTTP/1.1 200 ", "Verifying delete request returns status Line HTTP/1.1 200");
            scenario.write("Delete operation was success for id " + id);
        } else if (expectedResponse.equals("500")) {
            Assert.assertEquals(deleteCallResponse.asString(), "Exception occurred while deleting student data: No student data found with student id: " + id);
            scenario.write("Delete operation failed expectedly for " + id + " as id not present ");
        }
    }

    @Given("^I get (.*) response if I delete a student with invalid json body consisting of only (.*)$")
    public void delete_Operation_Student_Invalid(String expectedResponse, String body) {
        //Creating Delete student EndPoint
        String deleteStudentUrl = util.commonFunctions.make_End_Point_With_BaseUrl("BaseUrl", "DeleteStudentURL");
        Response deleteCallResponse = util.commonFunctions.perform_Operation_Delete_Invalid(deleteStudentUrl, body);
        Assert.assertEquals(Integer.toString(deleteCallResponse.statusCode()), expectedResponse);
        Assert.assertTrue(deleteCallResponse.asString().contains("JSON parse error:"));
        //Custom logging to cucumber report.
        scenario.write(deleteCallResponse.asString());
    }
}
