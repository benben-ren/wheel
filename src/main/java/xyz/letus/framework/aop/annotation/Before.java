package xyz.letus.framework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ǰ����ǿע��
 * @ClassName: Before
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2016��3��7��
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Before {
	/**
	 * �������ʽ
	 * 
	 * @Title: value
	 * @Description: TODO
	 * @param @return    
	 * @return String    
	 * @throws
	 */
	String value();
}
