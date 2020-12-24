package com.project.base.collection;

import java.io.Serializable;
import java.util.*;

/**
 * @see java.util.HashMap
 */
public class MapHashMap<K, V> extends AbstractMap<K, V>
        implements Map<K, V>, Cloneable, Serializable {

    /**
     * 初始默认容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * 最大容量   1073741824
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 变成红黑树的阈值
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 取消变红黑树的值
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * 红黑树最小容量
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * node 节点
     * @param <K>
     * @param <V>
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        MapHashMap.Node<K,V> next;

        Node(int hash, K key, V value, MapHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println(1 << 30);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
