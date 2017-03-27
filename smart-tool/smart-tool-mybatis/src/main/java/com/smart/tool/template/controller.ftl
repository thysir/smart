package com.${company!''}.${project!''}.<#if module??>${module}.</#if>controller;

import javax.annotation.Resource;
<#if containDate>
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
<#else>

</#if>
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.${company!''}.${project!''}.<#if module??>${module}.</#if>model.${model};
import com.${company!''}.${project!''}.<#if module??>${module}.</#if>service.${model}Service;
import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Result;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;

/**
 * ${tableComment}Controller
 * 
 * @author Joe
 */
@Controller
@RequestMapping("<#if module??>/${module}</#if>/${_model}")
public class ${model}Controller extends BaseController {

	@Resource private ${model}Service ${_model}Service;

	/**
	 * ${tableComment}列表
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "<#if module??>/${module}</#if>/${_model}";
	}
	
	/**
	 * ajax读取表格数据
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
			@ValidateParam(name = "显示条数", validators = { Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(${_model}Service.findByAllPagination(new Pagination<${model}>(pageNo, pageSize)));
	}

	/**
	 * 编辑按钮(含添加和修改两种操作)
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ValidateParam(name = "id") Integer id, Model model) {
		${model} ${_model};
		if (id == null) {
			${_model} = new ${model}();
		}else {
			${_model} = ${_model}Service.get(id);
		}
		model.addAttribute("${_model}", ${_model});
		return "<#if module??>/${module}</#if>/${_model}Edit";
	}

	/**
	 * 保存(含添加和更新两种操作)
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ValidateParam(name = "ID") Integer id,
		<#list fieldList as field>
			@ValidateParam(<#if field.description??>name = "${field.description}",</#if> validators = { <#if field.nullableStr == "false">Validator.NOT_BLANK</#if> }) <#if field.fieldType == "Date">@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date<#else>${field.fieldType}</#if> ${field.fieldName}<#if field_has_next>,</#if>
		</#list>
			) {
		${model} ${_model};
		if (id == null) {
			${_model} = new ${model}();
		}else {
			${_model} = ${_model}Service.get(id);
		}
		<#list fieldList as field>
		${_model}.set${field.upperFieldName}(${field.fieldName});
		</#list>
		${_model}Service.saveOrUpdate(${_model});
		return Result.createSuccessResult();
	}

	/**
	 * 删除(根据id删除，含多条删除情况)
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "ids", validators = { Validator.NOT_BLANK }) String ids) {
		return Result.createSuccessResult().setData(${_model}Service.deleteById(getAjaxIds(ids)));
	}
}