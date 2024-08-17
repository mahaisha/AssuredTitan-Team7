package api.Utility;

import java.util.ResourceBundle;

public class CommonUtils {


	public static ResourceBundle endpoints = ResourceBundle.getBundle("endpoint");
	
	private static String dieticianToken;
    private static String patientToken;
    private static String adminToken;

    public static String getAdminToken() {
		return adminToken;
	}

	public static void setAdminToken(String token) {
		adminToken = token;
	}

	public static String getDieticianToken() {
        return dieticianToken;
    }

    public static void setDieticianToken(String token) {
        dieticianToken = token;
    }

    public static String getPatientToken() {
        return patientToken;
    }

    public static void setPatientToken(String token) {
        patientToken = token;
    }
	
    public static String getBaseUrl() {
        return endpoints.getString("baseUrl");
	
    }
    public static String getAllPatientsEndpoint() {
        return endpoints.getString("getAllPatients");
	
    }
    public static String getPatientMorbidityEndpoint() {
        return endpoints.getString("getPatientMorbidity");
	
    }
    public static String getPatientByFileIdEndpoint() {
        return endpoints.getString("getPatientByFileId");
	
    }
//	public static LoginPojo loginPojo = new LoginPojo();
//	public static PatientPojo patientPojo= new PatientPojo();
//	public static Response response;
//	
}
