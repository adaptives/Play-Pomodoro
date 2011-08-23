package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.db.jpa.Model;
import play.libs.Codec;

@Entity
public class UserConfirmation extends Model {
	
	@OneToOne
	public User user;
	public String confirmation;
	
	public UserConfirmation(User user) {
		this.user = user;
		this.confirmation = Codec.UUID();
		create();
	}
}
