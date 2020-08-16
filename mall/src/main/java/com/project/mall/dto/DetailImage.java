package com.project.mall.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Reisen
 * @title: DetailImage
 * @projectName reisen
 * @description: TODO
 * @date 2020/8/16 23:32
 */
@Data
public class DetailImage {
    String desc;
    String anchor;
    String key;
    List imageList;
    Integer length;
}
