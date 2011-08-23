package models;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.libs.Crypto;
import play.test.Fixtures;
import play.test.UnitTest;

public class UserTest extends UnitTest {

	@Before
	public void setUp() throws Exception {
		Fixtures.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	
	}
	
	@Test
	public void testCreateAndRetrieve() {
		//create
		String email = "user1@somewhere.com";
		String password = "secret";		
		User user = new User(email, password);
		user.save();
		
		//retrieve
		List<User> retrievedUsers = User.findAll();
		assertEquals(1, retrievedUsers.size());
		User retrievedUser = retrievedUsers.get(0);
		assertEquals(Crypto.passwordHash(password), retrievedUser.password);
		assertEquals(email, retrievedUser.email);
		assertFalse(retrievedUser.isAdmin);
		assertFalse(retrievedUser.isVerified);
	}
	
	@Test
	public void testCreateAndRetrieveAdmin() {
		//create
		String email = "user2@somewhere.com";
		String password = "secret";		
		User user = new User(email, password);
		user.isAdmin = true;
		user.save();
		
		//retrieve
		List<User> retrievedUsers = User.findAll();
		assertEquals(1, retrievedUsers.size());
		User retrievedUser = retrievedUsers.get(0);
		assertEquals(Crypto.passwordHash(password), retrievedUser.password);
		assertEquals(email, retrievedUser.email);
		assertTrue(retrievedUser.isAdmin);
	}
	
	@Test
	public void testConnect() {
		//create
		String email = "user1@somewhere.com";
		String password = "secret";
		
		User user = new User(email, password);
		user.save();
		
		assertTrue(User.connect(email, password));
		assertFalse(User.connect(email, password + "incorrect"));
	}
	
	@Test
	public void testCheckAdmin() {
		//create
		String email = "user@somewhere.com";
		String password = "secret";
		
		User user = new User(email, password);
		user.save();
		
		String email2 = "admin@somewhere.com";
		String password2 = "secret";
		User user2 = new User(email2, password2);
		user2.isAdmin = true;
		user2.save();
		
		//verify
		assertTrue(User.checkAdmin(email2));
		assertFalse(User.checkAdmin(email));
		
	}

}
