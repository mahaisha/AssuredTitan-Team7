package api.StepDefinitions;

//import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.RestAssured.given;

public class PutUpdatePatientSteps {
	
	@Given("Dietician creates PUT request by entering valid data.")
	public void dietician_creates_put_request_by_entering_valid_data() {
		//System.out.println("hi");
		
				
	}

	@When("Dietician send PUT http request with endpoint")
	public void dietician_send_put_http_request_with_endpoint() {

	}

	@Then("Dietician recieves {int} ok and with updated response body.")
	public void dietician_recieves_ok_and_with_updated_response_body(Integer int1) {

	}

}
