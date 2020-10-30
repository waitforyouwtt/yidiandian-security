package com.yidiandian.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/11 20:32
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Target( {ElementType.METHOD,ElementType.FIELD,ElementType.PACKAGE} )  //可以标注在方法上，也可以标注在字段上，包上面，类上面
@Retention(  RetentionPolicy.RUNTIME) //程序运行时起作用
@Constraint( validatedBy = SelfValidator.class) //指定哪个类的逻辑
public @interface SelfConstraint {

    //自定义注解必须编写的三个方法
    String message() default "{selfValidator.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
