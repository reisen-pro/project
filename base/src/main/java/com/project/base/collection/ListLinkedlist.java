package com.project.base.collection;

import java.util.LinkedList;

/**
 * @author reisen
 */
public class ListLinkedlist<E> extends LinkedList {


    /**
     * LinkedList
     *
     * @see java.util.AbstractSequentialList extends
     * @see java.util.AbstractList extends
     * @see java.util.AbstractCollection implements
     * @see java.util.Collection
     * 实现
     * @see java.util.List
     * @see java.util.Deque
     * @see java.lang.Cloneable
     * @see java.io.Serializable
     */

    transient int size = 0;
    transient int modCount = 0;
    transient ListLinkedlist.Node<E> first;
    transient ListLinkedlist.Node<E> last;

    /**
     * 节点
     *
     * @param <E> 集合中的参数
     */
    private static class Node<E> {
        E item;
        // 指向后一个节点
        ListLinkedlist.Node<E> next;
        // 指向前一个节点
        ListLinkedlist.Node<E> prev;

        Node(ListLinkedlist.Node<E> prev, E element, ListLinkedlist.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * 头插
     *
     * @param e 链表e做为第一个元素
     */
    private void linkFirst(E e) {
        // 获取头信息 first
        final ListLinkedlist.Node<E> f = first;
        // 新建一个节点，尾部指向之前的 头元素 first
        final ListLinkedlist.Node<E> newNode = new ListLinkedlist.Node<>(null, e, f);
        // first指向新的节点
        first = newNode;
        // 如果之前是空链表，新建的节点，也就是最后一个节点
        if (f == null) {
            last = newNode;
        } else {
            // 原来的第一个节点（现在的第二个）指向新建的头节点
            f.prev = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 尾插
     *
     * @param e 链表e做为最后一个节点
     */
    private void linkLast(E e) {
        // 获取尾部节点
        final Node<E> l = last;
        // 新建一个节点，头部指向之前的 尾节点 last
        final Node<E> newNode = new Node<>(l, e, null);
        // last 指向新建的节点
        last = newNode;
        // 如果之前是空链表， 新建的节点也是第一个节点
        if (l == null) {
            first = newNode;
        } else {
            // 原来的尾节点尾部指向新建的尾节点
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 在指定节点 前插入一个元素
     *
     * @param e    插入元素e
     * @param succ 指定节点succ
     */
    void linkBefore(E e, Node<E> succ) {
        // 获取指定节点 succ 前面的一个节点
        final Node<E> pred = succ.prev;
        // 新建一个节点，头部指向 succ 前面的节点，尾部指向 succ 节点，数据为 e
        final Node<E> newNode = new Node<>(pred, e, succ);
        // 让 succ 节点头部指向 新建的节点
        succ.prev = newNode;
        // 如果 succ 前面的节点为空，说明 succ 就是第一个节点，那现在新建的节点就变成第一个节点了
        if (pred == null) {
            first = newNode;
        } else {
            //如果前面有节点，让前面的节点
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 删除头节点并返回该节点上的数据，假设不为 null
     *
     * @param f 删除的节点
     * @return 节点上的数据
     */
    private E unlinkFirst(Node<E> f) {
        // 获取数据，一会儿返回
        final E element = f.item;
        // 获取头节点后面一个节点
        final Node<E> next = f.next;
        // 使头节点上数据为空，尾部指向空
        f.item = null;
        // help GC
        f.next = null;
        // 现在头节点后边的节点变成第一个了
        first = next;
        // 如果头节点后面的节点为 null，说明移除这个节点后，链表里没节点了
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * 删除尾部节点并返回，假设不为空
     *
     * @param l 尾节点
     * @return 尾节点上的数据
     */
    private E unlinkLast(Node<E> l) {

        final E element = l.item;
        // 获取倒数第二个节点
        final Node<E> prev = l.prev;
        // 尾节点数据、尾指针置为空
        l.item = null;
        // help GC
        l.prev = null;
        // 现在倒数第二变成倒数第一了
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    public static void main(String[] args) {
        ListLinkedlist<String> listLinkedlist = new ListLinkedlist<>();
        // 插入头数据
        listLinkedlist.linkFirst("c");
        listLinkedlist.linkFirst("b");
        // 插入尾数据
        listLinkedlist.linkLast("d");
        // 在指定节点前插入数据
        listLinkedlist.linkBefore("a", listLinkedlist.first);
        // 删除头节点上并返回该节点上的数据
        listLinkedlist.unlinkFirst(listLinkedlist.first);
        // 删除尾节点上并返回该节点上的数据
        listLinkedlist.unlinkLast(listLinkedlist.last);
    }
}
