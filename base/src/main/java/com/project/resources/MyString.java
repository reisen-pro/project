package com.project.resources;

import org.springframework.core.codec.StringDecoder;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 根据String的源码模仿自定义的String类
 * 实现序列化 是为了网络传输
 * 比较接口<>
 * 字符序列
 */
public class MyString implements Serializable, Comparable<MyString>, CharSequence {

    /**
     * The value is used for character storage 这个值用来存储字符
     */
    private final char[] value;

    /**
     * hash码 默认0 缓存字符串的哈希码
     */
    private int hash;

    /**
     * for interoperability 互通性
     */
    private static final long serialVersionUID = -6849794470754667710L;

    /**
     * 类字符串在序列化流协议中是特殊情况。 根据对象序列化规范第6.2节“流元素”，将String实例写入ObjectOutputStream中。
     */
    private static final ObjectStreamField[] serialPersistentFields =
            new ObjectStreamField[0];


    /**
     * 创建一个新的String对象，使其代表一个空字符序列
     */
    public MyString() {
        this.value = getCharByBytes("".getBytes());
    }

    /**
     * byte数组转换成char数组
     * @param bytes
     * @return
     */
    public char[] getCharByBytes(byte[] bytes) {
        // Charset.forName("UTF-8")
        Charset cs = StandardCharsets.UTF_8;
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    /**
     * 其实就是创建了一个original的副本。
     * 除非需要显式的original副本，否则不需要使用此构造函数，因为字符串是不可变的。
     *
     * @param original
     */
    public MyString(MyString original) {
        this.value = original.value;
        this.hash = original.hash;
    }

    /**
     * 分配一个新的String以便它表示字符数组参数中当前包含的字符序列。 字符数组的内容被复制； 字符数组的后续修改不会影响新创建的字符串。
     *
     * @param value
     */
    public MyString(char[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    /**
     * 分配一个新的String ，该String包含来自character array参数的子数组的字符。
     * offset参数是子数组第一个字符的索引，而count参数指定子数组的长度。 子数组的内容被复制； 字符数组的后续修改不会影响新创建的字符串。
     *
     * @param value  作为字符源的数组
     * @param offset 初始偏移
     * @param count  长度
     */
    public MyString(char[] value, int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count <= 0) {
            if (count < 0) {
                throw new StringIndexOutOfBoundsException(count);
            }
            if (offset <= value.length) {
                this.value = new MyString().value;
                return;
            }
        }
        // offset or count might be near -1>>>1.
        // -1>>> 1 == Integer.MAX_VALUE
        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        // 调用Arrays进行截取拷贝。 Arrays调用的System.arraycopy方法
        this.value = Arrays.copyOfRange(value, offset, offset + count);
    }

    /**
     * 用于检查参数
     *
     * @param bytes  原字符数组
     * @param offset 切入点
     * @param length 切入长度
     */
    private static void checkBounds(byte[] bytes, int offset, int length) {
        if (length < 0)
            throw new StringIndexOutOfBoundsException(length);
        if (offset < 0)
            throw new StringIndexOutOfBoundsException(offset);
        if (offset > bytes.length - length)
            // 参数为切入点的位置+切入的长度和
            throw new StringIndexOutOfBoundsException(offset + length);
    }

    /**
     * 通过使用指定的字符集解码指定的字节子String来构造新的String 。
     * 新String的长度是字符集的函数，因此可能不等于子String的长度。
     * 未指定给定字符集中给定字节无效时此构造函数的行为。 当需要对解码过程进行更多控制时，应使用java.nio.charset.CharsetDecoder类。
     *
     * @param bytes
     * @param offset
     * @param length
     * @param charsetName
     * @throws UnsupportedEncodingException
     */
    public MyString(byte bytes[], int offset, int length, String charsetName)
            throws UnsupportedEncodingException {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        checkBounds(bytes, offset, length);
        // 调用的StringCoding类 decode方法 找对应的解析器 然后对对象进行拷贝复制已新的编码进行解析返回
        //this.value = StringCoding.decode(charsetName, bytes, offset, length);
        this.value = null;
    }

    /**
     * 分配一个新字符串，该字符串包含当前在字符串缓冲区参数中包含的字符序列。
     * 字符串缓冲区的内容被复制；
     * 字符串缓冲区的后续修改不会影响新创建的字符串。
     * @param buffer
     */
    public MyString(StringBuffer buffer) {
        synchronized (buffer) {
            // 注意通过StringBuffer构造的时候加了同步，因为StringBuffer是线程安全的，这里复制的时候加锁也要保证线程安全。
            this.value = Arrays.copyOf(getCharByBytes(buffer.toString().getBytes()), buffer.length());
        }
    }

    /**
     * 这个就不需要加锁
     * @param builder
     */
    public MyString(StringBuilder builder) {
        this.value = Arrays.copyOf(getCharByBytes(builder.toString().getBytes()), builder.length());
    }

































    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public int compareTo(MyString o) {
        return 0;
    }
}
