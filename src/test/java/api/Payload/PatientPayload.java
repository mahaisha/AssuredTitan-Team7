package api.Payload;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;



import api.Pojo.PatientPojo;
import api.Utility.CommonUtils;
import api.Utility.ExcelReader;

public class PatientPayload extends CommonUtils {
	static ExcelReader reader;
	
	public static PatientPojo patientPojo = new PatientPojo();

    // Date format
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static Iterator<PatientPojo> getDataFromExcel() throws IOException, ParseException
	{
	List<LinkedHashMap<String,String>> createPatient = reader.getExcelData("Patient"); {
		      
         // List to hold all PatientPojo objects
         List<PatientPojo> patients = new ArrayList<PatientPojo>();           

             for (LinkedHashMap<String, String> patientData : createPatient) {
                 PatientPojo patientPojo = new PatientPojo();
         
		patientPojo.setFirstName(patientData.get("Fname"));
		patientPojo.setLastName(patientData.get("Lname"));
		patientPojo.setContactNumber(patientData.get("ContactNo"));
		patientPojo.setEmail(patientData.get("email"));
		patientPojo.setAllergy(patientData.get("allergy"));
		patientPojo.setFoodPreference(patientData.get("Food"));
		patientPojo.setCuisineCategory(patientData.get("cuisine"));
		patientPojo.setDateOfBirth(patientData.get("DOB"));
		//patientPojo.setDateOfBirth("2020-01-01");
		patients.add(patientPojo);
		
//		  // Format the date before setting it
//        String originalDob = patientData.get("DOB");
//        Date dob = dateFormat.parse(originalDob);
//        String formattedDob = dateFormat.format(dob);
//        patientPojo.setDateOfBirth(formattedDob);

    }
    
    return patients.iterator(); // Return the list of PatientPojo objects
}
}
}