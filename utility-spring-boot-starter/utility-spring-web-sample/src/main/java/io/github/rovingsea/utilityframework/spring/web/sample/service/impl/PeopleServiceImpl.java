package io.github.rovingsea.utilityframework.spring.web.sample.service.impl;


import io.github.rovingsea.utilityframework.spring.web.sample.dao.PeopleDao;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.Food;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.Ingredient;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;
import io.github.rovingsea.utilityframework.spring.web.sample.exception.PeopleExceptionEnum;
import io.github.rovingsea.utilityframework.spring.web.sample.service.PeopleService;
import io.github.rovingsea.utilityframework.spring.web.utils.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleDao peopleDao;

    @Override
    public People getStudentById(int id) {
        return peopleDao.selectStudentById(id);
    }

    @Override
    public List<People> getStudentsByAge(int age) {
        return peopleDao.selectStudentsByAge(age);
    }

    @Override
    public Food cook(Ingredient ingredient, int duration) {
        String[] split = ingredient.getCookedTime().split("~");
        if (duration < Integer.parseInt(split[0])) {
            Throw.status428(PeopleExceptionEnum.COOK_TIME_IS_TOO_SHORT, duration);
//            Throw.exception(PeopleExceptionEnum.COOK_TIME_IS_TOO_SHORT, duration, HttpStatus.PRECONDITION_FAILED);
        }
        if (duration > Integer.parseInt(split[1])) {
            Throw.status428(PeopleExceptionEnum.COOK_TIME_IS_TOO_LONG, duration);
//            Throw.exception(PeopleExceptionEnum.COOK_TIME_IS_TOO_LONG, duration, HttpStatus.PRECONDITION_FAILED);
        }
        return new Food(duration);
    }

    @Override
    public void eat(Food food) {
        Random random = new Random();
        int i = random.nextInt(3);
        if (i == 1) {
            throw new RuntimeException("吃到鱼刺");
        }
    }

}

