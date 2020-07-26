package com.project.demo.netty.buffer;

public abstract class ByteBuffer {
    //缓冲区创建相关api
    public static ByteBuffer allocateDirect(int capacity) {
        return null;
    }

    public static ByteBuffer allocate(int capacity) {
        return null;
    }

    public static ByteBuffer wrap(byte[] array) {
        return null;
    }

    public static ByteBuffer wrap(byte[] array, int offset, int length) {
        return null;
    }

    //缓存区存取相关API
    public abstract byte get( );//从当前位置position上get，get之后，position会自动+1
    public abstract byte get (int index);//从绝对位置get
    public abstract ByteBuffer put (byte b);//从当前位置上普通，put之后，position会自动+1
    public abstract ByteBuffer put (int index, byte b);//从绝对位置上put
}
