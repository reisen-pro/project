package com.project.springboot;

import com.project.springboot.common.Person;
import com.project.springboot.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.validation.*;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {
    @Resource
    private PersonService service;

    @Test
    public void should_throw_exception_when_person_is_not_valid() {
        Person person = new Person();
        person.setSex("Man22");
        person.setClassId("123456");
        person.setEmail("123.email.com");
        try {
            service.checkPerson(person);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过 Validator 工厂类获得的 Validator 示例
     * 当然也可以通过 @Autowired 直接注入的方式。
     * 但是在非 Spring Component 类中使用这种方式的话，只能通过工厂类来获得 Validator。
     */
    @Test
    public void check_person_manually() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Person person = new Person();
        person.setSex("1");
        person.setClassId("82938390");
        person.setEmail("errorEmailFormat");
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        //output:
        //email 格式不正确
        //name 不能为空
        //sex 值不在可选范围
        for (ConstraintViolation<Person> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }
    }
}
