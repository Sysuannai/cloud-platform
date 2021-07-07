package com.yzchn.platform.service;

import com.yzchn.platform.model.bo.TUserDetails;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName SecurityUserService.java
 * @Description TODO 用于获取当前用户登录信息
 * @createTime 2021年06月23日 09:44:00
 */
public interface SecurityUserService {

    /**
     * @return java.lang.Long
     * @author 洛无异
     * @description //TODO 获得当前登录的用户id
     * @date 2021/6/23 9:45
     */
    public Long getUserId();

    /**
     * @return java.lang.String
     * @author 洛无异
     * @description //TODO 获得当前登录的用户名称
     * @date 2021/6/23 9:45
     */
    public String getUserName();

    /**
     * @return com.yzchn.platform.model.bo.TUserDetails
     * @author 洛无异
     * @description //TODO 获得当前登录用户
     * @date 2021/6/23 14:10
     */
    public TUserDetails getUserDetails();

    /**
     * @return java.lang.Long
     * @author 洛无异
     * @description //TODO 获得当前账号父级别Id
     * @date 2021/6/28 10:17
     */
    public Long getPid();
}
