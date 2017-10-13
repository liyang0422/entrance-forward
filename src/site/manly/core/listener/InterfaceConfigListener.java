package site.manly.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.ac.sict.core.util.CacheUtil;
import cn.ac.sict.core.util.XmlUtil;

public class InterfaceConfigListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/**
		 * 加载配置的接口信息
		 */

		String fileName = event.getServletContext().getInitParameter("interfaceConfigLocation");
		XmlUtil xmlUtil = new XmlUtil(fileName);
		CacheUtil.interfaces = xmlUtil.getProperties();
	}
	
}
