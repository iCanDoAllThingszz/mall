package io.renren.common.validator;/**
 * @Author:zhoayu
 * @Date:2024/11/23 13:05
 * @Description:io.renren.common.validator
 * @version:1.0
 */

import io.renren.common.annotation.ListValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;

/**
 * @ClassName ListValueConstraintValidator
 * @Description // @ListValue注解的校验器
 * @Author zhaoyu
 * @Date 2024/11/23
 */

// ListValueConstraintValidator 类是用来验证 Integer 类型的字段或参数是否满足 ListValue 注解定义的约束条件
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private HashSet<Integer> set = new HashSet<>();

    /**
     * 校验器初始化方法
     * eg: @ListValue(val = {1,2,3})
     * 获取1,2,3
     * */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        for (Integer val : constraintAnnotation.val()) {
            set.add(val);
        }
    }


    /**
     * 校验逻辑
     * eg: @ListValue(val = {1,2,3})
     * 判断1,2,3是否符合预期
     * */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}

 