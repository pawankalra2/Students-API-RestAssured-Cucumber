package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.studentDetails;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jodd.props.Props;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

/*Common utility class*/

public class commonFunctions {
    public static Response operationResponse;
    public static RequestSpecification Request;

    //Method to convert studentDetails Java Object to JSON.
    public static String convertStudentDetailsJavaOBJtoJSONstring(studentDetails studentDetailsObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String strJson = mapper.writeValueAsString(studentDetailsObject);
            strJson = strJson.replaceAll("firstName", "firstName");
            strJson = strJson.replaceAll("id", "id");
            strJson = strJson.replaceAll("lastName", "lastName");
            strJson = strJson.replaceAll("nationality", "nationality");
            strJson = strJson.replaceAll("studentClass", "studentClass");
            //System.out.println("ResultingJSONstring = " + json);
            return strJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*Method performing GET operations*/

    public static int perform_Operation_Get_Endpoint_Status(String strUrl) {
        RestAssured.baseURI = strUrl;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        return response.statusCode();

    }

    public static Response perform_Operation_Get_With_Id_Or_Class(Map<String, String> queryParams, String strUrl) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(strUrl);
        builder.setContentType(ContentType.JSON);
        RequestSpecification requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
        Request.queryParams(queryParams);
        operationResponse = Request.get();
        return operationResponse;
    }


    //Method to perform delete operation.
    public static Response perform_Operation_Delete(String url, String strBody, String expectedResponse, String id) {
        operationResponse = given().contentType(ContentType.JSON).with().body(strBody).when().delete(url);
        return operationResponse;
    }

    public static Response perform_Operation_Delete_Invalid(String url, String strBody) {
        operationResponse = given().contentType(ContentType.JSON).with().body(strBody).when().delete(url);

        return operationResponse;
    }

    /*Method performing POST operation*/
    public static Response perform_Operation_Post(String url, String strBody) {
        operationResponse = given().contentType(ContentType.JSON).with().body(strBody).when().post(url);
        return operationResponse;
    }

    public static Response perform_Operation_Post_Invalid(String url, String strBody) {
        operationResponse = given().contentType(ContentType.JSON).with().body(strBody).when().post(url);
        operationResponse.statusLine();
        return operationResponse;

    }

    public static Response perform_Operation_Put(String url, String strBody) {
        operationResponse = given().contentType(ContentType.JSON).with().body(strBody).when().put(url);
        return operationResponse;
    }


    //Method to fetch data form config.properties
    public static String make_End_Point_With_BaseUrl(String BaseUrl, String Endpoint) {
        Props p = new Props();
        File configFile = new File("src/test/java/resources/config.properties");
        try {
            p.load(new File(configFile.getAbsolutePath()));
        } catch (Exception e) {
            //log.error("Error while accessing config.properties");
            e.printStackTrace();
        }

        String BaseEndpoint = p.getValue(BaseUrl);
        String UrlEndpoint = p.getValue(Endpoint);
        return (BaseEndpoint + UrlEndpoint);
    }
}
