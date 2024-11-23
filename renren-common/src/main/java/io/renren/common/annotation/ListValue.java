package io.renren.common.annotation;

import io.renren.common.validator.ListValueConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author:zhoayu
 * @Date:2024/11/23 12:39
 * @Description:io.renren.common.annotation
 * @version:1.0
 */

@Documented
@Constraint(validatedBy = ListValueConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {

    String message() default "{aaa}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] val() default {};

}
