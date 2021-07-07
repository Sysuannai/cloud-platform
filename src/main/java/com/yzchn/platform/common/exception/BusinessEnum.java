package com.yzchn.platform.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName BusinessEnum.java
 * @Description TODO 自定义异常处理类
 * @createTime 2021年05月10日 15:09:00
 */
public enum BusinessEnum {

    SUCCESS(0, "成功"),
    PARAMETER_ERROR(1, "参数错误"),


    /**
     * 数据格式错误
     */
    FROMAT_EXCEPTION(403, "时间格式错误"),

    COORDINATE_EXCEPTION(403, "请输入正确的经纬度坐标"),

    BODY_NOT_MATCH(501, "请求的数据格式不符！"),

    AIRSPACE_EXCEPTION(403, "所选区域涉及管控空域"),

    SYSTEM_HANDLE_ERROR(502, "系统处理异常！"),

    REMAIN_AD_FULL(480, "部分设备广告位已满！"),

    REQUEST_FAILED(503, "远程请求失败！"),

    CODE_INVALID(504, "验证码已经失效，请从新获取！"),

    CODE_EMPTY(505, "验证码不能为空！"),

    CODE_INCORRECT(506, "验证码错误！"),


    MODEL_PLAN_EXCEL_SIZE(507, "excel里面计划数目大于30条"),

    IMAGE_CODE_INCORRECT(506, "验证码错误！"),

    SAVE_ERROR(500, "数据保存失败"),

    DATA_ERROR(500, "数据有误"),

    UNLOCK_FAIL(501, "当前用户非锁定状态,用户解锁失败！"),

    LOCKED(501, "当前用户已锁定"),

    IN_ANOMALY(502, "当前认证状态异常"),

    RELIEVE_FAIL(502, "当前认证状态正常，解除异常失败"),

    SYS_USER_FAIL(508, "用户未实名认证"),

    SYS_USER_INFO(509, "不能取消认证"),

    SYS_USERNAME_FAIL(509, "姓名格式错误"),

    SYS_USER_UPDATE_FAIL(509, "不能进行修改"),
    ROLE_ID_IS_NULL(509, "角色Id不能为空"),

    DELETE_ERROR(500, "数据删除失败"),

    UPDATE_ERROR(500, "更新失败"),

    STOP_ERROR(500, "取消或终止失败"),


    ID_CARD_ERROR(509, "身份证格式错误"),

    PHONE_ERROR(509, "手机号码格式错误"),

    USER_NOT_EXIST(405, "您输入的账号不存在，请确认后重新输入。"),
    DUPLICATE_ACCOUNT(405, "账号重复,请输入其他账号"),

    PWD_ERROR(405, "用户名或密码错误"),

    COM_ERROR(405, "请选择小区"),

    USER_PWD_ISNULL(405, "用户名和密码不能为空。"),

    BUILDING_TOKEN_FAIL(500, "生成token失败"),

    OVER_TIME(500, "登录已过期"),
    MENU_DELETE(500, "存在下级菜单,无法删除"),

    TOKEN_ERROR(3, "登录已过期"),

    RSA_ERROR(406, "RSA生成出现异常"),

    ILLEGAL_PARAM(400, "参数非法"),

    DISABLE_PARAM(500, "禁用失败"),

    AUDIT_EXCEPTION(500, "审核出现异常"),

    DATA_ISNULL(409, "excel里面的字段不能为空或者格式不对"),

    DATA_FORMAT(410, "excel里面的字段格式不对"),

    VALIDATED_EXCEPTION(10001, "参数格式验证错误"),


    UPLOAD_FAILED(10004, "文件上传失败"),

    FILE_DELETE_FAILED(10005, "文件删除失败"),

    GET_FILE_INFO_FAILED(10006, "获取文件信息失败"),

    GET_STORAGE_URL_FAILED(10007, "获取storage_url失败"),

    GET_NOTICE_AND_ERROR_FAILED(10012, "获取公告信息和异常信息失败"),

    GET_IP_ERROR(10013, "获取id地址错误"),

    TAKE_EFFECT_SUCCESS(500, "生效失败"),

    ENCRYPT_ERROR(500, "加密出现异常"),

    CITY_FILE_ERROR(500, "文件解析出现异常"),

    DECRYPT_ERROR(500, "解密出现异常"),

    JSON_FORMAT_EXCEPTION(407, "json格式异常"),
    PICTURE_FORMAT_ERROR(601, "图片格式错误"),


    /**
     * 文件处理错误
     */
    FILE_HANDLE_ERROR(470, "文件处理错误"),
    COMPANY_IS_NULL(471, "广告主不能为空"),
    SN_ID_IS_NULL(474, "请选择正确的设备"),
    PROHIBIT_REFUSAL_OF_REVIEW(473, "驳回失败,已存在上架发布记录"),
    HEADLINE_LENGTH(475, "广告标题请保持在30字以内"),
    CONTRACT_NUMBER_DOES_NOT_EXIST(476, "请在签约之后进行添加"),
    THE_CONTRACT_NUMBER_HAS_BEEN_USED(477, "合约号已被使用"),
    DUPLICATE_COMPANY_NAME(477, "公司名称不允许重复"),
    THE_CELL_DOES_NOT_EXIST(472, "小区不存在"),
    APPROVED_RECORD_EXISTS(478, "此广告主下有审核通过的广告记录,无法删除"),
    THE_RELEASE_HISTORY_CANNOT_BE_DELETED(478, "存在已上架的发布记录,无法删除广告"),
    CANNOT_BE_RELISTED_AFTER_BEING_REMOVED(479, "存在已上架的发布记录,无法删除广告"),
    DUPLICATE_USERNAME(480, "用户名已经注册,请更换用户名重试"),
    CANNOT_BE_LESS_THAN_CURRENT_TIME(481, "不能小于当前日期"),

    /**
     * 物业信息错误
     */
    PROPERTY_ALREADY_EXISTS(601, "物业信息已存在"),
    PROPERTY_DUPLICATE_ACCOUNT(601, "账号不允许重复"),
    ACCOUNT_IS_EMPTY(601, "账号不允许为空"),
    PLEASE_SELECT_A_PROPERTY_ACCOUNT(601, "请选择物业账号"),
    NAME_IS_REQUIRED(601, "物业名称不能为空");

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String msg;

    BusinessEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
