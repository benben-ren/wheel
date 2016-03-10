package xyz.letus.framework.context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * ��Դ������
 * @ClassName: ResourceFactory
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2016��2��17��
 *
 */
public class ResourceFactory {
	private static final List<String> SCAN_PACKAGES = new ArrayList<String>();

	/**
	 * ������Դ�ļ��������ļ���
	 * @Title: parse
	 * @Description: TODO
	 * @param @param fileName
	 * @param @throws FileNotFoundException
	 * @param @throws IOException    
	 * @return void    
	 * @throws
	 */
	public static void parse(String fileName) throws FileNotFoundException, IOException {
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);  
		Properties properties = new Properties();
		properties.load(stream);
		Enumeration<?> e = properties.propertyNames();// �õ������ļ�������
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = properties.getProperty(key);
			
			if("scanPackage".equals(key)){
				SCAN_PACKAGES.add(value);
			}
		}
	}
	/**
	 * ��ȡ�Զ������İ�·��
	 * @Title: getPackages
	 * @Description: TODO
	 * @param @return    
	 * @return List<String>    
	 * @throws
	 */
	public static List<String> getPackages(){
		return SCAN_PACKAGES;
	}
	
}
