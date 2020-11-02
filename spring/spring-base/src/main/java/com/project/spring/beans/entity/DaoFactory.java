package com.project.spring.beans.entity;

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
            // 根据className去反射实例
            T t = (T) Class.forName(className).newInstance();
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
