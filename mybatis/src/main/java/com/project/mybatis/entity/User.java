package com.project.mybatis.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Reisen
 */
@Data
@Getter
@Setter
@Repository
public class User implements Serializable {
    private static final long serialVersionUID = -3982101350948523161L;
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
