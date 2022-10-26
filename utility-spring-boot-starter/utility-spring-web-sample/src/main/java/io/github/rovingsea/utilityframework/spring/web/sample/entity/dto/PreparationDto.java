package io.github.rovingsea.utilityframework.spring.web.sample.entity.dto;

import io.github.rovingsea.utilityframework.spring.web.sample.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Haixin Wu
 * @since 1.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreparationDto {

    /**
     * 食材
     */
    private Ingredient ingredient;

    /**
     * 烹饪时长，单位分钟
     */
    private int duration;

}
