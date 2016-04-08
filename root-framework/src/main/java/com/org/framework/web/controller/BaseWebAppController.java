package com.org.framework.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.org.framework.aop.ExceptionHandlerAdvice;
import com.org.framework.api.BaseController;

public class BaseWebAppController extends BaseController {
	private Logger log = LoggerFactory.getLogger(BaseWebAppController.class);
	@Autowired
	protected ExceptionHandlerAdvice exceptionHandlerAdvice;
	@Autowired
	protected HttpServletRequest httpReq;

	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception ex, Locale locale) {
		ModelAndView mav = new ModelAndView(View.error);
		StringBuilder stack = new StringBuilder();

		log.error("[baseWebAppExceptionHandler] Response: " + ex.getMessage());
		mav.addObject("exception", ex.getMessage());
		for (StackTraceElement element : ex.getStackTrace()) {
			stack.append(element.toString());
			stack.append("\n");
		}

		mav.addObject("stack", stack.toString());
		return mav;
	}

	// Helper methods
	public void setup(Model model) {
		model.addAttribute("error", false);
		model.addAttribute("success", false);
		model.addAttribute("alert", false);
		model.addAttribute("info", false);
		model.addAttribute("loggedIn", false);
	}

	public void addError(String message, Model model) {
		model.addAttribute("error", true);
		model.addAttribute("errorMessage", message);
	}

	public void addSuccess(String message, Model model) {
		model.addAttribute("success", true);
		model.addAttribute("successMessage", message);
	}

	public void addAlert(String message, Model model) {
		model.addAttribute("alert", true);
		model.addAttribute("alertMessage", message);
	}

	public void addInfo(String message, Model model) {
		model.addAttribute("info", true);
		model.addAttribute("infoMessage", message);
	}

	public void addInfoWithAction(String infoWithActionHeading, String infoWithActionContent,
			String infoWithActionPrimaryAction, String infoWithActionPrimaryActionLink, String infoWithActionSecAction,
			String infoWithActionSecActionLink, Model model) {
		model.addAttribute("infoWithAction", true);
		model.addAttribute("infoWithActionHeading", infoWithActionHeading);
		model.addAttribute("infoWithActionContent", infoWithActionContent);
		model.addAttribute("infoWithActionPrimaryAction", infoWithActionPrimaryAction);
		model.addAttribute("infoWithActionPrimaryActionLink", infoWithActionPrimaryActionLink);

		if (infoWithActionSecAction != null)
			model.addAttribute("infoWithActionSecAction", infoWithActionSecAction);

		if (infoWithActionSecActionLink != null)
			model.addAttribute("infoWithActionSecActionLink", infoWithActionSecActionLink);
	}

	/**
	 * 获取page对象
	 * 
	 * @param request
	 * @return page对象
	 */
	public PageRequest getPage(HttpServletRequest request) {
		int pageNo = 1; // 当前页码
		int pageSize = 20; // 每页行数
		String orderBy = "id"; // 排序字段
		Direction order = Sort.Direction.ASC; // 排序顺序
		if (!StringUtils.isEmpty(request.getParameter("page"))) {
			pageNo = Integer.valueOf(request.getParameter("page"));
		}
		if (!StringUtils.isEmpty(request.getParameter("rows"))) {
			pageSize = Integer.valueOf(request.getParameter("rows"));
		}
		if (!StringUtils.isEmpty(request.getParameter("sort"))) {
			orderBy = request.getParameter("sort").toString();
		}
		if (!StringUtils.isEmpty(request.getParameter("order"))) {
			String sort = request.getParameter("order").toString();
			if(Sort.Direction.DESC.equals(sort)){
				order = Sort.Direction.DESC;
			}
		}
		PageRequest page = new PageRequest(pageNo, pageSize, order, orderBy);
		return page;

	}

	/**
	 * 获取easyui分页数据
	 * 
	 * @param page
	 * @return map对象
	 */
	public <T> Map<String, Object> getEasyUIData(Page<T> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", page.getContent());
		map.put("total", page.getTotalPages());
		return map;
	}

}
