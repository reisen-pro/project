package com.project.base.collection;

import java.util.*;

/**
 * 迭代器是一种设计模式，它是一个对象，它可以遍历并选择序列中的对象，而开发人员不需要了解该序列的底层结构。
 * 迭代器通常被称为“轻量级”对象，因为创建它的代价小。
 * <p>
 * Iterator 怎么使用？有什么特点？
 * <p>
 * (1) 使用方法iterator()要求容器返回一个Iterator。第一次调用Iterator的next()方法时，它返回序列的第一个元素。
 * 注意：iterator()方法是java.lang.Iterable接口,被Collection继承。
 * <p>
 * (2) 使用next()获得序列中的下一个元素。
 * <p>
 * (3) 使用hasNext()检查序列中是否还有元素。
 * <p>
 * (4) 使用remove()将迭代器新返回的元素删除。
 * <p>
 * Iterator是Java迭代器最简单的实现，为List设计的ListIterator具有更多的功能，它可以从两个方向遍历List，也可以从List中插入和删除元素。
 * <p>
 * Iterator 和 ListIterator 有什么区别？
 * <p>
 * Iterator可用来遍历Set和List集合，但是ListIterator只能用来遍历List。
 * <p>
 * Iterator对集合只能是前向遍历，ListIterator既可以前向也可以后向。
 * <p>
 * ListIterator实现了Iterator接口，并包含其他的功能，比如：增加元素，替换元素，获取前一个和后一个元素的索引，等等。
 */
public class CollectionDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<String>();
        list.add("天");
        list.add("地");
        list.add("玄");
        list.add("黄");

        // 迭代器
        Iterator<String> iterator = list.iterator();
        iterator.forEachRemaining(s -> {
            set.add(s);
            System.out.print(s);
        });

        System.out.println();

        set.iterator().forEachRemaining(System.out::print);

        System.out.println();

        // Collections工具类 collection的工具类
        // sort排序
        Collections.sort(list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.hashCode() - o1.hashCode();
            }
        });

        list.forEach(System.out::print);

    }
}
