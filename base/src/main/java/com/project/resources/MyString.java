package com.project.resources;


import com.project.base.reference.M;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 根据String的源码模仿自定义的String类
 * 实现序列化 是为了网络传输
 * 比较接口<>
 * 字符序列
 *
 * @see java.lang.String
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
     * 补一个String的构造，用于测试。
     *
     * @param val
     */
    public MyString(String val) {
        this.value = getCharByBytes(val.getBytes());
    }

    /**
     * byte数组转换成char数组
     *
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
     *
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
     *
     * @param builder
     */
    public MyString(StringBuilder builder) {
        this.value = Arrays.copyOf(getCharByBytes(builder.toString().getBytes()), builder.length());
    }

    /**
     * 包下私有的构造方法
     * 快速的复制值数组 share aways true
     * 性能好  一个是逐一拷贝。当然是直接赋值快了。
     * 共享内部数组节约内存
     * 设置成public 就破坏了字符串的不可变性。
     * 缺点，可能造成内存泄漏
     *
     * @param value
     * @param share
     */
    MyString(char[] value, boolean share) {
        // assert share : "unshared not supported";
        this.value = value;
    }

    @Override
    public int length() {
        return this.value.length;
    }

    public boolean isEmpty() {
        return value.length == 0;
    }

    @Override
    public char charAt(int index) {
        if ((index < 0) || (index >= value.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }

    /**
     * 字节数组
     *
     * @return
     */
    public byte[] getBytes() {
        // return StringCoding.encode(value, 0, value.length);
        return new String(value).getBytes();
    }

    /**
     * 重写equals
     *
     * @param anObject
     * @return
     */
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        // 首先对类型进行匹配判断
        if (anObject instanceof MyString) {
            MyString anotherString = (MyString) anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char[] v1 = value;
                char[] v2 = anotherString.value;
                int i = 0;
                // 逐个比较数组里面的值
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(MyString o) {
        int len1 = value.length;
        int len2 = o.value.length;
        int lim = Math.min(len1, len2);
        char[] v1 = value;
        char[] v2 = o.value;

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    /**
     * 在string中 这个比较方法在一个内部类 CaseInsensitiveComparator 中
     * string的 compareToIgnoreCase 方法会返回次方法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int compare(MyString s1, MyString s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        // 求的比较小的那个字符串的长度
        int min = Math.min(n1, n2);
        for (int i = 0; i < min; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            // 这里大写比较了一次 然后再小写比较了一次
            // 原因是有一些字符，两个小写不同的字符可能大写以后是一个字符
            // 所以大写比较一次 小写再比较一次
            if (c1 != c2) {
                c1 = Character.toUpperCase(c1);
                c2 = Character.toUpperCase(c2);
                if (c1 != c2) {
                    c1 = Character.toLowerCase(c1);
                    c2 = Character.toLowerCase(c2);
                    if (c1 != c2) {
                        // No overflow because of numeric promotion
                        return c1 - c2;
                    }
                }
            }
        }
        return n1 - n2;
    }

    /**
     * @param prefix
     * @param toffset
     * @return
     */
    public boolean startsWith(MyString prefix, int toffset) {
        char[] val1 = this.value;
        char[] val2 = prefix.value;
        int preLen = prefix.value.length;
        int start = 0;
        if (toffset < 0 || toffset > val1.length - preLen) {
            return false;
        }
        while (--preLen >= 0) {
            if (val1[toffset++] != val2[start++]) {
                return false;
            }
        }
        return true;
    }

    public boolean endsWith(MyString suffix) {
        return startsWith(suffix, value.length - suffix.value.length);
    }

    public boolean startsWith(MyString prefix) {
        return startsWith(prefix, 0);
    }

    public int indexOf(int ch) {
        return indexOf(ch, 0);
    }

    public int indexOf(int ch, int fromIndex) {
        final int max = value.length;
        if (fromIndex < 0) {
            fromIndex = 0;
        } else if (fromIndex >= max) {
            // Note: fromIndex might be near -1>>>1.
            return -1;
        }

        // 小于一万
        if (ch < Character.MIN_SUPPLEMENTARY_CODE_POINT) {
            // handle most cases here (ch is a BMP code point or a
            // negative value (invalid code point))
            final char[] value = this.value;
            // 循环去判断 返回数组下标
            for (int i = fromIndex; i < max; i++) {
                if (value[i] == ch) {
                    return i;
                }
            }
            return -1;
        } else {
            // 大于等于10000个字符
            return indexOfSupplementary(ch, fromIndex);
        }
    }

    private int indexOfSupplementary(int ch, int fromIndex) {
        // Character.isValidCodePoint(ch)
        int plane = fromIndex >>> 16;
        if (plane < ((0X10FFFF + 1) >>> 16)) {
            final char[] value = this.value;
            final char hi = Character.highSurrogate(ch);
            final char lo = Character.lowSurrogate(ch);
            final int max = value.length - 1;
            for (int i = fromIndex; i < max; i++) {
                if (value[i] == hi && value[i + 1] == lo) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MyString a = new MyString("hello");
        System.out.println(a.startsWith(new MyString("he")));
        System.out.println(a.endsWith(new MyString("lo")));

        System.out.println(a.hashCode());
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
}
