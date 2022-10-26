package io.github.rovingsea.utilityframework.spring.web.sample.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Haixin Wu
 * @since 1.0.1
 */
@Data
public class Food {

    private static final Map<Integer, Food> TYPE = new HashMap<>();

    static {
        TYPE.put(11, new Food("清蒸鱼", "口味咸鲜，鱼肉软嫩，鲜香味美，汤清味醇"));
        TYPE.put(12, new Food("糖醋鱼", "鲜嫩无比，酸甜可口，外焦里嫩，甜咸适口"));
        TYPE.put(13, new Food("酸辣鱼", "酸辣可口，辣中带酸，酸里回甜，和中开胃"));
        TYPE.put(14, new Food("红烧鱼", "口齿留香，质嫩爽口，入口即化，脍炙人口"));
        TYPE.put(15, new Food("油炸鱼", "芳香四溢，香飘十里，油而不腻，香脆可口"));
    }

    private Food(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    private String name;

    private String describe;

    public Food(int duration) {
        Food food = TYPE.getOrDefault(duration, new Food("食物", "奇怪的"));
        this.name = food.getName();
        this.describe = food.getDescribe();
    }

}
