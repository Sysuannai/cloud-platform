package com.yzchn.platform.common.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yzchn.platform.common.Constants;
import com.yzchn.platform.common.security.component.DynamicSecurityService;
import com.yzchn.platform.model.entity.TResource;
import com.yzchn.platform.service.RedisService;
import com.yzchn.platform.service.TResourceService;
import com.yzchn.platform.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName CloudSecurityConfig.java
 * @Description TODO 项目启动时加载资源路径信息
 * @createTime 2021年06月21日 16:12:00
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CloudSecurityConfig extends SecurityConfig {

    @Autowired
    private TUserService userService;
    @Autowired
    private TResourceService resourceService;
    @Autowired
    private RedisService redisService;


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>(7);
                List<TResource> resourceList = resourceService.getBaseMapper().selectList(new LambdaQueryWrapper<>());
                for (TResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                redisService.set(Constants.CLOUD_RESOURCE_SIZE, map.size());
                return map;
            }
        };
    }
}
