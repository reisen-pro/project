package com.project.base.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Java 注解详解
 * <p>
 * 知识点：
 * 1. 内置注解（@Override、@Deprecated、@SuppressWarnings）
 * 2. 元注解（@Target、@Retention、@Documented、@Inherited）
 * 3. 自定义注解
 * 4. 注解属性
 * 5. 注解处理（反射读取注解）
 * 6. 实际应用案例
 *
 * @author Reisen
 * @see JAVA_LEARNING_ROADMAP.md Stage 5
 */
public class AnnotationDemo {

    /**
     * 主方法 - Java注解演示程序入口
     * <p>
     * 依次执行四个演示模块：
     * 1. 内置注解演示（@Override、@Deprecated、@SuppressWarnings等）
     * 2. 自定义注解演示（定义和使用自定义注解）
     * 3. 注解处理演示（通过反射读取类、字段、方法上的注解）
     * 4. 实际应用演示（模拟ORM框架根据注解生成SQL语句）
     *
     * @param args 命令行参数（未使用）
     * @throws Exception 反射操作可能抛出的异常
     */
    public static void main(String[] args) throws Exception {
        builtInAnnotationsDemo();
        customAnnotationDemo();
        annotationProcessingDemo();
        practicalExampleDemo();
    }

    // ==================== 1. 内置注解 ====================

    /**
     * 内置注解演示
     */
    private static void builtInAnnotationsDemo() {
        System.out.println("========== 内置注解 ==========");

        // @Override：标记方法重写
        // 编译器会检查父类是否有该方法，没有则报错

        // @Deprecated：标记过时
        OldClass old = new OldClass();
        old.oldMethod();  // 编译时有警告

        // @SuppressWarnings：抑制警告
        SuppressWarningsDemo demo = new SuppressWarningsDemo();
        demo.unusedVariable();

        // @FunctionalInterface：是 Java 8 引入的一个核心注解，用于标识函数式接口——即有且仅有一个抽象方法的接口。
        // 接口只能有一个抽象方法
        // 编译时校验：防止团队成员误添加第二个抽象方法，破坏 Lambda 兼容性
        // 代码自文档：一眼就能看出该接口设计意图是用于 Lambda/方法引用
        // 生成元数据：Javadoc 等工具会特殊标注，IDE 也会提供 Lambda 补全提示

        // @SafeVarargs：抑制可变参数的堆污染警告
        safeVarargsDemo("Hello", "World",125);

        System.out.println();
    }

    /**
     * SafeVarargs 示例
     * Java 的泛型在编译后会类型擦除（Type Erasure）
     * 可变参数 T... 在运行时会被创建为 Object[]
     * 编译器无法保证数组中的所有元素都是同一类型
     * 可能导致运行时 ClassCastException
     */

    @SafeVarargs
    private static <T> void safeVarargsDemo(T... items) {
        for (T item : items) {
            System.out.println("Item: " + item);
        }
    }

    // ==================== 2. 自定义注解 ====================

    /**
     * 自定义注解演示
     */
    private static void customAnnotationDemo() {
        System.out.println("========== 自定义注解 ==========");

        // 查看注解定义
        System.out.println("@MyAnnotation 定义:");
        System.out.println("- @Target: " + Arrays.toString(MyAnnotation.class.getAnnotation(Target.class).value()));
        System.out.println("- @Retention: " + MyAnnotation.class.getAnnotation(Retention.class).value());

        // 使用自定义注解
        @MyAnnotation(value = "测试注解", count = 3)
        class TestClass {
            @MyAnnotation("字段注解")
            String field;

            @MyAnnotation(value = "方法注解", count = 5)
            void method() {
            }
        }

        System.out.println("自定义注解已定义，可用于类、字段、方法");
        System.out.println();
    }

    // ==================== 3. 注解处理 ====================

    /**
     * 注解处理演示（反射读取注解）
     */
    private static void annotationProcessingDemo() throws Exception {
        System.out.println("========== 注解处理（反射读取）==========");

        Class<UserEntity> clazz = UserEntity.class;

        // 读取类上的注解
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            System.out.println("表名: " + table.name());
            System.out.println("Schema: " + table.schema());
        }

