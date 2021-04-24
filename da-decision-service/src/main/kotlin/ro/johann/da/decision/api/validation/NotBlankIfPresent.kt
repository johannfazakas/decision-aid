package ro.johann.da.decision.api.validation

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.reflect.KClass

@MustBeDocumented
@Target(allowedTargets = [FIELD, FUNCTION])
@Constraint(validatedBy = [NotEmptyIfPresentValidator::class])
@Retention(RUNTIME)
annotation class NotBlankIfPresent(
  val message: String = "Value should not be empty if mentioned",
  val groups: Array<KClass<*>> = [],
  val payload: Array<KClass<out Payload>> = []
)

class NotEmptyIfPresentValidator : ConstraintValidator<NotBlankIfPresent, String> {
  override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
    return  value?.isNotBlank() ?: true
  }
}
