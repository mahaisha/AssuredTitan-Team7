package api.StepDefinitions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import api.Utility.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import model.Dietician;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class DieticianSteps {
	private static final Logger LOGGER = LogManager.getLogger(DieticianSteps.class);

	private static final String BASE_URI = "https://dietician-july-api-hackathon-80f2590665cc.herokuapp.com/dietician";
	private static final String DIETICIAN_BASE_PATH = "/dietician";
	private static final String API_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNy5hZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjM3Mzg5NDAsImV4cCI6MTcyMzc2Nzc0MH0.k49fIWXYD7O9Ymm-AGZGIgwwI6CMoWdzgcXlXMTG-K-X8TMNER0lhFDrLbmoRac10WFVamRF4bUqtmV4Rdz4fg";

	private static final String EXCEL_FILE_PATH = "src/test/resources/testdata/dietician.xlsx";
	private static final String EXCEL_SHEET_NAME = "Dietician";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static final SimpleDateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'");

	private ExcelReader excelReader;
	private String excelFilePath;
	private String sheetName;

	private Dietician dietician;

	public DieticianSteps() {
		excelReader = new ExcelReader();
		excelFilePath = EXCEL_FILE_PATH;
		sheetName = EXCEL_SHEET_NAME;

		RestAssured.baseURI = BASE_URI;
	}

	@Given("App has Admin token")
	public void app_has_admin_token() {
		// TODO: Integrate with Admin module
		return;
	}

	@Given("Excel file has Dietician details")
	public void excel_file_has_dietician_details() {
		try {
			List<Map<String, String>> excelData = excelReader.getData(excelFilePath, sheetName);
			Map<String, String> row1 = excelData.get(0);

			DATE_FORMAT.parse(row1.get("DateOfBirth"));

			Dietician dietician = new Dietician();
			dietician.setFirstName(row1.get("Firstname"));
			dietician.setLastName(row1.get("Lastname"));
			dietician.setEmail(row1.get("Email"));
			dietician.setContactNumber(row1.get("ContactNumber"));
			dietician.setEducation(row1.get("Education"));
			dietician.setDateOfBirth(row1.get("DateOfBirth"));

			dietician.setHospitalName(row1.get("HospitalName"));
			dietician.setHospitalStreet(row1.get("HospitalStreet"));
			dietician.setHospitalCity(row1.get("HospitalCity"));
			dietician.setHospitalPincode(row1.get("HospitalPincode"));

			this.dietician = dietician;
		} catch (InvalidFormatException | IOException | ParseException e) {
			LOGGER.error("Failed to read Excel file.", e);
			throw new RuntimeException("Failed to read Excel file.", e);
		}
	}

	@When("Dietician is created")
	public void dietician_is_created() {
		try {
			Dietician response = given().log().all()
					.with().body(this.dietician)
					.headers("Authorization", "Bearer " + API_TOKEN)
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.when().request("POST", DIETICIAN_BASE_PATH)
					.then().log().ifValidationFails(LogDetail.BODY)
					.statusCode(201)
					.assertThat()
					.body("Firstname", equalTo(dietician.getFirstName()))
					.body("Lastname", equalTo(dietician.getLastName()))
					.body("Email", equalTo(dietician.getEmail()))
					.body("DateOfBirth", equalTo(DATE_FORMAT_2.format(DATE_FORMAT.parse(dietician.getDateOfBirth()))))
					.body("ContactNumber", equalTo(dietician.getContactNumber()))
					.body("Education", equalTo(dietician.getEducation()))
					.body("HospitalName", equalTo(dietician.getHospitalName()))
					.body("HospitalStreet", equalTo(dietician.getHospitalStreet()))
					.body("HospitalCity", equalTo(dietician.getHospitalCity()))
					.body("HospitalPincode", equalTo(dietician.getHospitalPincode()))
					.body("$", hasKey("id"))
					.body("$", hasKey("loginPassword"))
					.extract().as(Dietician.class);

			this.dietician = response;

		} catch (ParseException e) {
			LOGGER.error("Failed to parse DateOfBirth of created Dietician.", e);
			throw new RuntimeException("Failed to parse DateOfBirth of created Dietician.", e);
		}
	}
}
