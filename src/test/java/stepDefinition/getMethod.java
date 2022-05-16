package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import util.commonFunctions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Step definition class for all GET operations
public class getMethod {
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Then("I perform a GET operation with student (.*) to verify the details are correct")
    public void get_Operation_Student_Id_Class(DataTable dtStudentDetails) {

        List<Map<String, String>> studentDetails = dtStudentDetails.asMaps(String.class, String.class);
        Map<String, String> queryParam = new HashMap<>();
        String expectedResponse = studentDetails.get(0).get("expectedResponse");
        String getBy = studentDetails.get(0).get("getBy");
        queryParam.put(getBy, studentDetails.get(0).get(getBy));
        //Creating Fetch student EndPoint
        String getStudentsUrl = util.commonFunctions.make_End_Point_With_BaseUrl("BaseUrl", "FetchStudentURL");
        Response getCallResponse = util.commonFunctions.perform_Operation_Get_With_Id_Or_Class(queryParam, getStudentsUrl);
        ResponseBody bodyOfResponse = getCallResponse.getBody();
        JsonPath j = new JsonPath(bodyOfResponse.asString());
        //Verifying details returned from GET operation.
        Assert.assertEquals(Integer.toString(getCallResponse.statusCode()), expectedResponse);
        Assert.assertEquals(getCallResponse.statusLine(), "HTTP/1.1 200 ", "Verifying get request returns status Line HTTP/1.1 200 OK");
        Assert.assertEquals(j.getString("firstName[0]"), studentDetails.get(0).get("firstName"));
        Assert.assertEquals(j.getString("lastname[0]"), studentDetails.get(0).get("lastname"));
        Assert.assertEquals(j.getString("nationality[0]"), studentDetails.get(0).get("nationality"));
        Assert.assertEquals(j.getString("studentClass[0]"), studentDetails.get(0).get("studentClass"));

        //Custom logging to cucumber report.
        scenario.write("Student " + j.getString("id[0]") + " is present with values : firstName -: " +
                j.getString("firstName[0]") +
                " - lastName : " + j.getString("lastName[0]") +
                " - nationality : " + j.getString("nationality[0]") +
                " - studentClass : " + j.getString("studentClass[0]"));
    }

    @Given("Student API endpoint is UP and running")
    public void get_Api_status() {
        String getStudentsUrl = util.commonFunctions.make_End_Point_With_BaseUrl("BaseUrl", "FetchStudentURL");
        int getCallResponseStatusCode = commonFunctions.perform_Operation_Get_Endpoint_Status(getStudentsUrl);
        Assert.assertEquals(getCallResponseStatusCode, 200);
        scenario.write("Endpoint is up and running as received response : " + getCallResponseStatusCode);
    }

}
