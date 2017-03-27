package com.smart.mvc.model.mybatis;

import java.util.HashSet;
import java.util.Set;

import com.smart.mvc.exception.ApplicationException;
import com.smart.util.StringUtils;

public class QueryPropertys {

	/** 排序语句 */
	private String sort;
	/** 属性集合 */
	private Set<Property> propertys;
	
	public QueryPropertys(String property, Object value) {
		
		this(null,new String[]{property},new Object[]{value});
	}
	
	public QueryPropertys(String sort, String property, Object value) {
		
		this(sort,new String[]{property},new Object[]{value});
	}

	public QueryPropertys(String[] propertys, Object[] values) {

		this(null,propertys,values);
	}
	
	public QueryPropertys(String sort, String[] propertys, Object[] values) {
		
		//验证参数长度是否一致
		if((propertys==null && values!=null) || (propertys!=null && values==null)
				|| (propertys!=null && values!=null && propertys.length!=values.length)){
			throw new ApplicationException("对象创建失败，属性参数长度不匹配");
		}
		
		this.sort=sort;
		if(propertys!=null){
			this.propertys=new HashSet<Property>(propertys.length);
			for(int i=0,size=propertys.length;i<size;i++){
				this.propertys.add(new Property(propertys[i], values[i]));
			}
		}
	}
	
	public QueryPropertys addProperty(String property,Object value) {
		
		if(StringUtils.isNotBlank(property)){
			this.propertys.add(new Property(property, value));
		}
		return this;
	}

	public String getSort() {
		return sort;
	}

	public Set<Property> getPropertys() {
		return propertys;
	}

}
