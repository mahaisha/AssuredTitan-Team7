package api.StepDefinitions;


import java.io.IOException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import api.Request.PatientReq;
import api.Request.UserLoginRequest;
import api.Utility.CommonUtils;
import api.Utility.LoggerLoad;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PatientSteps extends CommonUtils {

	private static String adminToken;
	public String dieticianToken;// =
	// "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmMueHl6QGV4YW1wbGUuY29tIiwiaWF0IjoxNzIzNzgxOTk1LCJleHAiOjE3MjM4MTA3OTV9.KDyWVaepHObLI3ZgZZhSDUgqSahUds4jEN8GHHcRlxyyth3sh_kw8_RtdAGDJDGpA9gU40dPcS8NzUExoYGa7A";
	private static String patientToken;
	private static String patientPassword = "test";
	private static String dieticianPassword = "Inspire45";
	private Response response;
	private static int patientId = 84;
	private static int invalidPatientId = 100000;

	private RequestSpecification request;
	private static String endPoint;
	private static String fileId = "66beb450a956ef1c5667388f";
	private static String invalidFileId = "1";

	
	 private String tokenType;
	    private String endpoint;
	    private String contentType;
	    private int statusCode;
	    private String responseBody;
	    private int expectedStatusCode;
	    private String expectedMessage;
	    public static String token;
	    
	PatientReq patientReq = new PatientReq();
	private static final Logger logger = LogManager.getLogger(PatientSteps.class);

UserLoginRequest login = new UserLoginRequest();


	@Before
	public void setup() {
		// Set the base URI for Rest Assured
		RestAssured.baseURI = "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician";
	}

	@Given("Admin logs in with valid credentials and receives a Admin token")
	public void admin_logs_in_with_valid_credentials() {

		response = RestAssured.given().contentType("application/json")
				.body("{ \"userLoginEmail\": \"Team7.admin@gmail.com\", \"password\": \"test\" }").log().all() // Log
				.post("/login").then().log().status() // Log response status code
				.log().body() // Log response body
				.extract().response();
		adminToken = response.jsonPath().getString("token");
		setAdminToken(response.jsonPath().getString("token"));
		System.out.println("adminToken :" + adminToken);
	}

	@Given("Dietician logs in and receives a Dietician token")
	public void dietician_logs_in_and_receives_a_token() {
		response = RestAssured.given().contentType("application/json")
				.body("{ \"userLoginEmail\": \"abc.xyz@example.com\", \"password\": \"" + dieticianPassword + "\" }")
				.log().all() // Log request details
				.post("/login").then().log().status() // Log response status code
				.log().body() // Log response body
				.extract().response();
		dieticianToken = response.jsonPath().getString("token");
		setDieticianToken(response.jsonPath().getString("token"));
		System.out.println("dieticianToken :" + dieticianToken);
	}

	/*
	 * @Given("Dietician creates a Patient") public void
	 * dietician_creates_a_patient() { Response createPatientResponse =
	 * RestAssured.given() .header("Authorization", "Bearer " + dieticianToken)
	 * .contentType("application/json")
	 * .body("{ \"FirstName\": \"Jane\", \"LastName\": \"Doe\", \"ContactNumber\": \"0987654321\", \"Email\": \"janedoe@example.com\", \"Allergy\": \"None\", \"FoodPreference\": \"Vegetarian\", \"CuisineCategory\": \"Indian\", \"DateOfBirth\": \"1990-01-01\" }"
	 * ) .post(
	 * "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician/patient"
	 * ); patientId = createPatientResponse.jsonPath().getInt("patientId");
	 * 
	 * }
	 */

	@Given("Patient logs in receives a Patient token")
	public void dietician_receives_a_patient_token() {
		response = RestAssured.given().contentType("application/json")
				.body("{ \"userLoginEmail\": \"janedoe@example.com\", \"password\": \"test\" }") 
				.log().all() // Log request details
				.post("/login").then().log().status() // Log response status code
				.log().body() // Log response body
				.extract().response();
		patientToken = response.jsonPath().getString("token");
		setPatientToken(response.jsonPath().getString("token"));
		System.out.println("patientToken :" + patientToken);
		logger.info("Patient token received: " + patientToken);
	}

	@Given("{string} create {string} request")
	public void userCreatesGETRequest(String user, String reqMethod) {
		patientReq.buildRequest(user);

	}

	@When("{string} send {string} http request with {string}")
	public void userSendsGETHTTPRequest(String user, String reqMethod, String endpointType) {

		patientReq.determineEndpoint(endpointType, patientId, fileId);
		response = patientReq.sendRequest(reqMethod);
	}

	@Then("{string} recieves {int} ok with response body for {string}")
	public void user_receivesOKWithResponseBody(String user, int statusCode, String endpointType)
			throws IOException {
		if(endpointType.equalsIgnoreCase("FileID"))
		{
			response.then().statusCode(statusCode);
			patientReq.savePdfResponse();
		}
		else
		{
			response.then().log().all().statusCode(statusCode);
			patientReq.validateResponseSchema(endpointType);
		}
	}

	@Given("{string} create {string} request with No Auth")
	public void createRequestWithNoAuth(String user, String reqMethod) {

		patientReq.buildRequest("NoAuth");
	}

	@Then("{string} recieves {int} unauthorized")
	public void recievesUnauthorized(String string, Integer statusCode) {
		response.then().log().all().statusCode(statusCode);
	}

	@Then("{string} recieves {int} Forbidden")
	public void recievesForbidden(String string, Integer statusCode) {
		response.then().log().all().statusCode(statusCode);
	}

	@Then("{string} recieves {int} method not allowed")
	public void recievesMethodNotAllowed(String string, Integer statusCode) {
		response.then().log().all().statusCode(statusCode);
	}

	@When("{string} send {string} http request with invalid {string}")
	public void sendHttpRequestWithInvalidEndpt(String user, String reqMethod, String endpointType) {

		patientReq.invalidEndpoint();
		response = patientReq.sendRequest(reqMethod);
	}

	@When("{string} send {string} http request with invalid ID {string}")
	public void sendHttpRequestWithInvalidID(String user, String reqMethod, String endpointType) {
		patientReq.determineEndpoint(endpointType, invalidPatientId, invalidFileId);
		response = patientReq.sendRequest(reqMethod);
	}

	@Then("{string} recieves {int} not found")
	public void recievesNotFound(String user, Integer statusCode) {
		response.then().log().all().statusCode(statusCode);
	}

	    
	
//	 String tokenType;
//	    String endpoint;
//	    String contentType;
//	    int statusCode;
//	    String responseBody;
//	    int expectedStatusCode;
//	    String expectedMessage;
//     	UserLoginRequest login;
//	    PatientReq patient;
	    
	    
	
	
	@Given("base URI is set to the patient API endpoint")
	public void base_uri_is_set_to_the_patient_api_endpoint() {
		patientReq.setBaseUri();
	
	}
	
	@Given("Dietician have a {string} token")
	public void dietician_have_a_token(String tokenType) throws IOException {
	    switch (tokenType) {
	        case "noToken":
	            token = null;
	            break;
	        case "adminToken":
	            token = login.getAdminToken(); // Implement method to fetch admin token
	            break;
	        case "dieticianToken":
	            token = login.getDieticianToken(); // Implement method to fetch dietician token
	            break;
	        default:
	            token = null;
	            break;
	    }
//	    if (token != null) {
//            RestAssured.requestSpecification = RestAssured.given()
//                .header("Authorization", "Bearer " + token);
       // }
	    LoggerLoad.info("Token Type: " + tokenType);
	    LoggerLoad.info("Token: " + (token != null ? token : "No Token"));
	}
	
	@When("Dietician send a request to create a patient with {string} endpoint and {string}")
	public void dietician_send_a_request_to_create_a_patient_with_endpoint_and(String endpointType, String contentType) throws InvalidFormatException, IOException, ParseException {
	   patientReq.createPatientReq(endpointType, contentType);
	}
	
	@Then("the response status code should be {string}")
	public void the_response_status_code_should_be(String expectedStatusCode) {
	    int actualStatusCode = patientReq.response.getStatusCode();
	    Assert.assertEquals(Integer.parseInt(expectedStatusCode), actualStatusCode);
	    LoggerLoad.info("Expected Status Code: " + expectedStatusCode + ", Actual Status Code: " + actualStatusCode);
	}

	@Then("the response body should contain {string}")
	public void the_response_body_should_contain(String expectedMessage) {
	    String responseBody = patientReq.response.getBody().asString();
	    Assert.assertTrue(responseBody.contains(expectedMessage));
	    LoggerLoad.info("Expected Message: " + expectedMessage + ", Actual Response Body: " + responseBody);
	}

//
//	@Given("Dietician is loggedin with valid credentials")
//	public void dietician_is_loggedin_with_valid_credentials() throws IOException {
//	   login.getToken();
//	   login.getdieticianToken();
//	}
//
//	@Then("dietician recieves valid dietician token")
//	public void dietician_recieves_valid_dietician_token() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("Dietician creates Post request to create new patient")
//	public void dietician_creates_post_request_to_create_new_patient() throws InvalidFormatException, IOException, ParseException {
//	  patient.createPatientReq();
//	  logger.info("--------------Patient is created------------");
//	}
//
//	@When("Dietician send the Post request to valid endpoints")
//	public void dietician_send_the_post_request_to_valid_endpoints() throws InvalidFormatException, IOException, ParseException {
//		 patient.createPatientReq();
//		// patient.printLogs(responseDetails);
//	}
//
//	@Then("Dietician recievs {int} status code with Patient Details in response body#And validate JSON Schema for the patient created with {string}")
//	public void dietician_recievs_status_code_with_patient_details_in_response_body_and_validate_json_schema_for_the_patient_created_with(Integer int1, String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//	
//	
	
}
