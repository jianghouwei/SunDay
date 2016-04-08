package com.model.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.api.common.ApiRoute;
import com.model.common.Key;
import com.model.model.entity.User;
import com.model.model.entity.request.UserRO;
import com.model.service.UserService;
import com.org.framework.response.Response;
import com.org.framework.validation.Validity;

/**
 * Controller to allow CRUD on User domain entity.
 *
 * @author: Y Kamesh Rao
 * @created: 3/22/12 8:14 AM
 * @company: &copy; 2012, Kaleidosoft Labs
 */
@Controller
@RequestMapping(ApiRoute.userController)
public class UserController extends BaseApiController {
    private @Autowired UserService userService;
    private Logger log = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = ApiRoute.uRegister, method = RequestMethod.POST)
    public @ResponseBody ModelAndView register(@RequestBody UserRO userRO,
                                               @RequestHeader("pass") String password) {
        Response response = serverResponse();
        try {
            User user = userRO.user(props);
            user.setPassWord(password);
            Validity vsUser = userService.validate(user);
            if (vsUser.isValid()) {
                userService.registerUser(user, request);
                response.setResult(user);
            } else {
                response.setError(key.vdnCode, vsUser.errorMsgs());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setError(key.iseCode, e.getMessage());
        }

        return mav().addObject(Key.response, response);
    }

}
