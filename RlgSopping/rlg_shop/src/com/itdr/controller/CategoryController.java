package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.service.CategoryService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/category/*")
public class CategoryController extends HttpServlet {
    private CategoryService cs = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        //创建统一返回对象
        ResponseCode rs = null;

        //判断请求类型
        switch (path){
            case "get_category":
                rs = getDo(request);
                break;
            case "add_category":
                rs = addDo(request);
                break;
            case "set_category_name":
                rs = setDo(request);
                break;
        }

        //返回响应数据
        response.getWriter().write(rs.toString());
    }

    //获取品类子节点(平级)
    private ResponseCode getDo(HttpServletRequest request){
        ResponseCode rs = new ResponseCode();

        //获取参数
        String cid = request.getParameter("categoryId");

        rs = cs.selectAll(cid);
//        rs = cs.selectAll();
        //调用业务层处理业务
        return rs;
    }

    //增加节点
    private ResponseCode addDo(HttpServletRequest request){
        ResponseCode rs =null;
        //获取参数
        String parentId = request.getParameter("parentId");
        String Name = request.getParameter("categoryName");
        rs = cs.addOne(parentId,Name);

        //调用业务层处理业务
        return rs;
    }

    //修改品类名字
    private ResponseCode setDo(HttpServletRequest request){
        //获取参数
        String cid = request.getParameter("categoryId");
        String name = request.getParameter("categoryName");
        ResponseCode rs = cs.setOne(cid,name);
        //调用业务层处理业务
        return rs;
    }
}
