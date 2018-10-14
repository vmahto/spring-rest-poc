package com.vm.rest.poc.exception;

public class ErrMessage {

	private String msg;
	private int errcode;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	@Override
	public String toString() {
		return "ErrMessage [msg=" + msg + ", errcode=" + errcode + "]";
	}
	
}
