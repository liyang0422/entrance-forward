package site.manly.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ac.sict.context.support.InstanceService;
import cn.ac.sict.context.support.ServiceObject;
import cn.ac.sict.core.util.MapUtil;
import cn.ac.sict.core.util.RequestDataUtil;
import net.sf.json.JSONObject;

public class EntranceForwardServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 *  获取接口传入所有参数
		 */
		Map<String, String> argument = RequestDataUtil.requestToMap(req);
		/**
		 * 进行乱码检测，将汉字乱码转换成汉字
		 */
		MapUtil.messyCodeCheck(argument);
		/**
		 * 处理接口访问参数
		 */
		this.processMethod(req, resp, argument);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 *  获取接口传入所有参数
		 */
		Map<String, String> argument = RequestDataUtil.requestToMap(req);
		/**
		 * 处理接口访问参数
		 */
		this.processMethod(req, resp, argument);
	}

	/**
	 * 处理接口访问参数
	 * @param req
	 * @param resp
	 * @param argument
	 * @throws IOException
	 */
	private void processMethod(HttpServletRequest req, HttpServletResponse resp, Map<String, String> argument) throws IOException {
		/**
		 * 通过工具类处理对象数据并进行反向映射及返回数据
		 */
		InstanceService service = new InstanceService(argument, req);
		/**
		 * 返回数据对象
		 */
		Object ret_obj = service.invoke();
		/**
		 * 将数据装换为json格式
		 */
		JSONObject jsonObject = JSONObject.fromObject(ret_obj);
		/**
		 * 发送json数据
		 */
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(jsonObject.toString());

		out.flush();
		out.close();
	}

}
