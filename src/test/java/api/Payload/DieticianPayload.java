package api.Payload;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import api.Pojo.DieticianPojo;
import api.Utility.ExcelReader;

public class DieticianPayload {
	private static final Logger LOGGER = LogManager.getLogger(DieticianPayload.class);
	
	private static final String EXCEL_FILE_PATH = "src/test/resources/ExcelData/dietician.xlsx";
	private static final String EXCEL_SHEET_NAME = "Dietician";

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	private ExcelReader excelReader;
	private String excelFilePath;
	private String sheetName;

	public DieticianPayload() {
		excelReader = new ExcelReader();
		
		excelFilePath = EXCEL_FILE_PATH;
		sheetName = EXCEL_SHEET_NAME;
	}
	
	public enum TestCase {
		FULL,
		MANDATORY,
		ADDITIONAL,
		INVALID
	}
	
	public DieticianPojo readRow(TestCase testCase) {
		try {
			List<Map<String, String>> excelData = excelReader.getData(excelFilePath, sheetName);
			
			LOGGER.info("Looking for test case in Excel file: " + testCase);
			
			for(Map<String, String> row : excelData) {
				if(testCase.toString().equals(row.get("TestCase"))) {
					DATE_FORMAT.parse(row.get("DateOfBirth"));
	
					DieticianPojo dietician = new DieticianPojo();
					dietician.setFirstName(row.get("Firstname"));
					dietician.setLastName(row.get("Lastname"));
					dietician.setEmail(row.get("Email"));
					dietician.setContactNumber(row.get("ContactNumber"));
					dietician.setEducation(row.get("Education"));
					dietician.setDateOfBirth(row.get("DateOfBirth"));
	
					dietician.setHospitalName(row.get("HospitalName"));
					dietician.setHospitalStreet(row.get("HospitalStreet"));
					dietician.setHospitalCity(row.get("HospitalCity"));
					dietician.setHospitalPincode(row.get("HospitalPincode"));
	
					LOGGER.info("Read dietician from Excel file: " + dietician);
					return dietician;
				}
			}
			
			throw new RuntimeException("Failed to find row for test case in Excel file: " + testCase);
		} catch (InvalidFormatException | IOException | ParseException e) {
			LOGGER.error("Failed to read Excel file.", e);
			throw new RuntimeException("Failed to read Excel file.", e);
		}
	}
}
