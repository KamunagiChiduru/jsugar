package jp.michikusa.chitose.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 例外安全性について注釈するために使う注釈型。<br>
 * 
 * @author kamichidu
 * @version 0.01
 * @since 2012/10/27
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface ExceptionSafety{
    public Level value();
    
    /**
     * 例外安全性の程度について記述するための列挙型。<br>
     * 
     * @author kamichidu
     * @version 0.01
     * @since 2012/12/16
     */
    public static enum Level{
        /**
         * 保証をしない。<br>
         */
        NO_GUARANTEE,
        /**
         * 基本保証をする。<br>
         */
        BASIC_GUARANTEE,
        /**
         * 強い保証をする。<br>
         */
        STRONG_GUARANTEE,
        /**
         * 投げない保証をする。<br>
         */
        NO_THROW_GUARANTEE, ;
    }
}
