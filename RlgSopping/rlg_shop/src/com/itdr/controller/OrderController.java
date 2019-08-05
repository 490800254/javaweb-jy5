package com.itdr.controller;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.service.OrderService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/order/*")
public class OrderController extends HttpServlet {
    private OrderService oc = new OrderService();

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
        switch (path) {
            case "list":
                rs = listDo(request);
                break;
            case "search":
                rs = searchDo(request);
                break;
            case "detail":
                rs = detailDo(request);
                break;
            case "send_goods":
                rs = sendDo(request);
                break;
        }
        //返回响应数据
        response.getWriter().write(rs.toString());
    }

    //获取所有订单列表的请求
    private ResponseCode listDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = oc.selectAll(pageSize, pageNum);
        //调用业务层处理业务
        return rs;
    }

    //查找订单的请求
    private ResponseCode searchDo(HttpServletRequest request) {
        //获取参数
        String oid = request.getParameter("orderNum");
        ResponseCode rs = oc.selectOne(Long.parseLong(oid));

        //调用业务层处理业务
        return rs;
    }

    //订单详情的请求
    private ResponseCode detailDo(HttpServletRequest request) {
        //获取参数
        String oid = request.getParameter("orderNum");
        ResponseCode  rs = oc.selectOne1(Long.parseLong(oid));

        //调用业务层处理业务
        return rs;
    }

    //订单发货
    private ResponseCode sendDo(HttpServletRequest request){
        //获取参数
        String oid = request.getParameter("orderNum");
        ResponseCode rs = oc.selectOne2(Long.parseLong(oid));
        //调用业务层处理业务
        return rs;
    }
}
