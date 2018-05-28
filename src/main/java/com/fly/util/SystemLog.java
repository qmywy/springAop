package com.fly.util;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SystemLog {

    LogType type() default LogType.DEFAULT;

}
