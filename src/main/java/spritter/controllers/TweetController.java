package spritter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value={"/", "/tweets"})
public class TweetController {
	
	@GetMapping
	public String showTweets() {
		return "tweets/index";
	}

}
