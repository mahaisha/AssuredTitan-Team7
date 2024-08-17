package api.Requests;

import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatientReq extends CommonUtils{

	private String endPoint;
	private String fileId = "66beb450a956ef1c5667388f";
	private int patientId = 84;
	RequestSpecification request;
	Response response;

	public Response getPatient(RequestSpecification request, String user, String method, String endpointType) {

		switch (endpointType) {
		case "AllPatients":
			this.endPoint = endpoints.getString("getAllPatients");
			break;
		case "PatientID":
			this.endPoint = endpoints.getString("getPatientMorbidity")+patientId;  
			break;
		case "FileID":
			this.endPoint = endpoints.getString("getPatientByFileId")+fileId;  
			break;
		case "DeleteEndpoint":
			this.endPoint = "/patient/"+patientId; 
			break;    
		default:
			throw new IllegalArgumentException("Unsupported endpoint type: " + endpointType);
		}
		if (method.equalsIgnoreCase("GET")) {
			System.out.println("Entered Get Request block");
			response = request.given()
					.log().all()
					.when()
					.get(this.endPoint);
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = request.given()
					.log().all()
					.when()
					.delete(this.endPoint);
		}
		return response;
	}

	public RequestSpecification getReqBuilder(String user) {
		RestAssured.baseURI = endpoints.getString("baseUrl"); 
		switch (user) {		
		case "Dietician":
			System.out.println("Dietician Token :"+CommonUtils.getDieticianToken());
			request =
			RestAssured.given().header("Authorization", "Bearer " +CommonUtils.getDieticianToken() ).contentType("application/json"); 
			//System.out.println(request);
			break;
		case "Patient":
			request = RestAssured.given().header("Authorization", "Bearer " +CommonUtils.getPatientToken() ).contentType("application/json");
			break;
		default:
			throw new IllegalArgumentException("Unsupported user type: " + user);
		}
		return request;
	}
	
	

}
