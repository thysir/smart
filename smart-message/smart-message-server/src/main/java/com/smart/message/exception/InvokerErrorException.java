package com.smart.message.exception;

/**
 * 接口调用失败
 * 
 * @author Joe
 */
public class InvokerErrorException extends Exception{

	private static final long serialVersionUID = -583007564501387511L;
	
	public static final String MESSAGE = "接口调用失败";

	public InvokerErrorException() {
		super(MESSAGE);
	}

	public InvokerErrorException(String s) {
		super(s);
	}

	public InvokerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvokerErrorException(Throwable cause) {
		super(cause);
	}
}
