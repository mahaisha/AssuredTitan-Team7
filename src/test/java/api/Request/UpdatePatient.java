package api.Request;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import api.Payload.AdditionalPatientPayload;
import api.Payload.PatientPayload;
import api.Pojo.AdditionalPatientPojo;
import api.Pojo.PatientPojo;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdatePatient extends CommonUtils {

	int responseCode;
	private Iterator<PatientPojo> patientIterator;
	String patientDetails;
	int patientId;
	String patientInfo;
	String vitals;
	String patientFileId;
	String patientEmail;
	static String pdfPath;
	HashMap<String, Object> responseDetails = new HashMap<>();
	List<Integer> statusCodeList = new ArrayList<>();
	List<String> requestValues = new ArrayList<>();
	private static List<String> fileIds;
	String baseUri;
	static String updateSchema;
	private static String token;
	PatientPojo patientPojo = new PatientPojo();
	UserLoginRequest login = new UserLoginRequest();

	private String endPoint;
	private RequestSpecification request;
	private Response response;

	private static final Logger logger = LogManager.getLogger(PatientReq.class);

	public Response updatePatientReq() throws InvalidFormatException, IOException, ParseException {

		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
		
			// Before sending the request
			System.out.println("Patient Details JSON: " + patientDetails);

			// Logging the exact date being sent
			System.out.println("Formatted Date of Birth: " + patientPojo.getDateOfBirth());

			// Logging the exact data being sent
			logger.info("Sending request to create patient with the following details:");
			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
		
			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.put("/patient"+ "/" + CommonUtils.getPatientID());

			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body

			if (response.getStatusCode() == 200) {
				patientEmail = response.path("Email");
				CommonUtils.setpatientEmail(patientEmail);
				}

			System.out.println("response " + response.prettyPrint());
		
	}
		return response;
	}
	public Response updatePatientReqWithMandatoryData() throws InvalidFormatException, IOException, ParseException {
		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
		
			System.out.println("Patient Details JSON: " + patientDetails);

			// Logging the exact date being sent
			System.out.println("Formatted Date of Birth: " + patientPojo.getDateOfBirth());

			// Logging the exact data being sent
			logger.info("Sending request to create patient with the following details:");
			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					// .put(endpoints.getString("patientEndpoint") + CommonUtils.getPatientID());
					.put("/patient"+"/"+ CommonUtils.getPatientID());

			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body

			if (response.getStatusCode() == 200) {
				patientEmail = response.path("Email");
				CommonUtils.setpatientEmail(patientEmail);
			}
			System.out.println("response " + response.prettyPrint());
		  }
		return response;
	}

	public static File getPdf() {
		pdfPath = paths.getString("hypoThyroidReport");
		return new File(pdfPath);
	}

	public Response updatePatientWithAddFile() throws IOException, ParseException {
		
Iterator<AdditionalPatientPojo> patientIterator = AdditionalPatientPayload.getadditionalDetails();
	    
	    while (patientIterator.hasNext()) {
	        AdditionalPatientPojo patientPojo = patientIterator.next();

			// Before sending the request
			System.out.println("Patient Details JSON: " + patientDetails);

			// Logging the exact data being sent
			logger.info("Sending request to create patient with the following details:");
			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), "application/pdf")
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.put(MessageFormat.format(endpoints.getString("patientUpdate"), 431));
			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body

			if (response.getStatusCode() == 201) {

				patientId = response.path("patientId");
				CommonUtils.setPatientID(patientId);
				patientFileId = response.path("fileId");
				CommonUtils.setFileID(patientFileId);
				patientEmail = response.path("Email");
				CommonUtils.setpatientEmail(patientEmail);
			}
			
			System.out.println("response " + response.prettyPrint());
	    }
		return response;
	}

	public Response validateUpdateSchema() {
		updateSchema = paths.getString("updatePatientSchema");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(updateSchema));
		return response;
	}

	public Response updateNoAuthPatientReq() throws IOException, ParseException {

		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
			// Before sending the request
			System.out.println("Patient Details JSON: " + patientDetails);

			logger.info("Patient Details: " + patientPojo);
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl")).headers("Authorization", "NoAuth ")
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.put(endpoints.getString("patientEndpoint"));

			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body
		  }
		return response;
	}

	public Response updatePatientReqWithAdminToken() throws IOException, ParseException {
		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
			System.out.println("Patient Details JSON: " + patientDetails);

			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getAdminToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getAdminToken())
					.contentType(paths.getString("conType"))
					.pathParam("patientId", CommonUtils.getPatientID())
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type")).log().all()
					.put(endpoints.getString("patientUpdate"));
			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body
		}
		return response;
	}

	public Response updatePatientReqWithPatientToken() throws IOException, ParseException {
		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
			// Before sending the request
			System.out.println("Patient Details JSON: " + patientDetails);

			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getPatientToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getPatientToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.put("/patient"+"/"+CommonUtils.getPatientID());
			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body
		  }
		return response;
	}

	public Response updatePatientWithOnlyAdditonalDetails() throws IOException, ParseException {
Iterator<AdditionalPatientPojo> patientIterator = AdditionalPatientPayload.getadditionalDetails();
	    
	    while (patientIterator.hasNext()) {
	        AdditionalPatientPojo patientPojo = patientIterator.next();
		
			System.out.println("Patient Details JSON: " + patientDetails);

			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("vitals", patientPojo, paths.getString("content.type"))
					.log().all()
					.put("/patient"+"/"+CommonUtils.getPatientID());
			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body

			if (response.getStatusCode() == 200) {
				CommonUtils.setFileID(patientFileId);
				patientEmail = response.path("Email");
			}		
	}
		return response;
	}
	

	public Response updatePatientReqwithPostReq() throws IOException, ParseException {

		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
			// Before sending the request
			System.out.println("Patient Details JSON: " + patientDetails);

			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type")).log().all()
					.post("/patient" +"/"+CommonUtils.getPatientID());
			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body

		  }
		return response;
	}

	public Response updatePatientReqwithInvalidPatientId() throws IOException, ParseException {

		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
			// Before sending the request
			System.out.println("Patient Details JSON: " + patientDetails);

			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type")).log().all()
					.put("/patient"+"/"+ 411111111);
			
			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body
		  }
		return response;
	}
	
	public Response updatePatientwithInvalidEndpt() throws IOException, ParseException {

		 patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
			// Before sending the request
			System.out.println("Patient Details JSON: " + patientDetails);

			logger.info("Patient Details: " + patientPojo);
			logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
			logger.info("Base URI: " + endpoints.getString("baseUrl"));
			logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			response = RestAssured.given().baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type")).log().all()
					.put("/patients"+"/"+ CommonUtils.getPatientID());
			
			// Logging the entire response, including the body
			String responseBody = response.getBody().asString(); // Capture the response body as a string
			logger.info("Response Status Code: " + response.getStatusCode());
			logger.info("Response Headers: " + response.getHeaders().toString());
			logger.info("Response Body: " + responseBody); // Log the full response body

		  }
		return response;
	}
}
