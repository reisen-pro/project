package com.project.jpa.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author Reisen
 */
@Data
@Getter
@Setter
@Table(name = "user")
@Entity
public class User implements Serializable {
    @Id
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createdate;
    private LocalDateTime modifydate;
}
