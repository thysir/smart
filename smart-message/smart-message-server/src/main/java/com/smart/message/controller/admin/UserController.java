package com.smart.message.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.message.model.User;
import com.smart.message.service.ApplicationService;
import com.smart.message.service.UserService;
import com.smart.message.vo.UserListItem;
import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.provider.PasswordProvider;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.util.StringUtils;

/**
 * <b>Description:用户管理</b><br>
 * @author jeason
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController{

	@Resource private UserService userService;
	@Resource private ApplicationService applicationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/user";
	}
	
	@RequestMapping(path="/edit",method = RequestMethod.GET)
	public String edit(@ValidateParam(name = "id") Integer id, Model model) {
		
		User bean=null;
		if(id!=null){
			bean=userService.get(id);
		}else{
			bean=new User();
		}
		
	//	List<Application> applicationList=applicationService.findAll();
		
		model.addAttribute("bean", bean);
	//	model.addAttribute("applicationList", applicationList);
		return "/admin/userEdit";
	}

	@RequestMapping(path="/list",method={RequestMethod.GET})
	public @ResponseBody Result list(@ValidateParam(name = "开始页码", validators = { Validator.NOT_BLANK }) Integer pageNo,
		@ValidateParam(name = "显示条数 ", validators = { Validator.NOT_BLANK }) Integer pageSize,
		@ValidateParam(name = "类型名称") String name) {
		
		Pagination<UserListItem> p= userService.findPagination(new Pagination<UserListItem>(pageNo, pageSize),name);
		return Result.createSuccessResult().setData(p);
	}

	@RequestMapping(path="/save",method = RequestMethod.POST)
	public @ResponseBody Result save(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "用户名称",validators=Validator.NOT_BLANK) String name,
			@ValidateParam(name = "登录帐号",validators=Validator.NOT_BLANK) String account,
			@ValidateParam(name = "登录密码") String password,
			@ValidateParam(name = "是否禁用",validators=Validator.NOT_BLANK) Boolean disable,
			@ValidateParam(name = "应用列表") String applicationIds) {
		
		User bean=null;
		if(id!=null){
			bean=userService.get(id);
			if(StringUtils.isNotBlank(password)){
				bean.setPassword(PasswordProvider.encrypt(password));
			}
		}else{
			bean=new User();
			if(StringUtils.isBlank(password)){
				return Result.createErrorResult().setMessage("登陆密码不能为空");
			}
			bean.setPassword(PasswordProvider.encrypt(password));
		}
		
		bean.setName(name);
		bean.setAccount(account);
		bean.setDisable(disable);
		userService.save(bean,getAjaxIds(applicationIds));
		return Result.createSuccessResult();
	}
	
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam(name = "id", validators = { Validator.NOT_BLANK }) String ids) {
		
		Result r=Result.createSuccessResult();		
		try{
			List<Integer> idList=getAjaxIds(ids);
			if(!CollectionUtils.isEmpty(idList)){
				userService.deleteById(idList);
			}
		}catch (Exception e) {
			r=Result.createErrorResult().setMessage("数据被引用,无法删除");
		}
		
		return r;
	}
	
	@RequestMapping(path="/validateAccount",method = RequestMethod.POST)
	public @ResponseBody Result validateAccount(@ValidateParam(name = "id") Integer id, 
			@ValidateParam(name = "登陆账号",validators=Validator.NOT_BLANK) String account) {

		User user=userService.getByAccount(account);
		if(user!=null){
			if(!user.getId().equals(id)){
				return Result.createErrorResult().setMessage("该账号已经存在");
			}
		}
		return Result.createSuccessResult();
	}
}