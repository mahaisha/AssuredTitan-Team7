package api.StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.Payload.DieticianPayload;
import api.Payload.DieticianPayload.TestCase;
import api.Pojo.DieticianPojo;
import api.Request.DieticianRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;

public class DieticianGetAllSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianGetAllSteps.class);
	
	private static final String DIETICIAN_ENDPOINT = "/dietician";
	private static final String INVALID_ENDPOINT = "/invalid";
	
	private static final String ADMIN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjM5OTA1OTMsImV4cCI6MTcyNDAxOTM5M30.OPtdczAakW0MedYn73x8YYms-yI5VYOAoqdabzc2lMgm2jbN90_gnVyc24yshvngeXxgHNfETNRTKNRmZy-tvg";
	private static final String DIETICIAN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxdHJiZGFAZ21haWwuY29tIiwiaWF0IjoxNzIzOTkwNjE1LCJleHAiOjE3MjQwMTk0MTV9.uMrF8SzL8OY36FfVCN6rDnN2TqmgDsfGR_068I_97J7pNleR4HlvVoAT6l2lt2qQNw-REybsL9ePdP6ltKfUAw";
	private static final String PATIENT_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhMTIzNDU2N0BnbWFpbC5jb20iLCJpYXQiOjE3MjM5OTA2MzAsImV4cCI6MTcyNDAxOTQzMH0.8lkOI2xnSMqYoCwnXl55KVfz_hpcVk2D6eRVb6Tg-fTHqII0pr6XHO0JM0jayMYlwveS1QooW_RK86ubISs6FA";


	private DieticianRequest restUtil;
	
	private DieticianPojo dietician;
	private ValidatableResponse response;

	public DieticianGetAllSteps() {
		restUtil = new DieticianRequest();
	}
	
	

	@Given("Get All Dieticians has Admin Auth token")
	public void app_has_admin_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}

	@Given("Get All Dieticians has Dietician Auth token")
	public void app_has_dietician_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}

	@Given("Get All Dieticians has Patient Auth token")
	public void app_has_patient_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}
	
	

	@When("Get All Dieticians without Auth token")
	public void create_dietician_without_auth_token() {
		this.response = restUtil.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, null);
	}
	
	@When("Get All Dieticians with Dietician Auth token")
	public void create_dietician_with_dietician_auth_token() {
		this.response = restUtil.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, DIETICIAN_AUTH_TOKEN);
	}
	
	@When("Get All Dieticians with Patient Auth token")
	public void create_dietician_with_patient_auth_token() {
		this.response = restUtil.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, PATIENT_AUTH_TOKEN);
	}
	
	@When("Get All Dieticians with Admin Auth token")
	public void create_dietician_with_admin_auth_token() {
		this.response = restUtil.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, ADMIN_AUTH_TOKEN);
	}
	
	@When("Get All Dieticians with Admin Auth token and invalid HTTP method")
	public void create_dietician_with_admin_auth_token_and_invalid_http_method() {
		this.response = restUtil.getAllDieticians(DIETICIAN_ENDPOINT, Method.PATCH, ContentType.JSON, ADMIN_AUTH_TOKEN);
	}
	
	@When("Get All Dieticians with Admin Auth token and invalid endpoint")
	public void create_dietician_with_admin_auth_token_and_invalid_endpoint() {
		this.response = restUtil.getAllDieticians(INVALID_ENDPOINT, Method.GET, ContentType.JSON, ADMIN_AUTH_TOKEN);
	}
	
	@When("Get All Dieticians with Admin Auth token and invalid content type")
	public void create_dietician_with_admin_auth_token_and_invalid_content_type() {
		this.response = restUtil.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.TEXT, ADMIN_AUTH_TOKEN);
	}
	
	
	
	
	
	@When("Get All Dieticians fails with http status BAD_REQUEST")
	public void dietician_get_all_fails_with_http_400() {
		this.response.statusCode(400);
	}
	
	@When("Get All Dieticians fails with http status UNAUTHORIZED")
	public void dietician_get_all_fails_with_http_401() {
		this.response.statusCode(401);
	}
	
	@When("Get All Dieticians fails with http status NOT_FOUND")
	public void dietician_get_all_fails_with_http_404() {
		this.response.statusCode(404);
	}
	
	@When("Get All Dieticians fails with http status METHOD_NOT_ALLOWED")
	public void dietician_get_all_fails_with_http_405() {
		this.response.statusCode(405);
	}
	
	@When("Get All Dieticians fails with http status NOT_ACCEPTABLE")
	public void dietician_get_all_fails_with_http_406() {
		this.response.statusCode(406);
	}

	@When("Get All Dieticians succeeds with http status OK")
	public void dietician_get_all_succeeds_with_http_200() {
		this.response.statusCode(200);
	}
}
