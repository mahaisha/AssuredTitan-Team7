package api.StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.Payload.DieticianPayload;
import api.Payload.DieticianPayload.TestCase;
import api.Pojo.DieticianPojo;
import api.Request.DieticianRequest;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;

public class DieticianDeleteByIdSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianDeleteByIdSteps.class);
	
	private static final int DIETICIAN_INVALID_ID = Integer.MAX_VALUE;
	
	private static final String DIETICIAN_ENDPOINT = "/dietician/";
	private static final String INVALID_ENDPOINT = "/invalid";
	
	private static final String ADMIN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjQwODI1ODEsImV4cCI6MTcyNDExMTM4MX0.qaDYaMLfvwhk6G5BGznmXrs43AC6uUwC5OPiHEeXz1By56W9GL8rHQgy2mgzVm6m7-iVXacCuEik5ujt4EaODQ";
	private static final String DIETICIAN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxdHJiZGFAZ21haWwuY29tIiwiaWF0IjoxNzI0MDg0NjcxLCJleHAiOjE3MjQxMTM0NzF9.CFPaZDayofJnajZC6eiZx5h7i15FtBmBIEaFlkLWyac_4oG8LJjDl13_l58od4E__dD6Xv4yIeTOWj1fSa-qKA";
	private static final String PATIENT_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhMTIzNDU2N0BnbWFpbC5jb20iLCJpYXQiOjE3MjQwODQ2ODMsImV4cCI6MTcyNDExMzQ4M30.LnrX-CoIAAYFPFP_WxrPV5Yg4BnbKN700x_UpwhVmvi9_5R07wc2Utq9BqHwHZCCNvAJ_H9Ox-qYIpixkqSvuA";

	
	private static final DieticianPayload EXCEL_READER = new DieticianPayload();
	private static final DieticianRequest REST_UTIL = new DieticianRequest();
	private static int dieticianId;
	private static DieticianPojo dieticianCreated;

	
	private DieticianPojo dietician;
	private ValidatableResponse response;
	
	
	
	@BeforeAll
	public static void setup() {
		DieticianPojo dieticianToCreate = EXCEL_READER.readRow(TestCase.DELETE_BY_ID);
		ValidatableResponse createResponse = REST_UTIL.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, ADMIN_AUTH_TOKEN, dieticianToCreate);
		
		dieticianCreated = createResponse.statusCode(201)
				.extract().as(DieticianPojo.class);
		dieticianId = Integer.parseInt(dieticianCreated.getId());
	}
	

	@Given("Delete Dietician By Id has Admin Auth token")
	public void app_has_admin_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}

	@Given("Delete Dietician By Id has Dietician Auth token")
	public void app_has_dietician_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}

	@Given("Delete Dietician By Id has Patient Auth token")
	public void app_has_patient_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}
	
	

	@When("Delete Dietician By Id without Auth token")
	public void delete_dietician_by_id_without_auth_token() {
		this.response = REST_UTIL.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, null, dieticianId);
	}
	
	@When("Delete Dietician By Id with Dietician Auth token and valid id")
	public void delete_dietician_by_id_with_dietician_auth_token() {
		this.response = REST_UTIL.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, DIETICIAN_AUTH_TOKEN, dieticianId);
	}
	
	@When("Delete Dietician By Id with Patient Auth token and valid id")
	public void delete_dietician_by_id_with_patient_auth_token() {
		this.response = REST_UTIL.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, PATIENT_AUTH_TOKEN, dieticianId);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and valid id")
	public void delete_dietician_by_id_with_admin_auth_token_and_valid_id() {
		this.response = REST_UTIL.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, ADMIN_AUTH_TOKEN, dieticianId);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and invalid id")
	public void delete_dietician_by_id_with_admin_auth_token_and_invalid_id() {
		this.response = REST_UTIL.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, ADMIN_AUTH_TOKEN, DIETICIAN_INVALID_ID);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and invalid HTTP method")
	public void delete_dietician_by_id_with_admin_auth_token_and_invalid_http_method() {
		this.response = REST_UTIL.deleteDieticianById(DIETICIAN_ENDPOINT, Method.PATCH, ContentType.JSON, ADMIN_AUTH_TOKEN, dieticianId);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and invalid endpoint")
	public void delete_dietician_by_id_with_admin_auth_token_and_invalid_endpoint() {
		this.response = REST_UTIL.deleteDieticianById(INVALID_ENDPOINT, Method.DELETE, ContentType.JSON, ADMIN_AUTH_TOKEN, dieticianId);
	}
	
	
	

	
	@Then("Delete Dietician By Id fails with http status BAD_REQUEST")
	public void dietician_delete_by_id_fails_with_http_400() {
		this.response.statusCode(400);
	}
	
	@Then("Delete Dietician By Id fails with http status UNAUTHORIZED")
	public void dietician_delete_by_id_fails_with_http_401() {
		this.response.statusCode(401);
	}
	
	@Then("Delete Dietician By Id fails with http status FORBIDDEN")
	public void dietician_delete_by_id_fails_with_http_403() {
		this.response.statusCode(403);
	}
	
	@Then("Delete Dietician By Id fails with http status NOT_FOUND")
	public void dietician_delete_by_id_fails_with_http_404() {
		this.response.statusCode(404);
	}
	
	@Then("Delete Dietician By Id fails with http status METHOD_NOT_ALLOWED")
	public void dietician_delete_by_id_fails_with_http_405() {
		this.response.statusCode(405);
	}

	@Then("Delete Dietician By Id succeeds with http status OK")
	public void dietician_delete_by_id_succeeds_with_http_200() {
		this.response.statusCode(200);
	}
}
