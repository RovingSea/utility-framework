package io.github.utilityframework.boot.example.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {

    private int id;

    private String name;

    private int age;

}

