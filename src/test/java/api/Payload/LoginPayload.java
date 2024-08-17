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
}
