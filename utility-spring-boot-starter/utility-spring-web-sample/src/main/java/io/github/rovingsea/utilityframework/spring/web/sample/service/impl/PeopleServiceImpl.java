package io.github.rovingsea.utilityframework.spring.web.sample.service.impl;


import io.github.rovingsea.utilityframework.spring.web.sample.dao.PeopleDao;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;
import io.github.rovingsea.utilityframework.spring.web.sample.service.PeopleService;
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

}

