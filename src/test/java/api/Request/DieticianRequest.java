package api.Request;

import static api.Payload.DieticianPayload.DATE_FORMAT;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.StringUtil;

import api.Pojo.DieticianPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DieticianRequest extends CommonUtils {
	private static final Logger LOGGER = LogManager.getLogger(DieticianRequest.class);

	//private static final String BASE_URI = "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician";
	private static final SimpleDateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'");
	private static Response response;
	
	

	public Response createDietician(String endpoint, Method httpMethod, ContentType contentType, String authToken, DieticianPojo dietician) {
	    try {
	        // Making the request
	        response = RestAssured.given()
	            .baseUri(endpoints.getString("baseUrl"))
	            .contentType(contentType)
	            .header("Authorization", "Bearer " + authToken)
	            .body(dietician)
	            .when()
	            .request(httpMethod, endpoint)
	            .then()
	            .log().all()
	            .extract().response();

	        return response;
	    } catch (Exception e) {
	        LOGGER.error("Failed to create dietician", e);
	        throw e; // Re-throwing the exception to ensure the failure is evident
	    }
	}
	
	public Response putDieticianById(String endpoint, Method httpMethod, ContentType contentType, String authToken, DieticianPojo dietician, String dieticianId) {
		response = RestAssured.given()
        .baseUri(endpoints.getString("baseUrl"))
        .contentType(contentType)
        .header("Authorization", "Bearer " + authToken)
        .body(dietician)
        .when()
        .request(httpMethod, endpoint + dieticianId)
        .then()
        .log().all()
        .extract().response();

    return response;
	}

	public Response getAllDieticians(String endpoint, Method httpMethod, ContentType contentType, String authToken) {
		response = RestAssured.given()
        .baseUri(endpoints.getString("baseUrl"))
        .contentType(contentType)
        .header("Authorization", "Bearer " + authToken)
        .when()
        .request(httpMethod, endpoint)
        .then()
        .log().all()
        .extract().response();

    return response;

	}

	public Response getDieticianById(String endpoint, Method httpMethod, ContentType contentType, String authToken, int dieticianId) {
		response = RestAssured.given()
		        .baseUri(endpoints.getString("baseUrl"))
		        .contentType(contentType)
		        .header("Authorization", "Bearer " + authToken)
		        .when()
		        .request(httpMethod, endpoint+ dieticianId)
		        .then()
		        .log().all()
		        .extract().response();

		    return response;

	}
	
	public Response deleteDieticianById(String endpoint, Method httpMethod, ContentType contentType, String authToken, int dieticianId) {
		
		response = RestAssured.given()
		        .baseUri(endpoints.getString("baseUrl"))
		        .contentType(contentType)
		        .header("Authorization", "Bearer " + authToken)
		        .when()
		        .request(httpMethod, endpoint+ dieticianId)
		        .then()
		        .log().all()
		        .extract().response();

		    return response;
	}
	
	public void validateCreation(DieticianPojo dietician, DieticianPojo response) {
		try {
			assertEquals(response.getFirstName(), dietician.getFirstName());
			assertEquals(response.getLastName(), dietician.getLastName());
			assertEquals(response.getEmail(), dietician.getEmail());
			assertEquals(response.getContactNumber(), dietician.getContactNumber());
			assertEquals(response.getEducation(), dietician.getEducation());
			assertEquals(DATE_FORMAT.parse(dietician.getDateOfBirth()), DATE_FORMAT_2.parse(response.getDateOfBirth()));
			assertEquals(response.getHospitalName(), dietician.getHospitalName());
			assertEquals(response.getHospitalStreet(), dietician.getHospitalStreet());
			assertEquals(response.getHospitalCity(), dietician.getHospitalCity());
			assertEquals(response.getHospitalPincode(), dietician.getHospitalPincode());
			assertTrue(StringUtil.isNotBlank(response.getId()));
			assertTrue(StringUtil.isNotBlank(response.getPassword()));
		} catch (ParseException e) {
			LOGGER.error("Failed to parse DateOfBirth of created Dietician.", e);
			throw new RuntimeException("Failed to parse DateOfBirth of created Dietician.", e);
		}
	}
}
