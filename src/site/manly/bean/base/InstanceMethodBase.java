package site.manly.bean.base;

import java.lang.reflect.Method;

public class InstanceMethodBase {
	
	protected Method method;

	/**
	 * 鍙嶅悜鏄犲皠method鍒濆鍖栨柟娉�
	 * 
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 */
	public InstanceMethodBase(Object object, String methodName, Class<?>... parameterTypes) {
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
}
