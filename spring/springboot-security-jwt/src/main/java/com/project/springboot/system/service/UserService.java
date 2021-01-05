package com.project.springboot.system.service;

import com.google.common.collect.ImmutableMap;
import com.project.springboot.system.entity.Role;
import com.project.springboot.system.entity.User;
import com.project.springboot.system.entity.UserRole;
import com.project.springboot.system.enums.RoleType;
import com.project.springboot.system.exception.RoleNotFoundException;
import com.project.springboot.system.exception.UserNameAlreadyExistException;
import com.project.springboot.system.exception.UserNameNotFoundException;
import com.project.springboot.system.repository.RoleRepository;
import com.project.springboot.system.repository.UserRepository;
import com.project.springboot.system.repository.UserRoleRepository;
import com.project.springboot.system.web.representation.UserRepresentation;
import com.project.springboot.system.web.request.UserRegisterRequest;
import com.project.springboot.system.web.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    public static final String USERNAME = "username:";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void save(UserRegisterRequest userRegisterRequest) {
        // 校验用户名是否已存在
        ensureUserNameNotExist(userRegisterRequest.getUserName());

        User user = userRegisterRequest.toUser();
        // 密码加密
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        // 保存用户信息
        userRepository.save(user);

        //给用户绑定两个角色：用户和管理者
        Role studentRole =
                // 寻找名为USER的角色
                roleRepository.findByName(RoleType.USER.getName())
                        .orElseThrow(
                                () -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName()))
                        );

        Role managerRole =
                roleRepository.findByName(RoleType.MANAGER.getName())
                        .orElseThrow(
                                () -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.MANAGER.getName()))
                        );

        userRoleRepository.save(new UserRole(user, studentRole));
        userRoleRepository.save(new UserRole(user, managerRole));
    }

    public User find(String userName) {
        // ImmutableMap 简单的说就是一个不可变的map
        return userRepository.findByUserName(userName)
                .orElseThrow(
                        () -> new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName))
                );
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        User user = find(userUpdateRequest.getUserName());
        if (Objects.nonNull(userUpdateRequest.getFullName())) {
            user.setFullName(userUpdateRequest.getFullName());
        }
        if (Objects.nonNull(userUpdateRequest.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
        }
        if (Objects.nonNull(userUpdateRequest.getEnabled())) {
            user.setEnabled(userUpdateRequest.getEnabled());
        }
        userRepository.save(user);
    }


    public void delete(String userName) {
        if (!userRepository.existsByUserName(userName)) {
            throw new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName));
        }
        userRepository.deleteByUserName(userName);
    }

    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(User::toUserRepresentation);
    }

    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    private void ensureUserNameNotExist(String userName) {
        boolean exist = userRepository.findByUserName(userName).isPresent();
        if (exist) {
            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, userName));
        }
    }
}
