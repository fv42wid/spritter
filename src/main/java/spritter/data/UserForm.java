package spritter.data;

import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import spritter.data.User;
import spritter.validation.PasswordMatches;

@Data
@PasswordMatches.List({@PasswordMatches(field="confirmPassword", fieldMatch="password", message="Passwords do not match")})
public class UserForm {
	
	@Size(min=1, message = "Email is not valid")
	@Email(message = "Email is not valid")
	private String email;
	
	@Size(min=4, message = "Password must be at least 4 characters")
	private String password;
	
	@Size(min=4, message = "Password must be at least 4 characters")
	private String confirmPassword;
	
	@NotNull
	@Size(min=1, message = "Please enter your name")
	private String firstName;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(email, passwordEncoder.encode(password), firstName);
	}
	

}
