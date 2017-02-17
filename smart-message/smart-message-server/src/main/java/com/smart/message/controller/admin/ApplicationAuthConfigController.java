package com.smart.message.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.message.model.ApplicationAuthConfig;
import com.smart.message.service.ApplicationAuthConfigService;
import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;

/**
 * <b>Description:消息应用授权配置信息</b><br>
 * @author jeason
 */
@Controller
@RequestMapping("/admin/applicationAuthConfig")
public class ApplicationAuthConfigController extends BaseController{

	@Resource private ApplicationAuthConfigService applicationAuthConfigService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model,
			@ValidateParam(name = "applicationTypeId", validators = { Validator.NOT_BLANK }) Integer applicationTypeId) {
		
		model.addAttribute("applicationTypeId", applicationTypeId);
		return "/admin/applicationAuthConfig";
	}
	
	@RequestMapping(path="/edit",method = RequestMethod.GET)
	public String edit(Model model,
			@ValidateParam(name = "id") Integer id,
			@ValidateParam(name = "applicationTypeId") Integer applicationTypeId) {
		
		ApplicationAuthConfig bean=null;
		if(id!=null){
			bean=applicationAuthConfigService.get(id);
		}else{
			bean=new ApplicationAuthConfig();
			bean.setApplicationTypeId(applicationTypeId);
		}
		
		model.addAttribute("bean", bean);
		return "/admin/applicationAuthConfigEdit";
	}

	@RequestMapping(path="/list",method={RequestMethod.GET})
	public @ResponseBody Result list(@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
		@ValidateParam(name = "显示条数 ", validators = { Validator.NOT_BLANK }) Integer pageSize,
		@ValidateParam(name = "应用类型id ", validators = { Validator.NOT_BLANK }) Integer applicationTypeId) {
		
		Pagination<ApplicationAuthConfig> p= applicationAuthConfigService.findPagination(new Pagination<ApplicationAuthConfig>(pageNo, pageSize),applicationTypeId);
		return Result.createSuccessResult().setData(p);
	}

	@RequestMapping(path="/save",method = RequestMethod.POST)
	public @ResponseBody Result save(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "应用类型id",validators=Validator.NOT_BLANK) Integer applicationTypeId,
			@ValidateParam(name = "授权的key",validators=Validator.NOT_BLANK) String authKey,
			@ValidateParam(name = "描述") String description) {
		
		ApplicationAuthConfig bean=null;
		if(id!=null){
			bean=applicationAuthConfigService.get(id);
		}else{
			bean=new ApplicationAuthConfig();
		}
		
		bean.setApplicationTypeId(applicationTypeId);
		bean.setAuthKey(authKey);
		bean.setDescription(description);

		applicationAuthConfigService.saveOrUpdate(bean);
		return Result.createSuccessResult();
	}
	
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "id", validators = { Validator.NOT_BLANK }) String ids) {
		
		Result r=Result.createSuccessResult();		
		try{
			List<Integer> idList=getAjaxIds(ids);
			if(!CollectionUtils.isEmpty(idList)){
				applicationAuthConfigService.deleteById(idList);
			}
		}catch (Exception e) {
			r=Result.createErrorResult().setMessage("数据被引用,无法删除");
		}
		
		return r;
	}
	
	@RequestMapping(path="/validateAuthKey",method = RequestMethod.POST)
	public @ResponseBody Result validateAuthKey(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "应用类型",validators=Validator.NOT_BLANK) Integer applicationTypeId,
			@ValidateParam(name = "授权key",validators=Validator.NOT_BLANK) String authKey) {

		ApplicationAuthConfig applicationAuthConfig=applicationAuthConfigService.findByApplicationTypeIdAndAuthKey(applicationTypeId,authKey);
		if(applicationAuthConfig!=null){
			
			if(!applicationAuthConfig.getId().equals(id)){
				return Result.createErrorResult().setMessage("该授权key已经存在");
			}
		}
		return Result.createSuccessResult();
	}
}