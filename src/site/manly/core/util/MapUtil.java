package site.manly.core.util;

import java.util.Iterator;
import java.util.Map;

public class MapUtil {
	
	/**
	 * 检测map中是否存在乱码，如果存在则将乱码进行转换
	 * @param argument
	 * @return
	 */
	public static void messyCodeCheck(Map<String, String> argument){
		if(argument != null && !argument.isEmpty()){
			Iterator<String> iter = argument.keySet().iterator();
			
			while (iter.hasNext()) {
				String key = iter.next();
				String value = argument.get(key);
				argument.put(key, ChineseUtill.toChinese(value));
			}
		}
	}
}
