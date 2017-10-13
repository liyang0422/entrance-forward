package site.manly.bean.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.ac.sict.bean.base.InstanceMethodBase;

public class InstanceMethod extends InstanceMethodBase{

	public InstanceMethod(Object object, String methodName, Class<?>... parameterTypes) {
		super(object, methodName, parameterTypes);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 鑾峰彇Method瀵硅薄
	 * @return
	 */
	public Method getMethod() {

		return method;
	}

	/**
	 * 浼犲叆鐩稿簲鍙傛暟锛岃幏鍙栨柟娉曡繑鍥炲�兼柟娉�
	 * @param obj
	 * @param args
	 * @return
	 */
	public Object invoke(Object obj, Object... args) {
		// 浼犲叆map绫诲瀷鍙傛暟骞惰繑鍥�
		try {
			Object ret_obj = method.invoke(obj, args);
			return ret_obj;
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
