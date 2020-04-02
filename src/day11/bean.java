package day11;

import java.util.List;

public class bean {
	private String id;
	private String clazz;
	private String name;
	private List<property>propertys;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public List<property> getPropertys() {
		return propertys;
	}
	public void setPropertys(List<property> propertys) {
		this.propertys = propertys;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "bean [id=" + id + ", clazz=" + clazz + ", name=" + name + ", propertys=" + propertys + "]";
	}


}
