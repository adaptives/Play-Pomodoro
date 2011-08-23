package controllers;

import models.User;

public class Security extends Secure.Security {
	
	static boolean authenticate(String username, String password) {
		return User.connect(username, password);
	}
	
	public static boolean check(String profile) {
		//at the moment we only support the 'admin' profile
		boolean retVal = false;
		if("admin".equals(profile)) {
			String connectedUserEmail = connected();
			if(connectedUserEmail != null) {
				retVal = User.checkAdmin(connectedUserEmail);
			}			
		}
		return retVal;
	}
	
	public static void onDisconnected() {
	    Application.index();
	}

	    
}
