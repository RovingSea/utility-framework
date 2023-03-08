package io.github.utilityframework.boot.example.web.api;

import io.github.utilityframework.boot.example.web.entity.People;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */

@RequestMapping("/people")
public interface PeopleApi {

    @RequestMapping("/queryById/{id}")
    public People queryById(@PathVariable int id);

}
