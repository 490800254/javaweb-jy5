package com.itdr.dao;

import com.itdr.pojo.Categorys;
import com.itdr.pojo.Products;
import com.itdr.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    //获取品类子节点(平级)
    public List<Categorys> selectAll() {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from categorys";
        List<Categorys> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Categorys>(Categorys.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //获取品类子节点(平级)
    public List<Products> selectAll(String cid) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products where categoryId = ?";
        List<Products> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Products>(Products.class),cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //获取品类子节点
    public Categorys selectOne(Integer id, String name) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql=null;
        if(id==0){
            sql = "select * from categorys where id=? or name=?";
        }else {
            sql = "select * from products where id=? or name=?";
        }

        Categorys c = null;
        try {
            c = qr.query(sql,new BeanHandler<Categorys>(Categorys.class),id,name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    //增加节点
    public int addOne(Integer id, String name) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = null;
        if(id==0){
            sql = "insert into categorys (id,parentId,name) value (?,0,?)";
        }else {
            sql = "insert into products (categoryId,pname) value (?,?)";
        }

        int row = 0;
        try {
            row = qr.update(sql,id,name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改品类名字
    public int setOne(Integer id, String name) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "update categorys set name = ? where id = ?";

        int row = 0;
        try {
            row = qr.update(sql,name,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
