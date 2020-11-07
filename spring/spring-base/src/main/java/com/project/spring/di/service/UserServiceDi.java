package com.project.spring.di.service;

import com.project.spring.di.dao.UserDaoDi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceDi {

    /**
     * 使用@Autowired注解来实现自动装配
     * 1.可以在构造器上修饰
     * 2.可以在setter方法上修饰
     *
     * @Inject
     * 1、@Inject是JSR330 (Dependency Injection for Java)中的规范，需要导入javax.inject.Inject;实现注入。
     * 2、@Inject是根据类型进行自动装配的，如果需要按名称进行装配，则需要配合@Named；
     * 3、@Inject可以作用在变量、setter方法、构造函数上。
     */
    private UserDaoDi userDao;

    public UserServiceDi() {}

    public void setUserDao(UserDaoDi userDao) {
        this.userDao = userDao;
        log.info("通过set设置的dao,{}", userDao);
    }

    public UserServiceDi(UserDaoDi userDao) {
        this.userDao = userDao;
        log.info("通过构造设置的userDao:{}", userDao);
    }

    public void save() {
        userDao.save();
    }
}
