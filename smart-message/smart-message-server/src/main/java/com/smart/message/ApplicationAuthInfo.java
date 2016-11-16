package com.smart.message;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.smart.message.model.ApplicationAuth;

/**
 * <b>Description:应用授权信息</b><br>
 * 
 * @author jeason
 */
public class ApplicationAuthInfo implements Serializable {

	private static final long serialVersionUID = -3186072251714479074L;

	private List<ApplicationAuth> applicationAuthList = null;
	
	public ApplicationAuthInfo(List<ApplicationAuth> applicationAuthList) {
		this.applicationAuthList = applicationAuthList;
	}

	/**
	 * Description:通过授权key获取授权value
	 * 
	 * @author jeason
	 * @param key
	 *            授权key
	 * @return
	 */
	public String getValue(String key) {

		String value = null;
		if (!CollectionUtils.isEmpty(applicationAuthList)) {
			for (ApplicationAuth item : applicationAuthList) {
				if (item.getAuthKey().equals(key)) {
					value = item.getAuthValue();
				}
			}
		}

		return value;
	}

	/**
	 * Description:通过授权key获取授权value
	 * 
	 * @author jeason
	 * @param key
	 *            授权key
	 * @return
	 */
	public Integer getIntegerValue(String key) {

		Integer value = null;
		if (!CollectionUtils.isEmpty(applicationAuthList)) {
			for (ApplicationAuth item : applicationAuthList) {
				if (item.getAuthKey().equals(key)) {
					value = Integer.parseInt(item.getAuthValue());
				}
			}
		}
		return value;
	}
}
