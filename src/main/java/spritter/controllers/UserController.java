package spritter.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.support.SessionStatus;

import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;

import spritter.data.User;
import spritter.data.UserFollowForm;
import spritter.data.UserRepository;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserRepository userRepo;
	
	public UserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@PostMapping("/follow")
	public String followUser(@Valid UserFollowForm follow, BindingResult result, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if(result.hasErrors()) {
			log.info("Follow - " + result);
			return "/tweets";
		}
		
		Optional<User> followed = userRepo.findById(follow.getFollowsId());
		if(followed.get() != null) {
			User savedUser = userRepo.findByIdFetchFollows(user.getId());
			savedUser.addFollow(followed.get());
			userRepo.save(savedUser);
		}
		
		return "redirect:/tweets";
	}

}
