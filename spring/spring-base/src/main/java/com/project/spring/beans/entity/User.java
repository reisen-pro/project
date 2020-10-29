package com.project.spring.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author reisen
 * bean实体类对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String userName;
}
