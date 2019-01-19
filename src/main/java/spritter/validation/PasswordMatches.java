package spritter.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
//@Documented
public @interface PasswordMatches {
	
	String message() default "Passwords don't match";
	String field();
	String fieldMatch();
	
	@Target({ TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		PasswordMatches[] value();
	}
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
