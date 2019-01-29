package spritter.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;
	
	User user1;
	User user2;
	User user3;
	Post post1;
	Post post2;
	Post post3;
	
	@Before
	public void setup() {
		user1 = new User("user1@a", "pass", "userone");
		user2 = new User("user2@a", "pass", "usertwo");
		user3 = new User("user3@a", "pass", "userthree");
		userRepo.save(user1);
		userRepo.save(user2);
		userRepo.save(user3);
		
		post1 = new Post();
		post1.setMessage("user1 post");
		post1.setUser(user1);
		postRepo.save(post1);
		
		post2 = new Post();
		post2.setMessage("user2 post");
		post2.setUser(user2);
		postRepo.save(post2);
		
		post3 = new Post();
		post3.setMessage("user3 post");
		post3.setUser(user3);
		postRepo.save(post3);
	}
	
	@Test
	public void findPostsByUserInTest() {
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		List<Post> result = postRepo.findPostsByUserIn(users);
		
		assertEquals(2, result.size());
		assertEquals("user1 post", result.get(0).getMessage());
		assertEquals("user2 post", result.get(1).getMessage());
	}
	
	@Test
	public void findPostsByUserInOrderByIdDescTest() {
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		List<Post> result = postRepo.findPostsByUserInOrderByIdDesc(users);
		
		assertEquals(2, result.size());
		assertEquals("user2 post", result.get(0).getMessage());
		assertEquals("user1 post", result.get(1).getMessage());
	}

}
