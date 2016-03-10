package xyz.letus.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * ���������
 * @ClassName: ProxyManager
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2016��3��7��
 *
 */
public class ProxyManager {
	/**
	 * ����һ��ǰ����ǿ�Ĵ������
	 * @Title: createBeforeProxy
	 * @Description: TODO
	 * @param @param aspect �������
	 * @param @param target Ŀ�����
	 * @param @param matchMethods ƥ��ķ�����
	 * @param @param before ǰ����ǿ����
	 * @param @return    
	 * @return T    
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createBeforeProxy(final Object aspect,final Object target,final List<String> matchMethods,final Method before){
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				/**
				 * ����ǰ����ǿ
				 */
				if(matchMethods.contains(method.getName())){
					before.invoke(aspect, args);
				}
				
				Object result = method.invoke(target, args);
				return result;
			}
		});
	}
}
