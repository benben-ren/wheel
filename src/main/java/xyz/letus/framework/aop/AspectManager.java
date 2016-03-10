package xyz.letus.framework.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import xyz.letus.framework.aop.annotation.Before;

/**
 * �������
 * @ClassName: AspectManager
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2016��3��7��
 *
 */
public class AspectManager {
	private Map<String, Object> beanMap;
	
	public AspectManager(Map<String, Object> beanMap){
		this.beanMap = beanMap;
	}
	
	/**
	 * ������������н���
	 * @Title: parse
	 * @Description: TODO
	 * @param @param aspectClasses    
	 * @return void    
	 * @throws
	 */
	public void parse(Map<String, Class<?>> aspectClasses) {
		for (Entry<String, Class<?>> entry : aspectClasses.entrySet()) {
			parse(entry.getValue());
		}
	}
	
	/**
	 * ����һ������
	 * @Title: parse
	 * @Description: TODO
	 * @param @param clazz    
	 * @return void    
	 * @throws
	 */
	private void parse(Class<?> clazz) {
		try {
			Method[] methods = clazz.getMethods();
			Object aspect = clazz.newInstance();
			for(Method method : methods){
				if(method.isAnnotationPresent(Before.class)){
					beforeHandle(aspect,method);
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * ��ȡƥ��ķ�����
	 * 
	 * ��ֻ�����������ӷ������Ľ���
	 * @Title: getMatchMethod
	 * @Description: TODO
	 * @param @param before
	 * @param @param target
	 * @param @return    
	 * @return boolean    
	 * @throws
	 */
	private List<String> getMatchMethod(String execution,Object target){
		List<String> list = new ArrayList<String>();
		Class<?>[] classes = target.getClass().getInterfaces();
		
		for(Class<?> clazz : classes){
			Method[] methods = clazz.getDeclaredMethods();
			
			Pattern pattern = Pattern.compile(execution);
			
			for(Method method : methods){
				StringBuffer methodName = new StringBuffer(clazz.getName());
				methodName.append(".").append(method.getName());
				if(pattern.matcher(methodName).matches()){
					list.add(method.getName());
				}
			}
		}
		
		return list;
	}
	
	
	/**
	 * ǰ����ǿ����
	 * @Title: beforeHandle
	 * @Description: TODO
	 * @param @param aspect �������
	 * @param @param beforeMethod  ��ǿ����
	 * @return void    
	 * @throws
	 */
	private void beforeHandle(Object aspect,Method beforeMethod){
		Before before = beforeMethod.getAnnotation(Before.class);
		String execution = before.value();
		for (Entry<String,Object> entry : beanMap.entrySet()) {
			String name = entry.getKey();
			Object target = entry.getValue();
			List<String> list = getMatchMethod(execution, target);
			if(list.size() > 0){
				Object obj = ProxyManager.createBeforeProxy(aspect, target, list, beforeMethod);
				beanMap.put(name, obj);
			}
		}
		
	}

	public Map<String, Object> getBeanMap() {
		return beanMap;
	}
}
