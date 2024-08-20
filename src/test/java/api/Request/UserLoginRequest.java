package api.Request;

import api.Payload.LoginPayload;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserLoginRequest extends CommonUtils {

	public static String adminToken;
	public static String dieticianToken;
	public static String patientToken;
	public Response response;
	int statusCode; 
	LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
	
	//Admin Valid login request
	public Response adminLoginRequest() {
		
		loginRequestPojo=LoginPayload.adminLogin();
		
		 response = RestAssured.given()				 
				.baseUri(endpoints.getString("baseUrl"))
				.contentType(ContentType.JSON)
				.body(loginRequestPojo)
				.post(endpoints.getString("login"));		
		 
		 System.out.println(response.contentType());
		 System.out.println("Response: " + response.asString());
		
		 adminToken = response.jsonPath().getString("token");		
		setAdminToken(adminToken);
		statusCode=response.getStatusCode();
		return response;
	}
	
	public Response dieticianLoginRequest() {

		loginRequestPojo = LoginPayload.dieticianLogin();
		response = RestAssured.given()

				.baseUri(baseURI)
				.contentType(ContentType.JSON)
				.body(loginRequestPojo)
				.log().all()
				.post(endpoints.getString("login"));

		dieticianToken = response.jsonPath().getString("token");		
		setDieticianToken(dieticianToken);
		System.out.println("Dietician Token :" + dieticianToken);
		if (dieticianToken == null)
			throw new RuntimeException("Dietician Token not generated!!");
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

		patientToken = response.jsonPath().getString("token");		
		setPatientToken(patientToken);
		System.out.println("Token :" + patientToken);
		if (patientToken == null)
			throw new RuntimeException("Patient Token not generated!!");
		statusCode = response.getStatusCode();
		System.out.println("Status Code :" + statusCode);

		return response;
	}
	//admin logout
	public Response adminLogoutRequest(String adminToken) {
		int statusCode;

		response = RestAssured.given()
				.baseUri(endpoints.getString("baseUrl"))
				.contentType(ContentType.JSON)
				.headers("Authorization","Bearer "+adminToken)
				.get(endpoints.getString("logout"));
		
		statusCode = response.getStatusCode();
		return response;
		
	}
	
	//Admin Invalid login request
		public Response adminInvalidLoginRequest() {
			 response = RestAssured.given()
					.baseUri(endpoints.getString("baseUrl"))
					.contentType(ContentType.JSON)
					.body("{\"password\":\"wrong.com\",\"userLoginEmail\":\"123\"}")
					.post(endpoints.getString("login"));
			 int statusCode = response.getStatusCode();
			 return response;
		}
		
		//Valid body with invalid method 
		public Response adminLoginValidBodyInvalidMethod() {
			loginRequestPojo=LoginPayload.adminLogin();
			response = RestAssured.given()
					.baseUri(endpoints.getString("baseUrl"))
					.contentType(ContentType.JSON)
					.body(loginRequestPojo)
					.get(endpoints.getString("login"));
			return response;
			
		}
		//Valid request body with Invalid content type
		public Response adminLoginInvalidContentType() {
			loginRequestPojo=LoginPayload.adminLogin();
			response = RestAssured.given()
					.baseUri(endpoints.getString("baseUrl"))
					.body(loginRequestPojo)
					.post(endpoints.getString("login"));
			return response;
		}
}