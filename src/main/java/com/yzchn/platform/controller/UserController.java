package com.yzchn.platform.controller;

import com.yzchn.platform.common.OprLogConst;
import com.yzchn.platform.common.annotation.OperLog;
import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.exception.BusinessException;
import com.yzchn.platform.common.utils.HttpUtils;
import com.yzchn.platform.common.utils.Response;
import com.yzchn.platform.common.utils.StringUtils;
import com.yzchn.platform.model.dto.LoginParamDTO;
import com.yzchn.platform.model.dto.TUserDTO;
import com.yzchn.platform.model.dto.TUserRegisterParamDTO;
import com.yzchn.platform.model.entity.TRole;
import com.yzchn.platform.model.entity.TUser;
import com.yzchn.platform.model.vo.UserInfoVO;
import com.yzchn.platform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2021年06月21日 17:10:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private TUserService userService;
    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private TMenuService menuService;

    @Autowired
    private TRoleService roleService;

    /**
     * @param registerParam 封装参数集
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO    用户注册
     * @date 2021/6/22 10:41
     */
    @PostMapping("/register")
    public Response register(@Validated @RequestBody TUserRegisterParamDTO registerParam) {
        TUser user = userService.register(registerParam);
        if (user == null) {
            return Response.error(BusinessEnum.SYSTEM_HANDLE_ERROR);
        }
        return Response.ok(BusinessEnum.SUCCESS, user);
    }

    /**
     * @param loginParam
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO  用户登录
     * @date 2021/6/22 10:35
     */
    @PostMapping("/login")
    @ResponseBody
    @OperLog(operModul = "首页-用户登录", operType = OprLogConst.SELECT, operDesc = "用户登录功能")
    public Response login(HttpServletRequest request, @RequestBody LoginParamDTO loginParam) {
        String token = userService.login(loginParam.getUsername(), loginParam.getPassword());
        if (StringUtils.isObjectEmpty(token)) {
            throw new BusinessException(BusinessEnum.PWD_ERROR);
        }
        return Response.ok(BusinessEnum.SUCCESS, new HashMap<String, String>(2) {{
            put("token", token);
            put("tokenHeader", tokenHeader);
        }});
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO     退出登录
     * @date 2021/6/22 13:51
     */
    @GetMapping("/logout")
    public Response logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        userService.logout(token);
        return Response.ok(BusinessEnum.SUCCESS);
    }

    /**
     * @param userDTO 查询用户列表信息
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO    分页查询用户信息
     * @date 2021/6/22 15:55
     */
    @PostMapping("/findByUserNameList")
    public Response findByUserNameList(@RequestBody TUserDTO userDTO) {
        return Response.ok(BusinessEnum.SUCCESS, userService.findByUserNameList(userDTO));
    }

    /**
     * @return com.yzchn.platform.common.utils.Response
     * @author 洛无异
     * @description //TODO 获得当前用户信息
     * @date 2021/6/23 13:57
     */
    @GetMapping("/getUserInfo")
    public Response getUserInfo() {
        // 获得当前用户登录的信息
        // 获得用户名
        List<TRole> roleList = roleService.getRoleList(securityUserService.getUserId());
        return Response.ok(BusinessEnum.SUCCESS, UserInfoVO.builder()
                .userName(securityUserService.getUserName())
                .icon(securityUserService.getUserDetails().getUmsAdmin().getIcon())
                .menuList(menuService.getMenuList(securityUserService.getUserId()))
                .roles(roleList.stream().map(TRole::getName).collect(Collectors.toList())));
    }

    @GetMapping("/sendGet")
    public void sendGet() {
        String s = HttpUtils.sendGet("http://127.0.0.1:8089/user/sendGetMessage", null);
        System.out.println(s);
    }

    @GetMapping("/sendGetMessage")
    public String sendGetMessage() {
        return "sendGetMessage";
    }
}
