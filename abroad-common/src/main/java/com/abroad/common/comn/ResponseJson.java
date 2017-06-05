package com.abroad.common.comn;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * 返回的json 对象
 * 
 * @author qupeng
 *
 */
public class ResponseJson {
	private int code = StatusCode.SUCCESS.getCode();
	private String msg = StatusCode.SUCCESS.getMsg();
	private Object data = new Object();

	private transient static ResponseJson successResponse = new ResponseJson(StatusCode.SUCCESS, true);
	private transient static ResponseJson failResponse = new ResponseJson(StatusCode.SYSTEM_ERROR, false);

	public ResponseJson() {
	}

	public ResponseJson(int code) {
		super();
		this.code = code;
	}

	public ResponseJson(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseJson(StatusCode statusCode, boolean success) {
		code = statusCode.getCode();
		msg = statusCode.getMsg();
	}
	public ResponseJson(StatusCode statusCode) {
		code = statusCode.getCode();
		msg = statusCode.getMsg();
	}
	
	public ResponseJson(Object data) {
		this.data = data;
	}

	public ResponseJson(List<ObjectError> errorList) {
		List<ValidationError> list = new ArrayList<ValidationError>(errorList.size());
		errorList.forEach(error -> {
			FieldError fieldError = (FieldError) error;
			list.add(new ValidationError(fieldError.getField(), error.getDefaultMessage()));
		});
		this.data = list;
		//this.code = StatusCode.PARAM_ERROR.getCode();
		//this.msg = StatusCode.PARAM_ERROR.getMsg();
	}

	public static ResponseJson getValidationErrorResponse(List<ValidationError> errorList) {
		ResponseJson responseJson = new ResponseJson();
		responseJson.setData(errorList);
		//responseJson.setCode(StatusCode.PARAM_ERROR.getCode());
		//responseJson.setMsg(StatusCode.PARAM_ERROR.getMsg());
		return responseJson;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "ResponseJson [code=" + code + ", msg=" + msg + ", data=" + JSON.toJSONString(data) + "]";
	}

	public static ResponseJson getSuccessResponse() {
		return ResponseJson.successResponse;
	}

	public static ResponseJson getFailedResponse() {
		return ResponseJson.failResponse;
	}

	public static ResponseJson getFailedResponse(String msg) {
		ResponseJson response = ResponseJson.failResponse;
		response.setMsg(msg);
		return response;
	}

	public static class ValidationError {
		private String param;
		private String msg;

		public ValidationError(String param, String msg) {
			this.param = param;
			this.msg = msg;
		}

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

}
