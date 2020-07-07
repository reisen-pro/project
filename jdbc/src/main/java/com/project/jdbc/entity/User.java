package com.project.jdbc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Reisen
 */
@Data
@Getter
@Setter
@Table(value = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -3982101350948523161L;
    @Id
    private Long id;
    private String username;
    private String password;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
}
