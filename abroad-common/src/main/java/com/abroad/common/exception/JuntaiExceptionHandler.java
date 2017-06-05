package com.abroad.common.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 拦截自定义异常信息，抛出异常信息至客户端
 *
 * @author qupeng
 */
@ControllerAdvice	//应用到所有@RequestMapping注解方法,意思是控制器增强
public class JuntaiExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Throwable.class)	//全局异常统一处理，在其抛出所有Exception异常时执行，Throwable是异常的祖先
    @ResponseBody
    public ResponseJson handleException(HttpServletRequest request, Throwable ex) {
        ResponseJson json = ResponseJson.getFailedResponse();
        if(ex instanceof HttpRequestMethodNotSupportedException){
        	HttpRequestMethodNotSupportedException httpEx = (HttpRequestMethodNotSupportedException) ex;
        	json = new ResponseJson(StatusCode.REQUEST_HTTP.getCode());
        	json.setMsg(String.format(StatusCode.REQUEST_HTTP.getMsg(), httpEx.getMessage()));
        } else if (ex instanceof TypeMismatchException) {
            TypeMismatchException typeEx = (TypeMismatchException) ex;
            json = new ResponseJson(StatusCode.TYPE_MISMATH.getCode());
            json.setMsg(String.format(StatusCode.TYPE_MISMATH.getMsg(), typeEx.getValue()));
        } else if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException missingEx = (MissingServletRequestParameterException) ex;
            json = new ResponseJson(StatusCode.MISSING_REQUEST_PARAMETER.getCode());
            json.setMsg(String.format(StatusCode.MISSING_REQUEST_PARAMETER.getMsg(), missingEx.getParameterName()));
        } else if (ex instanceof HttpMessageNotReadableException) {
            return new ResponseJson(StatusCode.HTTP_MESSAGE_NOT_READABLE);
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
            json = produceJson(manve.getBindingResult().getFieldErrors());
        } else if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            json = produceJson(be.getBindingResult().getFieldErrors());
        } else if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) ex;
            json = produceJson(cve.getConstraintViolations().toArray(new ConstraintViolation[cve.getConstraintViolations().size()]));
        } else if (ex instanceof AbroadException) {
            // 业务异常
        	AbroadException JuntaiException = (AbroadException) ex;
            String errorCode = JuntaiException.getErrorCode();
            Object[] args = JuntaiException.getArgs();
            if (errorCode != null) {
                json = produceJson(errorCode, args, request);
            }
        }

        try {
            logger.error("error log, request:{}, response:{}", LogInterceptor.getRequest(request), json.toString());
        } catch (IOException e) {
            logger.error("Get Request Error", e);
        }
        logger.error("error stackTrace log", ex);

        return json;

    }

    /**
     * 根据验证结果，构建异常返回
     * 默认错误编码：
     * 默认错误提示信息：
     * @param constraintViolations
     * @return
     */
    private ResponseJson produceJson(ConstraintViolation[] constraintViolations) {

        ResponseJson responseJson = new ResponseJson(StatusCode.VALIDATION_FAILED);
    
        Map<String, Object> data = new HashMap<>();
        Map<String, String> fields;
        List<Map<String, String>> errorList = Lists.newArrayList();
        PathImpl path;
        for (ConstraintViolation v : constraintViolations) {
            path = (PathImpl) v.getPropertyPath();
            fields = Maps.newHashMap();
            fields.put("field", path.getLeafNode().getName());
            fields.put("msg", v.getMessage());
            errorList.add(fields);
        }
        if(errorList.size() != 0){
            responseJson.setMsg(errorList.get(0).get("msg"));
        }
        data.put("errors", errorList);
        responseJson.setData(data);
        return responseJson;
    }

    private ResponseJson produceJson(List<FieldError> fieldError) {
        ResponseJson responseJson = new ResponseJson(StatusCode.VALIDATION_FAILED);
        Map<String, Object> data = new HashMap<>();
        Map<String, String> fields;
        List<Map<String, String>> errorList = Lists.newArrayList();
        for (FieldError e : fieldError) {
            fields = Maps.newHashMap();
            fields.put("field", e.getField());
            fields.put("msg", e.getDefaultMessage());
            errorList.add(fields);
        }
        if(errorList.size() != 0){
            responseJson.setMsg(errorList.get(0).get("msg"));
        }
        data.put("errors", errorList);
        responseJson.setData(data);
        return responseJson;
    }
    

     
    /**
    * @Title: produceJson
    * @Description: TODO(获取message.properties中的信息)
    * @param @param errorCode
    * @param @param args
    * @param @param request
    * @param @return    设定文件
    * @return ResponseJson    返回类型
    * @author: shicj
    * @date 2017年4月18日 上午10:10:23
    * @throws
    */
    private ResponseJson produceJson(String errorCode, Object[] args, HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        //获取message.properties的key,如有异常，是不能获取此文件
        String msg = requestContext.getMessage(errorCode, args);
        String[] msgToArray = msg.split("#");
        ResponseJson responseJson = new ResponseJson();
        if (msgToArray.length == 2) {
            responseJson.setCode(Integer.valueOf(msgToArray[0]));
            responseJson.setMsg(msgToArray[1]);
        } else {
            responseJson.setCode(StatusCode.SYSTEM_ERROR.getCode());
            responseJson.setMsg(StatusCode.SYSTEM_ERROR.getMsg());
        }
        
        
        
        return responseJson;
    }

}
