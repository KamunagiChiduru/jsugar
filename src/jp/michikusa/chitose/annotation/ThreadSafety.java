package jp.michikusa.chitose.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * スレッドセーフかそうでないかを表明するために使う。<br>
 * 
 * @author  kamichidu
 * @version 0.01
 * @since   2012/10/27
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ThreadSafety{
	public Level value();
	
	public static enum Level{
		UNSAFE,
		SAFE, ;
	}
}
