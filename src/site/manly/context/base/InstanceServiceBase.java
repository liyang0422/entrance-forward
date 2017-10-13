package site.manly.context.base;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.ac.sict.bean.reflect.InstanceMethod;
import cn.ac.sict.core.util.CacheUtil;

public class InstanceServiceBase {
	protected String[] fdms;
	protected HttpServletRequest req;
	protected Map<String, String> argument;

	protected Object service;
	
	protected Map<String, Object> service_map;
	
	protected InstanceMethod instanceObject;
	
	protected Method method;
	
	/**
	 * Service瀵硅薄澶勭悊鏋勯��
	 * @param argument鎺ユ敹鏁版嵁map
	 * @param req瀵硅薄
	 */
	public InstanceServiceBase(Map<String, String> argument, HttpServletRequest req) {
		// 鑾峰彇鎺ュ彛浼犲叆鏂规硶浠ｇ爜
		String fdm = argument.get("fdm");
		// 瑙ｆ瀽鏂规硶浠ｇ爜
		String[] fdms = fdm.split("\\.");
		//灏嗚В鏋愬悗鐨勬墍鏈変唬鐮佽繘琛屽睘鎬у鍒�
		this.fdms = fdms;
		//req瀵硅薄灞炴�ц祴鍊�
		this.req = req;
		//鎺ユ敹鏁版嵁map灞炴�ц祴鍊�
		this.argument = argument;
		//鑾峰彇service灞備腑鐨勬墍鏈夊弬鏁�
		this.service_map = (Map<String, Object>) CacheUtil.interfaces.get(fdms[0]);
		//鍒濆鍖杝ervice灞傚璞�
		this.initService();
		//鍒濆鍖杝ervice瀵硅薄涓殑鏂规硶
		this.initMethod();
	}

	/**
	 * 鍒濆鍖杝ervice灞傚璞�
	 */
	private void initService() {

		// 鑾峰彇service瀵硅薄
		Object object = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext())
				.getBean(service_map.get("class").toString());
		//service瀵硅薄灞炴�ц祴鍊�
		this.service = object;
	}
	
	/**
	 * 鍒濆鍖杝ervice瀵硅薄涓殑鏂规硶
	 */
	private void initMethod(){
		// 鑾峰彇service灞傛柟娉昺ap
		Map<String, Object> method_map = (Map<String, Object>) service_map.get(fdms[1]);
			
		this.instanceObject = new InstanceMethod(service, method_map.get("method").toString(), argument.getClass());
		
		this.method = this.instanceObject.getMethod();
	}
}
