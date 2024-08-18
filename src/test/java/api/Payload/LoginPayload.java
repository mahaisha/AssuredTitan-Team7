package api.Payload;

import api.Pojo.LoginRequestPojo;
import api.Utility.CommonUtils;

public class LoginPayload extends CommonUtils {

	
	public static LoginRequestPojo adminLogin(){
		LoginRequestPojo loginRequestPojo = new LoginRequestPojo();
		
		loginRequestPojo.setUserLoginEmail(endpoints.getString("adminEmail"));
		loginRequestPojo.setPassword(endpoints.getString("adminPassword"));
		return loginRequestPojo;
		
	}
//	public static LoginRequestPojo dieticianLogin() {
//		loginRequestPojo.setUserLoginEmail(endpoints.getString(null));
//	}
}
