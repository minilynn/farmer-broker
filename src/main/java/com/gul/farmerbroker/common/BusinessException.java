package com.gul.farmerbroker.common;

/**
 * 业务执行过程中的异常，根据指定的异常信息构造异常对象
 * 
 * @author Lynn
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	public BusinessException(String expInfo) {
		super(expInfo);
	}

	public BusinessException(String expInfo, Throwable cause) {
		super(expInfo, cause);
	}
}
