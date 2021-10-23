package recipes.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotated list must not be {@code null} or contain
 * at least one item. Accepts {@code List<String>}.
 *
 * @author Me
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneItemListValidator.class)
public @interface OneItemList {

    String message() default "must contain at least one item";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}