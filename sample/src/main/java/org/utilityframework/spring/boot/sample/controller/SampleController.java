package org.utilityframework.spring.boot.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.utilityframework.spring.boot.sample.exception.SampleException;


/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class SampleController {

    @RequestMapping("/400")
    public void test400() {
        throw new SampleException("test 400");
    }

}
