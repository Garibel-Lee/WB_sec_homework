package day11;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class Test{
public static void main(String[] args) throws Exception {
	String a;
	newSingleThreadExecutor();
	List<bean> beans = new ArrayList<bean>();
	SAXReader reader  = new SAXReader();
	InputStream is =Test.class.getClassLoader().getResourceAsStream("day11/app.xml");
	Document doc  = reader.read(is);
	List<Element> elements = doc.selectNodes("/beans/bean");
	for (Element element : elements) {
		bean bean1 = new bean();

		List<Element> propertyElements = element.elements("property");
		List<property>propertys=new ArrayList();
		for (Element property2 : propertyElements) {
			property property1=new property();
			property1.setName(property2.attribute("name").getText());
			if(property2.attribute("value")==null){
				property1.setValue(" ");
			}else{
				property1.setValue(property2.attribute("value").getText());
			}
			propertys.add(property1);
		}
		bean1.setPropertys(propertys);
		if(element.element("class")==null){
			bean1.setClazz("");
		}else{
			bean1.setClazz(element.element("class").getTextTrim());
		}
		if(element.element("name")==null&&element.element("id")==null){
			bean1.setId("");
			bean1.setClazz(" ");
		}else if(element.element("name")==null){
			bean1.setName(element.element("id").getTextTrim());
		}else if (element.element("id")==null) {
			bean1.setId(element.element("name").getTextTrim());
		}else{
			bean1.setId(element.element("id").getTextTrim());
			bean1.setName(element.element("name").getTextTrim());
		}
		beans.add(bean1);

	}
	//文件关闭
	is.close();

	//遍历集合
	for (bean b : beans) {
		System.out.println(b);
	}

}
}
