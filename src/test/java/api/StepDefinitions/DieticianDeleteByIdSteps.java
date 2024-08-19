package api.StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.Pojo.DieticianPojo;
import api.Request.DieticianRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;

public class DieticianDeleteByIdSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianDeleteByIdSteps.class);
	
	private static final int DIETICIAN_ID = 410;
	private static final int DIETICIAN_INVALID_ID = Integer.MAX_VALUE;
	
	private static final String DIETICIAN_ENDPOINT = "/dietician/";
	private static final String INVALID_ENDPOINT = "/invalid";
	
	private static final String ADMIN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjM5OTA1OTMsImV4cCI6MTcyNDAxOTM5M30.OPtdczAakW0MedYn73x8YYms-yI5VYOAoqdabzc2lMgm2jbN90_gnVyc24yshvngeXxgHNfETNRTKNRmZy-tvg";
	private static final String DIETICIAN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxdHJiZGFAZ21haWwuY29tIiwiaWF0IjoxNzIzOTkwNjE1LCJleHAiOjE3MjQwMTk0MTV9.uMrF8SzL8OY36FfVCN6rDnN2TqmgDsfGR_068I_97J7pNleR4HlvVoAT6l2lt2qQNw-REybsL9ePdP6ltKfUAw";
	private static final String PATIENT_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhMTIzNDU2N0BnbWFpbC5jb20iLCJpYXQiOjE3MjM5OTA2MzAsImV4cCI6MTcyNDAxOTQzMH0.8lkOI2xnSMqYoCwnXl55KVfz_hpcVk2D6eRVb6Tg-fTHqII0pr6XHO0JM0jayMYlwveS1QooW_RK86ubISs6FA";


	private DieticianRequest restUtil;
	
	private DieticianPojo dietician;
	private ValidatableResponse response;

	public DieticianDeleteByIdSteps() {
		restUtil = new DieticianRequest();
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
		this.response = restUtil.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, null, DIETICIAN_ID);
	}
	
	@When("Delete Dietician By Id with Dietician Auth token and valid id")
	public void delete_dietician_by_id_with_dietician_auth_token() {
		this.response = restUtil.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, DIETICIAN_AUTH_TOKEN, DIETICIAN_ID);
	}
	
	@When("Delete Dietician By Id with Patient Auth token and valid id")
	public void delete_dietician_by_id_with_patient_auth_token() {
		this.response = restUtil.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, PATIENT_AUTH_TOKEN, DIETICIAN_ID);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and valid id")
	public void delete_dietician_by_id_with_admin_auth_token_and_valid_id() {
		this.response = restUtil.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, ADMIN_AUTH_TOKEN, DIETICIAN_ID);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and invalid id")
	public void delete_dietician_by_id_with_admin_auth_token_and_invalid_id() {
		this.response = restUtil.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, ADMIN_AUTH_TOKEN, DIETICIAN_INVALID_ID);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and invalid HTTP method")
	public void delete_dietician_by_id_with_admin_auth_token_and_invalid_http_method() {
		this.response = restUtil.deleteDieticianById(DIETICIAN_ENDPOINT, Method.PATCH, ContentType.JSON, ADMIN_AUTH_TOKEN, DIETICIAN_ID);
	}
	
	@When("Delete Dietician By Id with Admin Auth token and invalid endpoint")
	public void delete_dietician_by_id_with_admin_auth_token_and_invalid_endpoint() {
		this.response = restUtil.deleteDieticianById(INVALID_ENDPOINT, Method.DELETE, ContentType.JSON, ADMIN_AUTH_TOKEN, DIETICIAN_ID);
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
