package api.Request;

import api.Payload.LoginPayload;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserLoginRequest extends CommonUtils {

	public String token;
	public Response response;
	LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
	
	//Admin Valid login request
	public Response adminLoginRequest() {
		int statusCode; 
		loginRequestPojo=LoginPayload.adminLogin();
		
		 response = RestAssured.given()				 
				.baseUri(endpoints.getString("baseUrl"))
				.contentType(ContentType.JSON)
				.body(loginRequestPojo)
				.post(endpoints.getString("login"));		
		 
		 System.out.println(response.contentType());
		 System.out.println("Response: " + response.asString());
		
		 token = response.jsonPath().getString("token");		
		
		statusCode=response.getStatusCode();
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
		//Valid dietican login
}