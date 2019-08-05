package com.itdr.controller;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.PathUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/manage/user/*")
public class UsersController extends HttpServlet {
    private UserService uc = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        //创建统一返回对象
        ResponseCode rs = null;

        //判断请求类型
        switch (path){
            case "list":
                rs = listDo(request);
                break;
            case "login":
                rs = loginDo(request);
                break;
            case "disableuser":
                rs = disableuserDo(request);
                break;
        }

        //返回响应数据
        response.getWriter().write(rs.toString());
    }

    //获取所有用户列表的请求
    private ResponseCode listDo(HttpServletRequest request){
        ResponseCode rs = new ResponseCode();

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = uc.selectAll(pageSize,pageNum);
        //调用业务层处理业务
        return rs;
    }

    //用户登录的请求
    private ResponseCode loginDo(HttpServletRequest request){
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ResponseCode rs = uc.selectOne(username, password);
        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("user",rs.getData());
        //调用业务层处理业务
        return rs;
    }

    //用户禁用的操作
    private ResponseCode disableuserDo(HttpServletRequest request){
        //获取参数
        String uid = request.getParameter("uid");
        ResponseCode rs = uc.selectOne(uid);
        //调用业务层处理业务
        return rs;
    }

}
