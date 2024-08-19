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
import api.Utility.ExcelReader;

public class UpdatePatientPayload extends CommonUtils {
	//ExcelReader excelReader=new ExcelReader();
	 static ExcelReader reader;

	   
	   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	   public static Iterator<PatientPojo> getUpdateDataFromExcel() throws IOException, ParseException {
	      List<LinkedHashMap<String, String>> updatePatient = reader.getExcelData("UpdatePatient"); 
	      
	      List<PatientPojo> updatedPatients = new ArrayList<PatientPojo>();

	      for (LinkedHashMap<String, String> patientData : updatePatient) {
	         PatientPojo patientPojo = new PatientPojo();

	         patientPojo.setFirstName(patientData.get("UpdatedFname"));
	         patientPojo.setLastName(patientData.get("UpdatedLname"));
	         patientPojo.setContactNumber(patientData.get("UpdatedContactNo"));
	         patientPojo.setEmail(patientData.get("UpdatedEmail"));
	         patientPojo.setAllergy(patientData.get("UpdatedAllergy"));
	         patientPojo.setFoodPrefernce(patientData.get("UpdatedFood"));
	         patientPojo.setCuisineCategory(patientData.get("UpdatedCuisine"));
	         patientPojo.setDateOfBirth(patientData.get("UpdatedDOB"));
	         updatedPatients.add(patientPojo);
	      }

	      return updatedPatients.iterator(); 
	   }

}
