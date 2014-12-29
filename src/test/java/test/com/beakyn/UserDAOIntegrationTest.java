package test.com.beakyn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.beakyn.config.AbstractDAOIntegrationTest;
import com.beakyn.dao.UserDAO;
import com.beakyn.model.User;
import com.google.common.collect.Lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDAOIntegrationTest extends AbstractDAOIntegrationTest {

	@Autowired
	private UserDAO userDAO;

	@Before
	public void setup() {
		purgeAllData();
	}

	@After
	public void tearDown() {
		purgeAllData();
	}

	private void purgeAllData() {
		userDAO.deleteAll();
		assertEquals(0, userDAO.count());
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setFirstName("Fname");
		userDAO.save(user);
		Iterable<User> users = userDAO.findAll();
		assertEquals(1, Lists.newArrayList(users).size());
	}
}
