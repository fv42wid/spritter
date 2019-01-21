package spritter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpritterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpritterApplication.class, args);
	}
	
	/*@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
	    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    templateEngine.addDialect(sec); // Enable use of "sec"
	    return templateEngine;
	}*/

}

