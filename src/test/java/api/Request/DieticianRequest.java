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
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DieticianRequest {
	private static final Logger LOGGER = LogManager.getLogger(DieticianRequest.class);

	private static final String BASE_URI = "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician";
	private static final SimpleDateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'");

	public DieticianRequest() {
		RestAssured.baseURI = BASE_URI;
	}
	
	public ValidatableResponse createDietician(String endpoint, Method httpMethod, ContentType contentType, String authToken, DieticianPojo dietician) {
		RequestSpecification request = given().log().all()
		.with().body(dietician);
		
		if(StringUtil.isNotBlank(authToken)) {
			request.headers("Authorization", "Bearer " + authToken);
		}
		
		return request.contentType(contentType)
		.accept(contentType)
		.when().request(httpMethod, endpoint)
		.then().log().all();
	}
	
	public ValidatableResponse getAllDieticians(String endpoint, Method httpMethod, ContentType contentType, String authToken) {
		RequestSpecification request = given().log().all();
		
		if(StringUtil.isNotBlank(authToken)) {
			request.headers("Authorization", "Bearer " + authToken);
		}
		
		return request.accept(contentType)
		.when().request(httpMethod, endpoint)
		.then().log().all();
	}

	public ValidatableResponse getDieticianById(String endpoint, Method httpMethod, ContentType contentType, String authToken, int dieticianId) {
		RequestSpecification request = given().log().all();
		
		if(StringUtil.isNotBlank(authToken)) {
			request.headers("Authorization", "Bearer " + authToken);
		}
		
		return request.accept(contentType)
		.when().request(httpMethod, endpoint + dieticianId)
		.then().log().all();
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
