package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class User extends Model {
	
	public String password;
	public String email;
	public boolean isAdmin;
	public boolean isVerified;
	
	public User(String email, 
				String password) {
		
		this.password = Crypto.passwordHash(password);
		this.email = email;
	}
	
	public static User findByEmail(String email) {
		return User.find("byEmail", email).first();
	}
	
	public static boolean connect(String email, String password) {
		User user = 
			User.find("byEmailAndPassword", email, Crypto.passwordHash(password)).first(); 
		return user != null;
	}
	
	public static boolean checkAdmin(String username) {
		boolean retVal = false;
		User user = User.find("byEmail", username).first();
		if(user != null) {
			retVal = user.isAdmin;
		}
		return retVal;
	}

}
