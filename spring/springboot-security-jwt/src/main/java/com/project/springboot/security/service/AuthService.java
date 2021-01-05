package com.project.springboot.security.service;

import com.project.springboot.security.common.util.CurrentUserUtils;
import com.project.springboot.security.common.util.JwtTokenUtils;
import com.project.springboot.security.dto.LoginRequest;
import com.project.springboot.security.entity.JwtUser;
import com.project.springboot.system.entity.User;
import com.project.springboot.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 权限服务
 * BadCredentialsException 错误的凭证
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthService {
    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;
    private final CurrentUserUtils currentUserUtils;

    public String getToken(LoginRequest loginRequest) {
        // 先根据用户名查询用户
        User user = userService.find(loginRequest.getUsername());
        // 检查用户密码是否匹配
        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }
        JwtUser jwtUser = new JwtUser(user);
        // 用户是否生效
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to log in");
        }
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 根据登录的用户信息生成token
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId().toString(), authorities, loginRequest.getRememberMe());
        // 保存到redis中
        stringRedisTemplate.opsForValue().set(user.getId().toString(), token);
        return token;
    }

    /**
     * 将redis中的token删除
     */
    public void deleteTokenFromRedis() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getId().toString());
    }
}
