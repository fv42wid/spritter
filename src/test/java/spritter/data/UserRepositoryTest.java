package spritter.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	User user1;
	User user2;
	
	@Before
	public void setup() {
		user1 = new User("user1@a", "pass", "userone");
		userRepo.save(user1);
		user2 = new User("user2@a", "pass", "usertwo");
		userRepo.save(user2);
	}
	
	@Test
	public void whenFindByName_ReturnUser() {
		//given
		String username = "user1@a";
		
		//when
		User found = userRepo.findByUsername(username);
		
		//then
		assertEquals(username, found.getUsername());
	}

}
