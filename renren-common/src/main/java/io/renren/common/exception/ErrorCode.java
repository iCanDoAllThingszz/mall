package io.renren.common.exception;

import lombok.Getter;

/**
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    UNAUTHORIZED(401, "未登录"),
    NOT_NULL(10001, "参数不能为空"),
    DB_RECORD_EXISTS(10002, "记录已存在"),
    PARAMS_GET_ERROR(10003, "参数获取异常"),
    ACCOUNT_PASSWORD_ERROR(10004, "账号或密码错误"),
    ACCOUNT_DISABLED(10005, "账号被禁用"),
    IDENTIFIER_NOT_NULL(10006, "验证码不能为空"),
    CAPTCHA_ERROR(10007, "验证码错误"),
    SUB_MENU_EXIST(10008, "存在子菜单，不能删除"),
    PASSWORD_ERROR(10009, "密码错误"),
    SUPERIOR_DEPT_ERROR(10010, "上级部门不能为本人"),
    SUPERIOR_MENU_ERROR(10011, "上级菜单不能为本人"),
    DATA_SCOPE_PARAMS_ERROR(10012, "数据权限参数错误"),
    DEPT_SUB_DELETE_ERROR(10013, "存在下级部门，不能删除"),
    DEPT_USER_DELETE_ERROR(10014, "部门下存在用户，不能删除"),
    UPLOAD_FILE_EMPTY(10015, "上传文件不能为空"),
    TOKEN_NOT_EMPTY(10016, "Token不能为空"),
    TOKEN_INVALID(10017, "Token无效"),
    ACCOUNT_LOCKED(10018, "账号被锁定"),
    OSS_UPLOAD_FILE_ERROR(10019, "OSS文件上传失败"),
    REDIS_ERROR(10020, "Redis异常"),
    JOB_ERROR(10021, "定时任务异常"),
    INVALID_SYMBOL(10022, "非法字符");

    private final int code;
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

}
