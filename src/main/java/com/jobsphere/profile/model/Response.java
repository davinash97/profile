package com.jobsphere.profile.model;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class Response<T> {
	
	public Integer code;

	public String status;

	public T result;

	public Response(String status, Integer code, T result) {
		this.status = status;
		this.code = code;
		this.result = result;
	}
}