package io.github.rovingsea.utilityframework.spring.web.sample.controller;

import io.github.rovingsea.utilityframework.spring.web.sample.entity.Food;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.Ingredient;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.dto.PreparationDto;
import io.github.rovingsea.utilityframework.spring.web.sample.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping("/queryById/{id}")
    public People queryById(@PathVariable int id) {
        return peopleService.getStudentById(id);
    }

    @RequestMapping("/queryByAge/{age}")
    public List<People> queryByAge(@PathVariable int age) {
        return peopleService.getStudentsByAge(age);
    }

    @RequestMapping("/queryAgeById/{id}")
    public Integer queryAgeById(@PathVariable int id) {
        return peopleService.getStudentById(id).getAge();
    }

    @RequestMapping("/cook")
    public String cook(@RequestBody PreparationDto preparationDto) {
        Ingredient ingredient = preparationDto.getIngredient();
        int duration = preparationDto.getDuration();
        Food food = peopleService.cook(ingredient, duration);
        peopleService.eat(food);
        return "今天花了" + duration + "分钟煮了一道" + food.getDescribe() + "的" + food.getName() + "吃";
    }
}

