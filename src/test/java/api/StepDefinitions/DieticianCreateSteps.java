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

public class DieticianCreateSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianCreateSteps.class);
	
	private static final String DIETICIAN_ENDPOINT = "/dietician";
	private static final String INVALID_ENDPOINT = "/invalid";
	
	private static final String ADMIN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjM5MTA0OTgsImV4cCI6MTcyMzkzOTI5OH0.ImECkl3UWwd6VwUtuOxnavrW4gHnWob3NHq9Y5wBvZfRR9sEA1DT2kqIMEySsjasrWJflvyOHoiGemYqxY_dvQ";
	private static final String DIETICIAN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxdHJiZGFAZ21haWwuY29tIiwiaWF0IjoxNzIzOTEwNTI4LCJleHAiOjE3MjM5MzkzMjh9.FXazbgP02e8g_9gw8_jqQMsfy8xZdalT0lRqvxQ7VGO5T9kaa5fwwe0g2j6q7hSu6Id9II75TMWQDFErZSxCSg";
	private static final String PATIENT_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhMTIzNDU2N0BnbWFpbC5jb20iLCJpYXQiOjE3MjM5MTA1NDAsImV4cCI6MTcyMzkzOTM0MH0.3EDLhKlNZoCSX3tI0YZcn07Gxrek-4McEn119R4WMMgsfOmo_qVkt45WQoF8ycNAw2FEzCRf2NtigQZl-ILkXw";


	private DieticianExcelReader excelReader;
	private DieticianRestUtil restUtil;
	
	private Dietician dietician;
	private ValidatableResponse response;

	public DieticianCreateSteps() {
		excelReader = new DieticianExcelReader();
		restUtil = new DieticianRestUtil();
	}
	
	

	@Given("Create Dietician has Admin Auth token")
	public void app_has_admin_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}

	@Given("Create Dietician has Dietician Auth token")
	public void app_has_dietician_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}

	@Given("Create Dietician has Patient Auth token")
	public void app_has_patient_auth_token() {
		// TODO: Integrate with Admin module
		return;
	}
	
	

	@Given("Excel file has full Dietician details")
	public void excel_file_has_full_dietician_details() {
		this.dietician = excelReader.readRow(TestCase.FULL);
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
		this.response = restUtil.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, null, this.dietician);
	}
	
	@When("Create Dietician with Dietician Auth token")
	public void create_dietician_with_dietician_auth_token() {
		this.response = restUtil.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, DIETICIAN_AUTH_TOKEN, this.dietician);
	}
	
	@When("Create Dietician with Patient Auth token")
	public void create_dietician_with_patient_auth_token() {
		this.response = restUtil.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, PATIENT_AUTH_TOKEN, this.dietician);
	}
	
	@When("Create Dietician with Admin Auth token")
	public void create_dietician_with_admin_auth_token() {
		this.response = restUtil.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, ADMIN_AUTH_TOKEN, this.dietician);
	}
	
	@When("Create Dietician with Admin Auth token and invalid HTTP method")
	public void create_dietician_with_admin_auth_token_and_invalid_http_method() {
		this.response = restUtil.createDietician(DIETICIAN_ENDPOINT, Method.PATCH, ContentType.JSON, ADMIN_AUTH_TOKEN, this.dietician);
	}
	
	@When("Create Dietician with Admin Auth token and invalid endpoint")
	public void create_dietician_with_admin_auth_token_and_invalid_endpoint() {
		this.response = restUtil.createDietician(INVALID_ENDPOINT, Method.POST, ContentType.JSON, ADMIN_AUTH_TOKEN, this.dietician);
	}
	
	@When("Create Dietician with Admin Auth token and invalid content type")
	public void create_dietician_with_admin_auth_token_and_invalid_content_type() {
		this.response = restUtil.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.TEXT, ADMIN_AUTH_TOKEN, this.dietician);
	}
	
	
	
	
	
	@When("Create Dietician fails with http status BAD_REQUEST")
	public void dietician_creation_fails_with_http_400() {
		this.response.statusCode(400);
	}
	
	@When("Create Dietician fails with http status UNAUTHORIZED")
	public void dietician_creation_fails_with_http_401() {
		this.response.statusCode(401);
	}
	
	@When("Create Dietician fails with http status FORBIDDEN")
	public void dietician_creation_fails_with_http_403() {
		this.response.statusCode(403);
	}
	
	@When("Create Dietician fails with http status NOT_FOUND")
	public void dietician_creation_fails_with_http_404() {
		this.response.statusCode(404);
	}
	
	@When("Create Dietician fails with http status METHOD_NOT_ALLOWED")
	public void dietician_creation_fails_with_http_405() {
		this.response.statusCode(405);
	}
	
	@When("Create Dietician fails with http status UNSUPPORTED_MEDIA_TYPE")
	public void dietician_creation_fails_with_http_415() {
		this.response.statusCode(415);
	}

	@When("Create Dietician succeeds with http status CREATED")
	public void dietician_creation_succeeds_with_http_201() {
		Dietician response = this.response.statusCode(201)
		.extract().as(Dietician.class);
		
		LOGGER.info("Create Dietician succeeded.");

		restUtil.validateCreation(dietician, response);
	}
}
