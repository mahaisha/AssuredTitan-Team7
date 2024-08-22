package api.StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import api.Payload.DieticianPayload;
import api.Payload.DieticianPayload.TestCase;
import api.Pojo.DieticianPojo;
import api.Request.DieticianRequest;
import api.Request.UserLoginRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DieticianCreateSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianCreateSteps.class);
	
	private static final String DIETICIAN_ENDPOINT = "/dietician";
	private static final String INVALID_ENDPOINT = "/invalid";

	private DieticianPayload excelReader;
	private DieticianRequest dieticianRequest;
	
	private UserLoginRequest userLoginRequest = new UserLoginRequest();
	private DieticianPojo dietician;
	private Response response;
	static String adminAuthToken;
	static String dieticianAuthToken;
	static String patientAuthToken;
	
	public DieticianCreateSteps() {
		excelReader = new DieticianPayload();
		dieticianRequest = new DieticianRequest();
	}
	
	@Given("Create Dietician has Admin Auth token")
	public void app_has_admin_auth_token() {
	    adminAuthToken = userLoginRequest.adminLoginRequest().jsonPath().getString("token");
	}

	@Given("Excel file has login Dietician details")
	public void excel_file_has_login_dietician_details() {
	    this.dietician = excelReader.readRow(TestCase.LOGIN);
	}

	@Given("Excel file has full Dietician details")
	public void excel_file_has_full_dietician_details() {
	    this.dietician = excelReader.readRow(TestCase.FULL);
	}

	@When("Create Dietician with Admin Auth token")
	public void create_dietician_with_admin_auth_token() {
	    this.response = dieticianRequest.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, adminAuthToken, this.dietician);

	    // Store key details for future use
	    CommonUtils.setDieticianEmail(response.jsonPath().getString("Email"));
	    CommonUtils.setDieticianPassword(response.jsonPath().getString("loginPassword"));
	    CommonUtils.setDieticianId(response.jsonPath().getString("id"));
	}

	@Then("Create Dietician succeeds with http status CREATED")
	public void dietician_creation_succeeds_with_http_201() {
	    int responseStatusCode = this.response.getStatusCode();
	    Assert.assertEquals(responseStatusCode, 201); // Check for "CREATED" status code
	    LOGGER.info("Create Dietician succeeded with status code: " + responseStatusCode);
	    
	    // Optionally validate other aspects of the response or trigger further processing
	    // dieticianRequest.validateCreation(dietician, response);
	}
	
	
	@Given("Create Dietician has Dietician Auth token")
	public void app_has_dieticianAuthToken() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dieticianAuthToken = userLoginRequest.dieticianLoginRequest().jsonPath().getString("token");
	}

	@Given("Create Dietician has Patient Auth token")
	public void app_has_patient_auth_token() {
		// patientAuthToken = userLoginRequest.dieticianLoginRequest().jsonPath().getString("token");
	}
	

	@Given("Excel file has mandatory Dietician details")
	public void excel_file_has_mandatory_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.MANDATORY);
	}

	@Given("Excel file has additional Dietician details")
	public void excel_file_has_additional_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.ADDITIONAL);
	}

	@Given("Excel file has invalid Dietician details")
	public void excel_file_has_invalid_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.INVALID);
	}
	
	

	@When("Create Dietician without Auth token")
	public void create_dietician_without_auth_token() {
		this.response = dieticianRequest.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, null, this.dietician);
		
	}
	
	@When("Create Dietician with Dietician Auth token")
	public void create_dietician_with_dieticianAuthToken() {
		this.response = dieticianRequest.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, dieticianAuthToken, this.dietician);
	}
	
	@When("Create Dietician with Patient Auth token")
	public void create_dietician_with_patient_auth_token() {
		this.response = dieticianRequest.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, dieticianAuthToken, this.dietician);
	}
	
	
	@When("Create Dietician with Admin Auth token and invalid HTTP method")
	public void create_dietician_with_admin_auth_token_and_invalid_http_method() {
		this.response = dieticianRequest.createDietician(DIETICIAN_ENDPOINT, Method.PATCH, ContentType.JSON, adminAuthToken, this.dietician);
	}
	
	@When("Create Dietician with Admin Auth token and invalid endpoint")
	public void create_dietician_with_admin_auth_token_and_invalid_endpoint() {
		this.response = dieticianRequest.createDietician(INVALID_ENDPOINT, Method.POST, ContentType.JSON, adminAuthToken, this.dietician);
	}
	
	@When("Create Dietician with Admin Auth token and invalid content type")
	public void create_dietician_with_admin_auth_token_and_invalid_content_type() {
		this.response = dieticianRequest.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.TEXT, adminAuthToken, this.dietician);
	}
	
	@Then("Create Dietician fails with http status BAD_REQUEST")
	public void dietician_creation_fails_with_http_400() {
		this.response.then().statusCode(400);
	}
	
	@Then("Create Dietician fails with http status UNAUTHORIZED")
	public void dietician_creation_fails_with_http_401() {
		this.response.then().statusCode(401);
	}
	
	@Then("Create Dietician fails with http status FORBIDDEN")
	public void dietician_creation_fails_with_http_403() {
		this.response.then().statusCode(403);
	}
	
	@Then("Create Dietician fails with http status NOT_FOUND")
	public void dietician_creation_fails_with_http_404() {
		this.response.then().statusCode(404);
	}
	
	@Then("Create Dietician fails with http status METHOD_NOT_ALLOWED")
	public void dietician_creation_fails_with_http_405() {
		this.response.then().statusCode(405);
	}
	
	@Then("Create Dietician fails with http status UNSUPPORTED_MEDIA_TYPE")
	public void dietician_creation_fails_with_http_415() {
		this.response.then().statusCode(415);
	}

}
