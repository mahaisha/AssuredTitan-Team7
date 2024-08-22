package api.Payload;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import api.Pojo.PatientPojo;
import api.Utility.CommonUtils;
import api.Utility.PatientExcelReader;

public class UpdatePatientPayload extends CommonUtils {
//static PatientExcelReader reader;

	public static PatientPojo patientPojo = new PatientPojo();

	// Date format
	// private static final SimpleDateFormat dateFormat = new
	// SimpleDateFormat("yyyy-MM-dd");

	public static List<PatientPojo> getupdate() throws IOException, ParseException {

		List<LinkedHashMap<String, String>> UpdatePatient = PatientExcelReader.getExcelData("Patient");

		// List to hold all PatientPojo objects
		List<PatientPojo> patients = new ArrayList<PatientPojo>();

		for (LinkedHashMap<String, String> patientData : UpdatePatient) {
			PatientPojo patientPojo = new PatientPojo();

			patientPojo.setFirstName(patientData.get("UpdatedFname"));
			patientPojo.setLastName(patientData.get("UpdatedLname"));
			patientPojo.setContactNumber(patientData.get("UpdatedContactNo"));
			patientPojo.setEmail(patientData.get("UpdatedEmail"));
			patientPojo.setAllergy(patientData.get("UpdatedAllergy"));
			patientPojo.setFoodPreference(patientData.get("UpdatedFood"));
			patientPojo.setCuisineCategory(patientData.get("UpdatedCuisine"));
			patientPojo.setDateOfBirth(patientData.get("UpdatedDOB"));
			patients.add(patientPojo);
		}
		return patients; // Return the list of PatientPojo objects

		
	}
	public static List<PatientPojo> getUpdateWithMandatoryField() throws IOException, ParseException {

		List<LinkedHashMap<String, String>> UpdatePatient = PatientExcelReader.getExcelData("Patient");

		// List to hold all PatientPojo objects
		List<PatientPojo> patients = new ArrayList<PatientPojo>();

		for (LinkedHashMap<String, String> patientData : UpdatePatient) {
			PatientPojo patientPojo = new PatientPojo();

			patientPojo.setFirstName(patientData.get("UpdatedFname"));
			patientPojo.setLastName(patientData.get("UpdatedLname"));
			patientPojo.setContactNumber(patientData.get("UpdatedContactNo"));
			patientPojo.setEmail(patientData.get("UpdatedEmail"));
			patientPojo.setAllergy(patientData.get("UpdatedAllergy"));
			//patientPojo.setFoodPreference(patientData.get("UpdatedFood"));
			patientPojo.setCuisineCategory(patientData.get("UpdatedCuisine"));
			patientPojo.setDateOfBirth(patientData.get("UpdatedDOB"));
			patients.add(patientPojo);
		}
		return patients; // Return the list of PatientPojo objects

	}

//	public static List<PatientPojo> getAdditionalDetails() throws IOException, ParseException {
//		List<LinkedHashMap<String, String>> UpdatePatient = PatientExcelReader.getExcelData("Patient");
//
//		// List to hold all PatientPojo objects
//		List<PatientPojo> patients = new ArrayList<>();
//
//		for (LinkedHashMap<String, String> patientData : UpdatePatient) {
//			PatientPojo patientPojo = new PatientPojo();
//
//			try {
//				// Convert data from string to appropriate type
//				patientPojo.setWeight(Float.parseFloat(patientData.get("weight")));
//				patientPojo.setHeight(Float.parseFloat(patientData.get("height")));
//				patientPojo.setTemperature(Float.parseFloat(patientData.get("temperature")));
//				patientPojo.setSP(Integer.parseInt(patientData.get("sp")));
//				patientPojo.setDP(Integer.parseInt(patientData.get("dp")));
//			} catch (NumberFormatException e) {
//				// Handle the case where conversion fails
//				System.err.println("Error parsing numeric values: " + e.getMessage());
//			}
//
//			patients.add(patientPojo);
//		}
//
//		return patients; // Return the list of PatientPojo objects

	}