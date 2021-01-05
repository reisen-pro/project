package com.project.springboot.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;


/**
 * 1.@MappedSuperclass
 * 注解使用在父类上面，是用来标识父类的
 * 因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中
 * 标识不能再有@Entity或@Table注解
 *
 * 2.@JsonIgnore
 * 此注解是类注解，作用是json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
 * 此注解用于属性或者方法上（最好是属性上）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AbstractAuditBase {

    /**
     * updatable = false 在保存数据时，字段不会被保存到表中
     */
    @CreatedDate
    @Column(updatable = false)
    @JsonIgnore
    private Instant createdAt;

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    private String createdBy;

    @LastModifiedDate
    @JsonIgnore
    private Instant updatedAt;

    @LastModifiedBy
    @JsonIgnore
    private String updatedBy;

    public static void main(String[] args) {
        // java8的新时间类 相比date精确到毫秒，这个类可以精确到纳秒
        Instant instant = Instant.now();
        System.out.println(instant);
    }
}
