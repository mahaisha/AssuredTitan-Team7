package api.StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import api.Payload.DieticianPayload;
import api.Payload.DieticianPayload.TestCase;
import api.Pojo.DieticianPojo;
import api.Request.DieticianRequest;
import api.Request.UserLoginRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DieticianPutByIdSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianPutByIdSteps.class);
	
	//private static final String dieticianID = "6";
	private static final String DIETICIAN_INVALID_ID = "abcd";
	
	private static final String DIETICIAN_ENDPOINT = "/dietician/";
	private static final String INVALID_ENDPOINT = "/invalid";
	
	//private static final String adminAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjQwODI1ODEsImV4cCI6MTcyNDExMTM4MX0.qaDYaMLfvwhk6G5BGznmXrs43AC6uUwC5OPiHEeXz1By56W9GL8rHQgy2mgzVm6m7-iVXacCuEik5ujt4EaODQ";
	//private static final String dieticianAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxdHJiZGFAZ21haWwuY29tIiwiaWF0IjoxNzI0MDg0NjcxLCJleHAiOjE3MjQxMTM0NzF9.CFPaZDayofJnajZC6eiZx5h7i15FtBmBIEaFlkLWyac_4oG8LJjDl13_l58od4E__dD6Xv4yIeTOWj1fSa-qKA";
	//private static final String patientAuthToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhMTIzNDU2N0BnbWFpbC5jb20iLCJpYXQiOjE3MjQwODQ2ODMsImV4cCI6MTcyNDExMzQ4M30.LnrX-CoIAAYFPFP_WxrPV5Yg4BnbKN700x_UpwhVmvi9_5R07wc2Utq9BqHwHZCCNvAJ_H9Ox-qYIpixkqSvuA";


	private DieticianPayload excelReader;
	private DieticianRequest dieticianRequest;
	private UserLoginRequest userLoginRequest = new UserLoginRequest();
	private DieticianPojo dietician;
	private Response response;
	static String adminAuthToken;
	static String dieticianAuthToken;
	static String patientAuthToken;
	static String dieticianID = CommonUtils.getDieticianId();

	public DieticianPutByIdSteps() {
		excelReader = new DieticianPayload();
		dieticianRequest = new DieticianRequest();
	}
	
	

	@Given("Put Dietician By Id has Admin Auth token")
	public void app_has_adminAuthToken() {
		adminAuthToken = userLoginRequest.adminLoginRequest().jsonPath().getString("token");
		
	}

	@Given("Put Dietician By Id has Dietician Auth token")
	public void app_has_dieticianAuthToken() {
		dieticianAuthToken =userLoginRequest.dieticianLoginRequest().jsonPath().getString("token");
	}

	@Given("Put Dietician By Id has Patient Auth token")
	public void app_has_patientAuthToken() {
		
		patientAuthToken = userLoginRequest.patientLoginRequest().jsonPath().getString("token");
	}
	
	

	@Given("Put Dietician By Id Excel file has full Dietician details")
	public void excel_file_has_full_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.FULL);
	}

	@Given("Put Dietician By Id Excel file has mandatory Dietician details")
	public void excel_file_has_mandatory_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.MANDATORY);
	}

	@Given("Put Dietician By Id Excel file has additional Dietician details")
	public void excel_file_has_additional_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.ADDITIONAL);
	}

	@Given("Put Dietician By Id Excel file has invalid Dietician details")
	public void excel_file_has_invalid_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.INVALID);
	}
	
	

	@When("Put Dietician By Id without Auth token")
	public void put_dietician_by_id_without_auth_token() {
		this.response = dieticianRequest.putDieticianById(DIETICIAN_ENDPOINT, Method.PUT, ContentType.JSON, null, this.dietician, dieticianID);
	}
	
	@When("Put Dietician By Id with Dietician Auth token")
	public void put_dietician_by_id_with_dieticianAuthToken() {
		this.response = dieticianRequest.putDieticianById(DIETICIAN_ENDPOINT, Method.PUT, ContentType.JSON, dieticianAuthToken, this.dietician, dieticianID);
	}
	
	@When("Put Dietician By Id with Patient Auth token")
	public void put_dietician_by_id_with_patientAuthToken() {
		this.response = dieticianRequest.putDieticianById(DIETICIAN_ENDPOINT, Method.PUT, ContentType.JSON, patientAuthToken, this.dietician, dieticianID);
	}
	
	@When("Put Dietician By Id with Admin Auth token")
	public void put_dietician_by_id_with_adminAuthToken() {
		this.response = dieticianRequest.putDieticianById(DIETICIAN_ENDPOINT, Method.PUT, ContentType.JSON, adminAuthToken, this.dietician, dieticianID);
	}
	
	@When("Put Dietician By Id with Admin Auth token and invalid id")
	public void put_dietician_by_id_with_adminAuthToken_and_invlaid_id() {
		this.response = dieticianRequest.putDieticianById(DIETICIAN_ENDPOINT, Method.PUT, ContentType.JSON, adminAuthToken, this.dietician, DIETICIAN_INVALID_ID);
	}
	
	@When("Put Dietician By Id with Admin Auth token and invalid HTTP method")
	public void put_dietician_by_id_with_adminAuthToken_and_invalid_http_method() {
		this.response = dieticianRequest.putDieticianById(DIETICIAN_ENDPOINT, Method.PATCH, ContentType.JSON, adminAuthToken, this.dietician, dieticianID);
	}
	
	@When("Put Dietician By Id with Admin Auth token and invalid endpoint")
	public void put_dietician_by_id_with_adminAuthToken_and_invalid_endpoint() {
		this.response = dieticianRequest.putDieticianById(INVALID_ENDPOINT, Method.PUT, ContentType.JSON, adminAuthToken, this.dietician, dieticianID);
	}
	
	@When("Put Dietician By Id with Admin Auth token and invalid content type")
	public void put_dietician_by_id_with_adminAuthToken_and_invalid_content_type() {
		this.response = dieticianRequest.putDieticianById(DIETICIAN_ENDPOINT, Method.PUT, ContentType.TEXT, adminAuthToken, this.dietician, dieticianID);
	}
		
	@Then("Put Dietician By Id fails with http status BAD_REQUEST")
	public void put_dietician_by_id_fails_with_http_400() {
		this.response.then().statusCode(400);
	}
	
	@Then("Put Dietician By Id fails with http status UNAUTHORIZED")
	public void put_dietician_by_id_fails_with_http_401() {
		this.response.then().statusCode(401);
	}
	
	@Then("Put Dietician By Id fails with http status FORBIDDEN")
	public void put_dietician_by_id_fails_with_http_403() {
		this.response.then().statusCode(403);
	}
	
	@Then("Put Dietician By Id fails with http status NOT_FOUND")
	public void put_dietician_by_id_fails_with_http_404() {
		this.response.then().statusCode(404);
	}
	
	@Then("Put Dietician By Id fails with http status METHOD_NOT_ALLOWED")
	public void put_dietician_by_id_fails_with_http_405() {
		this.response.then().statusCode(405);
	}
	
	@Then("Put Dietician By Id fails with http status UNSUPPORTED_MEDIA_TYPE")
	public void put_dietician_by_id_fails_with_http_415() {
		this.response.then().statusCode(415);
	}

	@Then("Put Dietician By Id succeeds with http status OK")
	public void put_dietician_by_id_succeeds_with_http_200() {
		
		 int responseStatusCode = this.response.getStatusCode();
		    Assert.assertEquals(responseStatusCode, 200); 
//		DieticianPojo response = this.response.then().statusCode(200)
//		.extract().as(DieticianPojo.class);
//		
//		LOGGER.info("Put Dietician By Id succeeded.");
//
//		dieticianRequest.validateCreation(dietician, response);
	}
}
