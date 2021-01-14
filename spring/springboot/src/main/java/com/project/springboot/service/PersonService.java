package com.project.springboot.service;

import com.project.springboot.common.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @Validated 告诉 Spring 去校验方法参数。
 */
@Slf4j
@Service
@Validated
public class PersonService {
    public void checkPerson(@Valid Person person) {
        log.info("start check:{}", person);
        System.out.println(person);
    }
}
