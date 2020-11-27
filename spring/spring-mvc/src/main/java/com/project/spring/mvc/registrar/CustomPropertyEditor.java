package com.project.spring.mvc.registrar;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 如果想多个controller需要共同注册相同的属性编辑器，可以实现PropertyEditorRegistrar接口
 * 并注入webBindingInitializer中。
 * 这种方式比较老
 */
public class CustomPropertyEditor implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
        propertyEditorRegistry.registerCustomEditor(Date.class,new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"),true
        ));
    }
}
