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
	private static String patientEmail;
	private static String dieticianEmail;
	private static String dieticianPassword;
	private static String dieticianId;

	public static String getDieticianId() {
		return dieticianId;
	}

	public static void setDieticianId(String dieticianId) {
		CommonUtils.dieticianId = dieticianId;
	}

	public static String getDieticianEmail() {
		return dieticianEmail;
	}

	public static void setDieticianEmail(String dieticianEmail) {
		CommonUtils.dieticianEmail = dieticianEmail;
	}

	public static String getDieticianPassword() {
		return dieticianPassword;
	}

	public static void setDieticianPassword(String dieticianPassword) {
		CommonUtils.dieticianPassword = dieticianPassword;
	}

	public static String getpatientEmail() {
		return patientEmail;
	}

	public static void setpatientEmail(String patientEmail) {
		patientEmail = patientEmail;
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

	// public static LoginPojo loginPojo = new LoginPojo();
	public static PatientPojo patientPojo = new PatientPojo();

}
