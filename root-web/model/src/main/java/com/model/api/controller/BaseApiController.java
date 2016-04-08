package com.model.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.common.Key;
import com.model.common.Message;
import com.model.common.Props;
import com.org.framework.aop.ExceptionHandlerAdvice;
import com.org.framework.api.BaseController;
import com.org.framework.common.Const;
import com.org.framework.data.BaseService;
import com.org.framework.data.Entity;
import com.org.framework.response.Response;
import com.org.framework.validation.Validity;

/**
 * Base API controller to provide basic functionality
 * to the extending classes to handle exceptions
 * and render responses
 *
 * @author: Y Kamesh Rao
 * @created: 3/17/12 9:44 PM
 * @company: &copy; 2012, Kaleidosoft Labs
 */
public class BaseApiController extends BaseController {
    protected @Autowired ExceptionHandlerAdvice exceptionHandlerAdvice;
    protected @Autowired HttpServletRequest request;
    private Logger log = LoggerFactory.getLogger(BaseApiController.class);


    protected Response serverResponse() {
        Response response = new Response();
        exceptionHandlerAdvice.setResponse(response);
        return response;
    }


    protected ModelAndView mav() {
        return new ModelAndView();
    }


    public Response validateAndSaveEntity(Entity entity, BaseService service) {
        Response response = serverResponse();
        try {
            Validity vsEntity = service.validate(entity);
            if (vsEntity.isValid()) {
                service.insert(entity);
                response.setResult(entity);
            } else {
                response.setError(404, vsEntity.errors());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setError(500, e.getMessage());
        }

        return response;
    }


    @ExceptionHandler(Exception.class)
    public @ResponseBody ModelAndView exceptionHandler(Exception ex) {
        Response response = new Response();
        response.setError(500, ex.getMessage());
        log.error("Response: " + response.toString());
        return mav().addObject(Const.responseKey, response);
    }
}
