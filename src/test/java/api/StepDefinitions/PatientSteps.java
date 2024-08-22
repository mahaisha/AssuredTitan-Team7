package api.StepDefinitions;


import java.io.IOException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import api.Request.PatientReq;
import api.Request.UpdatePatient;
import api.Request.UserLoginRequest;
import api.Utility.CommonUtils;
import api.Utility.LoggerLoad;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class PatientSteps extends CommonUtils {

	private static String adminToken;
	public String dieticianToken;// =
	// "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmMueHl6QGV4YW1wbGUuY29tIiwiaWF0IjoxNzIzNzgxOTk1LCJleHAiOjE3MjM4MTA3OTV9.KDyWVaepHObLI3ZgZZhSDUgqSahUds4jEN8GHHcRlxyyth3sh_kw8_RtdAGDJDGpA9gU40dPcS8NzUExoYGa7A";
	private static String patientToken;
	//private static String patientPassword = "test";
	//private static String dieticianPassword = "Inspire45";
	private Response response;
	//private static int patientId = 84;
	private static String invalidPatientId = "10000a";

	private RequestSpecification request;
	private static String endPoint;
	//private static String fileId = "66beb450a956ef1c5667388f";
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
	UpdatePatient updatePatient=new UpdatePatient();
	private static final Logger logger = LogManager.getLogger(PatientSteps.class);

UserLoginRequest login = new UserLoginRequest();


	@Before
	public void setup() {
		// Set the base URI for Rest Assured
		RestAssured.baseURI = "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician";
	}

	@Given("Admin logs in with valid credentials and receives a Admin token")
	public void admin_logs_in_with_valid_credentials() {
      login.adminLoginRequest();
 
	}

	@Given("Dietician logs in and receives a Dietician token")
	public void dietician_logs_in_and_receives_a_token() {
		login.dieticianLoginRequest();
		
	}

	
	@Given("Patient logs in receives a Patient token")
	public void dietician_receives_a_patient_token() {
		login.patientLoginRequest();
			}

	@Given("{string} create {string} request")
	public void userCreatesGETRequest(String user, String reqMethod) {
		patientReq.buildRequest(user);

	}

	@When("{string} send {string} http request with {string}")
	public void userSendsGETHTTPRequest(String user, String reqMethod, String endpointType) {

		patientReq.determineEndpoint(endpointType);
		response = patientReq.sendRequest(reqMethod);
	}

	@Then("{string} recieves {int} ok with response body for {string}")
	public void user_receivesOKWithResponseBody(String user, int statusCode, String endpointType)
			throws IOException {
		if(endpointType.equalsIgnoreCase("FileID"))
		{
			response.then().statusCode(statusCode);
			patientReq.savePdfResponse();
			//patientReq.PDFResponseValidator(endpointType);

		}
		else if(endpointType.equalsIgnoreCase("DeleteEndpoint")) {
			int id= CommonUtils.getPatientID();
			String expectedMessage = "Patient with Id " + id + " deleted Successfully!";
			response.then()
		    .statusCode(statusCode)  
		    .body(equalTo(expectedMessage));
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
		patientReq.determineInvalidEndpoint(endpointType);
		response = patientReq.sendRequest(reqMethod);
	}

	@Then("{string} recieves {int} not found")
	public void recievesNotFound(String user, Integer statusCode) {
		response.then().log().all().statusCode(statusCode);
	}

//--------------------IshaSteps------------------//
	@Given("base URI is set to the patient API endpoint")
	public void base_uri_is_set_to_the_patient_api_endpoint() {
		patientReq.setBaseUri();
	
	}
	
@When("Dietician sets NoAuth in Post request to valid endpoints")
public void dietician_sets_no_auth_in_post_request_to_valid_endpoints() throws IOException, ParseException {
	response = patientReq.createNoAuthPatientReq();
	}

@When("Dietician sends Post request with adminToken to valid endpoints")
public void dietician_sends_post_request_with_admin_token_to_valid_endpoints() throws IOException, ParseException {
	response = patientReq.createPatientReqWithAdminToken();
}

@When("Dietician sends Post request with patientToken to valid endpoints")
public void dietician_sends_post_request_with_patient_token_to_valid_endpoints() throws IOException, ParseException {
	response = patientReq.createPatientReqWithPatientToken();
}

@When("Dietician sends POST request with only valid additional details")
public void dietician_sends_post_request_with_only_valid_additional_details() throws IOException, ParseException {
	response = patientReq.createPatientWithOnlyAdditonalDetails();
}

@Then("Dietician recieves {int} Bad Request")
public void recieves_bad_request(Integer int1) {
    response.getStatusCode();
}

@When("Dietician sends PUT request for create patient")
public void dietician_sends_put_request_for_create_patient() throws IOException, ParseException {
	response = patientReq.putCreatePatientReq();
}

@Then("Dietician receives {int} Method Not Allowed")
public void dietician_receives_method_not_allowed(Integer int1) {
   response.getStatusCode();
   response.getStatusLine();
}


@When("Dietician sends POST request with valid data to invalid endpoint")
public void dietician_sends_post_request_with_valid_data_to_invalid_endpoint() throws IOException, ParseException {
	response = patientReq.createPatientReqwithInvalidEndpoint();
}

@Then("Dietician receives {int} Not Found")
public void dietician_receives_not_found(Integer int1) {
	 response.getStatusCode();
	   response.getStatusLine();
}

@When("Dietician sends POST request with valid data to invalid content type")
public void dietician_sends_post_request_with_valid_data_to_invalid_content_type() throws IOException, ParseException {
	response = patientReq.createPatientReqwithInvalidContenType();
}

@Then("Dietician receives {int} Unsupported Media Type")
public void dietician_receives_unsupported_media_type(Integer int1) {
	 response.getStatusCode();
	   response.getStatusLine();
}

	@When("Dietician send the Post request to valid endpoints")
	public void dietician_send_the_post_request_to_valid_endpoints() throws InvalidFormatException, IOException, ParseException {
		response = patientReq.createPatientReq();
		// patient.printLogs(responseDetails);
	}

	@Then("Dietician recievs {int} status code with Patient Details in response body")
	public void dietician_recievs_status_code_with_patient_details_in_response_body(int expectedStatusCode) {
		response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, response.getStatusCode());
	   
	}
	@Then("validate JSON Schema for the patient created")
	public void validate_json_schema_for_the_patient_created() {
		response=patientReq.validateCreatechema();
	}
	
	
	//*************Madhu**************************************************
	
		@Given("Dietician creates PUT request with valid data.")
		public void dietician_creates_put_request_with_valid_data() throws InvalidFormatException, IOException, ParseException {
			response=updatePatient.updatePatientReq();
		   
		}
		@When("Dietician send PUT http request with the endpoint")
		public void dietician_send_put_http_request_with_the_endpoint() throws InvalidFormatException, IOException, ParseException {
		//response=updatePatient.updatePatientReq();
		  System.out.println("Dietician send PUT http request with the endpoint");
		}
		@Then("Dietician recieves {int} ok  with updated response body.")
		public void dietician_recieves_ok_with_updated_response_body(int expectedStatusCode) {
		    response.getStatusCode();
			response.getStatusLine();
			System.out.println( response.getStatusCode());
			Assert.assertEquals(expectedStatusCode, response.getStatusCode());
		}
		@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields")
		public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields() throws InvalidFormatException, IOException, ParseException {
			response=updatePatient.updatePatientReqWithMandatoryData();
		   
		}
		
		@Given("Dietician creates PUT request by entering only valid mandatory details in the form-data key and value fields")
		public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_in_the_form_data_key_and_value_fields() throws InvalidFormatException, IOException, ParseException {
			//System.out.println("Dietician creates PUT request by entering only valid mandatory details in the form-data key and value fields");
			response=updatePatient.updatePatientReqwithInvalidPatientId();
		}
		@Given("Dietician creates PUT request by entering only valid additional details into the form-data key and value fields.")
		public void dietician_creates_put_request_by_entering_only_valid_additional_details_into_the_form_data_key_and_value_fields() throws IOException, ParseException {
		   
			response=updatePatient.updatePatientWithOnlyAdditonalDetails();
		}
		@Then("Dietician recieves {int} Bad request")
		public void dietician_recieves_bad_request(int statusCode) {
		response.getStatusCode();
			   Assert.assertEquals(statusCode, response.getStatusCode());
		}
//		@Given("Dietician creates PUT request by entering only invalid additional details into the form-data key and value fields")
//		public void dietician_creates_put_request_by_entering_only_invalid_additional_details_into_the_form_data_key_and_value_fields() {
		    //}
		
		@When("Dietician send PUT http request with the endpoint with invalid Id")
		public void dietician_send_put_http_request_with_the_endpoint_with_invalid_id() throws IOException, ParseException {
			logger.info("-----------------------send invalidID put request-----------");
		}
		@Then("Dietician recieves {int} Not Found")
		public void dietician_recieves_not_found(int statusCode) {
			response.getStatusCode();
			   response.getStatusLine();
			   Assert.assertEquals(statusCode, response.getStatusCode());
		   
		}
		@Given("Dietician creates PUT request by not attaching any file into the form-data key and value fields")
		public void dietician_creates_put_request_by_not_attaching_any_file_into_the_form_data_key_and_value_fields() throws InvalidFormatException, IOException, ParseException {
			response=updatePatient.updatePatientReq();
		   
		}
		@Given("Dietician creates PUT request by entering valid data into  form-data key and value fields")
		public void dietician_creates_put_request_by_entering_valid_data_into_form_data_key_and_value_fields() throws InvalidFormatException, IOException, ParseException {
		logger.info("-----------upodate with invalid data-----------");
		   
		}
		@When("Dietician send POST http request with endpoint")
		public void dietician_send_post_http_request_with_endpoint() throws IOException, ParseException {
			response=updatePatient.updatePatientReqwithPostReq();
		   
		}
		@Then("Dietician recieves {int} method not allowed")
		public void dietician_recieves_method_not_allowed(int statusCode) {
			response.getStatusCode();
			  response.getStatusLine();
			Assert.assertEquals(statusCode, response.getStatusCode());
		}
		@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields.")
		public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws InvalidFormatException, IOException, ParseException {
			response=updatePatient.updatePatientwithInvalidEndpt();
		   
		}
		@When("Dietician sent PUT http request with invalid endpoint")
		public void dietician_sent_put_http_request_with_invalid_endpoint() throws IOException, ParseException {
			//updatePatient.updatePatientReqwithInvalidEndpoint();
		   
		}
		@Then("Dietician recieves {int} unsupported media type")
		public void dietician_recieves_unsupported_media_type(int statusCode) {
			response.getStatusCode();
			   response.getStatusLine();
			   Assert.assertEquals(statusCode, response.getStatusCode());
		}
	
	
}
