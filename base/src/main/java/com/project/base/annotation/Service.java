package com.project.base.annotation;

// 定义一个注解，用于标记需要记录日志的方法
@interface Loggable {
    boolean enable() default true;
}

// 使用注解的示例类
class Service {
    @Loggable(enable = true)
    public void performAction() {
        System.out.println("Action performed");
    }
}

// 测试类
class TestDynamicAnnotations {
    public static void main(String[] args) {
        Service service = new Service();
        boolean isLoggingEnabled = checkLoggingConfig(); // 假设这个函数检查配置并返回是否开启日志

        if (isLoggingEnabled) {
            // 模拟动态注解的应用
            System.out.println("Logging is enabled");
            service.performAction(); // 打印日志
        } else {
            // 模拟动态注解的不应用
            System.out.println("Logging is disabled");
            service.performAction(); // 不打印日志
        }
    }

    private static boolean checkLoggingConfig() {
        // 这里只是一个示例，实际中你可能需要读取配置文件或环境变量
        return true; // 假设配置中开启了日志
    }
}