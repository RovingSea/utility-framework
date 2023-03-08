package io.github.utilityframework.boot.example.web.controller;

import io.github.utilityframework.boot.example.web.api.PeopleApi;
import io.github.utilityframework.boot.example.web.entity.Food;
import io.github.utilityframework.boot.example.web.entity.Ingredient;
import io.github.utilityframework.boot.example.web.entity.People;
import io.github.utilityframework.boot.example.web.entity.dto.PreparationDto;
import io.github.utilityframework.boot.example.web.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestController
public class PeopleController implements PeopleApi {

    @Autowired
    private PeopleService peopleService;

    @Override
    @RequestMapping("/queryById/{id}")
    public People queryById(int id) {
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

