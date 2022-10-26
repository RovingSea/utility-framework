package io.github.rovingsea.utilityframework.spring.web.sample.dao.impl;

import io.github.rovingsea.utilityframework.spring.web.sample.dao.PeopleDao;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Repository
public class PeopleDaoImpl implements PeopleDao {

    private static final List<People> PEOPLE;

    static {
        PEOPLE = new ArrayList<>();
        PEOPLE.add(new People(1, "rovingsea", 22));
        PEOPLE.add(new People(2, "1024shao", 22));
    }

    @Override
    public People selectStudentById(int id) {
        return PEOPLE.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<People> selectStudentsByAge(int age) {
        return PEOPLE.stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }

}

