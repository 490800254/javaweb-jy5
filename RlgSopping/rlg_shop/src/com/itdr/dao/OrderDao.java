package com.itdr.dao;

import com.itdr.pojo.Order;
import com.itdr.pojo.Products;
import com.itdr.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    //查找所有订单
    public List<Order> selectAll(String pageSize, String pageNum) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from orders";
        List<Order> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //按产品号查找
    public Order selectOne(long oid) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from orders where oid = ? ";
        Order o  = null;
        try {
            o = qr.query(sql,new BeanHandler<Order>(Order.class),oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    //根据订单号修改发货信息
    public int updateByOid(long oid) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "update orders set status = 1,statusDesc='已发货' where oid = ?";
        int row  = 0;
        try {
            row = qr.update(sql,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
