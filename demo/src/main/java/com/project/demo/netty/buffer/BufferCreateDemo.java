package com.project.demo.netty.buffer;

public class BufferCreateDemo {
    public static void main(String[] args) {
        //方式1：allocate方式直接分配，内部将隐含的创建一个数组
        ByteBuffer allocate = ByteBuffer.allocate(10);
        //方式2：通过wrap根据一个已有的数组创建
        byte[] bytes = new byte[10];
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        //方式3：通过wrap根据一个已有的数组指定区间创建
        ByteBuffer wrapoffset = ByteBuffer.wrap(bytes, 2, 5);

        //打印出刚刚创建的缓冲区的相关信息
//        print(allocate, wrap, wrapoffset);
    }

    private static void print(Buffer... buffers) {
        for (Buffer buffer : buffers) {
            System.out.println("capacity=" + buffer.capacity()
                    + ",limit=" + buffer.limit()
                    + ",position=" + buffer.position()
                    + ",hasRemaining:" + buffer.hasArray()
                    + ",remaining=" + buffer.remaining()
                    + ",hasArray=" + buffer.hasArray()
                    + ",isReadOnly=" + buffer.isReadOnly()
                    + ",arrayOffset=" + buffer.arrayOffset());
        }
    }
}
