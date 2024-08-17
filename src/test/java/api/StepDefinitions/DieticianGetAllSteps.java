package api.StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.Utility.dietician.DieticianExcelReader;
import api.Utility.dietician.DieticianExcelReader.TestCase;
import api.Utility.dietician.DieticianRestUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import model.Dietician;

public class DieticianGetAllSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianGetAllSteps.class);
	
	private static final String DIETICIAN_ENDPOINT = "/dietician";
	private static final String INVALID_ENDPOINT = "/invalid";
	
	private static final String ADMIN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjM5MTA0OTgsImV4cCI6MTcyMzkzOTI5OH0.ImECkl3UWwd6VwUtuOxnavrW4gHnWob3NHq9Y5wBvZfRR9sEA1DT2kqIMEySsjasrWJflvyOHoiGemYqxY_dvQ";
	private static final String DIETICIAN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxdHJiZGFAZ21haWwuY29tIiwiaWF0IjoxNzIzOTEwNTI4LCJleHAiOjE3MjM5MzkzMjh9.FXazbgP02e8g_9gw8_jqQMsfy8xZdalT0lRqvxQ7VGO5T9kaa5fwwe0g2j6q7hSu6Id9II75TMWQDFErZSxCSg";
	private static final String PATIENT_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhMTIzNDU2N0BnbWFpbC5jb20iLCJpYXQiOjE3MjM5MTA1NDAsImV4cCI6MTcyMzkzOTM0MH0.3EDLhKlNZoCSX3tI0YZcn07Gxrek-4McEn119R4WMMgsfOmo_qVkt45WQoF8ycNAw2FEzCRf2NtigQZl-ILkXw";


	private DieticianRestUtil restUtil;
	
	private Dietician dietician;
	private ValidatableResponse response;

	public DieticianGetAllSteps() {
		restUtil = new DieticianRestUtil();
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
