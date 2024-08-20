package api.Utility;

import java.util.ResourceBundle;

import api.Pojo.PatientPojo;

public class CommonUtils {


	public static ResourceBundle endpoints = ResourceBundle.getBundle("endpoint");
	public static ResourceBundle paths = ResourceBundle.getBundle("path");

	public static String baseURI = endpoints.getString("baseUrl");
	private static String dieticianToken;
    private static String patientToken;
    private static String adminToken;
    private static int patientID;
    private static String fileID;
    private static String emailPatientID;
    

    public static String getEmailPatientID() {
		return emailPatientID;
	}

	public static void setEmailPatientID(String emailPatientid) {
		emailPatientID = emailPatientid;
	}

	public static int getPatientID() {
		return patientID;
	}

	public static void setPatientID(int patientid) {
		patientID = patientid;
	}

	public static String getFileID() {
		return fileID;
	}

	public static void setFileID(String fileid) {
	fileID = fileid;
	}

	
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


	
}
