package com.project.mall.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Reisen
 * @title: ShopInfo
 * @projectName reisen
 * @description: TODO
 * @date 2020/8/16 16:10
 */
@Data
public class ShopInfo {
    String allGoodsUrl;

    String logo;
    String name;
    Integer fans;
    Integer sells;
    List<Score> score;
    Integer goodsCount;
    Boolean isMarked;
    Integer level;


}
