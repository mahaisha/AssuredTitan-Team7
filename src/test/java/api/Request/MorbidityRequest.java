package api.Request;

import api.Payload.LoginPayload;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MorbidityRequest extends CommonUtils {

	static String baseUrl;
	private static String token;
	public Response response;
	static int statusCode;
	static RequestSpecification request;

	static LoginRequestPojo loginRequestPojo = new LoginRequestPojo();

	public MorbidityRequest(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Response dieticianLoginRequest() {

		loginRequestPojo = LoginPayload.dieticianLogin();
		response = RestAssured.given()

				.baseUri(baseURI).contentType(ContentType.JSON).body(loginRequestPojo).log().all()
				.post(endpoints.getString("login"));

		token = response.jsonPath().getString("token");
		System.out.println("Token :" + token);
		if (token == null)
			throw new RuntimeException("Token not generated!!");
		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);

		return response;

	}

	public Response getAllMorbidities() {

		response = getAPIUsingToken(token, endpoints.getString("morbidity"));

		return response;
	}
	
	public void validateJsonResponseSchema(String endpointType) {
		String schemaPath = getSchemaPath(endpointType);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
	}
	private String getSchemaPath(String endpointType) {
		switch (endpointType) {
		case "AllMorbidities":
			return paths.getString("schema.allMorbidities");
		case "TestName":
			return paths.getString("schema.morbidityTestName");
		
		default:
			throw new IllegalArgumentException("Unsupported endpoint type for schema validation: " + endpointType);
		}
	}


	public Response postMorbidityInvalidMethod() {

		response = RestAssured.given()

				.baseUri(baseURI).contentType(ContentType.JSON).body(loginRequestPojo)
				.header("Authorization", "Bearer " + token)
				.log().all()
				.post(endpoints.getString("morbidity"));

		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);
		return response;
	}

	public Response postMorbidityByNameInvalidMethod() {


		String morbidityByName = endpoints.getString("morbidityByTestName");
		String testName = endpoints.getString("testName");
		String morbidityByNameEndpoint = morbidityByName.replace("{morbidityName}", testName);
		response = RestAssured.given()

				.baseUri(baseURI).contentType(ContentType.JSON).body(loginRequestPojo)
				.header("Authorization", "Bearer " + token)
				.log().all()
				.post(morbidityByNameEndpoint);

		System.out.println("Status Code :" + response.getStatusCode());
		return response;
	}

	public Response getMorbidityInvalidEndpoint() {

		String morbidityInvalidEndpoint = endpoints.getString("invalidEndpoint");

		response = getAPIUsingToken(token, morbidityInvalidEndpoint);
		System.out.println("Status Code :" + response.getStatusCode());
		
		return response;
	}
	
	public Response getMorbidityByTestNameInvalidEndpoint() {

		String morbiditybyTestName = endpoints.getString("morbidityByTestName");
		String morbidityInvalidEndpoint = endpoints.getString("invalidEndpoint");
		String morbidityByNameEndpoint = morbiditybyTestName.replace("{morbidityName}", morbidityInvalidEndpoint);
		

		response = getAPIUsingToken(token, morbidityByNameEndpoint);
		System.out.println("Status Code :" + response.getStatusCode());
		return response;
	}
	
	public Response getMorbidityInvalidTestName() {

		String morbidityInvalidTestName = endpoints.getString("invalidTestName");

		response = getAPIUsingToken(token, morbidityInvalidTestName);
		
		return response;
	}
	public Response getMorbidityNoAuthToken() {

		RestAssured.baseURI = baseURI;
		request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("Authorization", "No Auth");
		request.log().all();
		response = request.get(endpoints.getString("morbidity"));
		
		System.out.println("Status Code :" + response.getStatusCode());

		return response;
	}
	public Response getMorbidityTestNameNoAuthToken() {

		RestAssured.baseURI = baseURI;
		request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("Authorization", "No Auth");
		String morbidityByName = endpoints.getString("morbidityByTestName");
		String morbidityByNameEndpoint = morbidityByName.replace("{morbidityName}", "Fasting Glucose");
		request.log().all();
		response = request.get(morbidityByNameEndpoint);
		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);

		return response;
	}

	public Response getMorbidityByTestName() {

		String morbidityByName = endpoints.getString("morbidityByTestName");
		String testName = endpoints.getString("testName");
		String morbidityByNameEndpoint = morbidityByName.replace("{morbidityName}", testName);

		response = getAPIUsingToken(token, morbidityByNameEndpoint);
		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);
		return response;
	}

	public Response getAPIUsingToken(String token, String endPoint) {
		response = RestAssured.given()

				.baseUri(endpoints.getString("baseUrl")).contentType(ContentType.JSON)
				.header("Authorization", "Bearer " + token)
				.log().all()
				.get(endPoint);

		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);
		return response;
	}
	
	public Response patientLoginRequest() {

		loginRequestPojo = LoginPayload.patientLogin();
		response = RestAssured.given()

				.baseUri(baseURI)
				.contentType(ContentType.JSON)
				.body(loginRequestPojo)
				.log().all()
				.post(endpoints.getString("login"));

		token = response.jsonPath().getString("token");
		System.out.println("Token :" + token);
		if (token == null)
			throw new RuntimeException("Token not generated!!");
		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);

		return response;
	}
	
	public Response adminLoginRequest() {

		loginRequestPojo = LoginPayload.adminLogin();
		response = RestAssured.given()

				.baseUri(baseURI)
				.contentType(ContentType.JSON)
				.body(loginRequestPojo)
				.log().all()
				.post(endpoints.getString("login"));

		token = response.jsonPath().getString("token");
		System.out.println("Token :" + token);
		if (token == null)
			throw new RuntimeException("Token not generated!!");
		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);

		return response;

	}

}
