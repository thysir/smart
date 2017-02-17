package com.smart.message.controller.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jmx.access.InvocationFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.message.model.Application;
import com.smart.message.model.ApplicationCallLog;
import com.smart.message.service.ApplicationCallLogService;
import com.smart.message.service.ApplicationService;
import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;

/**
 * <b>Description:应用调用日志</b><br>
 * @author jeason
 */
@Controller
@RequestMapping("/admin/applicationCallLog")
public class ApplicationCallLogController extends BaseController{

	@Resource private ApplicationService applicationService;
	@Resource private ApplicationCallLogService applicationCallLogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model,
			@ValidateParam(name = "应用id", validators = { Validator.NOT_BLANK }) Integer applicationId) {
		
		model.addAttribute("applicationId", applicationId);
		model.addAttribute("application", applicationService.get(applicationId));
		return "/admin/applicationCallLog";
	}
	
	@RequestMapping(path="/show",method = RequestMethod.GET)
	public String show(@ValidateParam(name = "id", validators = { Validator.NOT_BLANK }) Integer id, Model model) {
		
		ApplicationCallLog bean=applicationCallLogService.get(id);
		Application application=null;
		if(bean!=null){
			application=applicationService.get(bean.getApplicationId());
		}
		
		model.addAttribute("bean", bean);
		model.addAttribute("application", application);
		return "/admin/applicationCallLogEdit";
	}

	@RequestMapping(path="/list",method={RequestMethod.GET})
	public @ResponseBody Result list(@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
		@ValidateParam(name = "显示条数 ", validators = { Validator.NOT_BLANK }) Integer pageSize,
		@ValidateParam(name = "应用id", validators = { Validator.NOT_BLANK }) Integer applicationId,
		@ValidateParam(name = "消息接收者") String receiver,
		@ValidateParam(name = "消息内容") String content,
		@ValidateParam(name = "查询开始时间") @DateTimeFormat(pattern="yyyy-MM-dd") Date beginTime,
		@ValidateParam(name = "查询结束时间") @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime) {
		
		Pagination<ApplicationCallLog> p= applicationCallLogService.findPagination(new Pagination<ApplicationCallLog>(pageNo, pageSize),
				applicationId, receiver, content, beginTime, endTime);
		return Result.createSuccessResult().setData(p);
	}

	@RequestMapping(path="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "id", validators = { Validator.NOT_BLANK }) String ids) {
		
		Result r=Result.createSuccessResult();		
		try{
			List<Integer> idList=getAjaxIds(ids);
			if(!CollectionUtils.isEmpty(idList)){
				applicationCallLogService.deleteById(idList);
			}
		}catch (InvocationFailureException e) {
			r=Result.createErrorResult().setMessage(e.getMessage());
		}
		
		return r;
	}
}