package io.github.utilityframework.boot.example.web.validator;

import io.github.utilityframework.boot.example.web.api.PeopleApi;
import io.github.utilityframework.boot.example.web.entity.Ingredient;
import io.github.utilityframework.boot.example.web.entity.dto.PreparationDto;
import io.github.utilityframework.boot.example.web.exception.PeopleExceptionEnum;
import io.github.utilityframework.boot.web.utils.Throw;
import io.github.utilityframework.boot.web.validator.Validator;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Validator(PeopleApi.class)
public class PeopleValidator {

    public void queryById(int id) {
        if (id < 0) {
            Throw.status400(PeopleExceptionEnum.QUERY_BY_ID);
        }
    }

    public void queryByAge(int age) {
        if (age < 0 || age > 150) {
            Throw.status400(PeopleExceptionEnum.QUERY_BY_AGE);
        }
    }

    public void cook(@RequestBody PreparationDto preparationDto) throws ParseException {
        Ingredient ingredient = preparationDto.getIngredient();
        if (ingredient.isBad()) {
            Throw.status400(PeopleExceptionEnum.COOK_INGREDIENT_BAD);
        }
        String productionDateStr = ingredient.getProductionDate();
        int qualityGuaranteePeriod = ingredient.getQualityGuaranteePeriod();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date productionDate = sdf.parse(productionDateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(productionDate);
        calendar.add(Calendar.DAY_OF_MONTH, qualityGuaranteePeriod);
        Date qualityGuaranteePeriodDate = calendar.getTime();

        Date nowDate = new Date();
        if (qualityGuaranteePeriodDate.compareTo(nowDate) < 0) {
            Throw.status400(PeopleExceptionEnum.COOK_INGREDIENT_OUT_OF_DATE);
        }

    }

}

