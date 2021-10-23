package recipes.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class OneItemListValidator implements ConstraintValidator<OneItemList, List<String>> {

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
//                && value.get(0).matches("\\s*\\w{4,}\\s*");
    }
}
