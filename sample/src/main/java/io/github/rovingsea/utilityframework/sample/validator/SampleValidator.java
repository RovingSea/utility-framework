package io.github.rovingsea.utilityframework.sample.validator;

import io.github.rovingsea.utilityframework.core.exception.http.BadRequestException;
import io.github.rovingsea.utilityframework.core.validator.ValidateMapping;
import io.github.rovingsea.utilityframework.core.validator.Validator;
import io.github.rovingsea.utilityframework.sample.entity.SampleEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Validator("/sample")
public class SampleValidator {

    @ValidateMapping("/object")
    public void object(@RequestBody SampleEntity sample) {
        if (sample.getAge() > 150 || sample.getAge() < 0) {
            throw new BadRequestException(400001, "The age is incorrect");
        }
        if (StringUtils.isEmpty(sample.getName())) {
            throw new BadRequestException(400002, "The name is incorrect");
        }
    }

}
