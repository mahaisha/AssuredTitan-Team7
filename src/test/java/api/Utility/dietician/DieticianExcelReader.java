package api.Utility.dietician;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import api.Utility.ExcelReader;
import model.Dietician;

public class DieticianExcelReader {
	private static final Logger LOGGER = LogManager.getLogger(DieticianExcelReader.class);
	
	private static final String EXCEL_FILE_PATH = "src/test/resources/testdata/dietician.xlsx";
	private static final String EXCEL_SHEET_NAME = "Dietician";

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	private ExcelReader excelReader;
	private String excelFilePath;
	private String sheetName;

	public DieticianExcelReader() {
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
	
	public Dietician readRow(TestCase testCase) {
		int rowNumber = getRowNumber(testCase);
		
		try {
			List<Map<String, String>> excelData = excelReader.getData(excelFilePath, sheetName);
			Map<String, String> rowWithFullDetails = excelData.get(rowNumber);

			DATE_FORMAT.parse(rowWithFullDetails.get("DateOfBirth"));

			Dietician dietician = new Dietician();
			dietician.setFirstName(rowWithFullDetails.get("Firstname"));
			dietician.setLastName(rowWithFullDetails.get("Lastname"));
			dietician.setEmail(rowWithFullDetails.get("Email"));
			dietician.setContactNumber(rowWithFullDetails.get("ContactNumber"));
			dietician.setEducation(rowWithFullDetails.get("Education"));
			dietician.setDateOfBirth(rowWithFullDetails.get("DateOfBirth"));

			dietician.setHospitalName(rowWithFullDetails.get("HospitalName"));
			dietician.setHospitalStreet(rowWithFullDetails.get("HospitalStreet"));
			dietician.setHospitalCity(rowWithFullDetails.get("HospitalCity"));
			dietician.setHospitalPincode(rowWithFullDetails.get("HospitalPincode"));

			LOGGER.info("Read dietician from Excel file." + dietician);
			return dietician;
		} catch (InvalidFormatException | IOException | ParseException e) {
			LOGGER.error("Failed to read Excel file.", e);
			throw new RuntimeException("Failed to read Excel file.", e);
		}
	}
	
	private int getRowNumber(TestCase testCase) {
		switch(testCase) {
			case FULL: return 0;
			case MANDATORY: return 1;
			case ADDITIONAL: return 2;
			case INVALID: return 3;
		}
		
		throw new RuntimeException("Unknown test case." + testCase);
		
	}
}
