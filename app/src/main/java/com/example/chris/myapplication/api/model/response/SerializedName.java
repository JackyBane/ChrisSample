package com.example.chris.myapplication.api.model.response;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 16:33
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface SerializedName {
    String value() default "";
}
