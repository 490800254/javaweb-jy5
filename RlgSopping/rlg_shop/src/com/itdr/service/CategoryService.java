package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.CategoryDao;
import com.itdr.dao.ProductDao;
import com.itdr.pojo.Categorys;
import com.itdr.pojo.Products;

import java.util.List;

public class CategoryService {
    static CategoryDao cd = new CategoryDao();
    static ProductDao pd = new ProductDao();

    //获取品类子节点(平级)
    public ResponseCode<Categorys> selectAll() {

        List<Categorys> c = cd.selectAll();

        //如果集合元素为空
        ResponseCode rs = new ResponseCode();
        rs = ResponseCode.successRS(0,c);
        return rs;
    }

    //获取品类子节点(平级)
    public ResponseCode<Products> selectAll(String cid) {

        List<Products> c = cd.selectAll(cid);

        //如果集合元素为空
        ResponseCode rs = new ResponseCode();
        rs = ResponseCode.successRS(0,c);
        return rs;
    }

    //增加节点
    public ResponseCode addOne(String parentId, String name) {
        ResponseCode rs = new ResponseCode();
        if (name == null || name.equals("") || parentId == null || parentId.equals("")) {
            rs = ResponseCode.defeatedRS(Const.CATEGORY_PARAMETER_CODE,Const.CATEGORY_PARAMETER_MSG);
            return rs;
        }

        //字符串转数值
        Integer id = null;
        try {
            id = Integer.parseInt(parentId);
        } catch (Exception e) {
            rs = ResponseCode.defeatedRS(Const.CATEGORY_ERROR_CODE,Const.CATEGORY_ERROR_MSG);
            return rs;
        }

        int row = 0;

        //查找品类是否存在
        Categorys c = null;
        Products p = null;
        if(id==0){
            c = cd.selectOne(id, name);
            //如果品类存在
            if (c != null) {
                rs = ResponseCode.defeatedRS(Const.CATEGORY_IN_CODE,Const.CATEGORY_IN_MSG);
                return rs;
            }
            //增加节点
            row = cd.addOne(id, name);
        }else {
            p = pd.selectOne1(name);
            //如果品类存在
            if (p != null) {
                rs = ResponseCode.defeatedRS(Const.CATEGORY_IN_CODE,Const.CATEGORY_IN_MSG);
                return rs;
            }
            //增加节点
            row = pd.addOne(id, name);

        }

        if (row <= 0) {
            rs = ResponseCode.defeatedRS(106,"添加品类失败");
        }

        rs = ResponseCode.defeatedRS(0,"添加品类成功");
        return rs;
    }

    //修改品类名字
    public ResponseCode setOne(String parentId, String name) {
        ResponseCode rs = new ResponseCode();
        if (name == null || name.equals("")) {
            rs = ResponseCode.defeatedRS(Const.CATEGORY_PARAMETER_CODE,Const.CATEGORY_PARAMETER_MSG);
            return rs;
        }

        //字符串转数值
        Integer id = null;
        if (parentId == null || parentId.equals("")) {
            id = 0;
        } else {
            try {
                id = Integer.parseInt(parentId);
            } catch (Exception e) {
                rs = ResponseCode.defeatedRS(Const.CATEGORY_ERROR_CODE,Const.CATEGORY_ERROR_MSG);
                return rs;
            }
        }

        //查找品类是否存在
        Categorys c = cd.selectOne(id, name);

        //如果品类存在
        if (c != null) {
            rs = ResponseCode.defeatedRS(Const.CATEGORY_IN_CODE,Const.CATEGORY_IN_MSG);
            return rs;
        }

        //修改品类名字
        int row = cd.setOne(id, name);

        if (row <= 0) {
            rs = ResponseCode.defeatedRS(106,"更新品类名字失败");
        }

        rs = ResponseCode.defeatedRS(0,"更新品类名字成功");
        return rs;
    }
}
