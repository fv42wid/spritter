package spritter.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	User user1;
	User user2;
	User user3;
	User user4;
	User user5;
	User user6;
	Post post1;
	
	@Before
	public void setup() {
		user1 = new User("user1@a", "pass", "userone");
		userRepo.save(user1);
		user2 = new User("user2@a", "pass", "usertwo");
		userRepo.save(user2);
		user3 = new User("user3@a", "pass", "userthree");
		userRepo.save(user3);
		user4 = new User("user4@a", "pass", "userfour");
		userRepo.save(user4);
		user5 = new User("user5@a", "pass", "userfive");
		userRepo.save(user5);
		user6 = new User("user6@a", "pass", "usersix");
		userRepo.save(user6);
		post1 = new Post();
		post1.setMessage("user1 post");
		post1.setUser(user1);
		postRepo.save(post1);
	}
	
	@Test
	public void findByName_ReturnUser() {
		//given
		String username = "user1@a";
		
		//when
		User found = userRepo.findByUsername(username);
		
		//then
		assertEquals(username, found.getUsername());
	}
	
	@Test
	public void findTop5ByIdNotInOrderByIdDescIntTest_filterUsers() {
		List<Long> users = new ArrayList<Long>() {{
				add(user1.getId());
				add(user2.getId());
		}};
		
		List<User> result = userRepo.findTop5ByIdNotInOrderByIdDesc(users);
		
		assertEquals(4, result.size());
		assertEquals("usersix", result.get(0).getFirstName());
		assertEquals("userthree", result.get(3).getFirstName());
	}
	
	@Test
	public void findTop5ByIdNotInOrderByIdDescIntTest_NoFilter() {
		List<Long> users = new ArrayList<Long>();
		users.add(user6.getId());
		List<User> result = userRepo.findTop5ByIdNotInOrderByIdDesc(users);
		
		assertEquals(5, result.size());
		assertEquals("userfive", result.get(0).getFirstName());
		assertEquals("userone", result.get(4).getFirstName());
	}
	
	@Test
	public void findByIdFetchFollowsTest() {
		User setup = userRepo.findByIdFetchFollows(user2.getId());
		setup.addFollow(user6);
		userRepo.save(setup);
		Long user = user2.getId();
		User result = userRepo.findByIdFetchFollows(user);
		
		assertEquals(1, result.getFollows().size());
		assertEquals(user6.getId(), result.getFollows().get(0).getId());
	}

}
