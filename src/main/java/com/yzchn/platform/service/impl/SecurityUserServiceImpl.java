package com.yzchn.platform.service.impl;

import com.yzchn.platform.model.bo.TUserDetails;
import com.yzchn.platform.service.SecurityUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName SecurityUserServiceImpl.java
 * @Description TODO
 * @createTime 2021年06月23日 09:45:00
 */
@Service
public class SecurityUserServiceImpl implements SecurityUserService {


    /**
     * @return java.lang.String
     * @author 洛无异
     * @description //TODO 获得当前登录的用户id
     * @date 2021/6/23 9:45
     */
    @Override
    public Long getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return ((TUserDetails) userDetails).getUmsAdmin().getId();
    }

    /**
     * @return java.lang.String
     * @author 洛无异
     * @description //TODO 获得当前登录的用户名称
     * @date 2021/6/23 9:45
     */
    @Override
    public String getUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails.getUsername();
    }

    /**
     * @return com.yzchn.platform.model.bo.TUserDetails
     * @author 洛无异
     * @description //TODO 获得当前登录用户
     * @date 2021/6/23 14:10
     */
    @Override
    public TUserDetails getUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return (TUserDetails) userDetails;
    }

    /**
     * @return java.lang.Long
     * @author 洛无异
     * @description //TODO 获得当前账号父级别Id
     * @date 2021/6/28 10:17
     */
    @Override
    public Long getPid() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return ((TUserDetails) userDetails).getUmsAdmin().getPid();
    }
}
