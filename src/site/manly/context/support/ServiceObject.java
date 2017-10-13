package site.manly.context.support;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.ac.sict.bean.reflect.CreateObject;
import cn.ac.sict.core.util.CacheUtil;

/**
 * 已过时，不推荐使用，统一使用InstanceService
 * @author Administrator
 *
 */
@Deprecated
public class ServiceObject {

	private String[] fdms;
	private HttpServletRequest req;
	private Map<String, String> argument;

	private Object service;
	
	private Map<String, Object> service_map;
	
	private CreateObject createObject;

	/**
	 * 已过时，不推荐使用
	 * Service对象处理构造
	 * @param argument接收数据map
	 * @param req对象
	 */
	@Deprecated
	public ServiceObject(Map<String, String> argument, HttpServletRequest req) {
		// 获取接口传入方法代码
		String fdm = argument.get("fdm");
		// 解析方法代码
		String[] fdms = fdm.split("\\.");
		//将解析后的所有代码进行属性复制
		this.fdms = fdms;
		//req对象属性赋值
		this.req = req;
		//接收数据map属性赋值
		this.argument = argument;
		//获取service层中的所有参数
		this.service_map = (Map<String, Object>) CacheUtil.interfaces.get(fdms[0]);
		//初始化service层对象
		this.initService();
		//初始化service对象中的方法
		this.initMethod();
	}

	/**
	 * 已过时，不推荐使用
	 * 初始化service层对象
	 */
	@Deprecated
	private void initService() {

		// 获取service对象
		Object object = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext())
				.getBean(service_map.get("class").toString());
		//service对象属性赋值
		this.service = object;
	}
	
	/**
	 * 已过时，不推荐使用
	 * 初始化service对象中的方法
	 */
	@Deprecated
	private void initMethod(){
		// 获取service层方法map
		Map<String, Object> method_map = (Map<String, Object>) service_map.get(fdms[1]);
			
		this.createObject = new CreateObject(service, method_map.get("method").toString(), argument.getClass());
	}
	
	/**
	 * 已过时，不推荐使用
	 * 获取service对象
	 * @return
	 */
	@Deprecated
	public Object getService() {

		return service;
	}

	/**
	 * 已过时，不推荐使用
	 * 获取Method对象
	 * @return
	 */
	@Deprecated
	public Method getMethod(){
		return this.createObject.getMethod();
	}
	
	/**
	 * 已过时，不推荐使用
	 * 获取方法返回值
	 * @return
	 */
	@Deprecated
	public Object invoke(){
		try {
			return this.createObject.getMethod().invoke(service, argument);
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
