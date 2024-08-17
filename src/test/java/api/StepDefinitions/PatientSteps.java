package api.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import api.Requests.PatientReq;
import api.Utility.CommonUtils;
import org.apache.logging.log4j.Logger;



public class PatientSteps extends CommonUtils {

	private static String adminToken;
	public String dieticianToken ;//= "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmMueHl6QGV4YW1wbGUuY29tIiwiaWF0IjoxNzIzNzgxOTk1LCJleHAiOjE3MjM4MTA3OTV9.KDyWVaepHObLI3ZgZZhSDUgqSahUds4jEN8GHHcRlxyyth3sh_kw8_RtdAGDJDGpA9gU40dPcS8NzUExoYGa7A";
	private static String patientToken;
	private static String patientPassword ="test";
	private static String dieticianPassword = "Inspire45";
	private Response response;
	private static int patientId = 83;
	private static int actualStatusCode;
	private RequestSpecification request;
	private static String endPoint;
	private static String fileId = "66beb450a956ef1c5667388f";
	
	PatientReq patientReq = new PatientReq();
	private static final Logger LOG=LogManager.getLogger(PatientSteps.class);
	@Before
	public void setup() {
		// Set the base URI for Rest Assured
		RestAssured.baseURI = "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician";
	}

	@Given("Admin logs in with valid credentials and receives a Admin token")
	public void admin_logs_in_with_valid_credentials() {
		
		response = RestAssured.given()
				.contentType("application/json")
				.body("{ \"userLoginEmail\": \"Team7.admin@gmail.com\", \"password\": \"test\" }")
				.log().all()  // Log request details
				.post("/login")
				.then()
				.log().status()  // Log response status code
				.log().body()    // Log response body
				.extract().response();

		adminToken = response.jsonPath().getString("token");
		CommonUtils.setAdminToken(adminToken);
		System.out.println("adminToken :" + adminToken);
	}

	@Given("Dietician logs in and receives a Dietician token")
	public void dietician_logs_in_and_receives_a_token() {
		response = RestAssured.given()
				.contentType("application/json")
				.body("{ \"userLoginEmail\": \"abc.xyz@example.com\", \"password\": \"" + dieticianPassword + "\" }")
				.log().all()  // Log request details
				.post("/login")
				.then()
				.log().status()  // Log response status code
				.log().body()    // Log response body
				.extract().response();

		dieticianToken = response.jsonPath().getString("token");
		CommonUtils.setDieticianToken(dieticianToken);
		System.out.println("dieticianToken :" + dieticianToken);
	}
	
	/*
	 * @Given("Dietician creates a Patient") public void
	 * dietician_creates_a_patient() { Response createPatientResponse =
	 * RestAssured.given() .header("Authorization", "Bearer " + dieticianToken)
	 * .contentType("application/json")
	 * .body("{ \"FirstName\": \"Jane\", \"LastName\": \"Doe\", \"ContactNumber\": \"0987654321\", \"Email\": \"janedoe@example.com\", \"Allergy\": \"None\", \"FoodPreference\": \"Vegetarian\", \"CuisineCategory\": \"Indian\", \"DateOfBirth\": \"1990-01-01\" }"
	 * ) .post(
	 * "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician/patient"
	 * ); patientId = createPatientResponse.jsonPath().getInt("patientId");
	 * 
	 * }
	 */

	@Given("Patient logs in receives a Patient token")
	public void dietician_receives_a_patient_token() {
		response = RestAssured.given()
				.contentType("application/json")
				.body("{ \"userLoginEmail\": \"janedoe@example.com\", \"password\": \"test\" }")  // Assuming the password for patient
				.log().all()  // Log request details
				.post("/login")
				.then()
				.log().status()  // Log response status code
				.log().body()    // Log response body
				.extract().response();

		patientToken = response.jsonPath().getString("token");
		CommonUtils.setPatientToken(patientToken);
		System.out.println("patientToken :" + patientToken);
	}

	@Given("{string} create {string} request")
	public void user_creates_a_GET_request(String user, String method) {
		// Setup for different user types ( Dietician, Patient)
		request = patientReq.getReqBuilder(user);
		/*
		 * switch (user) { case "Dietician": request =
		 * RestAssured.given().header("Authorization", "Bearer " +dieticianToken
		 * ).contentType("application/json"); break; case "Patient": request =
		 * RestAssured.given().header("Authorization", "Bearer " +patientToken
		 * ).contentType("application/json"); break; default: throw new
		 * IllegalArgumentException("Unsupported user type: " + user); }
		 */
	}

	@When("{string} send {string} http request with {string}")
	public void user_sends_a_GET_HTTP_request(String user, String method, String endpointType) {
		// Map the endpointType to the actual endpoint URL
		response = patientReq.getPatient(request,user,method,endpointType);
		/*
		 * switch (endpointType) { case "AllPatients": this.endPoint = "/patient";
		 * break; case "PatientID": this.endPoint = "/patient/testReports/84"; break;
		 * case "FileID": this.endPoint = "/patient/testReports/viewFile/"+fileId;
		 * break; case "DeleteEndpoint": this.endPoint = "/patient/"+patientId; break;
		 * default: throw new IllegalArgumentException("Unsupported endpoint type: " +
		 * endpointType); } if (method.equalsIgnoreCase("GET")) {
		 * System.out.println("Entered Get Request block"); response = request.given()
		 * //.log().all() .when() .get(this.endPoint); } else if
		 * (method.equalsIgnoreCase("DELETE")) { response = request.given() .log().all()
		 * .when() .delete(this.endPoint); }
		 */
	}

	@Then("{string} recieves {int} ok with response body for {string}")
	public void user_receives_a_OK_with_response_body(String user, int statusCode, String endpointType) throws IOException {
		switch (endpointType) {
		case "AllPatients":
			response
			.then()
			.log().all()
			.statusCode(statusCode)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema\\getAllPatientsSchema.json"));
			break;
		case "PatientID":
			response
			.then()
			.log().all()
			.statusCode(statusCode)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema\\getPatientByIDSchema.json"));
			break;
		case "FileID":
			response
			.then()
			.log().all()
			.statusCode(statusCode)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema\\getPatientByIDSchema.json"));
			try (InputStream is = response.asInputStream();
		             FileOutputStream fos = new FileOutputStream("target\\output.pdf")) {
		            byte[] buffer = new byte[4096];
		            int bytesRead;
		            while ((bytesRead = is.read(buffer)) != -1) {
		                fos.write(buffer, 0, bytesRead);
		            }
		        }
		        // Optionally, extract and print text content using PDFBox
		        //extractTextFromPDF("target\\output.pdf");
			break;
		case "DeleteEndpoint":
			response
			.then()
			.log().all()
			.statusCode(statusCode)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema\\getAllPatientsSchema.json"));	
			break;    
		default:
			throw new IllegalArgumentException("Unsupported endpoint type: " + endpointType);
		/*
		 * response .then() .log().all() .statusCode(statusCode);
		 */
		}

	}
	/*
	 * private static void extractTextFromPDF(String filePath) throws IOException {
	 * try (PDDocument document = PDDocument.load(new File(filePath))) {
	 * PDFTextStripper pdfStripper = new PDFTextStripper(); String pdfText =
	 * pdfStripper.getText(document);
	 * System.out.println("Extracted text from PDF:\n" + pdfText); } }
	 */

			
	}

