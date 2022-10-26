package io.github.rovingsea.utilityframework.spring.web.sample.service;



import io.github.rovingsea.utilityframework.spring.web.sample.entity.Food;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.Ingredient;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;

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

