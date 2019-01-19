package spritter.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;

import spritter.data.UserRepository;
import spritter.data.UserForm;

@Slf4j
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
		model.addAttribute("userForm", new UserForm());
		
		return "registration/form";
	}
	
	@PostMapping
	public String processRegistration(@Valid UserForm userForm, BindingResult result) {
		if(result.hasErrors()) {
			log.info("User - " + result);
			//model.addAttribute("user", form);
			return "registration/form";
		}
		userRepo.save(userForm.toUser(passwordEncoder));
		return "redirect:/login";
	}

}
