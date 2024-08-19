package api.Request;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.StepDefinitions.PatientSteps;
import api.Utility.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatientReq extends CommonUtils{

	private String endPoint;
	//private String fileId = "66beb450a956ef1c5667388f";
	//private int patientId = 84;
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

	public void determineEndpoint(String endpointType,int patientId, String fileId) {
		switch (endpointType) {
		case "AllPatients":
			this.endPoint = endpoints.getString("getAllPatients");
			break;
		case "PatientID":
			this.endPoint = endpoints.getString("getPatientMorbidity") + patientId;
			break;
		case "FileID":
			this.endPoint = endpoints.getString("getPatientByFileId") + fileId;
			break;
		case "DeleteEndpoint":
			this.endPoint = endpoints.getString("deletePatient") + patientId;
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
	        byte[] pdfBytes = response.asByteArray();
	        try (FileOutputStream fos = new FileOutputStream(paths.getString("pdf.file"))) {
	            fos.write(pdfBytes);
	            logger.info("PDF saved successfully at " + paths.getString("pdf.file"));
	        }
	    }

}
