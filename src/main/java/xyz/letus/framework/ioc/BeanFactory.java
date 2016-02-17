package xyz.letus.framework.ioc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * Bean������
 * @ClassName: BeanHelper
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2015��9��19��
 *
 */
public class BeanFactory {
	private static final Map<String, Object> BEAN_MAP = new HashMap<String, Object>();
	
	/**
	 * ���������йܵ�ʵ��
	 * @Title: createInstance
	 * @Description: TODO
	 * @param @param packages    
	 * @return void    
	 * @throws
	 */
	public static void createInstance(List<String> packages){
		for (String packagePath : packages) {
			Map<String, Class<?>> beanClasses = ClassFactory
					.getBeanClasses(packagePath);
			for (Entry<String, Class<?>> entry : beanClasses.entrySet()) {
				Object obj = ReflectionFactory.newInstance(entry.getValue());
				
				BEAN_MAP.put(entry.getKey(), obj);
			}
		}
		
		IocHelper.inject(BEAN_MAP);
	}
	
	
	/**
	 * ��ȡBeanʵ��
	 * @Title: getBean
	 * @Description: TODO
	 * @param @param name
	 * @param @return    
	 * @return T    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		if(!BEAN_MAP.containsKey(name)){
			throw new RuntimeException("can not get bean by className:"+name);
		}
		
		return (T) BEAN_MAP.get(name);
	}
}
