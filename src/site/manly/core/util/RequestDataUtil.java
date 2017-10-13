package site.manly.core.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestDataUtil {

	/**
	 * 获取request对象中的参数，并封装到Map中
	 * @param req
	 * @return
	 */
	public static Map<String, String> requestToMap(HttpServletRequest req) {
		Map<String, String> result = new HashMap<String, String>();

		Enumeration<String> rnames = req.getParameterNames();
		while (rnames.hasMoreElements()) {
			String key = (String) rnames.nextElement();
			String value = req.getParameter(key);
			result.put(key, value);
		}
		return result;
	}

}
