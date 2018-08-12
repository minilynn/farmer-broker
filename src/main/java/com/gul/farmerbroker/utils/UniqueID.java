/**
 * 
 */
package com.gul.farmerbroker.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Lynn
 *
 */
public class UniqueID {
	private static String ID;

	/**
	 * 获取当前应用节点的全局唯一的ID值
	 * 
	 * @return
	 */
	public static String getID() {
		if (StringUtils.isBlank(ID)) {
			// 如果ID为空，则通过全局缓存获取当前应用节点的全局唯一的ID值
			ID = "000";
		}
		return ID;
	}
}
