package com.project.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springboot.common.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @AutoConfigureMockMvc 该注解表示 MockMvc由spring容器构建，你只负责注入之后用就可以了。这种写法是为了让测试在Spring容器环境下执行。
 *
 * 参考地址：
 * https://reflectoring.io/bean-validation-with-spring-boot/
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_get_person_correctly() throws Exception {
        Person person = new Person();
        person.setName("jack");
        person.setSex("Man");
        person.setClassId("82938390");
        person.setEmail("jack@qq.com");

        mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(person)));
    }

    @Test
    public void should_check_person_value() throws Exception {
        Person person = new Person();
        person.setSex("Man22");
        person.setClassId("82938390");
        person.setEmail("errorEmail");

        mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(MockMvcResultMatchers.jsonPath("sex").value("sex 值不在可选范围"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("name 不能为空"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("email 格式不正确"));
    }


    @Test
    public void should_check_param_value() throws Exception {

        mockMvc.perform(get("/api/person/6")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("getPersonByID.id: 超过 id 的范围了"));
    }

    @Test
    public void should_check_param_value2() throws Exception {

        mockMvc.perform(put("/api/person")
                .param("name", "snailclimbsnailclimb")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("getPersonByName.name: 超过 name 的范围了"));
    }
}
