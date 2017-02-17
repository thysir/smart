package com.smart.message.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.message.model.ApplicationType;
import com.smart.message.service.ApplicationTypeService;
import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;

/**
 * <b>Description:消息应用类型管理</b><br>
 * @author jeason
 */
@Controller
@RequestMapping("/admin/applicationType")
public class ApplicationTypeController extends BaseController{

	@Resource private ApplicationTypeService applicationTypeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/applicationType";
	}
	
	@RequestMapping(path="/edit",method = RequestMethod.GET)
	public String edit(@ValidateParam(name = "id") Integer id, Model model) {
		
		ApplicationType bean=null;
		if(id!=null){
			bean=applicationTypeService.get(id);
		}else{
			bean=new ApplicationType();
			bean.setSort(1);
		}
		
		model.addAttribute("bean", bean);
		return "/admin/applicationTypeEdit";
	}

	@RequestMapping(path="/list",method={RequestMethod.GET})
	public @ResponseBody Result list(@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
		@ValidateParam(name = "显示条数 ", validators = { Validator.NOT_BLANK }) Integer pageSize,
		@ValidateParam(name = "类型名称") String name) {
		
		Pagination<ApplicationType> p= applicationTypeService.findPagination(new Pagination<ApplicationType>(pageNo, pageSize),name);
		return Result.createSuccessResult().setData(p);
	}

	@RequestMapping(path="/save",method = RequestMethod.POST)
	public @ResponseBody Result save(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "类型名称",validators=Validator.NOT_BLANK) String name,
			@ValidateParam(name = "应用类型",validators=Validator.NOT_BLANK) Integer type,
			@ValidateParam(name = "描述") String description,
			@ValidateParam(name = "排序",validators=Validator.NOT_BLANK) Integer sort) {
		
		ApplicationType bean=null;
		if(id!=null){
			bean=applicationTypeService.get(id);
		}else{
			bean=new ApplicationType();
		}
		
		bean.setName(name);
		bean.setDescription(description);
		bean.setSort(sort);
		bean.setType(type);

		applicationTypeService.saveOrUpdate(bean);
		return Result.createSuccessResult();
	}
	
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "id", validators = { Validator.NOT_BLANK }) String ids) {
		
		Result r=Result.createSuccessResult();		
		try{
			List<Integer> idList=getAjaxIds(ids);
			if(!CollectionUtils.isEmpty(idList)){
				applicationTypeService.deleteById(idList);
			}
		}catch (Exception e) {
			r=Result.createErrorResult().setMessage("数据被引用,无法删除");
		}
		
		return r;
	}
	
	@RequestMapping(path="/validateType",method = RequestMethod.POST)
	public @ResponseBody Result validateType(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "应用类型",validators=Validator.NOT_BLANK) Integer type) {

		ApplicationType applicationType=applicationTypeService.getByType(type);
		if(applicationType!=null){
			if(!applicationType.getId().equals(id)){
				return Result.createErrorResult().setMessage("该类型已经存在");
			}
		}
		return Result.createSuccessResult();
	}
}