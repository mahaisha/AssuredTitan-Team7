package api.Utility;

import java.util.ResourceBundle;

import api.Pojo.PatientPojo;

public class CommonUtils {


	public static ResourceBundle endpoints = ResourceBundle.getBundle("endpoint");
	public static ResourceBundle paths = ResourceBundle.getBundle("path");
	
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
	
	//public static LoginPojo loginPojo = new LoginPojo();
	public static PatientPojo patientPojo= new PatientPojo();
//	public static Response response;
//	
}
