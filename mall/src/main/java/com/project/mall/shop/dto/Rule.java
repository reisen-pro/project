package com.project.mall.shop.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Reisen
 * @title: Rule
 * @projectName reisen
 * @description: TODO
 * @date 2020/8/20 0:26
 */
@Data
public class Rule {
    String anchor;
    String disclaimer;
    String key;
    List tables;
}
