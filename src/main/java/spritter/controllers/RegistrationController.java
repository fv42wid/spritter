package spritter.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import spritter.data.UserRepository;
import spritter.data.UserForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String registrationForm(Model model) {
		model.addAttribute("user", new UserForm());
		
		return "registration/form";
	}
	
	@PostMapping
	public String processRegistration(UserForm form) {
		userRepo.save(form.toUser(passwordEncoder));
		return "redirect:/login";
	}

}
