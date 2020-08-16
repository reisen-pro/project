package com.project.mall.dto;

import lombok.Data;

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
    Integer cFans;
    Integer cSells;
    Boolean isMarked;
    Integer level;
    String name;

}
