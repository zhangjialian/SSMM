package com.zjl.controller;

import com.zjl.common.Pager;
import com.zjl.common.user.UserBO;
import com.zjl.common.user.UserDO;
import com.zjl.common.user.UserQO;
import com.zjl.common.user.UserVO;
import com.zjl.common.response.SuccessResponse;
import com.zjl.manager.ExcelReadManager;
import com.zjl.manager.ExcelWriteManager;
import com.zjl.manager.UserManager;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjialian on 2017/11/9.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserManager userManager;

    @Resource
    private ExcelReadManager excelReadManager;

    @Resource
    private ExcelWriteManager excelWriteManager;

    /**
     * 默认访问页面
     * @param map
     * @param query
     * @param pager
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap map, UserQO query, Pager pager){
        pager.initiPager(20);

        int totalSize = 500;

        List<UserDO> userList = null;
        if(totalSize != 0){
            pager.setTotalSize(totalSize);
            userList = userManager.getUserList(query, pager);
        }else{
            pager.setTotalSize(0);
            userList = new ArrayList<>();
        }

        map.put("query", query);
        map.put("userList", userList);
        map.put("pageHtml", pager.getPageHtml());
        return "userManager/index";
    }

    /**
     * 获取用户列表
     * @param query
     * @param pager
     * @return
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public SuccessResponse<Object> getUserList(UserQO query, Pager pager){
        List<UserDO> userList = userManager.getUserList(query, pager);

        return getSuccessResponse(userList);
    }

    /**
     * 增加用户
     * @param userBO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    @ResponseBody
    public String addUser(UserBO userBO) throws Exception{

        userBO.setName("name");
        userBO.setEmail("1234@qq.com");
        userBO.setUsername("username");

        userManager.insertUser(userBO);

        return "123123";
    }

    /**
     * 批量添加用户
     * @param file
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchImportUser", method = RequestMethod.POST)
    @ResponseBody
    public SuccessResponse<Object> batchImportUser(@RequestParam(value = "file", required = false) MultipartFile file,
                                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<UserBO> userBOList = excelReadManager.batchReadUser(file.getInputStream());
        return getSuccessResponse("导入成功");
    }

    /**
     * 下载excel模板
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/downloadUserExcel", method = RequestMethod.GET)
    @ResponseBody
    public void downloadUserExcel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileName = "downloadUserExcel";
        fileName = java.net.URLEncoder.encode(fileName + ".xlsx", "utf-8");
        response.reset();
        response.setContentType("application/msexcel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        request.setCharacterEncoding("utf-8");
        SXSSFWorkbook hwb = excelWriteManager.downloadUserExcel(fileName);
        hwb.write(response.getOutputStream());
        response.getOutputStream().close();
    }

    /**
     * 测试用户cookie
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/testCookie", method = RequestMethod.GET)
    @ResponseBody
    public Object testCookie(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        String cookieKey = "JSESSIONID";
        String cookie = getUserCookie(request, cookieKey);
        System.out.println(cookie);
        UserBO userBO = getUserInfo(request);
        System.out.println(userBO);
        return "123123123";
    }

}
