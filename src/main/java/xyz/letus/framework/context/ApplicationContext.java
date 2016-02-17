package xyz.letus.framework.context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import xyz.letus.framework.ioc.BeanFactory;

/**
 * ������
 * @ClassName: IocContext
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2016��2��17��
 *
 */
public class ApplicationContext {
	private String path;
	
	private ApplicationContext(String path){
		this.path = path;
		init();
	}
	
	public static ApplicationContext getContext(String path){
		return new ApplicationContext(path);
	}
	/**
	 * ��ʼ��
	 * @Title: init
	 * @Description: TODO
	 * @param     
	 * @return void    
	 * @throws
	 */
	public void init(){
		try {
			ResourceFactory.parse(path);
			List<String> packages = ResourceFactory.getPackages();
			BeanFactory.createInstance(packages);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���ݱ�����ȡһ������
	 * @Title: getBean
	 * @Description: TODO
	 * @param @param name
	 * @param @return    
	 * @return T    
	 * @throws
	 */
	public <T> T getBean(String name)  {
		return BeanFactory.getBean(name);
	}
}
