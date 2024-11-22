package com.zy.mallproduct.exception;/**
 * @Author:zhoayu
 * @Date:2024/11/22 12:44
 * @Description:com.zy.mallproduct.exception
 * @version:1.0
 */

import com.alibaba.nacos.api.model.v2.ErrorCode;
import io.renren.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ValidatorExceptionHandler
 * @Description //全局异常处理类
 * @Author zhaoyu
 * @Date 2024/11/22
 */

// @RestControllerAdvice = @ControllerAdvice + @ResponseBody

@RestControllerAdvice(basePackages =  "com.zy.mallproduct.controller")
@Slf4j
public class ValidatorExceptionHandler {
    /**
     * MethodArgumentNotValidException 是 Spring Framework 中的一个异常类，它在进行方法参数验证时出现错误时抛出。这个异常通常与 @Valid 或 @Validated 注解一起使用，用于处理请求体、表单数据或路径变量的验证失败情况。
     * 方法参数校验失败 - 异常处理类
     * */

    //value 属性在 @ExceptionHandler 注解中用于指定这个异常处理方法应该处理的异常类型。你可以将一个或多个异常类作为这个属性的值。当指定的异常类型（或其子类型）被抛出时，Spring 框架会自动调用相应的异常处理方法。
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handlerValidException(MethodArgumentNotValidException e){
        log.error("数据校验出现问题{},异常类型{}",e.getMessage(),e.getClass());
        return new Result().error(ErrorCode.PARAMETER_VALIDATE_ERROR.getCode(), e.getMessage());
    }

    /**
     * 其他异常处理类
     * */
    @ExceptionHandler(value = Throwable.class)
    public Result handlerValidException(Throwable e){
        log.error("数据校验出现问题{},异常类型{}",e.getMessage(),e.getClass());
        return new Result().error(ErrorCode.SERVER_ERROR.getCode(), e.getMessage());
    }

}

 