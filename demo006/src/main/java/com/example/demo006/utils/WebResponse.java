package com.example.demo006.utils;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * web响应对象
 * @author jeremy
 *
 */
@Data
public class WebResponse {
	private String msg;

	private Object data;
	
	private boolean success = false;

	private String code;

	private Object extData;
	
	private Map<String,Object> ext = new HashMap<String,Object>();

	public WebResponse(){}

	public WebResponse(String msg, Object data, boolean success) {
		this.msg = msg;
		this.data = data;
		this.success = success;
	}
	public Object getExtData() {
		return extData;
	}

	public void setExtData(Object extData) {
		this.extData = extData;
	}

	public String getMsg() {
		return msg;
	}

	public WebResponse setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public WebResponse setData(Object data) {
		this.data = data;
		return this;
	}

	public  void setSuccessData(Object o){
		this.data=o;
		this.msg="成功";
		this.success=true;
	}

	public boolean isSuccess() {
		return success;
	}

	public WebResponse setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public Map<String, Object> getExt() {
		return ext;
	}

	public WebResponse setExt(Map<String, Object> ext) {
		this.ext = ext;
		return this;
	}
	
	public WebResponse addResult(String key, Object val){
		ext.put(key, val);
		return this;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		 this.code = code;
	}

	public static WebResponse ofSuccess(Object o) {
		WebResponse webResponse = new WebResponse();
		webResponse.setSuccess(true);
		webResponse.setMsg("成功");
		webResponse.setData(o);
		return webResponse;
	}

	public static WebResponse ofSuccess() {
		WebResponse webResponse = new WebResponse();
		webResponse.setSuccess(true);
		webResponse.setMsg("成功");
		return webResponse;
	}

	public static WebResponse ofError(String msg) {
		WebResponse webResponse = new WebResponse();
		webResponse.setSuccess(false);
		webResponse.setMsg(msg);
		return webResponse;
	}

	public static WebResponse ofError(String msg, Object o) {
		WebResponse webResponse = new WebResponse();
		webResponse.setSuccess(true);
		webResponse.setMsg(msg);
		webResponse.setData(o);
		return webResponse;
	}
}
