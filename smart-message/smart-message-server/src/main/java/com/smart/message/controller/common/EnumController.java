package com.smart.message.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.model.EnumItemable;
import com.smart.mvc.model.Item;
import com.smart.mvc.model.Result;

/**
 * <b>Description:枚举工具类</b><br>
 * @author 唐海洋
 * @version 1.0 2016-8-17 下午5:27:55
 */
@Controller
@RequestMapping("/common/enums")
public class EnumController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getByClassName", method = RequestMethod.GET)
	public @ResponseBody Result getByClassName(HttpServletRequest request,String className) {
		
		Class<? extends EnumItemable<?>> enumClass=null;
		List<String> enumClassNameList=(List<String>) request.getServletContext().getAttribute(EnumItemable.class.getSimpleName());
		if(!CollectionUtils.isEmpty(enumClassNameList)){
			for(String enumClassName:enumClassNameList){
				if(enumClassName.endsWith(className)){
					try {
						enumClass=(Class<? extends EnumItemable<?>>) Class.forName(enumClassName);
						break;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		Result r=null;
		if(enumClass==null){
			r=Result.createErrorResult().setMessage("类型匹配失败");
		}else{
			Map<String, Object> enumMap=new HashMap<String, Object>();
			for (EnumItemable<? extends Enum<?>> e : enumClass.getEnumConstants()) {
				enumMap.put(e.toString(), new Item(e.getLabel(), e.getValue()));
			}
			r=Result.createSuccessResult().setData(enumMap);
		}
		
		return r;
	}
}