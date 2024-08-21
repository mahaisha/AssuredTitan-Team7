package api.StepDefinitions;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import api.Request.UserLoginRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLoginSteps {

UserLoginRequest userLoginRequest = new UserLoginRequest();
	
	public static String token;
	
	@Given("User creates Post request with request body")
	public void user_creates_post_request_with_request_body() {
	    
	}
	@When("User send POST HTTP request with endpoint")
	public void user_send_post_http_request_with_endpoint() throws JsonProcessingException {
		userLoginRequest.adminLoginRequest();
		token = userLoginRequest.response.jsonPath().getString("token");
	}
	@Then("User recieves {int} created with response body")
	public void user_recieves_created_with_response_body(Integer int1) {
		int responseStatusCode = userLoginRequest.response.getStatusCode();
		   Assert.assertEquals(responseStatusCode, int1);
	}
	@Given("User creates GET request")
	public void user_creates_get_request() {
	}

	@When("User send GET HTTP request with endpoint")
	public void user_send_get_http_request_with_endpoint() {
		userLoginRequest.adminLogoutRequest(token);
	}

	@Then("User gets {int} created with Logout successful message")
	public void user_gets_created_with_logout_successful_message(Integer int1) {
	   int responseStatusCode = userLoginRequest.response.getStatusCode();
	   Assert.assertEquals(responseStatusCode, int1);
	}
	
	@Given("User creates Post request with invalid credential")
	public void user_creates_post_request_with_invalid_credential() {
	   
	}
	@When("User send invalid POST HTTP request with endpoint")
	public void user_send_invalid_post_http_request_with_endpoint() {
		userLoginRequest.adminInvalidLoginRequest();
	}
	@Then("User recieves {int} unauthorized")
	public void user_recieves_unauthorized(Integer int1) {
		int responseStatusCode = userLoginRequest.response.getStatusCode();
		   Assert.assertEquals(responseStatusCode, int1);
	}

	@Given("User creates GET request with request body")
	public void user_creates_get_request_with_request_body() {
	  
	}

	@When("User send GET HTTP request with valid credential")
	public void user_send_get_http_request_with_valid_credential() {
		userLoginRequest.adminLoginValidBodyInvalidMethod();
	}

	@Then("User recieves {int} method not allowed")
	public void user_recieves_method_not_allowed(Integer int1) {
		int responseStatusCode = userLoginRequest.response.getStatusCode();
		   Assert.assertEquals(responseStatusCode, int1);
	}
	
	@Given("User creates Post request with request body and invalid content type")
	public void user_creates_post_request_with_request_body_and_invalid_content_type() {
	   
	}

	@When("User send POST HTTP request endpoint with invalid content type")
	public void user_send_post_http_request_endpoint_with_invalid_content_type() {
		userLoginRequest.adminLoginInvalidContentType();
	}

	@Then("User recieves {int} unsupported media type")
	public void user_recieves_unsupported_media_type(Integer int1) {
		int responseStatusCode = userLoginRequest.response.getStatusCode();
		   Assert.assertEquals(responseStatusCode, int1);
	}

//	//Dietician login
//	@When("User send POST HTTP request with dietician credentials")
//	public void user_send_post_http_request_with_dietician_credentials() {
//		userLoginRequest.dieticianLoginRequest();
//		System.out.println();
//	}
//	//Invalid dietician login
//	@When("User send POST HTTP request with invalid dietician")
//	public void user_send_post_http_request_with_invalid_dietician() {
//	   
//	}
//	//patient login
//	@When("User send POST HTTP request with patient credentials")
//	public void user_send_post_http_request_with_patient_credentials() {
//	   
//	}
//	//invalid patient login
//	@When("User send POST HTTP request with invalid patient")
//	public void user_send_post_http_request_with_invalid_patient() {
//	   
//	}

}