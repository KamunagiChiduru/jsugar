package jp.michikusa.chitose.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 例外安全性についてドキュメント化するために使う。<br>
 * 
 * @author  kamichidu
 * @version 0.01
 * @since   2012/10/27
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface ExceptionSafety{
	public Level value();
	
	public static enum Level{
		NO_GUARANTEE,
		BASIC_GUARANTEE,
		STRONG_GUARANTEE,
		NO_THROW_GUARANTEE, ;
	}
}
