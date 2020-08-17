package com.project.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
    String name;
    Double score;
    Boolean isBetter;
}
