package com.smart.message.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.message.service.ApplicationAuthService;
import com.smart.message.vo.ApplicationAuthItem;
import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;

/**
 * <b>Description:消息应用授权信息</b><br>
 * @author jeason
 */
@Controller
@RequestMapping("/admin/applicationAuth")
public class ApplicationAuthController extends BaseController{

	@Resource private ApplicationAuthService applicationAuthService;
	
	@RequestMapping(path="authInfo",method = RequestMethod.GET)
	public @ResponseBody Result authInfo(Model model,
			@ValidateParam(name = "应用类型id", validators = { Validator.NOT_BLANK }) Integer applicationTypeId,
			@ValidateParam(name = "应用id") Integer applicationId) {
		
		List<ApplicationAuthItem> applicationAuthItemList=
				applicationAuthService.findAuthInfoByApplicationIdAndApplicationTypeId(applicationId,applicationTypeId);
	
		return Result.createSuccessResult().setData(applicationAuthItemList);
	}
}