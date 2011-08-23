package controllers;

import play.*;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.libs.F.Promise;
import play.libs.WS;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	@Before
	public static void globals() {
		renderArgs.put("username", session.get("username"));
	}
	
    public static void index() {
        render();
    }
    
    public static void register() {
    	render();
    }
    
    public static void registerUser(@Required @Email String email,
    								@Required @Equals("password2") String password,
    								@Required String password2) {
    	if(validation.hasErrors()) {
    		validation.keep();
    		params.flash();
    		register();
    	}
    	User user = new User(email, password);
    	user.save();
    	System.out.println("User saved");
    	try {
    		Secure.login();
    	} catch(Throwable e) {
    		error(e.getMessage());
    	}
    }
    
    public static void start(@Required(message="You must enter a task name") String task) {
    	smartWait("4s", task);
    }
    
    public static void viewPomodoros() {
    	if(Security.isConnected()) {
    		String username = Security.connected();
    		User user = User.findByEmail(username);
    		List<Pomodoro> pomodoros = Pomodoro.findAllByUser(user.id);
    		render(pomodoros);
    	} else {
    		forbidden();
    	}
    }
    
    private static void smartWait(String howLong, String task) {
    	Pomodoro p = null;
    	if(Security.isConnected()) {
    		String username = Security.connected();
    		User user = User.findByEmail(username);
    		p = new Pomodoro(user, task);
    		p.save();    		
    	}
    	final long pId = (p != null ? p.id : 0);
    	
    	await(howLong);
    	
    	Pomodoro p1 = Pomodoro.findById(pId);
    	if(p1 != null) {
    		p1.isComplete = true;
    		p1.save();
    	}
    	
    	renderArgs.put("done", task);
		render("Application/index.html");
    }

}