package com.sundeinfo.core.excel;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelNumColumn {

    String value() default "";

    String format() default "0";

}