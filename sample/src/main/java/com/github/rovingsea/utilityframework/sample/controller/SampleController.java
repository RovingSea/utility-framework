package com.github.rovingsea.utilityframework.sample.controller;

import com.github.rovingsea.utilityframework.sample.exception.SampleException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
