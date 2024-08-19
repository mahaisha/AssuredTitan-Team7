package api.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import api.Request.PatientReq;
import api.Utility.CommonUtils;
import org.apache.logging.log4j.Logger;

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

	PatientReq patientReq = new PatientReq();
	private static final Logger logger = LogManager.getLogger(PatientSteps.class);

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

}
