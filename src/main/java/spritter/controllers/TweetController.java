package spritter.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import spritter.data.User;

@Slf4j
@Controller
@RequestMapping(value={"/", "/tweets"})
public class TweetController {
	
	@GetMapping
	public String showTweets(SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		return "tweets/index";
	}

}
