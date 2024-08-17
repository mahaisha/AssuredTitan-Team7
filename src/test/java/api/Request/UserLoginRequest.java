package api.Request;

import api.Payload.LoginPayload;
import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserLoginRequest extends CommonUtils {

	private String token;
	public Response response;
	int statusCode; 
	LoginRequestPojo loginRequestPojo =new LoginRequestPojo();
	
	public Response adminLoginRequest() {
		System.out.println("jhbhjbf");
		loginRequestPojo=LoginPayload.adminLogin();
		 response = RestAssured.given()
				 
				.baseUri(endpoints.getString("baseUrl"))
				.contentType(ContentType.JSON)
				.body(loginRequestPojo)
				.post(endpoints.getString("login"));
				
		 System.out.println(response.prettyPrint());
		token = response.jsonPath().getString("token");
				
		statusCode=response.getStatusCode();
				return response;
	}

	
}
