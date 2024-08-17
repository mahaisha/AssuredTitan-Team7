package api.Payload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import api.Pojo.PatientPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;

public class createPatientPayload extends CommonUtils {
	
	public static List<PatientPojo> createPatient() throws InvalidFormatException, IOException {
		
		 ExcelReader excelReader = new ExcelReader();
         List<Map<String, String>> data = excelReader.getData();

         // Assuming there's only one record in the list, we'll use the first one.
        // Map<String, String> patientData = data.get(0);
         
         // List to hold all PatientPojo objects
         List<PatientPojo> patients = new ArrayList<>();

         // Create an iterator for the list
         Iterator<Map<String, String>> iterator = data.iterator();

         while (iterator.hasNext()) {
             Map<String, String> patientData = iterator.next();
             
     
         
		patientPojo.setFirstName(patientData.get("Fname"));
		patientPojo.setLastName(patientData.get("Lname"));
		patientPojo.setContactNumber(patientData.get("ConatctNo"));
		patientPojo.setEmail(patientData.get("email"));
		patientPojo.setAllergy(patientData.get("allergy"));
		patientPojo.setFoodPrefernce(patientData.get("Food"));
		patientPojo.setCuisineCategory(patientData.get("cuisine"));
		patientPojo.setDateOfBirth(patientData.get("DOB"));
		
		 // Add the populated PatientPojo object to the list
        patients.add(patientPojo);
    }
    
    return patients; // Return the list of PatientPojo objects
}
}