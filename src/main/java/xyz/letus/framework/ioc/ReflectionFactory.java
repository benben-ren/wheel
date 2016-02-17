package xyz.letus.framework.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ������
 * @ClassName: ReflectionFactory
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2015��9��19��
 *
 */
public class ReflectionFactory {
	public static final Logger LOGGER = LoggerFactory.getLogger(ReflectionFactory.class);
	/**
	 * ����ʵ��
	 * @Title: newInstance
	 * @Description: TODO
	 * @param @param clazz
	 * @param @return    
	 * @return Object    
	 * @throws
	 */
	public static Object newInstance(Class<?> clazz){
		Object instance = null;
		try {
			instance = clazz.newInstance();
		} catch (Exception e) {
			LOGGER.error("new instatnce failure", e);
			throw new RuntimeException(e);
		} 
		
		return instance;
	}
	
	/**
	 * ���÷���
	 * @Title: invokeMethod
	 * @Description: TODO
	 * @param @param obj
	 * @param @param method
	 * @param @param args
	 * @param @return    
	 * @return Object    
	 * @throws
	 */
	public static Object invokeMethod(Object obj,Method method,Object...args){
		Object result = null;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("invoke method failure", e);
			throw new RuntimeException(e);
		} 
		return result;
	}
	/**
	 * ���ó�Ա����ֵ
	 * @Title: setField
	 * @Description: TODO
	 * @param @param obj
	 * @param @param field
	 * @param @param value    
	 * @return void    
	 * @throws
	 */
	public static void setField(Object obj,Field field,Object value){
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			LOGGER.error("set field failure", e);
			throw new RuntimeException(e);
		} 
	}
}
