package api.Payload;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import api.Pojo.AdditionalPatientPojo;
import api.Utility.CommonUtils;
import api.Utility.PatientExcelReader;

public class AdditionalPatientPayload extends CommonUtils {
    static PatientExcelReader reader;
    
    // Date format
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Iterator<AdditionalPatientPojo> getadditionalDetails() throws IOException, ParseException {
        List<LinkedHashMap<String,String>> createPatient = reader.getExcelData("Patient");
        
        // List to hold all AdditionalPatientPojo objects
        List<AdditionalPatientPojo> patients = new ArrayList<>();
        
        for (LinkedHashMap<String, String> patientData : createPatient) {
            AdditionalPatientPojo additional = new AdditionalPatientPojo();
            
            try {
                // Safely parse the Float values, providing default values if necessary
                additional.setWeight(parseFloatOrDefault(patientData.get("weight"), 0.0f));
                additional.setHeight(parseFloatOrDefault(patientData.get("height"), 0.0f));
                additional.setTemperature(parseFloatOrDefault(patientData.get("temperature"), 0.0f));
                
                // Safely parse the Integer values, providing default values if necessary
                additional.setSP(parseIntOrDefault(patientData.get("sp"), 0));
                additional.setDP(parseIntOrDefault(patientData.get("dp"), 0));
            } catch (NumberFormatException e) {
                // Handle the case where conversion fails
                System.err.println("Error parsing numeric values: " + e.getMessage());
            }
            
            patients.add(additional);
        }
        
        return patients.iterator(); // Return the list of AdditionalPatientPojo objects
    }

    public static Float parseFloatOrDefault(String value, Float defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            // Log and handle the parsing error
            System.err.println("Error parsing float value: " + e.getMessage());
            return defaultValue; // Return default value in case of an error
        }
    }

    public static Integer parseIntOrDefault(String value, Integer defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // Log and handle the parsing error
            System.err.println("Error parsing integer value: " + e.getMessage());
            return defaultValue; // Return default value in case of an error
        }
    }
}
