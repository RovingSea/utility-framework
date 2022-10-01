package io.github.rovingsea.utilityframework.sample.validator;

import io.github.rovingsea.utilityframework.core.validator.Validator;
import io.github.rovingsea.utilityframework.sample.entity.SampleEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Validator("/sample")
public class SampleValidator {

    @GetMapping("/object")
    public Object object(@RequestBody SampleEntity sample) {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setName(sample.getName());
        sampleEntity.setAge(sample.getAge());
        return sampleEntity;
    }

}
