package com.project.springboot.security.common.util;

import com.project.springboot.security.common.constans.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * token工具类
 */
public class JwtTokenUtils {


    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     * 使用同一个key，如果多个服务的时候，就可以保证使用相同key加密，相同key解析
     */
    private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);

    public static String createToken(String username, String id, List<String> roles, boolean isRememberMe) {
        // 是否记住用户 会影响token过期时间
        long expiration = isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;
        // token创建日期
        final Date createdDate = new Date();
        // 过期时间为 创建时间+设定的过期时间
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        // 生成token 通过Jwts
        String tokenPrefix = Jwts.builder()
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .claim(SecurityConstants.ROLE_CLAIMS, String.join(",", roles))
                .setId(id)
                // 发布者信息
                .setIssuer("Reisen")
                // 从什么时候开始
                .setIssuedAt(createdDate)
                // 用户名
                .setSubject(username)
                // 过期时间
                .setExpiration(expirationDate)
                // 创建
                .compact();
        // 添加 token 前缀 "Bearer "
        return SecurityConstants.TOKEN_PREFIX + tokenPrefix;
    }

    /**
     * 根据token得到id
     *
     * @param token token
     * @return id
     */
    public static String getId(String token) {
        Claims claims = getClaims(token);
        return claims.getId();
    }

    /**
     * 用户名密码验证的令牌
     *
     * @param token token
     * @return 用户名密码信息
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        // 解析
        Claims claims = getClaims(token);
        // 角色解析
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        // 得到用户名
        String userName = claims.getSubject();
        // 用户名,token,权限信息
        return new UsernamePasswordAuthenticationToken(userName, token, authorities);
    }

    private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        String role = (String) claims.get(SecurityConstants.ROLE_CLAIMS);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // token解析
    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        // 加密
        System.out.println(JwtTokenUtils.createToken("jack", "001", new ArrayList<>(), true));
        // 解析
        System.out.println(JwtTokenUtils.getClaims("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJyb2wiOiIiLCJqdGkiOiIwMDEiLCJpc3MiOiJSZWlzZW4iLCJpYXQiOjE2MDk3NDg1MDMsInN1YiI6ImphY2siLCJleHAiOjE2MTAzNTMzMDN9.TvqeQqZs4VAmeWyLLrwWTefWiMqMUGILG4uCBdmCT3I"));
    }
}
