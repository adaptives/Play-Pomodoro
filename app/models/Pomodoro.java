package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Pomodoro extends Model {
	
	@ManyToOne
	public User user;
	public String task;
	public Date started;
	public boolean isComplete;
	
	public Pomodoro(User user, String task) {
		this.user = user;
		this.task = task;
		this.started = new Date();
	}
	
	public static List<Pomodoro> findAllByUser(long id) {
		String query = 
			"select p from Pomodoro p where p.user.id = ? and p.isComplete = ? order by p.started desc";
		return Pomodoro.find(query, id, true).fetch();
	}
	
	@Override
	public String toString() {
		return this.user.email + " : " + 
			   this.task + 
			   (isComplete ? "completed" : "");
	}
	
}
