package com.project.mall.model;

import lombok.*;

/**
 * @author Reisen
 * @title: Good
 * @projectName reisen
 * @description: TODO
 * @date 2020/7/27 23:03
 */

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    String id;
    String type;
    String title;
    String img;
    String price;
    String cfav;
}
