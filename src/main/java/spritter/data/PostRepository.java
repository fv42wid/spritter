package spritter.data;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import spritter.data.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findPostsByUserIn(Collection<User> users);
}
