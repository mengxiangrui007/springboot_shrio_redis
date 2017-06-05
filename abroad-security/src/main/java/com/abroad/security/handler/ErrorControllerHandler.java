package com.abroad.security.handler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;

 
/**
* @ClassName: ErrorControllerHandler
* @Description: TODO(用于错误请求Handler)
* @author: mengxr
* @date 2017年3月28日 上午10:23:34
*/
@Controller
public class ErrorControllerHandler implements ErrorController {
    @RequestMapping(value = "/error")
    public String error(HttpServletResponse resp, HttpServletRequest req) {
        if (resp.getStatus() == HttpStatus.NOT_FOUND.value()) {
            return "forward:/error400";
        }
        return "forward:/error500";
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(value = "/error400")
    public ResponseJson error404(HttpServletResponse resp, HttpServletRequest req) {
        return new ResponseJson(StatusCode.NOT_FOUND, false);
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(value = "/error500")
    public ResponseJson error505(HttpServletResponse resp, HttpServletRequest req) {
        Object servletError = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer errorCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); //错误编码
        Object errorMessage = req.getAttribute(RequestDispatcher.ERROR_MESSAGE); //获取错误信息
        ResponseJson json = null;
        if (servletError instanceof ServletException) {
            Throwable rootCause = ((ServletException) servletError).getRootCause();
            if (rootCause instanceof UnauthorizedException) {
                json = new ResponseJson(errorCode);
                json.setMsg(StatusCode.NO_PERMISSION.getMsg());
            }
        }else if(null != errorMessage){
        	 json = new ResponseJson();
        	 json.setCode(errorCode);
        	 json.setMsg((String)errorMessage);
        }
        resp.setStatus(Response.SC_OK);
        return json == null ? new ResponseJson(StatusCode.SYSTEM_ERROR, false) : json;
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
