package spritter.data;

import java.util.Collection;
import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spritter.data.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String email);
	List<User> findTop5ByIdNotInOrderByIdDesc(Long id);
	List<User> findTop5ByIdNotInOrderByIdDesc(Collection<Long> users);
	
	@Query("SELECT u from User u LEFT JOIN FETCH u.follows f WHERE u.id= ?1")
	User findByIdFetchFollows(Long id);

}
