package xyz.letus.framework.ioc;

import java.lang.reflect.Field;
import java.util.Map;

import xyz.letus.framework.ioc.annotation.Inject;

/**
 * ����ע��������
 * @ClassName: IocHelper
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2015��9��19��
 *
 */
public class IocHelper {
	/**
	 * ע������
	 * @Title: inject
	 * @Description: TODO
	 * @param     
	 * @return void    
	 * @throws
	 */
	public static void inject(Map<String, Object> beanMap){
		/**
		 * Ϊ���е�����ע��ֵ
		 */
		for(Map.Entry<String, Object> entry : beanMap.entrySet()){
			String name = entry.getKey();
			Object beanInstance = entry.getValue();
			
			Field[] beanFields = beanInstance.getClass().getDeclaredFields();
			
			for(Field field : beanFields){
				if(field.isAnnotationPresent(Inject.class)){
					Inject inject = field.getAnnotation(Inject.class);
					if(inject.value().length() > 0){
						name = inject.value();
					}
					Class<?> fieldClazz = field.getType();
					Object fieldInstance = beanMap.get(name);
					if(fieldInstance != null){
						ReflectionFactory.setField(beanInstance, field, fieldInstance);
					}
				}
			}
		}
	}
}
