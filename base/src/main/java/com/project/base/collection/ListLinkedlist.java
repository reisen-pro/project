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
     * @param <E>
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
     * @param e 链表e做为最后一个节点
     */
    private void linkLast(E e) {
        //获取尾部节点
        final Node<E> l = last;
        //新建一个节点，头部指向之前的 尾节点 last
        final Node<E> newNode = new Node<>(l, e, null);
        //last 指向新建的节点
        last = newNode;
        //如果之前是空链表， 新建的节点也是第一个节点
        if (l == null) {
            first = newNode;
        } else {
            //原来的尾节点尾部指向新建的尾节点
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    public static void main(String[] args) {
        ListLinkedlist<String> listLinkedlist = new ListLinkedlist<>();
        listLinkedlist.linkFirst("c");
        listLinkedlist.linkFirst("b");
        listLinkedlist.linkLast("d");
        for (Object o : listLinkedlist) {
            System.out.println(o);
        }
    }
}
