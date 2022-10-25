package io.github.rovingsea.utilityframework.spring.web.sample.controller;

import io.github.rovingsea.utilityframework.spring.web.exception.ExpectedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/expected")
    public void expected() {
        throw new ExpectedException(500000, "An expected exception");
    }

    @RequestMapping("/unexpected")
    public void unexpected() {
        throw new RuntimeException("An unexpected exception");
    }

}

