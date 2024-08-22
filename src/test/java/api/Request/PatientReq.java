package api.Request;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatientReq extends CommonUtils{
	
	int responseCode;
	private Iterator<PatientPojo> patientIterator;
	String patientDetails;
	int	patientId; 
	String patientInfo;
	String vitals;
	String	patientFileId;
	String	patientEmail;
	static String pdfPath;
	HashMap<String ,Object> responseDetails = new HashMap<>();
	List<Integer> statusCodeList = new ArrayList<>(); 
	List<String> requestValues = new ArrayList<>();
	private static List<String> fileIds;
	String baseUri;
	static String createSchema;
	private static String token;
	byte[] pdfBytes ;
	String extractedText ;
	 PatientPojo patientPojo = new PatientPojo();
	UserLoginRequest login = new UserLoginRequest();
	private static String invalidFileId = "1";
	private static int invalidPatientId = 999999999;

	
	private String endPoint;
	private RequestSpecification request;
	private Response response;
	private static final Logger logger = LogManager.getLogger(PatientReq.class);


	public RequestSpecification buildRequest(String user) {
		RestAssured.baseURI = endpoints.getString("baseUrl");
		switch (user) {
		case "Dietician":
			request = RestAssured.given()
			.header("Authorization", "Bearer " + CommonUtils.getDieticianToken())
			.contentType(paths.getString("content.type"));
			break;
		case "Patient":
			request = RestAssured.given()
			.header("Authorization", "Bearer " + CommonUtils.getPatientToken())
			.contentType(paths.getString("content.type"));
			break;
		case "Admin":
			request = RestAssured.given()
			.header("Authorization", "Bearer " + CommonUtils.getAdminToken())
			.contentType(paths.getString("content.type"));
			break;
		case "NoAuth":
			request = RestAssured.given().contentType(paths.getString("content.type"));
			break;
		default:
			throw new IllegalArgumentException("Unsupported user type: " + user);
		}
		return request;
	}

	public void determineEndpoint(String endpointType) {
		switch (endpointType) {
		case "AllPatients":
			this.endPoint = endpoints.getString("getAllPatients");
			break;
		case "PatientID":
			this.endPoint = endpoints.getString("getPatientMorbidity") + CommonUtils.getPatientID();
			break;
		case "FileID":
			this.endPoint = endpoints.getString("getPatientByFileId") + CommonUtils.getFileID();
			System.out.println("FileId Endpoint: " + endPoint );
			break;
		case "DeleteEndpoint":
			this.endPoint = endpoints.getString("deletePatient") +  CommonUtils.getPatientID();
			break;
		default:
			throw new IllegalArgumentException("Unsupported endpoint type: " + endpointType);
		}
	}
	public Response sendRequest(String method) {
		switch (method.toUpperCase()) {
		case "GET":
			response = request.when().get(this.endPoint);
			break;
		case "DELETE":
			response = request.when().delete(this.endPoint);
			break;
		case "POST":
			response = request.when().post(this.endPoint);
			break;
		case "PUT":
			response = request.when().put(this.endPoint);
			break;
		default:
			throw new IllegalArgumentException("Unsupported method: " + method);
		}
		return response;
	}

	public void invalidEndpoint() {

		this.endPoint =  endpoints.getString("invalidEndPont");
	}

	public void validateResponseSchema(String endpointType) {
		String schemaPath = getSchemaPath(endpointType);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
	}

	private String getSchemaPath(String endpointType) {
		switch (endpointType) {
		case "AllPatients":
			return paths.getString("schema.allPatients");
		case "PatientID":
			return paths.getString("schema.patientID");
		case "FileID":
			return paths.getString("schema.patientID");
		default:
			throw new IllegalArgumentException("Unsupported endpoint type for schema validation: " + endpointType);
		}
	}
	 public void savePdfResponse() throws IOException {
	        pdfBytes = response.asByteArray();
	        try (FileOutputStream fos = new FileOutputStream(paths.getString("pdf.file"))) {
	            fos.write(pdfBytes);
	            logger.info("PDF saved successfully at " + paths.getString("pdf.file"));
	        }
	    }

	
	public void setBaseUri() {
		baseUri = endpoints.getString("baseUrl");
	}
	
	public static File getPdf() {
		pdfPath = paths.getString("hyperThyroidReport");
		return new File(pdfPath);
			}
	    
	public Response createPatientReq() throws InvalidFormatException, IOException, ParseException {
		
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
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		        logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			 
		 			response = RestAssured.given()
		 			.baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType("multipart/form-data")
					.multiPart("file", getPdf(),"application/pdf")
					.multiPart("patientInfo", patientPojo,"application/json")
					.log().all()
					.post(endpoints.getString("patientEndpoint"));
		 			
		 		    // Logging the entire response, including the body
		 	        String responseBody = response.getBody().asString(); // Capture the response body as a string
		 	        logger.info("Response Status Code: " + response.getStatusCode());
		 	        logger.info("Response Headers: " + response.getHeaders().toString());
		 	        logger.info("Response Body: " + responseBody); // Log the full response body
		 	        
		 		if(response.getStatusCode()==201) {
			      
			patientId = response.path("patientId");
			CommonUtils.setPatientID(patientId);
			//patientFileId = response.path("fileId");
			//System.out.println("Patient File Id : + "+ patientFileId);
			patientEmail = response.path("Email");
			CommonUtils.setpatientEmail(patientEmail);
			JsonPath jsonPath = response.jsonPath();

	        // Extract the map from the 'FileMorbidity' object
	        Map<String, Object> fileMorbidityMap = jsonPath.getMap("FileMorbidity");

	        // Get the first key from the map
	        patientFileId = fileMorbidityMap.keySet().iterator().next();

	        System.out.println("patientFileId key: " + patientFileId);
			CommonUtils.setFileID(patientFileId);

	        responseCode = response.getStatusCode();
		 			}
		 			statusCodeList.add(response.getStatusCode()); // Collects the status codes from each API request.
					requestValues.add(patientDetails); //Collects the JSON representations of the patient details sent in each request.
					
					System.out.println("response "+response.prettyPrint());
				}
				responseDetails.put("StatusCode",statusCodeList); //Stores the list of status codes in the responseDetails map.
				responseDetails.put("RequestGiven", requestValues); // Stores the list of JSON strings representing the patient details in the responseDetails map.
return response;
	}
	
	public Response validateCreatechema() {
		createSchema= paths.getString("createPatientSchema");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(createSchema));
		return response;
	}
	
	public Response createNoAuthPatientReq() throws IOException, ParseException  {
		
		  patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
				 
			// Before sending the request
			 System.out.println("Patient Details JSON: " + patientDetails);
			 
			    logger.info("Patient Details: " + patientPojo);
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		        logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			 
		 			response = RestAssured.given()
		 			.baseUri(endpoints.getString("baseUrl"))
					.headers("Authorization", "NoAuth "  )
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.post(endpoints.getString("patientEndpoint"));
		 			
		 		    // Logging the entire response, including the body
		 	        String responseBody = response.getBody().asString(); // Capture the response body as a string
		 	        logger.info("Response Status Code: " + response.getStatusCode());
		 	        logger.info("Response Headers: " + response.getHeaders().toString());
		 	        logger.info("Response Body: " + responseBody); // Log the full response body
		 	        
		 		if(response.getStatusCode()==201) {
			      
			patientId = response.path("patientId");
			CommonUtils.setPatientID(patientId);
			patientFileId = response.path("fileId");
			CommonUtils.setFileID(patientFileId);
			patientEmail = response.path("Email");
			CommonUtils.setpatientEmail(patientEmail);
	        responseCode = response.getStatusCode();
		 			}
		 				}
		return response;
}
	
	public Response createPatientReqWithAdminToken() throws IOException, ParseException  {
		
		  patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
				 
			// Before sending the request
			 System.out.println("Patient Details JSON: " + patientDetails);
			 
			    logger.info("Patient Details: " + patientPojo);
			    logger.info("Authorization Token: Bearer " + CommonUtils.getAdminToken());
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		        logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			 
		 			response = RestAssured.given()
		 			.baseUri(endpoints.getString("baseUrl"))
		 			.headers("Authorization", "Bearer " + CommonUtils.getAdminToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.post(endpoints.getString("patientEndpoint"));
		 			
		 		    // Logging the entire response, including the body
		 	        String responseBody = response.getBody().asString(); // Capture the response body as a string
		 	        logger.info("Response Status Code: " + response.getStatusCode());
		 	        logger.info("Response Headers: " + response.getHeaders().toString());
		 	        logger.info("Response Body: " + responseBody); // Log the full response body
		 	        
		 		if(response.getStatusCode()==201) {
			      
			patientId = response.path("patientId");
			CommonUtils.setPatientID(patientId);
			patientFileId = response.path("fileId");
			CommonUtils.setFileID(patientFileId);
			patientEmail = response.path("Email");
			CommonUtils.setpatientEmail(patientEmail);
	        responseCode = response.getStatusCode();
		 			}
		 				}
		return response;
}
	
	
	public Response createPatientReqWithPatientToken() throws IOException, ParseException {
		
		  patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
				 
			// Before sending the request
			 System.out.println("Patient Details JSON: " + patientDetails);
			 
			    logger.info("Patient Details: " + patientPojo);
			    logger.info("Authorization Token: Bearer " + CommonUtils.getPatientToken());
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		        logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));

			 
		 			response = RestAssured.given()
		 			.baseUri(endpoints.getString("baseUrl"))
		 			.headers("Authorization", "Bearer " + CommonUtils.getPatientToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.post(endpoints.getString("patientEndpoint"));
		 			
		 		    // Logging the entire response, including the body
		 	        String responseBody = response.getBody().asString(); // Capture the response body as a string
		 	        logger.info("Response Status Code: " + response.getStatusCode());
		 	        logger.info("Response Headers: " + response.getHeaders().toString());
		 	        logger.info("Response Body: " + responseBody); // Log the full response body
		 	        
		 		if(response.getStatusCode()==201) {
			      
			patientId = response.path("patientId");
			CommonUtils.setPatientID(patientId);
			patientFileId = response.path("fileId");
			CommonUtils.setFileID(patientFileId);
			patientEmail = response.path("Email");
	        responseCode = response.getStatusCode();
		 			}
		 				}
		return response;
}
		
	public Response createPatientWithOnlyAdditonalDetails() throws IOException, ParseException {
	    Response response = null;
	    // Get the iterator for AdditionalPatientPojo data
	    Iterator<AdditionalPatientPojo> patientIterator = AdditionalPatientPayload.getadditionalDetails();
	   
	    while (patientIterator.hasNext()) {
	        AdditionalPatientPojo patientPojo = patientIterator.next();
	       
	            // Logging the details
	        logger.info("Preparing to send request to create patient with the following details:");
	        logger.info("Patient Details: " + patientPojo);
	        logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
	        logger.info("Base URI: " + endpoints.getString("baseUrl"));
	        logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));
	        // Send the POST request
	        response = RestAssured.given()
	            .baseUri(endpoints.getString("baseUrl"))
	            .headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
	            .contentType(paths.getString("conType"))
	            .multiPart("file", getPdf(), paths.getString("pdfConType"))
	            .multiPart("vitals", patientPojo, paths.getString("content.type"))
	            .log().all()
	            .post(endpoints.getString("patientEndpoint"));
	        // Check for a successful response and handle it
	        if (response != null) {
	            String responseBody = response.getBody().asString();
	            logger.info("Response Status Code: " + response.getStatusCode());
	            logger.info("Response Headers: " + response.getHeaders().toString());
	            logger.info("Response Body: " + responseBody);
	            if (response.getStatusCode() == 201) {
	                // Handle successful creation response
	                int patientId = response.path("patientId");
	                CommonUtils.setPatientID(patientId);
	                String patientFileId = response.path("fileId");
	                CommonUtils.setFileID(patientFileId);
	                String patientEmail = response.path("Email");
	            } else {
	                logger.warn("Unexpected status code: " + response.getStatusCode());
	            }
	        } else {
	            logger.error("Received null response from the server.");
	        }
	    }
	    return response;
	}
	public Response putCreatePatientReq() throws IOException, ParseException {
		
		  patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
				
			// Before sending the request
			 System.out.println("Patient Details JSON: " + patientDetails);
			
			    logger.info("Patient Details: " + patientPojo);
			    logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		        logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));
			
		 			response = RestAssured.given()
		 			.baseUri(endpoints.getString("baseUrl"))
		 			.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
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
		 	       
		 		if(response.getStatusCode()==201) {
			     
			patientId = response.path("patientId");
			CommonUtils.setPatientID(patientId);
			patientFileId = response.path("fileId");
			CommonUtils.setFileID(patientFileId);
			patientEmail = response.path("Email");
	        responseCode = response.getStatusCode();
		 			}
		 				}
		return response;
}
	
	public Response createPatientReqwithInvalidEndpoint() throws IOException, ParseException {
		
		  patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
				
			// Before sending the request
			 System.out.println("Patient Details JSON: " + patientDetails);
			
			    logger.info("Patient Details: " + patientPojo);
			    logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		        logger.info("Endpoint: " + endpoints.getString("patientInvalidEndpoint"));
			
		 			response = RestAssured.given()
		 			.baseUri(endpoints.getString("baseUrl"))
		 			.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("conType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.post(endpoints.getString("patientInvalidEndpoint"));
		 			
		 		    // Logging the entire response, including the body
		 	        String responseBody = response.getBody().asString(); // Capture the response body as a string
		 	        logger.info("Response Status Code: " + response.getStatusCode());
		 	        logger.info("Response Headers: " + response.getHeaders().toString());
		 	        logger.info("Response Body: " + responseBody); // Log the full response body
		 	       
		 		if(response.getStatusCode()==201) {
			     
			patientId = response.path("patientId");
			CommonUtils.setPatientID(patientId);
			patientFileId = response.path("fileId");
			CommonUtils.setFileID(patientFileId);
			patientEmail = response.path("Email");
	        responseCode = response.getStatusCode();
		 			}
		 				}
		return response;
}
	public Response createPatientReqwithInvalidContenType() throws IOException, ParseException {
		
		  patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
				
			// Before sending the request
			 System.out.println("Patient Details JSON: " + patientDetails);
			
			    logger.info("Patient Details: " + patientPojo);
			    logger.info("Authorization Token: Bearer " + CommonUtils.getDieticianToken());
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		        logger.info("Endpoint: " + endpoints.getString("patientEndpoint"));
			
		 			response = RestAssured.given()
		 			.baseUri(endpoints.getString("baseUrl"))
		 			.headers("Authorization", "Bearer " + CommonUtils.getDieticianToken())
					.contentType(paths.getString("invalidConType"))
					.multiPart("file", getPdf(), paths.getString("pdfConType"))
					.multiPart("patientInfo", patientPojo, paths.getString("content.type"))
					.log().all()
					.post(endpoints.getString("patientEndpoint"));
		 			
		 		    // Logging the entire response, including the body
		 	        String responseBody = response.getBody().asString(); // Capture the response body as a string
		 	        logger.info("Response Status Code: " + response.getStatusCode());
		 	        logger.info("Response Headers: " + response.getHeaders().toString());
		 	        logger.info("Response Body: " + responseBody); // Log the full response body
		 	       
		 		if(response.getStatusCode()==201) {
			     
			patientId = response.path("patientId");
			CommonUtils.setPatientID(patientId);
			patientFileId = response.path("fileId");
			CommonUtils.setFileID(patientFileId);
			patientEmail = response.path("Email");
	        responseCode = response.getStatusCode();
		 			}
		 				}
		return response;
}

	public void determineInvalidEndpoint(String endpointType) {
		// TODO Auto-generated method stub
		switch (endpointType) {
		case "AllPatients":
			this.endPoint = endpoints.getString("getAllPatients");
			break;
		case "PatientID":
			this.endPoint = endpoints.getString("getPatientMorbidity") + invalidPatientId;
			break;
		case "FileID":
			this.endPoint = endpoints.getString("getPatientByFileId") + invalidFileId;
			break;
		case "DeleteEndpoint":
			this.endPoint = endpoints.getString("deletePatient") + invalidPatientId;
			break;
		default:
			throw new IllegalArgumentException("Unsupported endpoint type: " + endpointType);
		}
	}
	
	}



	
	  