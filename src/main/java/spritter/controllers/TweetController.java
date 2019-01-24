package spritter.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import spritter.data.User;
import spritter.data.Post;
import spritter.data.PostRepository;
import spritter.data.UserRepository;

@Slf4j
@Controller
@RequestMapping(value={"/", "/tweets"})
public class TweetController {
	
	private PostRepository postRepo;
	private UserRepository userRepo;
	
	public TweetController(PostRepository postRepo, UserRepository userRepo) {
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}
	
	@GetMapping
	public String showTweets(SessionStatus sessionStatus, @AuthenticationPrincipal User user, Model model) {
		model.addAttribute("post", new Post());

		if(user != null) {
			List<Long> excludeUsers = new ArrayList<Long>();
			excludeUsers.add(user.getId());
			//add user.getFollows
			//user.getFollows should be passed to findPostsByUserIn
			List<User> postUsers = userRepo.findByIdFetchFollows(user.getId()).getFollows();
			postUsers.add(user);
			
			postUsers.forEach(u -> excludeUsers.add(u.getId()));
			
			List<User> newUsers = userRepo.findTop5ByIdNotInOrderByIdDesc(excludeUsers);
			List<Post> posts = postRepo.findPostsByUserInOrderByIdDesc(postUsers);
		
			model.addAttribute("newUsers", newUsers);
			model.addAttribute("posts", posts);
		}
		return "tweets/index";
	}
	
	@PostMapping
	public String createTweet(@Valid Post post, BindingResult result, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if(result.hasErrors()) {
			log.info("Post - " + result);
			return "tweets/index";
		}
		post.setUser(user);
		postRepo.save(post);
		return "redirect:/tweets";
	}

}
