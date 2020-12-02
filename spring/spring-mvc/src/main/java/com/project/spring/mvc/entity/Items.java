package com.project.spring.mvc.entity;

import com.project.spring.mvc.group.ValidGroup1;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Items {
    private Integer id;

    // 通过groups指定此校验属于哪个分组，可以指定多个分组
    @Size(min = 1,max = 30,message = "commodity's name must between 1 and 30",groups = {ValidGroup1.class})
    private String name;

    private Float price;

    private String pic;

    @NotNull(message = "date must be not null")
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
