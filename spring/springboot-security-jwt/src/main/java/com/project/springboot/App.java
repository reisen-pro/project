package com.project.springboot;

import com.project.springboot.system.entity.Role;
import com.project.springboot.system.entity.User;
import com.project.springboot.system.entity.UserRole;
import com.project.springboot.system.enums.RoleType;
import com.project.springboot.system.repository.RoleRepository;
import com.project.springboot.system.repository.UserRepository;
import com.project.springboot.system.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(java.lang.String... args) {
        //初始化角色信息
        for (RoleType roleType : RoleType.values()) {
            roleRepository.save(new Role(roleType.getName(), roleType.getDescription()));
        }
        //初始化一个 admin 用户
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .enabled(true)
                .fullName("admin")
                .userName("root")
                .password(bCryptPasswordEncoder.encode("root"))
                .build();
        userRepository.save(user);
        Role role = roleRepository.findByName(RoleType.ADMIN.getName()).get();
        userRoleRepository.save(new UserRole(user, role));
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}