package restapi.stepdefinitions;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;

public class CommonStepData {

    public enum RequestType {
        PUT, GET, POST, DELETE
    }
    public static WebDriver baseInstanceDriver;

    Response commonResponse;
    ValidatableResponse commonJson;
    RequestSpecification commonRequest;
    String baseURI = "";
    String port = "";
    String basePath = "";
    RequestType requestType;
    String requestStatus;


}
