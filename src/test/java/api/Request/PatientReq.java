package api.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Payload.LoginPayload;
import api.Payload.PatientPayload;
import api.Pojo.PatientPojo;
import api.Utility.CommonUtils;
import api.Utility.LoggerLoad;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatientReq extends CommonUtils{
	
	public Response response;
	int responseCode;
	private Iterator<PatientPojo> patientIterator;
	String patientDetails;
	int	patientId; 
	String patientInfo;
	String	patientFileId;
	String	patientEmail;
	static String pdfPath;
	HashMap<String ,Object> responseDetails = new HashMap<>();
	List<Integer> statusCodeList = new ArrayList<>(); 
	List<String> requestValues = new ArrayList<>();
	private static List<String> fileIds;
	String baseUri;
	private static String token;
	
	private static final Logger logger = LogManager.getLogger("PatientReq.class");
	PatientPojo patientPojo = new PatientPojo();
	UserLoginRequest login = new UserLoginRequest();
	
	public void setBaseUri() {
		baseUri = endpoints.getString("baseUrl");
	}
	
	public static File getPdf() {
		pdfPath = paths.getString("hyperThyroidReport");
		return new File(pdfPath);
			}
	
	
	public void createPatientReq(String endpointType, String contentType) throws InvalidFormatException, IOException, ParseException {
			
		  patientIterator = PatientPayload.getDataFromExcel();
		  while (patientIterator.hasNext()) {
				patientPojo = patientIterator.next();
				
		    ObjectMapper mapper = new ObjectMapper();
			 patientDetails = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patientPojo);

			// String token = login.getdieticianToken();			 
			// Before sending the request
			// System.out.println("Patient Details JSON: " + patientDetails);
						 
			 // Logging the exact data being sent
		        logger.info("Sending request to create patient with the following details:");
		        logger.info("Patient Details: " + patientPojo);
		        logger.info("Authorization Token: Bearer " + token);
		        logger.info("Base URI: " + endpoints.getString("baseUrl"));
		       logger.info("Endpoint: " + endpoints.getString("patient"));
		      // String endpoint = endpointType.equals("validEndpoint") ? "/validEndpointPath" : "/invalidEndpointPath";
		      // if (endpointType.)
		       String endpoint = endpointType.equals(endpoints.getString("validEndpoint")) ? endpoints.getString("validEndpoint") : endpoints.getString("invalidEndpoint");
		       
		       token = response.jsonPath().getString("token");
	            System.out.println("Token :" +token);
	            if (token == null) 

	                
	            	
	          response = RestAssured.given()
	                .baseUri(baseUri)
	                .contentType(contentType)
	                .header("Authorization", "Bearer " + token);
	                .multiPart("file", getPdf(), "application/pdf")
	                .multiPart("patientInfo", patientPojo, "application/json")
	                .log().all()
	               .post(endpoint);
	          
	           
	            

	            LoggerLoad.info("Response: " + response.getBody().asString());

	            // Logging the entire response, including the body
	            String responseBody = response.getBody().asString(); // Capture the response body as a string
	            logger.info("Response Status Code: " + response.getStatusCode());
	            logger.info("Response Headers: " + response.getHeaders().toString());
	            logger.info("Response Body: " + responseBody); // Log the full response body
	        	if(response.getStatusCode()==201) {
				      
	    			patientId = response.path("patientId"); 
	    			patientFileId = response.path("fileId");
	    			patientEmail = response.path("Email");
	    	        responseCode = response.getStatusCode();
	    		 			}
	        }
	    }

	    public Response getResponse() {
	        return response;
	    
	

		     

//			 		 			response = RestAssured.given()
//		    		 			.baseUri(endpoints.getString("baseUrl"))
//		    					.headers("Authorization", "Bearer " + token)
//		    					.contentType("multipart/form-data")
//		    					.multiPart("file", getPdf(),"application/pdf")
//		    					.multiPart("patientInfo", patientPojo,"application/json")
//		    					.log().all()
//		    					.post(endpoints.getString("patient"));
//		 			
		 		    // Logging the entire response, including the body
//		 	        String responseBody = response.getBody().asString(); // Capture the response body as a string
//		 	        logger.info("Response Status Code: " + response.getStatusCode());
//		 	        logger.info("Response Headers: " + response.getHeaders().toString());
//		 	        logger.info("Response Body: " + responseBody); // Log the full response body
		 	        
//		 			 System.out.println("Status Code: " + response.getStatusCode());
//		 	        System.out.println("Response Body: " + response.getBody().asString());
		 	    		
//		 		if(response.getStatusCode()==201) {
//			      
//			patientId = response.path("patientId"); 
//			patientFileId = response.path("fileId");
//			patientEmail = response.path("Email");
//	        responseCode = response.getStatusCode();
//		 			}
//		 			statusCodeList.add(response.getStatusCode()); // Collects the status codes from each API request.
//					requestValues.add(patientDetails); //Collects the JSON representations of the patient details sent in each request.
//					
//					logger.info("response "+response.prettyPrint());
//					System.out.println("response "+response.prettyPrint());
//				}
//				responseDetails.put("StatusCode",statusCodeList); //Stores the list of status codes in the responseDetails map.
//				responseDetails.put("RequestGiven", requestValues); // Stores the list of JSON strings representing the patient details in the responseDetails map.
//				        
//	}
}
}
		
	  

