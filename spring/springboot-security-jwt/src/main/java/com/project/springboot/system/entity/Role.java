package com.project.springboot.system.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AbstractAuditBase {

    /**
     * strategy = GenerationType.
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    /**
     * OneToMany 1对多 级联操作
     * 只有OneToOne，OneToMany，ManyToMany上才有mappedBy属性，ManyToOne不存在该属性；
     *
     * cascade 属性的可能值有
     * all: 所有情况下均进行关联操作，即save-update和delete。
     * none: 所有情况下均不进行关联操作。这是默认值。
     * save-update: 在执行save/update/saveOrUpdate时进行关联操作。
     * delete: 在执行delete 时进行关联操作。
     * all-delete-orphan: 当一个节点在对象图中成为孤儿节点时，删除该节点。
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
