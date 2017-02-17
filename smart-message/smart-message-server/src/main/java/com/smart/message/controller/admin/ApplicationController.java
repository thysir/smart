package com.smart.message.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jmx.access.InvocationFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.smart.message.model.Application;
import com.smart.message.model.ApplicationAuth;
import com.smart.message.model.ApplicationType;
import com.smart.message.service.ApplicationService;
import com.smart.message.service.ApplicationTypeService;
import com.smart.message.vo.ApplicationListItem;
import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;

/**
 * <b>Description:消息应用管理</b><br>
 * @author jeason
 */
@Controller
@RequestMapping("/admin/application")
public class ApplicationController extends BaseController{

	@Resource private ApplicationService applicationService;
	@Resource private ApplicationTypeService applicationTypeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/application";
	}
	
	@RequestMapping(path="/edit",method = RequestMethod.GET)
	public String edit(@ValidateParam(name = "id") Integer id, Model model) {
		
		Application bean=null;
		if(id!=null){
			bean=applicationService.get(id);
		}else{
			bean=new Application();
			bean.setSort(1);
		}
		
		List<ApplicationType> applicationTypeList=applicationTypeService.getAll();
		
		model.addAttribute("bean", bean);
		model.addAttribute("applicationTypeList", applicationTypeList);
		return "/admin/applicationEdit";
	}

	@RequestMapping(path="/list",method={RequestMethod.GET})
	public @ResponseBody Result list(@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
		@ValidateParam(name = "显示条数 ", validators = { Validator.NOT_BLANK }) Integer pageSize,
		@ValidateParam(name = "应用名称") String name) {
		
		Pagination<ApplicationListItem> p= applicationService.findPagination(new Pagination<ApplicationListItem>(pageNo, pageSize),name);
		return Result.createSuccessResult().setData(p);
	}

	@RequestMapping(path="/save",method = RequestMethod.POST)
	public @ResponseBody Result save(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "应用名称",validators=Validator.NOT_BLANK) String name,
			@ValidateParam(name = "应用描述") String description,
			@ValidateParam(name = "应用类型",validators=Validator.NOT_BLANK) Integer applicationTypeId,
			@ValidateParam(name = "排序",validators=Validator.NOT_BLANK) Integer sort,
			@ValidateParam(name = "应用授权信息",validators=Validator.NOT_BLANK) String authInfo) {
		
		Application bean=null;
		if(id!=null){
			bean=applicationService.get(id);
		}else{
			bean=new Application();
		}
		
		bean.setName(name);
		bean.setDescription(description);
		bean.setSort(sort);
		bean.setApplicationTypeId(applicationTypeId);

		applicationService.saveOrUpdate(bean,JSON.parseArray(authInfo, ApplicationAuth.class));
		return Result.createSuccessResult();
	}
	
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "id", validators = { Validator.NOT_BLANK }) String ids) {
		
		Result r=Result.createSuccessResult();		
		try{
			List<Integer> idList=getAjaxIds(ids);
			if(!CollectionUtils.isEmpty(idList)){
				applicationService.deleteById(idList);
			}
		}catch (InvocationFailureException e) {
			r=Result.createErrorResult().setMessage(e.getMessage());
		}
		
		return r;
	}
}