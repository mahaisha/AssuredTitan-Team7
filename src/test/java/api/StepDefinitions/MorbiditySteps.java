package api.StepDefinitions;

import org.testng.Assert;

import api.Request.MorbidityRequest;
import api.Request.UserLoginRequest;
import api.Utility.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MorbiditySteps extends CommonUtils {

	private static Response response;
	public static String token;
	static RequestSpecification request;
	static String morbidityTestName;

	MorbidityRequest morbidityRequest = new MorbidityRequest(baseURI);
	UserLoginRequest userLoginRequest = new UserLoginRequest();

	@Given("User is logged in as Dietician with Token")
	public void user_is_logged_in_as_dietician() {
		
		userLoginRequest.dieticianLoginRequest();
		userLoginRequest.getDieticianToken();
	}

	@Given("Dietician sends GET request to get morbidities")
	public void dietician_creates_get_request_to_get_morbidities() {
		
		morbidityRequest.getAllMorbidities();
	}
	@Then("Dietician receives all morbidity details")
	public void dietician_receives_all_morbidity_details() {

		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, 200);
	}

//	@Then("Dietician receives all morbidity details for {string}")
//	public void dietician_receives_all_morbidity_details(String endpointType) {
//		
//		
//			morbidityRequest.validateJsonResponseSchema(endpointType);
//			int responseStatusCode = morbidityRequest.response.getStatusCode();
//			Assert.assertEquals(responseStatusCode, 200);
//	
//	}

	@Then("Dietician should receive status 404")
	public void dietician_receives_status_unauthorized() {

		Assert.assertEquals(response.getStatusCode(), 404);
	}

	@Given("Dietician user sends GET request with endpoint having Test Name")
	public void dietician_user_sends_GET_request_with_TestName() {
		
		morbidityRequest.getMorbidityByTestName();
	}

	@Then("Dietician receives morbidity details with Test Name")
	public void dietician_receives_morbidity_byTestName() {
		
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, 200);
	}

	@Given("Dietician creates POST request for morbidity")
	public void dietician_creates_post_request_for_morbidity() {
		
		morbidityRequest.postMorbidityInvalidMethod();
	}

	@Then("Dietician gets {int} method not allowed")
	public void dietician_gets_method_not_allowed(int int1) {

		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, int1);
	}

	@When("Dietician sends POST request with morbidity by Test Name endpoint")
	public void dietician_sends_post_request_with_morbidity_by_test_name_endpoint() {
		
		morbidityRequest.postMorbidityByNameInvalidMethod();
	}

	@Given("Dietician creates GET request for morbidity with invalid endpoint")
	public void dietician_creates_get_request_for_morbidity() {

		morbidityRequest.getMorbidityInvalidEndpoint();

	}

	@Given("GET request for morbidity by Test Name with invalid endpoint")
	public void dietician_get_request_for_morbidity_testName_invalidEndpoint() {

		morbidityRequest.getMorbidityByTestNameInvalidEndpoint();

	}

	@Given("Dietician GET request for morbidity having invalid test name")
	public void dietician_get_request_for_morbidity_invalid_testName() {

		morbidityRequest.getMorbidityInvalidTestName();
	}

	@Then("Dietician should get {int} not found")
	public void dietician_should_get_not_found(int int1) {
		//Assert.assertEquals(response.getStatusCode(), int1);
		
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, 404);

	}

	@Given("Dietician sends GET request to get morbidities with NO Auth Token")
	public void user_is_logged_in_as_dietician_with_no_auth_token() {
		morbidityRequest.getMorbidityNoAuthToken();

	}
	
	@Given("Dietician sends GET request having Test Name with NO Auth Token")
	public void user_is_logged_in_dietician_with_no_auth_token_testName() {

		morbidityRequest.getMorbidityTestNameNoAuthToken();
		
	}

	@Then("Dietician receives {int} unauthorized")
	public void dietician_receives_unauthorized(int int1) {
		
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, int1);
		
	}

	@Given("User is logged in as Patient with Patient Token")
	public void user_is_logged_in_as_patient_with_token() {
		
		userLoginRequest.patientLoginRequest();
	}

	@Given("Patient sends GET request to get morbidities")
	public void patient_sends_get_request_to_get_morbidities() {
		
		morbidityRequest.getAllMorbidities();
	}
	
	@Given("Patient sends GET request to get morbidities having Test Name")
	public void patient_sends_get_request_to_get_morbidityName() {
		
		morbidityRequest.getMorbidityByTestName();
	}

	@Then("Patient receives {int} forbidden")
	public void patient_receives_forbidden(int int1) {
		
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, int1);
	}


	@Given("User is logged in as Admin with valid Token")
	public void user_is_logged_in_as_admin_with_valid_token() {
		
		morbidityRequest.adminLoginRequest();
	}

	@Given("Admin sends GET request to get morbidities")
	public void admin_sends_get_request_to_get_morbidities() {
		morbidityRequest.getAllMorbidities();
	}

	@Then("Admin receives all morbidity details")
	public void admin_receives_all_morbidity_details() {
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, 200);
	}

	@Given("Admin creates POST request for morbidity")
	public void admin_creates_post_request_for_morbidity() {
		morbidityRequest.postMorbidityInvalidMethod();
	}

	@Then("Admin gets {int} method not allowed")
	public void admin_gets_method_not_allowed(int int1) {
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, int1);
	}

	@Given("Admin creates GET request for morbidity with invalid endpoint")
	public void admin_creates_get_request_for_morbidity_with_invalid_endpoint() {
		morbidityRequest.getMorbidityInvalidEndpoint();
	}

	@Then("Admin should get {int} not found")
	public void admin_should_get_not_found(int int1) {
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, int1);
	}

	@Given("Admin user sends GET request with endpoint having Test Name")
	public void admin_user_sends_get_request_with_endpoint_having_test_name() {
		morbidityRequest.getMorbidityByTestName();
	}

	@Then("Admin receives morbidity details with Test Name")
	public void admin_receives_morbidity_details_with_test_name() {
		int responseStatusCode = morbidityRequest.response.getStatusCode();
		Assert.assertEquals(responseStatusCode, 200);
	}

	@When("Admin sends POST request with morbidity by Test Name endpoint")
	public void admin_sends_post_request_with_morbidity_by_test_name_endpoint() {
		morbidityRequest.postMorbidityByNameInvalidMethod();
	}

	@Given("Admin GET request for morbidity by Test Name with invalid endpoint")
	public void admin_get_request_for_morbidity_by_test_name_with_invalid_endpoint() {
		morbidityRequest.getMorbidityByTestNameInvalidEndpoint();
	}

	@Given("Admin GET request for morbidity having invalid test name")
	public void admin_get_request_for_morbidity_having_invalid_test_name() {
		
		morbidityRequest.getMorbidityInvalidTestName();
	}

}
