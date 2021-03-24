package com.project.base.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Reisen
 * @title: Atomic
 * @projectName project
 * @description: juc下的Atomic包
 * @date 2021/3/24 17:36
 */
public class Atomic {
    // 包括一些原子性的数据类型
    AtomicInteger atomicInteger = new AtomicInteger();
    // 原子引用
    AtomicReference<Atomic> atomicAtomicReference = new AtomicReference<>();
}
