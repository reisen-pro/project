package com.project.resources;

import java.io.ObjectStreamField;
import java.io.Serializable;
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
        this.value = new char[]{};
    }

    /**
     * 其实就是创建了一个original的副本。
     * 除非需要显式的original副本，否则不需要使用此构造函数，因为字符串是不可变的。
     * @param original
     */
    public MyString(MyString original) {
        this.value = original.value;
        this.hash = original.hash;
    }

    /**
     * 分配一个新的String以便它表示字符数组参数中当前包含的字符序列。 字符数组的内容被复制； 字符数组的后续修改不会影响新创建的字符串。
     * @param value
     */
    public MyString(char[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    /**
     * 分配一个新的String ，该String包含来自character array参数的子数组的字符。
     * offset参数是子数组第一个字符的索引，而count参数指定子数组的长度。 子数组的内容被复制； 字符数组的后续修改不会影响新创建的字符串。
     * @param value 作为字符源的数组
     * @param offset 初始偏移
     * @param count 长度
     */
    public MyString(char value[], int offset, int count) {
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
        // Note: offset or count might be near -1>>>1.
        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
        this.value = Arrays.copyOfRange(value, offset, offset+count);
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
