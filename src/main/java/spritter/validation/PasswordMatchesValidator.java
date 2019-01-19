package spritter.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
	
	private String field;
	private String fieldMatch;
	
	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
	}
	
	@Override
	public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
		//final UserForm user = (UserForm) obj;
		//return user.getPassword().equals(user.getConfirmPassword());
		Object fieldValue = new BeanWrapperImpl(obj).getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(obj).getPropertyValue(fieldMatch);
		boolean matches = fieldValue.equals(fieldMatchValue);
		
		if(!matches) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(field).addConstraintViolation();
		}
		return matches;
	}

}
