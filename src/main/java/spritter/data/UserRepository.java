package spritter.data;

import org.springframework.data.repository.CrudRepository;
import spritter.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

}
