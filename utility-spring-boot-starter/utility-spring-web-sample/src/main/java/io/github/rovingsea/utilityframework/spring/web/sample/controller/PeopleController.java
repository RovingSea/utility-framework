package io.github.rovingsea.utilityframework.spring.web.sample.controller;

import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;
import io.github.rovingsea.utilityframework.spring.web.sample.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

