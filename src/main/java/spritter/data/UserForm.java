package spritter.data;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import spritter.data.User;

@Data
public class UserForm {
	
	private String email;
	private String password;
	private String confirmPassword;
	private String firstName;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(email, passwordEncoder.encode(password), firstName);
	}
	

}
