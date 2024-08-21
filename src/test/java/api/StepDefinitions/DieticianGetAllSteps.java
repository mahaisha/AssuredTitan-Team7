package api.StepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

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
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DieticianGetAllSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianGetAllSteps.class);
	
	private static final String DIETICIAN_ENDPOINT = "/dietician/";
	private static final String INVALID_ENDPOINT = "/invalid";
	
//	private static final String ADMIN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjQwODI1ODEsImV4cCI6MTcyNDExMTM4MX0.qaDYaMLfvwhk6G5BGznmXrs43AC6uUwC5OPiHEeXz1By56W9GL8rHQgy2mgzVm6m7-iVXacCuEik5ujt4EaODQ";
	private static final String DIETICIAN_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxdHJiZGFAZ21haWwuY29tIiwiaWF0IjoxNzI0MDg0NjcxLCJleHAiOjE3MjQxMTM0NzF9.CFPaZDayofJnajZC6eiZx5h7i15FtBmBIEaFlkLWyac_4oG8LJjDl13_l58od4E__dD6Xv4yIeTOWj1fSa-qKA";
	private static final String PATIENT_AUTH_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhMTIzNDU2N0BnbWFpbC5jb20iLCJpYXQiOjE3MjQwODQ2ODMsImV4cCI6MTcyNDExMzQ4M30.LnrX-CoIAAYFPFP_WxrPV5Yg4BnbKN700x_UpwhVmvi9_5R07wc2Utq9BqHwHZCCNvAJ_H9Ox-qYIpixkqSvuA";


	private static final DieticianPayload EXCEL_READER = new DieticianPayload();
	private static DieticianRequest dieticianRequest = new DieticianRequest() ;
	private static int dieticianId;
	private static DieticianPojo dieticianCreated;
	static String adminAuthToken;
	private static Response response;


	
	//@BeforeAll
	public static void setup() {
		DieticianPojo dieticianToCreate = EXCEL_READER.readRow(TestCase.GET_ALL);
		 response = dieticianRequest.createDietician(DIETICIAN_ENDPOINT, Method.POST, ContentType.JSON, adminAuthToken, dieticianToCreate);
		
		dieticianCreated = response.then().statusCode(201)
				.extract().as(DieticianPojo.class);
		dieticianId = Integer.parseInt(dieticianCreated.getId());
	}
	
//	@AfterAll
	public static void tearDown() {
		response = dieticianRequest.deleteDieticianById(DIETICIAN_ENDPOINT, Method.DELETE, ContentType.JSON, adminAuthToken, dieticianId);
		response.then().statusCode(200);
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
		this.response = dieticianRequest.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, null);
	}
	
	@When("Get All Dieticians with Dietician Auth token")
	public void create_dietician_with_dietician_auth_token() {
		this.response = dieticianRequest.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, DIETICIAN_AUTH_TOKEN);
	}
	
	@When("Get All Dieticians with Patient Auth token")
	public void create_dietician_with_patient_auth_token() {
		this.response = dieticianRequest.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, PATIENT_AUTH_TOKEN);
	}
	
	@When("Get All Dieticians with Admin Auth token")
	public void create_dietician_with_admin_auth_token() {
		this.response = dieticianRequest.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.JSON, adminAuthToken);
	}
	
	@When("Get All Dieticians with Admin Auth token and invalid HTTP method")
	public void create_dietician_with_admin_auth_token_and_invalid_http_method() {
		this.response = dieticianRequest.getAllDieticians(DIETICIAN_ENDPOINT, Method.PATCH, ContentType.JSON, adminAuthToken);
	}
	
	@When("Get All Dieticians with Admin Auth token and invalid endpoint")
	public void create_dietician_with_admin_auth_token_and_invalid_endpoint() {
		this.response = dieticianRequest.getAllDieticians(INVALID_ENDPOINT, Method.GET, ContentType.JSON, adminAuthToken);
	}
	
	@When("Get All Dieticians with Admin Auth token and invalid content type")
	public void create_dietician_with_admin_auth_token_and_invalid_content_type() {
		this.response = dieticianRequest.getAllDieticians(DIETICIAN_ENDPOINT, Method.GET, ContentType.TEXT, adminAuthToken);
	}
	
	
	
	
	
	@Then("Get All Dieticians fails with http status BAD_REQUEST")
	public void dietician_get_all_fails_with_http_400() {
		this.response.then().statusCode(400);
	}
	
	@Then("Get All Dieticians fails with http status UNAUTHORIZED")
	public void dietician_get_all_fails_with_http_401() {
		this.response.then().statusCode(401);
	}
	
	@Then("Get All Dieticians fails with http status NOT_FOUND")
	public void dietician_get_all_fails_with_http_404() {
		this.response.then().statusCode(404);
	}
	
	@Then("Get All Dieticians fails with http status METHOD_NOT_ALLOWED")
	public void dietician_get_all_fails_with_http_405() {
		this.response.then().statusCode(405);
	}
	
	@Then("Get All Dieticians fails with http status NOT_ACCEPTABLE")
	public void dietician_get_all_fails_with_http_406() {
		this.response.then().statusCode(406);
	}

	@Then("Get All Dieticians succeeds with http status OK")
	public void dietician_get_all_succeeds_with_http_200() {
		List<DieticianPojo> dieticians = this.response.then().statusCode(200).extract().jsonPath().getList(".", DieticianPojo.class);
		
		boolean found = false;
		
		for(DieticianPojo dietician : dieticians) {
			if(dietician.getId().equals(dieticianCreated.getId())) {
				assertEquals(dietician, this.dieticianCreated);
				found = true;
			}
		}
		
		assertTrue(found);
	}
}
