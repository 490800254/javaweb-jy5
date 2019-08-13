package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Products;

import java.util.List;

public class ProductService {
    static ProductDao pd = new ProductDao();

    //查找所有产品
    public ResponseCode<Products> selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }
        List<Products> u = pd.selectAll(pageSize, pageNum);

        //如果集合元素为空
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }

    //按产品名查找
    public ResponseCode<Products> selectOne(String productName) {
        ResponseCode rs = new ResponseCode();
        if (productName == null && productName.equals("")) {
            rs.setStatus(Const.PRODUCT_PARAMETER_CODE);
            rs.setMag(Const.PRODUCT_PARAMETER_MSG);
            return rs;
        }

        //查找商品是否存在
        List<Products> p = pd.selectOne(productName);


        //如果商品不存在
        if (p == null) {
            rs.setStatus(Const.PRODUCT_NO_CODE);
            rs.setMag(Const.PRODUCT_NO_MSG);
            return rs;
        }


        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

    //按产品号查找
    public ResponseCode selectOne(int productId) {
        ResponseCode rs = new ResponseCode();
        if (productId == 0) {
            rs.setStatus(Const.PRODUCT_PARAMETER_CODE);
            rs.setMag(Const.PRODUCT_PARAMETER_MSG);
            return rs;
        }

        //查找商品是否存在
        Products p = pd.selectOne(productId);


        //如果商品不存在
        if (p == null) {
            rs.setStatus(Const.PRODUCT_NO_CODE);
            rs.setMag(Const.PRODUCT_NO_MSG);
            return rs;
        }

        //商品状态
        if (p.getStatus() != 1) {
            rs.setStatus(Const.PRODUCT_DISABLE_CODE);
            rs.setData(p.getPname());
            rs.setMag(Const.PRODUCT_DISABLE_MSG);
            return rs;
        }

        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

    //按父类号和商品名添加
    public ResponseCode selectOne(int categoryId,String productName) {
        ResponseCode rs = new ResponseCode();
        if (productName == null && productName.equals("")) {
            rs.setStatus(Const.PRODUCT_PARAMETER_CODE);
            rs.setMag(Const.PRODUCT_PARAMETER_MSG);
            return rs;
        }

        //查找商品是否存在
        Products p = pd.selectOne(categoryId,productName);


        //如果商品不存在
        if (p == null) {
            rs.setStatus(Const.PRODUCT_NO_CODE);
            rs.setMag(Const.PRODUCT_NO_MSG);
            return rs;
        }


        return rs;
    }

    //按产品号查看详情
    public ResponseCode selectOne1(int productId) {
        ResponseCode rs = new ResponseCode();
        if (productId == 0) {
            rs.setStatus(Const.PRODUCT_PARAMETER_CODE);
            rs.setMag(Const.PRODUCT_PARAMETER_MSG);
            return rs;
        }

        //查找商品是否存在
        Products p = pd.selectOne(productId);


        //如果商品不存在
        if (p == null) {
            rs.setStatus(Const.PRODUCT_NO_CODE);
            rs.setMag(Const.PRODUCT_NO_MSG);
            return rs;
        }

        //商品状态
        if (p.getStatus() != 1) {
            rs.setStatus(Const.PRODUCT_DISABLE_CODE);
            rs.setData(p.getPname());
            rs.setMag(Const.PRODUCT_DISABLE_MSG);
            return rs;
        }

        rs.setStatus(0);
        rs.setData(p);
        return rs;
    }

    //商品上下架
    public ResponseCode selectOne1(String productId, String status) {
        ResponseCode rs = new ResponseCode();
        if (productId == null || productId.equals("") || status == null || status.equals("")) {
            rs.setStatus(Const.PRODUCT_PARAMETER_CODE);
            rs.setMag(Const.PRODUCT_PARAMETER_MSG);
            return rs;
        }

        //字符串转数值
        Integer pid = null;
        Integer status1 = null;
        try {
            pid = Integer.parseInt(productId);
            status1 = Integer.parseInt(status);
        } catch (Exception e) {
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }


        //查找用户是否存在
        Products p = pd.selectOne(pid);

        //如果用户不存在
        if (p == null) {
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMag(Const.USER_NO_MSG);
            return rs;
        }

        //是否在加一个当前状态
//        if(p.getStatus() != 1){
//            System.out.println("产品名："+p.getPname()+" " + "状态：" + Const.PRODUCT_DISABLE_MSG);
//        }else if(p.getStatus() == 1){
//            System.out.println("产品名："+p.getPname()+" " + "状态：售卖中");
//        }

        //修改商品状态
        int row = pd.updateByUid(pid, status1);

        if (row <= 0) {
            rs.setStatus(106);
            rs.setMag("修改产品状态失败");
            return rs;
        }

        //阐述一下详细状态
        rs.setStatus(0);
        rs.setMag("修改产品状态成功");
        return rs;
    }

    //新增OR更新产品
    public ResponseCode update(String id,String categoryId, String name, String subtitle, String mainImage, String status, String price) {
        ResponseCode rs = new ResponseCode();

        /**
         *  更新时id为判断条件不能为空
         *  新增时名字和状态不能为空
         */
        if (name == null || name.equals("") || id == null || id.equals("") || status == null || status.equals("")) {
            rs.setStatus(Const.PRODUCT_PARAMETER_CODE);
            rs.setMag(Const.PRODUCT_PARAMETER_MSG);
            return rs;
        }

        //字符串转数值
        int id1 = 0;
        Integer categoryId1 = null;
        Integer status1 = null;
        double price1 = 0;
        try {
            id1 = Integer.parseInt(id);
            categoryId1 = Integer.parseInt(categoryId);
            status1 = Integer.parseInt(status);
            price1 = Double.parseDouble(price);
        } catch (Exception e) {
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }


        Products p1 = pd.selectOne(id1);

        int row1 = 0;
        int row2 = 0;
        if (p1 != null) {
            row1 = pd.update(id1,categoryId1, name, subtitle, mainImage,status1,price1);
        } else {
            row2 = pd.insert(categoryId1, name, subtitle, mainImage,status1,price1);
        }

        if (row1 > 0) {
            rs.setStatus(0);
            rs.setMag("更新产品成功");
            return rs;
        }
        if (row2 > 0) {
            rs.setStatus(0);
            rs.setMag("新增产品成功");
            return rs;
        }

        rs.setStatus(206);
        rs.setMag("更新产品失败");
        return rs;
    }
}
