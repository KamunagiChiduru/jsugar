package jp.michikusa.chitose.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不変オブジェクトであることを示す。<br>
 * 
 * @author  kamichidu
 * @version 0.01
 * @since   2012/10/27
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Immutable{
}
