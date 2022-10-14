package io.github.rovingsea.utilityframework.sample.controller;

import io.github.rovingsea.utilityframework.core.Throw;
import io.github.rovingsea.utilityframework.core.exception.http.InnerServerException;
import io.github.rovingsea.utilityframework.sample.entity.SampleEntity;
import io.github.rovingsea.utilityframework.sample.exception.SampleErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        Throw.internalServerError(SampleErrorCode.TEST_INTER_SERVER_ERROR);
        throw new InnerServerException(500001, "测试服务内部错误");
    }

    @GetMapping("/object")
    public Object object(@RequestBody SampleEntity sample) {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setName(sample.getName());
        sampleEntity.setAge(sample.getAge());
        return sampleEntity;
    }

    @GetMapping("/string")
    public String string() {
        return "rovingsea";
    }

}
