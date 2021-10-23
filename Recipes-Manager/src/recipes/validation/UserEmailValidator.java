package recipes.validation;

import org.springframework.beans.factory.annotation.Autowired;
import recipes.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UserEmailValidator implements ConstraintValidator<UserEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        String emailRegex = "^\\w+@(\\S+)\\.[a-z]+$";

        if (value.isBlank() || value.length() < 8)
            context.buildConstraintViolationWithTemplate(
                            "Email address must have at least 8 characters.")
                    .addConstraintViolation();
        else if (!value.matches(emailRegex))
            context.buildConstraintViolationWithTemplate(
                            "Enter email address in format: yourname@example.com")
                    .addConstraintViolation();
        else if (userRepository.existsByEmail(value))
            context.buildConstraintViolationWithTemplate("This email is already registered.")
                    .addConstraintViolation();
        else
            return true;

        return false;
    }
}
