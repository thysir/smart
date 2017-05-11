package com.smart.sso.server.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.server.model.Modular;
import com.smart.sso.server.service.ModularService;

/**
 * <b>Description:app模块管理</b><br>
 * @author jeason
 */
@Controller
@RequestMapping("/admin/modular")
public class ModularController extends BaseController {
	
	@Resource private ModularService modularService;

	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model,
			@ValidateParam(name = "appId",validators={Validator.NOT_BLANK}) Integer appId) {
		
		model.addAttribute("appId", appId);
		return "/admin/modular";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model,
			@ValidateParam(name = "id") Integer id,
			@ValidateParam(name = "appId") Integer appId) {
		Modular bean;
		if (id == null) {
			bean = new Modular();
			bean.setAppId(appId);
		}else {
			bean = modularService.get(id);
		}
		model.addAttribute("bean", bean);
		return "/admin/modularEdit";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(@ValidateParam(name = "appId",validators={Validator.NOT_BLANK}) Integer appId,
			@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
			@ValidateParam(name = "显示条数 ", validators = { Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(modularService.findPaginationByAppId(appId,pageNo, pageSize));
	}
	
	@RequestMapping(value = "/listByAppId", method = RequestMethod.GET)
	public @ResponseBody Result listByAppId(@ValidateParam(name = "appId",validators={Validator.NOT_BLANK}) Integer appId) {
		return Result.createSuccessResult().setData(modularService.findByAppId(appId));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(@ValidateParam(name = "ID") Integer id,
			@ValidateParam(name = "appId",validators={Validator.NOT_BLANK}) Integer appId,
			@ValidateParam(name = "名称 ", validators = { Validator.NOT_BLANK }) String name,
			@ValidateParam(name = "编码 ", validators = { Validator.NOT_BLANK }) String code) {
		Modular app;
		if (id == null) {
			app = new Modular();
			app.setAppId(appId);
		}else {
			app = modularService.get(id);
		}
		app.setName(name);
		app.setCode(code);
		modularService.saveOrUpdate(app);
		return Result.createSuccessResult();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "ids", validators = { Validator.NOT_BLANK }) String ids) {
		return Result.createSuccessResult().setData(modularService.deleteById(getAjaxIds(ids)));
	}
}