package com.project.base.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Reisen
 * @title: Locks
 * @projectName project
 * @description: juc下locks包
 * @date 2021/3/24 17:38
 */
public class Locks {
    // lock包下包括 lock readwrite lock读写锁 可重入锁 ReentrantLock
    Lock lock = new ReentrantLock();
    ReentrantLock reentrantLock = new ReentrantLock();
    ReadWriteLock lockRead = new ReentrantReadWriteLock();

}
