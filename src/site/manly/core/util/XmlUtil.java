package site.manly.core.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {

	private String fileName;

	public XmlUtil(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, Object> getProperties() {

		System.out.println("-------读取interface.xml配置文件开始-------");

		ClassLoader classLoader = getClass().getClassLoader();
		/**
		 * getResource()方法会去classpath下找这个文件，获取到url resource,
		 * 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
		 */
		URL url = classLoader.getResource(fileName);

		Document doc = DomUtils.loadXMLDocumentFromFile(url.getFile().replaceAll("%20", " "));

		Map<String, Object> ret_data = this.doc2Map(doc);

		System.out.println("-------读取interface.xml配置文件完成-------");
		return ret_data;
	}

	/**
	 * 将Document对象转为map对象
	 * @param doc
	 * @return
	 */
	private Map<String, Object> doc2Map(Document doc) {
		// 得到一个elment根元素
		Element element = doc.getDocumentElement();

		return this.element2Map(element);
	}

	/**
	 * 将element对象转为map对象
	 * @param element
	 * @return
	 */
	private Map<String, Object> element2Map(Element element) {
		NodeList nl =element.getElementsByTagName("service");
		
		Map<String, Object> parentMap = new HashMap<String, Object>();
		
		return this.nodeList2Map(nl, parentMap);
	}

	/**
	 * 将nodeList对象转为map对象
	 * @param nodeList
	 * @param parentMap
	 * @return
	 */
	private Map<String, Object> nodeList2Map(NodeList nodeList, Map<String, Object> parentMap) {

		for (int i = 0; i < nodeList.getLength(); i++) {
			Map<String, Object> childMap = new HashMap<String, Object>();
			Node n = nodeList.item(i);

			NamedNodeMap attributes = n.getAttributes(); // 遍历节点所有属性如<dbstore
			// single="false"
			// att="tta">
			if (n.hasAttributes()) {
				for (int j = 0; j < attributes.getLength(); j++) {
					Node attribute = attributes.item(j);
					// 得到属性名
					childMap.put(attribute.getNodeName(), attribute.getNodeValue());
				}
				//将子节点数据插入父节点
				parentMap.put(n.getAttributes().getNamedItem("code").getNodeValue(), childMap);
			}

			//判断当前节点下是否存在子节点
			if (n.hasChildNodes()) {
				this.nodeList2Map(n.getChildNodes(), childMap);
			} else{				
				//判断当前数据是否为末级节点
				String method_val = n.getTextContent().trim().replaceAll("\n", "").replaceAll("\t", "");
				if(method_val != null && !"".equals(method_val)){
					parentMap.put("method", n.getTextContent());
				}
				
			}
			
		}

		return parentMap;
	}
}
