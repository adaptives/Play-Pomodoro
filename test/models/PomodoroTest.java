package models;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class PomodoroTest extends UnitTest {

	@Before
	public void setUp() throws Exception {
		Fixtures.deleteAll();
		Fixtures.load("users.yml");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreateAndRetrieve() throws Exception {
		//create
		String task = "Play Workshop";
		User user = User.findByEmail("user1@somewhere.com");
		Pomodoro p = new Pomodoro(user, task);
		p.save();
		
		//retrieve
		List<Pomodoro> pomodoros = Pomodoro.findAll();
		assertEquals(1, pomodoros.size());
		Pomodoro retrievedPomodoro = pomodoros.get(0);
		assertEquals(task, retrievedPomodoro.task);
		assertEquals((new Date()).getTime(), retrievedPomodoro.started.getTime(), 1000);
	}
	
	@Test
	public void testFindAllByUser() throws Exception {
		//create
		User user = User.findByEmail("user1@somewhere.com");
		
		String task1 = "Check Email";
		Pomodoro p1 = new Pomodoro(user, task1);
		p1.isComplete = true;
		p1.save();
		
		String task2 = "Write tests";
		Pomodoro p2 = new Pomodoro(user, task2);
		p2.isComplete = true;
		p2.save();
		
		String task3 = "Push code";
		Pomodoro p3 = new Pomodoro(user, task3);
		p3.isComplete = true;
		p3.save();
		
		List<Pomodoro> pomodoros = Pomodoro.findAllByUser(user.id);
		assertEquals(3, pomodoros.size());
	}

}
