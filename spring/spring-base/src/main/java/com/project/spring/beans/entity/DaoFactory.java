package com.project.spring.beans.entity;

import java.lang.reflect.Constructor;

/**
 * dao factory
 */
public class DaoFactory {
    private static final DaoFactory factory = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return factory;
    }

    public <T> T createDao(String className, Class<T> clazz) {
        try {

/*            // 根据className去反射实例
            T t = (T) Class.forName(className).newInstance();*/

            // 根据构造器去反射创建对象
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
