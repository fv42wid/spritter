package spritter.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsTest {
	
	@InjectMocks
	private UserRepositoryUserDetailsService service;
	
	@Mock
	UserRepository userRepo;
	
	@Test
	public void loadUserByUsernameTest() {
		when(userRepo.findByUsername("user")).thenReturn(
				new User("user1@a", "pass", "userone")
				);
		UserDetails user = service.loadUserByUsername("user");
		
		assertEquals("user1@a", user.getUsername());
	}
	
	@Test
	public void loadUserByUserNameTest_NullReturn() {
		when(userRepo.findByUsername("user")).thenReturn(null);
		try {
			UserDetails user = service.loadUserByUsername("user");
			fail("This is the wrong message");
		} catch (UsernameNotFoundException e) {
			//Exception expected
			assertEquals("User 'user' not found", e.getMessage());
		}
		
	}

}
