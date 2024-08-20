package api.Payload;

import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;

public class LoginPayload extends CommonUtils {

	static LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
	public static LoginRequestPojo adminLogin(){
		
		
		loginRequestPojo.setUserLoginEmail(endpoints.getString("adminEmail"));
		loginRequestPojo.setPassword(endpoints.getString("adminPassword"));
		return loginRequestPojo;
	}
	
public static LoginRequestPojo dieticianLogin(){
		
		loginRequestPojo.setUserLoginEmail(endpoints.getString("dieticianEmail"));
		loginRequestPojo.setPassword(endpoints.getString("dieticianPwd"));
		return loginRequestPojo;
	}

public static LoginRequestPojo patientLogin(){
	
	loginRequestPojo.setUserLoginEmail(endpoints.getString("patientEmail"));
	loginRequestPojo.setPassword(endpoints.getString("patientPsw"));
	return loginRequestPojo;
	
}
}
