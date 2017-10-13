package site.manly.bean.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Deprecated
public class CreateObject {
	
	private Method method;
	
	/**
	 * 宸茶繃鏃讹紝涓嶆帹鑽愪娇鐢�
	 * 鍙嶅悜鏄犲皠method鍒濆鍖栨柟娉�
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 */
	@Deprecated
	public CreateObject(Object object, String methodName, Class<?>... parameterTypes) {
		try {
			this.method = object.getClass().getMethod(methodName, parameterTypes);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 宸茶繃鏃讹紝涓嶆帹鑽愪娇鐢�
	 * 鑾峰彇Method瀵硅薄
	 * @return
	 */
	@Deprecated
	public Method getMethod() {

		return method;
	}

	/**
	 * 宸茶繃鏃讹紝涓嶆帹鑽愪娇鐢�
	 * 浼犲叆鐩稿簲鍙傛暟锛岃幏鍙栨柟娉曡繑鍥炲�兼柟娉�
	 * @param obj
	 * @param args
	 * @return
	 */
	@Deprecated
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
