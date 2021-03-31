package com.project.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Reisen
 * @title: ThreadLocalController
 * @projectName project
 * @description: 本地线程thread local发生的内存泄漏问题
 * @date 2021/3/31 20:06
 */
@RestController
public class ThreadLocalController {
    // 创建线程局部变量。 变量的初始值是通过在Supplier上调用get方法确定的。
    private final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    @GetMapping("wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        currentUser.set(userId);
        String after = Thread.currentThread().getName() + ":" + currentUser.get();
        Map result = new HashMap();
        result.put("before",before);
        result.put("after",after);
        return result;
    }

    @GetMapping("wrongFix")
    public Map wrongFix(@RequestParam("userId") Integer userId) {
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        currentUser.set(userId);
        try {
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
            Map result = new HashMap();
            result.put("before",before);
            result.put("after",after);
            return result;
        } finally {
            // 在finally代码块中删除ThreadLocal中的数据，确保数据不串
            currentUser.remove();
        }
    }
}
