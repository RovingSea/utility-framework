package io.github.rovingsea.utilityframework.sample.controller;

import io.github.rovingsea.utilityframework.core.exception.http.InnerServerException;
import io.github.rovingsea.utilityframework.sample.entity.SampleEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/exception")
    public Object exception() {
        throw new InnerServerException(500001, "测试服务内部错误");
    }

    @GetMapping("/object")
    public Object object() {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setName("rovingsea");
        sampleEntity.setAge(21);
        return sampleEntity;
    }

    @GetMapping("/string")
    public String string() {
        return "rovingsea";
    }

}
