package recipes.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated Email must not be {@code Blank},
 * with {@code length() > 8} and formatted to: yourname@example.com.
 * The email must not exist in a database. Accepts {@code String}.
 *
 * @author Me
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserEmailValidator.class)
public @interface UserEmail {

    String message() default "email address is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}