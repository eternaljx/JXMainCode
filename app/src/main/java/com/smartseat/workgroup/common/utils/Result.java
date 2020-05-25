package com.smartseat.workgroup.common.utils;

public class Result {
	private Boolean isSucessed;
	private String error;
	public Boolean getIsSucessed() {
		return isSucessed;
	}
	public void setIsSucessed(Boolean isSucessed) {
		this.isSucessed = isSucessed;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Result(Boolean isSucessed, String error) {
		super();
		this.isSucessed = isSucessed;
		this.error = error;
	}
	
	
}
