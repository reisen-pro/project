package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity配置信息
 */
@Configuration
//开启SpringSecurity的WEB相关的功能
@EnableWebSecurity
//开启SpringSecurity中的注解
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class SpringSeurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置认证信息的提供方，这里使用基于数据库的方式提供认证信息的管理
     * @param auth 用于构建认证提供方
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        //jdbcDao.setDataSource(ApplicationContextUtils.getBean(DruidDataSource.class));
        jdbcDao.setUsersByUsernameQuery("select username,password,status as enabled from user where username=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select\n" +
                "                u.username,r.name as authority\n" +
                "                from user u\n" +
                "                join user_role ur\n" +
                "                on u.id=ur.user_id\n" +
                "                join role r\n" +
                "                on r.id=ur.role_id\n" +
                "                where u.username=?");
        auth.userDetailsService(jdbcDao);
    }

    /**
     * SpringSecurity的具体配置
     * @param http 相当于xml中http节点下的配置信息
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //角色名在底层已经拼接了ROLE_，这里不用添加
        http.authorizeRequests().mvcMatchers("/depts").hasAnyRole("USER");
        //其它路径的拦截可以在这里配置
        //http.authorizeRequests().mvcMatchers("/hello/method02").hasAnyRole("MANAGER");
        // 关闭csrf保护功能（跨域访问）
        http.csrf().disable();
        //……
        //登录页面（实际只是提示用户没有权限访问）
        //登出页面
        http.formLogin()
                //登录页
                .loginPage("http://127.0.0.1:8020/BootStrapVue/login.html")
                //登录处理逻辑,这个值和表单action值对应
                .loginProcessingUrl("/user/login")
                //登录成功后的处理逻辑
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        //HttpUtils.writeJson(resp, JsonResult.buildSuccess(HttpStatusConstant.SUCCESS, authentication.getPrincipal()), HttpStatusConstant.SUCCESS);
                    }
                })
                //登录失败后的处理逻辑
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        System.out.println(e);
                        //HttpUtils.writeJson(resp, JsonResult.buildFailure(HttpStatusConstant.LOGIN_FAILURE, e.getLocalizedMessage()), HttpStatusConstant.LOGIN_FAILURE);
                    }
                })
                //登录页面用户任意访问
                .permitAll()
                .and()
                //注销
                .logout()
                //注销操作的url
                .logoutUrl("/user/logout")
                //注销操作成功后跳转的页面
                .logoutSuccessUrl("/login.html")
                //指定是否在注销时让HttpSession失效
                .invalidateHttpSession(true)
                //注销行为任意访问
                .permitAll();
        //session并发控制
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
    }

}
