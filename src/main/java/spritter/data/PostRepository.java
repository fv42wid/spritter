package spritter.data;

import org.springframework.data.repository.CrudRepository;

import spritter.data.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
