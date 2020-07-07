package com.project.base.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class ListArrayList {
    public static void main(String[] args) {
        ArrayList arraylist = new ArrayList();
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);
        Integer integer;
        // 使用的for循环+Iterator，类似于链表迭代：
        // for (ListNode cur = head; cur != null; System.out.println(cur.val)){
        //     cur = cur.next;
        // }
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); System.out.println(integer)) {
            integer = (Integer) iterator.next();
        }
    }
}
