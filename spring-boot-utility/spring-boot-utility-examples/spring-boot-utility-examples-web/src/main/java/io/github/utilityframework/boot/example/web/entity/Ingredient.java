package io.github.utilityframework.boot.example.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 食材
 * @author Haixin Wu
 * @since 1.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    /**
     * 生产日期 yyyy-MM-dd
     */
    private String productionDate;
    /**
     * 保质期
     */
    private int qualityGuaranteePeriod;
    /**
     * 是否变质
     */
    private boolean isBad;
    /**
     * 被煮熟的时间范围，比如10-15分钟之间，表示为10~15
     */
    private String cookedTime;


}
