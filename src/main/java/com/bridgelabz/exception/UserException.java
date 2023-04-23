package com.bridgelabz.exception;


public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String message;
	public UserException() {
		super();
	}
	public UserException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "UserException [message=" + message + "]";
	}
    

}
