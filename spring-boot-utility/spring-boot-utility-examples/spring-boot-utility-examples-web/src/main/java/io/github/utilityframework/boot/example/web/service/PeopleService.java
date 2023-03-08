package io.github.utilityframework.boot.example.web.service;


import io.github.utilityframework.boot.example.web.entity.Food;
import io.github.utilityframework.boot.example.web.entity.Ingredient;
import io.github.utilityframework.boot.example.web.entity.People;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface PeopleService {

    People getStudentById(int id);

    List<People> getStudentsByAge(int age);

    Food cook(Ingredient ingredient, int duration);

    void eat(Food food);

}

