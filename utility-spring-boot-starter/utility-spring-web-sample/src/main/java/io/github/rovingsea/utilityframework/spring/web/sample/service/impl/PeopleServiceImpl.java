package io.github.rovingsea.utilityframework.spring.web.sample.service.impl;


import io.github.rovingsea.utilityframework.spring.web.sample.dao.PeopleDao;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.Food;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.Ingredient;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;
import io.github.rovingsea.utilityframework.spring.web.sample.exception.PeopleExceptionEnum;
import io.github.rovingsea.utilityframework.spring.web.sample.service.PeopleService;
import io.github.rovingsea.utilityframework.spring.web.utils.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Food cook(Ingredient ingredient, int time) {
        String[] split = ingredient.getCookedTime().split("~");
        if (time < Integer.parseInt(split[0])) {
            Throw.preconditionFailed(PeopleExceptionEnum.COOK_TIME_IS_TOO_SHORT);
        }
        if (time > Integer.parseInt(split[1])) {
            Throw.preconditionFailed(PeopleExceptionEnum.COOK_TIME_IS_TOO_LONG);
        }
        // todo
        return null;
    }

}

