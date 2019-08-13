package com.itdr.dao;

import com.itdr.pojo.Products;
import com.itdr.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    //查找所有用户
    public List<Products> selectAll(String pageSize, String pageNum) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products";
        List<Products> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Products>(Products.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }


    /**
     *
     * 查找返回可能不止一个用户
     */
    //按产品名查找
    public List<Products> selectOne(String productName) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products where pname like ?";
        List<Products> p  = null;
        String productName1="%"+productName+"%";
        try {
            p = qr.query(sql,new BeanListHandler<Products>(Products.class),productName1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Products selectOne1(String productName) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products where pname like ?";
        Products p  = null;
        String productName1="%"+productName+"%";
        try {
            p = qr.query(sql,new BeanHandler<Products>(Products.class),productName1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    //按产品号查找
    public Products selectOne(int productId) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products where pid = ? ";
        Products p  = null;
        try {
            p = qr.query(sql,new BeanHandler<Products>(Products.class),productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    //按产品父Id和名称查找
    public Products selectOne(Integer id,String productName) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from products where parentId = ? and pname = ?";
        Products p  = null;
        try {
            p = qr.query(sql,new BeanHandler<Products>(Products.class),id,productName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    //产品上下架
    public int updateByUid(Integer  pid,Integer status) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "update products set status = ? where pid = ?";
        int row  = 0;
        try {
            row = qr.update(sql,status,pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //更新产品
    public int update(int id,int categoryId, String name, String subtitle, String mainImage, int status,double price) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "update products set pname = ?,categoryId = ?,status = ?,subtitle = ?,mainImage = ?,price = ? where pid = ?";
        int row  = 0;
        try {
            row = qr.update(sql,name,categoryId,status,subtitle,mainImage,price,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //新增产品
    public int insert(int categoryId, String name, String subtitle, String mainImage, int status,double price) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "insert into products (pid,pname,categoryId,status,subtitle,mainImage,price) value (null,?,?,?,?,?,?) ";
        int row  = 0;
        try {
            row = qr.update(sql,name,categoryId,status,subtitle,mainImage,price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //增加节点
    public int addOne(Integer id, String name) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "insert into products (parentId,pname) value (?,?)";
        int row = 0;
        try {
            row = qr.update(sql,id,name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

}
