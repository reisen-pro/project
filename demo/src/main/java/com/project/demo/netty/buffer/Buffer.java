package com.project.demo.netty.buffer;

/**
 * 容量（ Capacity）
 * <p>
 * 缓冲区能够容纳的数据元素的最大数量，可以理解为数组的长度。 这一容量在缓冲区创建时被设定，并且永远不能被改变。
 * <p>
 * 上界（ Limit）
 * <p>
 * 缓冲区的第一个不能被读或写的元素。或者说，缓冲区中现存元素的计数。
 * <p>
 * 位置（ Position）
 * <p>
 * 下一个要被读或写的元素的索引。Buffer类提供了get( )和 put( )函数 来读取或存入数据，position位置会自动进行相应的更新。
 * <p>
 * 标记（ Mark）
 * <p>
 * 一个备忘位置。调用 mark( )来设定 mark = postion。调用 reset( )设定 position = mark。标记在设定前是未定义的(undefined)。
 * <p>
 * 这四个属性之间总是遵循以下关系：
 * 0 <= mark <= position <= limit <= capacity
 */


public abstract class Buffer {
    // Invariants: mark <= position <= limit <= capacity
    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;

    //JDK1.4时，引入的api
    public final int capacity()//返回此缓冲区的容量
    {
        return 0;
    }

    public final int position()//返回此缓冲区的位置
    {
        return 0;
    }

    public final Buffer position(int newPositio) {
        return null;
    }//设置此缓冲区的位置

    public final int limit() {
        return 0;
    }//返回此缓冲区的限制

    public final Buffer limit(int newLimit) {
        return null;
    }//设置此缓冲区的限制

    public final Buffer mark() {
        return null;
    }//在此缓冲区的位置设置标记

    public final Buffer reset() {
        return null;
    }//将此缓冲区的位置重置为以前标记的位置

    public final Buffer clear() {
        return null;
    }//清除此缓冲区

    public final Buffer flip() {
        return null;
    }//反转此缓冲区

    public final Buffer rewind() {
        return null;
    }//重绕此缓冲区

    public final int remaining() {
        return 0;
    }//返回当前位置与限制之间的元素数

    public final boolean hasRemaining() {
        return false;
    }//告知在当前位置和限制之间是否有元素

    public abstract boolean isReadOnly();//告知此缓冲区是否为只读缓冲区

    //JDK1.6时引入的api
    public abstract boolean hasArray();//告知此缓冲区是否具有可访问的底层实现数组

    public abstract Object array();//返回此缓冲区的底层实现数组

    public abstract int arrayOffset();//返回此缓冲区的底层实现数组中第一个缓冲区元素的偏移量

    public abstract boolean isDirect();//告知此缓冲区是否为直接缓冲区
}
