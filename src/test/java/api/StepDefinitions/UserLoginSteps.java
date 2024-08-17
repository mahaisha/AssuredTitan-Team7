package api.StepDefinitions;
import org.testng.Assert;

import api.Request.UserLoginRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLoginSteps {

UserLoginRequest userLoginRequest = new UserLoginRequest();
	
	@Given("User creates Post request with request body")
	public void user_creates_post_request_with_request_body() {
	    
	}
	@When("User send POST HTTP request with endpoint")
	public void user_send_post_http_request_with_endpoint() {
		userLoginRequest.adminLoginRequest();
	}
	@Then("User recieves {int} created with response body")
	public void user_recieves_created_with_response_body(Integer int1) {
		int responseStatusCode = userLoginRequest.response.getStatusCode();
		   Assert.assertEquals(responseStatusCode, int1);
	}

}
