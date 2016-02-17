package xyz.letus.framework.ioc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import xyz.letus.framework.ioc.annotation.Component;

/**
 * ���������
 * @ClassName: ClassHelper
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2015��9��19��
 *
 */
public class ClassFactory {

	/**
	 * ��ȡ���������������
	 * @Title: getBeanClasses
	 * @Description: TODO
	 * @param @return    
	 * @return Map<String, Class<?>>    
	 * @throws
	 */
	public static  Map<String, Class<?>> getBeanClasses(String basePackage){
		Map<String, Class<?>> annotationClasses = new HashMap<String, Class<?>>();
		Set<Class<?>> classes = ClassLoader.getClassSet(basePackage);
		for(Class<?> clazz : classes){
			if(clazz.isAnnotationPresent(Component.class)){
				Component component = clazz.getAnnotation(Component.class);
				String name = clazz.getSimpleName();
				String value = component.value();
				if(value.length() > 0){
					name = value;
				}
				classes.add(clazz);
				annotationClasses.put(name, clazz);
			}
		}
		return annotationClasses;
	}
	
	

}
