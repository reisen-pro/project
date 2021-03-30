package com.project.spring.annotation;

/**
 * @author Reisen
 * @title: TestController
 * @projectName project
 * @description: TODO
 * @date 2021/3/30 18:51
 */
public class TestController {
    @Autowired
    private TestService testService;

    public TestService getTestService() {
        return testService;
    }
}
