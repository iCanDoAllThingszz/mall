package com.zy.mallproduct.interceptor;/**
 * @Author:zhoayu
 * @Date:2024/11/24 11:27
 * @Description:com.zy.mallproduct.interceptor
 * @version:1.0
 */

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * @ClassName MybatisPlusInterceptor
 * @Description // mybatis-plus拦截器, 实现sql语句日志打印功能
 * @Author zhaoyu
 * @Date 2024/11/24
 */

@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
@Component
public class MybatisPlusInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            BoundSql boundSql = statementHandler.getBoundSql();
            Object parameterObject = statementHandler.getParameterHandler().getParameterObject();
            String parameterObjectString = parameterObject.toString();

            String sql = boundSql.getSql();
            System.out.println("Executing SQL: " + sql + " with parameters: " + parameterObjectString);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}

 