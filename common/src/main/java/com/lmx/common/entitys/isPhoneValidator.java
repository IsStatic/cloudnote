package com.lmx.common.entitys;

import com.lmx.common.utils.ValidatorUtil;
import com.lmx.common.validators.isPhone;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isPhoneValidator implements ConstraintValidator<isPhone,String> {
    private boolean required = false;

    @Override
    public void initialize(isPhone constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isMobile(s);
        }else {
            //由于非必须
            if (StringUtils.isEmpty(s)){
                return true;
            }else {
                return ValidatorUtil.isMobile(s);
            }
        }
    }
}
