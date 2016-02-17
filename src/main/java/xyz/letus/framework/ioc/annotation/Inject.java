package xyz.letus.framework.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ע��ע��
 * @ClassName: Controller
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2015��9��19��
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
	String value() default "";
}
