package com.yzchn.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzchn.platform.common.Constants;
import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.exception.BusinessException;
import com.yzchn.platform.common.tenum.ModelIsDelEnum;
import com.yzchn.platform.common.utils.JwtTokenUtil;
import com.yzchn.platform.common.utils.StringUtils;
import com.yzchn.platform.common.utils.copy.ModelUtil;
import com.yzchn.platform.dao.TUserDao;
import com.yzchn.platform.model.bo.TUserDetails;
import com.yzchn.platform.model.dto.TUserDTO;
import com.yzchn.platform.model.dto.TUserRegisterParamDTO;
import com.yzchn.platform.model.entity.TUser;
import com.yzchn.platform.common.tenum.AdminEnum;
import com.yzchn.platform.model.vo.TUserVO;
import com.yzchn.platform.service.RedisService;
import com.yzchn.platform.service.SecurityUserService;
import com.yzchn.platform.service.TResourceService;
import com.yzchn.platform.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;
import java.time.LocalDateTime;

/**
 * 用户表(TUser)表服务实现类
 *
 * @author songyuan
 * @since 2021-06-21 16:20:39
 */
@Service("tUserService")
public class TUserServiceImpl extends ServiceImpl<TUserDao, TUser> implements TUserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TResourceService resourceService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SecurityUserService securityUserService;

    /**
     * @param id 主键
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  通过ID查询单条数据
     * @date 2021-06-21 16:20:39
     */
    @Override
    public TUser selectUserById(Long id) {
        LambdaQueryWrapper<TUser> tUserlam = new LambdaQueryWrapper<TUser>();
        tUserlam.eq(TUser::getId, id);
        return baseMapper.selectOne(tUserlam);
    }

    /**
     * @param userName
     * @return com.yzchn.platform.model.entity.TUser
     * @author 洛无异
     * @description //TODO       获得用户信息
     * @date 2021/6/21 16:30
     */
    @Override
    public TUser getUserByUserName(String userName) {
        if (StringUtils.isObjectEmpty(userName)) {
            return null;
        }
        LambdaQueryWrapper<TUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(TUser::getUsername, userName).eq(TUser::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
        return baseMapper.selectOne(userLambdaQueryWrapper);
    }

    /**
     * @param tUserdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  分页查询数据
     * @date 2021-06-21 16:20:39
     */
    @Override
    public IPage<TUser> findUserSelectList(TUserDTO tUserdto) {
        IPage<TUser> tUserPage = new Page<>(tUserdto.getPageNum(), tUserdto.getPageSize());
        LambdaQueryWrapper<TUser> tUserWrapper = new LambdaQueryWrapper<>();

        return baseMapper.selectPage(tUserPage, tUserWrapper);
    }

    /**
     * @param tUserdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  新增数据
     * @date 2021-06-21 16:20:39
     */
    @Override
    public void insert(TUserDTO tUserdto) {
        TUser tUser = ModelUtil.copyModel(tUserdto, TUser.class);
        tUser.setIsDelete(ModelIsDelEnum.EFFECTIVE.getCode());
        tUser.setCreatedTime(LocalDateTime.now());
        baseMapper.insert(tUser);
    }


    /**
     * @param tUserdto 实例对象
     * @return 实例对象
     * @author 洛无异
     * @description //TODO  修改数据并返回
     * @date 2021-06-21 16:20:39
     */
    @Override
    public TUser update(TUserDTO tUserdto) {
        TUser tUser = ModelUtil.copyModel(tUserdto, TUser.class);
        tUser.setUpdatedTime(LocalDateTime.now());
        baseMapper.updateById(tUser);
        return baseMapper.selectById(tUser.getId());
    }


    /**
     * @param idList 主键
     * @return
     * @author 洛无异
     * @description //TODO    通过主键删除数据
     * @date 2021-06-21 16:20:39
     */
    @Override
    public void deleteById(List<Long> idList) {
        if (idList != null && idList.size() > 0) {
            for (int i = 0; i < idList.size(); i++) {
                baseMapper.deleteById(idList.get(i));
            }
        }
    }

    /**
     * @param username 用户账号
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author 洛无异
     * @description //TODO 获得用户信息
     * @date 2021/6/21 16:28
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        TUser user = getUserByUserName(username);
        if (user == null) {
            throw new BusinessException(BusinessEnum.PWD_ERROR);
        }
        return new TUserDetails(user, resourceService.getResourceList(user.getId()));
    }

    /**
     * @param username 账号
     * @param password 密码
     * @return java.lang.String
     * @author 洛无异
     * @description //TODO  用户登录
     * @date 2021/6/21 17:22
     */
    @Override
    public String login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BusinessException(BusinessEnum.PWD_ERROR);
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        redisService.set(Constants.USER_INFO + token, userDetails, Constants.AUTO_LOGIN_TIME_OUT);
        // 更新登录时间
        TUserDetails tUserDetails = (TUserDetails) userDetails;
        tUserDetails.getUmsAdmin().setLoginTime(LocalDateTime.now());
        baseMapper.updateById(tUserDetails.getUmsAdmin());
        return token;
    }

    /**
     * @param registerParam 封装参数集
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO    用户注册
     * @date 2021/6/22 10:41
     */
    @Override
    public TUser register(TUserRegisterParamDTO registerParam) {
        // 判断用户名是否重复
        LambdaQueryWrapper<TUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(TUser::getUsername, registerParam.getUsername());
        TUser byUser = baseMapper.selectOne(userLambdaQueryWrapper);
        if (byUser != null) {
            throw new BusinessException(BusinessEnum.DUPLICATE_ACCOUNT);
        }
        TUser user = ModelUtil.copyModel(registerParam, TUser.class);
        user.setIsAdmin(AdminEnum.GENERAL_USER.getCode());
        user.setIsDelete(ModelIsDelEnum.EFFECTIVE.getCode());
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setCreatedTime(LocalDateTime.now());
        baseMapper.insert(user);
        return user;
    }

    /**
     * @param token 当前用户的token
     * @return void
     * @author 洛无异
     * @description //TODO     退出登录
     * @date 2021/6/22 13:48
     */
    @Override
    public void logout(String token) {
        redisService.del(Constants.USER_INFO + token);
    }

    /**
     * @param userDTO 查询用户列表信息
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO    分页查询用户信息
     * @date 2021/6/22 15:55
     */
    @Override
    public IPage findByUserNameList(TUserDTO userDTO) {
        IPage<TUser> iPage = new Page<>(userDTO.getPageNum(), userDTO.getPageSize());
        LambdaQueryWrapper<TUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();

        userLambdaQueryWrapper.select(TUser::getId, TUser::getUsername, TUser::getNickName, TUser::getNote,
                TUser::getIsAdmin, TUser::getIcon, TUser::getLoginTime, TUser::getCreatedTime);

        userLambdaQueryWrapper.eq(TUser::getIsDelete, ModelIsDelEnum.EFFECTIVE.getCode());
        if (StringUtils.isObjectNotEmpty(userDTO.getUsername())) {
            userLambdaQueryWrapper.and(wapper -> {
                wapper.like(TUser::getUsername, userDTO.getUsername()).or().like(TUser::getNickName, userDTO.getUsername());
            });
        }
        return baseMapper.selectPage(iPage, userLambdaQueryWrapper);
    }
}
