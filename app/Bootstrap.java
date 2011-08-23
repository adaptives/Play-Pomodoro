import models.User;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

	@Override
	public void doJob() {
		if(Play.mode == Play.Mode.DEV && User.findAll().size() == 0) {
			System.out.println("Creating Users in Bootstrap");
			
			User user = new User("user@somewhere.com", "secret");
			user.save();
			
			User admin = new User("admin@somewhere.com", "secret");
			admin.isAdmin = true;
			admin.save();
		}
	}
}
