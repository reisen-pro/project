package com.project.mall.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Reisen
 * @title: ItemInfo
 * @projectName reisen
 * @description: TODO
 * @date 2020/8/16 14:53
 */
@Data
public class ItemInfo {
    List<String> topImages;
    String title;
    String newPrice;
    String oldPrice;
    String discount;
    Double lowNowPrice;
    String[] columns;
    List services;
    String desc;
    String discountBgColor;
    DetailImage detailImage;
    ShopInfo shopInfo;
}
