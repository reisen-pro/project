package com.project.base.reference;

import java.nio.ByteBuffer;

/**
 * @author Reisen
 * @title: TestDirectByteBuffer
 * @projectName project
 * @description: TODO
 * @date 2020/10/27 23:15
 */
public class TestDirectByteBuffer {
    // jvm内部的内存
    ByteBuffer bufferIn = ByteBuffer.allocate(1024);
    // jvm缓冲区直接分配到外面的内存
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
}
