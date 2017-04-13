package com.smart.sso.rpc;

import java.util.List;


/**
 * 身份认证系统通信服务接口
 * 
 * @author Joe
 */
public interface AuthenticationRpcService {
	
	/**
	 * 验证是否已经登录
	 * 
	 * @param token
	 *            授权码
	 * @return
	 */
	public boolean validate(String token);

	/**
	 * 根据登录的Token和应用编码获取授权用户信息
	 * 
	 * @param token
	 *            授权码
	 * @param appCode
	 *            应用编码
	 * @return
	 */
	public RpcUser findAuthInfo(String token, String appCode);
	
	/**
	 * 获取当前应用所有权限(含菜单)
	 * 
	 * @param token
	 *            授权码 (如果token不为空，获取当前用户的所有权限)
	 * @param appCode
	 *            应用编码
	 * @return
	 */
	public List<Menu> findPermissionList(String token, String appCode);
	
	/**
	 * 更新当前登录用户密码
	 * 
	 * @param token
	 *            授权码
	 * @param newPassword
	 *            新密码
	 * @return
	 */
	public boolean updatePassword(String token, String newPassword);
	
	/**
	 * 注册新用户
	 * 
	 * @param token
	 *            授权码
	 * @param appCode
	 *            应用编码
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @return
	 */
	public boolean regiestUser(String token, String appCode, String account, String password);

	/**
	 * Description:注册新用户并授权
	 * 
	 * @param token
	 *            授权码
	 * @param appCode
	 *            应用编码
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @param roleCodes
	 * 			   将要给予 授权的角色列表
	 * @return
	 */
	public boolean regiestAndAuthorize(String token, String appCode, String account,
			String password, String[] roleCodes);
	
	/**
	 * Description:注册账号
	 * @author jeason
	 * @param account 账号
	 * @param password 密码
	 * @param roleCodes 角色列表
	 * @return
	 */
	public boolean createUser(String account,String password,String[] roleCodes);
	
}
