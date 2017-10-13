package site.manly.context.support;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.ac.sict.context.base.InstanceServiceBase;

public class InstanceService extends InstanceServiceBase{

	public InstanceService(Map<String, String> argument, HttpServletRequest req) {
		super(argument, req);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 鑾峰彇service瀵硅薄
	 * @return
	 */
	public Object getService() {

		return service;
	}

	/**
	 * 鑾峰彇Method瀵硅薄
	 * @return
	 */
	public Method getMethod(){
		return method;
	}
	
	/**
	 * 鑾峰彇鏂规硶杩斿洖鍊�
	 * @return
	 */
	public Object invoke(){
		try {
			return this.instanceObject.getMethod().invoke(service, argument);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
