package com.itdr.controller;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.pojo.Products;
import com.itdr.service.ProductService;
import com.itdr.utils.JsonUtils;
import com.itdr.utils.PathUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/manage/product/*")
public class ProductController extends HttpServlet {
    private ProductService ps = new ProductService();
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
            case "list":
                rs = listDo(request);
                break;
            case "search":
                rs = searchDo(request);
                break;
            case "detail":
                rs = detailDo(request);
                break;
            case "set_sale_status":
                rs = disableuserDo(request);
                break;
            case "save":
                rs = saveDo(request);
                break;
        }

        //返回响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().println(JsonUtils.obj2String(rs));
//        response.getWriter().write(rs.toString());

    }

    //获取所有产品列表的请求
    private ResponseCode listDo(HttpServletRequest request){
        ResponseCode rs = new ResponseCode();

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = ps.selectAll(pageSize,pageNum);
        //调用业务层处理业务
        return rs;
    }

    //查找产品的请求
    private ResponseCode searchDo(HttpServletRequest request){
        ResponseCode rs =null;
        //获取参数
        String productName = request.getParameter("productName");
        String productId = request.getParameter("productId");
        if(productName !=null){
            rs = ps.selectOne(productName);
        }else if(productId !=null){
            rs = ps.selectOne(Integer.parseInt(productId));
        }

        //调用业务层处理业务
        return rs;
    }

    //产品详情的请求
    private ResponseCode detailDo(HttpServletRequest request) {
        //获取参数
        String productId = request.getParameter("productId");
        ResponseCode  rs = ps.selectOne1(Integer.parseInt(productId));

        //调用业务层处理业务
        return rs;
    }

    //产品上下架的操作
    private ResponseCode disableuserDo(HttpServletRequest request){
        //获取参数
        String productId = request.getParameter("productId");
        String status = request.getParameter("status");
        ResponseCode rs = ps.selectOne1(productId,status);
        //调用业务层处理业务
        return rs;
    }

    //新增OR更新产品
    private ResponseCode saveDo(HttpServletRequest request) {
//        categoryId=1&name=海尔洗衣机&subtitle=海尔大促销
// &mainImage=sss.jpg&subImages=test.jpg
// &detail=detailtext&price=1000&stock=100&status=1&id=3
        //获取参数
        String id = request.getParameter("id");
        String categoryId = request.getParameter("categoryId");
        String name = request.getParameter("name");
        String subtitle = request.getParameter("subtitle");
        String mainImage = request.getParameter("mainImage");
        String status = request.getParameter("status");
        String price = request.getParameter("price");
//        String detail = request.getParameter("detail");
//        String subImages = request.getParameter("subImages");
//        String stock = request.getParameter("stock");
        ResponseCode rs = ps.update(id,categoryId,name,subtitle,mainImage,status,price);
        //调用业务层处理业务
        return rs;
    }

}
