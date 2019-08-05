package com.n26.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.ToString
@AllArgsConstructor
public class StatisticsDto {
    private int count;
    private String max;
    private String min;
    private String sum;
    private String avg;
}