        // 读取字段上的注解
        System.out.println("\n字段信息:");
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                System.out.println("字段: " + field.getName());
                System.out.println("  - 列名: " + column.name());
                System.out.println("  - 长度: " + column.length());
                System.out.println("  - 可空: " + column.nullable());
                System.out.println("  - 唯一: " + column.unique());
            }
        }

        // 读取方法上的注解
        System.out.println("\n方法信息:");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ApiOperation.class)) {
                ApiOperation api = method.getAnnotation(ApiOperation.class);
                System.out.println("方法: " + method.getName());
                System.out.println("  - 描述: " + api.value());
                System.out.println("  - HTTP方法: " + api.method());
                System.out.println("  - 路径: " + api.path());
            }
        }

        System.out.println();
    }

    // ==================== 4. 实际应用案例 ====================

    /**
     * 实际应用演示：模拟 ORM 框架
     */
    private static void practicalExampleDemo() throws Exception {
        System.out.println("========== 实际应用：模拟 ORM ==========");

        // 模拟根据注解生成 SQL
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("zhangsan");
        user.setEmail("zhangsan@example.com");
        user.setAge(25);

        // 生成 INSERT SQL
        String insertSql = generateInsertSql(user);
        System.out.println("INSERT SQL:\n" + insertSql);

        // 生成 SELECT SQL
        String selectSql = generateSelectSql(UserEntity.class, 1L);
        System.out.println("\nSELECT SQL:\n" + selectSql);

        // 生成 UPDATE SQL
        String updateSql = generateUpdateSql(user);
        System.out.println("\nUPDATE SQL:\n" + updateSql);

        System.out.println();
    }

    /**
     * 根据 @Table 和 @Column 注解生成 INSERT SQL
     */
    private static String generateInsertSql(Object entity) throws Exception {
        Class<?> clazz = entity.getClass();

        // 获取表名
        Table table = clazz.getAnnotation(Table.class);
        String tableName = (table != null && !table.name().isEmpty()) ? table.name() : clazz.getSimpleName().toLowerCase();

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        boolean first = true;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name().isEmpty() ? field.getName() : column.name();

                field.setAccessible(true);
                Object value = field.get(entity);

                // 前置加分隔符设计
                if (!first) {
                    columns.append(", ");
                    values.append(", ");
                }
                columns.append(columnName);
                values.append(value instanceof String ? "'" + value + "'" : value);
                first = false;
            }
        }

        return String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, columns, values);
    }

    /**
     * 生成 SELECT SQL
     */
    private static String generateSelectSql(Class<?> clazz, Object id) {
        Table table = clazz.getAnnotation(Table.class);
        String tableName = (table != null && !table.name().isEmpty()) ? table.name() : clazz.getSimpleName().toLowerCase();

        return String.format("SELECT * FROM %s WHERE id = %s;", tableName, id);
    }

    /**
     * 生成 UPDATE SQL
     */
    private static String generateUpdateSql(Object entity) throws Exception {
        Class<?> clazz = entity.getClass();

        Table table = clazz.getAnnotation(Table.class);
        String tableName = (table != null && !table.name().isEmpty()) ? table.name() : clazz.getSimpleName().toLowerCase();

        StringBuilder setClause = new StringBuilder();
        Object idValue = null;
        boolean first = true;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name().isEmpty() ? field.getName() : column.name();

                field.setAccessible(true);
                Object value = field.get(entity);

                // 记录 ID 值
                if (field.getName().equals("id")) {
                    idValue = value;
                    continue;
                }

                if (!first) {
                    setClause.append(", ");
                }
                setClause.append(columnName).append(" = ");
                setClause.append(value instanceof String ? "'" + value + "'" : value);
                first = false;
            }
        }
        return String.format("UPDATE %s SET %s WHERE id = %s;", tableName, setClause, idValue);
    }
}
