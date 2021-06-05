package ro.johann.da.user.api.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {

  private String first;
  private String second;

  @Override
  public void initialize(FieldsMatch constraintAnnotation) {
    this.first = constraintAnnotation.first();
    this.second = constraintAnnotation.second();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    Object firstValue = new BeanWrapperImpl(value).getPropertyValue(first);
    Object secondValue = new BeanWrapperImpl(value).getPropertyValue(second);

    if (firstValue != null) {
      return firstValue.equals(secondValue);
    } else {
      return secondValue == null;
    }
  }
}
