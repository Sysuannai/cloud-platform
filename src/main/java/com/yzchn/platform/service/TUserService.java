package com.yzchn.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.dao.TUserDao;
import com.yzchn.platform.model.dto.TUserDTO;
import com.yzchn.platform.model.dto.TUserRegisterParamDTO;
import com.yzchn.platform.model.entity.TUser;
import com.yzchn.platform.model.vo.TUserVO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 用户表(TUser)表服务接口
 *
 * @author songyuan
 * @since 2021-06-21 16:20:39
 */
public interface TUserService extends IService<TUser> {
    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-21 16:20:39
     */
    TUser selectUserById(Long id);

    /**
     * @return com.yzchn.platform.model.entity.TUser
     * @author 洛无异
     * @description //TODO       获得用户信息
     * @date 2021/6/21 16:30
     */
    TUser getUserByUserName(String userName);

    /**
     * @param tUserdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-21 16:20:39
     */
    IPage<TUser> findUserSelectList(TUserDTO tUserdto);


    /**
     * @param tUserdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-21 16:20:39
     */
    void insert(TUserDTO tUserdto);

    /**
     * @param tUserdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-21 16:20:39
     */
    TUser update(TUserDTO tUserdto);


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-21 16:20:39
     */
    void deleteById(List<Long> idList);

    /**
     * @param username 用户账号
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author 洛无异
     * @description //TODO 获得用户信息
     * @date 2021/6/21 16:28
     */
    UserDetails loadUserByUsername(String username);

    /**
     * @param username 账号
     * @param password 密码
     * @return java.lang.String
     * @author 洛无异
     * @description //TODO  用户登录
     * @date 2021/6/21 17:22
     */
    String login(String username, String password);

    /**
     * @param registerParam 封装参数集
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO    用户注册
     * @date 2021/6/22 10:41
     */
    TUser register(TUserRegisterParamDTO registerParam);

    /**
     * @param token 当前用户的token
     * @return void
     * @author 洛无异
     * @description //TODO     退出登录
     * @date 2021/6/22 13:48
     */
    void logout(String token);

    /**
     * @param userDTO 查询用户列表信息
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO    分页查询用户信息
     * @date 2021/6/22 15:55
     */
    IPage<TUserVO> findByUserNameList(TUserDTO userDTO);
}

