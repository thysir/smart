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

import com.smart.message.model.MessageApplication;
import com.smart.message.service.MessageApplicationService;
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
@RequestMapping("/admin/messageAppliaction")
public class MessageApplicationController extends BaseController{

	@Resource private MessageApplicationService messageApplicationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/messageAppliaction";
	}
	
	@RequestMapping(path="/list",method={RequestMethod.GET})
	public @ResponseBody Result list(@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
		@ValidateParam(name = "显示条数 ", validators = { Validator.NOT_BLANK }) Integer pageSize,
		@ValidateParam(name = "应用名称") String name) {
		
		Pagination<MessageApplication> p= messageApplicationService.findPagination(pageNo, pageSize,name);
		return Result.createSuccessResult().setData(p);
	}
	
	@RequestMapping(path="/edit",method = RequestMethod.GET)
	public String edit(@ValidateParam(name = "id") Integer id, Model model) {
		
		MessageApplication bean=null;
		if(id!=null){
			bean=messageApplicationService.get(id);
		}else{
			bean=new MessageApplication();
			bean.setSort(1);
		}
		
		model.addAttribute("bean", bean);
		return "/admin/messageApplicationEdit";
	}

	@RequestMapping(path="/save",method = RequestMethod.POST)
	public @ResponseBody Result save(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "应用名称",validators=Validator.NOT_BLANK) String name,
			@ValidateParam(name = "应用描述") String description,
			@ValidateParam(name = "类型",validators=Validator.NOT_BLANK) Integer type,
			@ValidateParam(name = "排序",validators=Validator.NOT_BLANK) Integer sort) {
		
		MessageApplication bean=null;
		if(id!=null){
			bean=messageApplicationService.get(id);
		}else{
			bean=new MessageApplication();
		}
		
		bean.setName(name);
		bean.setDescription(description);
		bean.setSort(sort);
		bean.setType(type);

		messageApplicationService.saveOrUpdate(bean);
		return Result.createSuccessResult();
	}
	
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "id", validators = { Validator.NOT_BLANK }) String ids) {
		
		Result r=Result.createSuccessResult();		
		try{
			List<Integer> idList=getAjaxIds(ids);
			if(!CollectionUtils.isEmpty(idList)){
				messageApplicationService.deleteById(idList);
			}
		}catch (InvocationFailureException e) {
			r=Result.createErrorResult().setMessage(e.getMessage());
		}
		
		return r;
	}
}