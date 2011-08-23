package models;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class UserConfirmationTest extends UnitTest {

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
		User user = User.findByEmail("user1@somewhere.com");
		UserConfirmation userConfirmation = new UserConfirmation(user);
		
		//retrieve
		List<UserConfirmation> retrievedUserConfirmations = UserConfirmation.findAll();
		assertEquals(1, retrievedUserConfirmations.size());
		UserConfirmation retrievedUserConfirmation = retrievedUserConfirmations.get(0);
		assertEquals(user.id, retrievedUserConfirmation.user.id);
		assertNotNull(retrievedUserConfirmation.confirmation);
	}

}
