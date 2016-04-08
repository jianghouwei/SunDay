package com.org.admin.system.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.org.admin.system.entity.Dict;
import com.org.admin.system.entity.Log;
import com.org.admin.system.service.LogService;
import com.org.framework.web.controller.BaseWebAppController;

/**
 * 日志controller
 */
@Controller
@RequestMapping("system/log")
public class LogController extends BaseWebAppController {

	@Autowired
	private LogService logService;

	/**
	 * 默认页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/logList";
	}

	/**
	 * 获取日志json
	 */
	@RequiresPermissions("sys:log:view")
	@RequestMapping("json")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		// 分页
		PageRequest pageRequest = getPage(request);
		Page<Log> page = logService.findAll(pageRequest);
		// 构造easyui表格数据
		return getEasyUIData(page);
	}

	/**
	 * 删除日志
	 * 
	 * @param id
	 */
	@RequiresPermissions("sys:log:delete")
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		logService.delete(id);
		return "success";
	}

	/**
	 * 批量删除日志
	 * 
	 * @param idList
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestBody List<Integer> idList) {
		logService.delete(idList);
		return "success";
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * response.setContentType("application/msexcel;charset=GBK");
		 * 
		 * List<Log> list = logService.getAll();//获取数据
		 * 
		 * String title = "log"; String[] hearders = new String[] {"操作编码",
		 * "详细描述", "执行时间(mm)", "操作系统", "浏览器", "IP","MAC","操作者","操作时间"};//表头数组
		 * String[] fields = new String[] {"operationCode", "description",
		 * "executeTime", "os", "browser",
		 * "ip","mac","creater","createDate"};//People对象属性数组 TableData td =
		 * ExcelUtils.createTableData(list,
		 * ExcelUtils.createTableHeader(hearders),fields); JsGridReportBase
		 * report = new JsGridReportBase(request, response);
		 * report.exportToExcel(title, "admin", td);
		 */
	}
}
