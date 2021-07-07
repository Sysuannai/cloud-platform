package com.yzchn.platform.common.security.component;

import com.yzchn.platform.common.Constants;
import com.yzchn.platform.common.exception.BusinessEnum;
import com.yzchn.platform.common.exception.BusinessException;
import com.yzchn.platform.common.utils.JwtTokenUtil;
import com.yzchn.platform.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 * Created by macro on 2018/4/26.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException, BusinessException {
        String authToken = request.getHeader(this.tokenHeader);
        authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWJJZCI6MSwic3ViIjoid3V5aSIsImNyZWF0ZWQiOjE2MjQ5MjkwMzYxOTgsInBpZCI6MSwiZXhwIjoxNjI1NTMzODM2fQ.d3cBFVQFl65ztLuqfNUCAmNaU2sYGhgxQ8ZqYwAFJrAHBa8ZTkEv6gHwzgUI3YWkRxCDEmzatErcPkSAgS2ENg";
        if (authToken != null) {
            // 在redis中判断token是否存在
//            if (!redisService.hasKey(Constants.USER_INFO + authToken)) {
//                new RestAuthenticationEntryPoint().commence(request, response, new BusinessException(BusinessEnum.TOKEN_ERROR.getMsg()));
//                return;
//            }
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            LOGGER.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
