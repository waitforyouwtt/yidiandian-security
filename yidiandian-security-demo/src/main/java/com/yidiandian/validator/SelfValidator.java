package com.yidiandian.validator;

import com.yidiandian.entity.UserInfo;
import com.yidiandian.service.UserInfoOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/11 20:39
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Slf4j
public class SelfValidator implements ConstraintValidator<SelfConstraint, Object> {

    @Autowired
    private UserInfoOperationService userInfoService;

    @Override
    public void initialize(SelfConstraint constraintAnnotation) {
        log.info( "进入自定义校验init......" );
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        log.info( "object:{}",value );
        log.info( "context:{}",context );
        String userName = (String) value;
        UserInfo userInfo = userInfoService.isExists(userName);
        if (userInfo == null){
            return true;
        }
        return false;
    }
}
