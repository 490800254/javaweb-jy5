package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.OrderDao;
import com.itdr.pojo.Order;
import com.itdr.pojo.Products;

import java.util.List;

public class OrderService {
    static OrderDao od = new OrderDao();

    //查看所有订单
    public ResponseCode<Order> selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }
        List<Order> o = od.selectAll(pageSize, pageNum);

        //如果集合元素为空
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(o);
        return rs;
    }

    //按订单号查找
    public ResponseCode selectOne(long oid) {
        ResponseCode rs = new ResponseCode();
        if (oid == 0) {
            rs.setStatus(Const.ORDER_PARAMETER_CODE);
            rs.setMag(Const.ORDER_PARAMETER_MSG);
            return rs;
        }

        //查找订单是否存在
        Order o = od.selectOne(oid);


        //如果订单不存在
        if (o == null) {
            rs.setStatus(Const.ORDER_NO_CODE);
            rs.setMag(Const.ORDER_NO_MSG);
            return rs;
        }

        //订单状态
        if (o.getPayType() == 0) {
            rs.setStatus(Const.ORDER_NONPAYMENT_CODE);
            rs.setData(o.getPname());
            rs.setMag(Const.ORDER_NONPAYMENT_MSG);
        } else if (o.getPayType() == 1) {
            if (o.getStatus() == 0) {
                rs.setStatus(Const.ORDER_UNDELIVER_CODE);
                rs.setData(o.getPname());
                rs.setMag(Const.ORDER_UNDELIVER_MSG);
            } else if (o.getStatus() == 1) {
                rs.setStatus(Const.ORDER_DELIVER_CODE);
                rs.setData(o.getPname());
                rs.setMag(Const.ORDER_DELIVER_MSG);
            } else if (o.getStatus() == 2) {
                rs.setStatus(Const.ORDER_SIGN_CODE);
                rs.setData(o.getPname());
                rs.setMag(Const.ORDER_SIGN_MSG);
            }
        }
        return rs;

    }

    //按订单号查找
    public ResponseCode selectOne1(long oid) {
        ResponseCode rs = new ResponseCode();
        if (oid == 0) {
            rs.setStatus(Const.ORDER_PARAMETER_CODE);
            rs.setMag(Const.ORDER_PARAMETER_MSG);
            return rs;
        }

        //查找订单是否存在
        Order o = od.selectOne(oid);


        //如果订单不存在
        if (o == null) {
            rs.setStatus(Const.ORDER_NO_CODE);
            rs.setMag(Const.ORDER_NO_MSG);
            return rs;
        }

        //订单状态
        if (o.getPayType() == 0) {
            rs.setStatus(Const.ORDER_NONPAYMENT_CODE);
            rs.setData(o);
            rs.setMag(Const.ORDER_NONPAYMENT_MSG);
        } else if (o.getPayType() == 1) {
            if (o.getStatus() == 0) {
                rs.setStatus(Const.ORDER_UNDELIVER_CODE);
                rs.setData(o);
                rs.setMag(Const.ORDER_UNDELIVER_MSG);
            } else if (o.getStatus() == 1) {
                rs.setStatus(Const.ORDER_DELIVER_CODE);
                rs.setData(o);
                rs.setMag(Const.ORDER_DELIVER_MSG);
            } else if (o.getStatus() == 2) {
                rs.setStatus(Const.ORDER_SIGN_CODE);
                rs.setData(o);
                rs.setMag(Const.ORDER_SIGN_MSG);
            }
        }
        return rs;

    }

    //按订单号查找
    public ResponseCode selectOne2(long oid) {
        ResponseCode rs = new ResponseCode();
        if (oid == 0) {
            rs.setStatus(Const.ORDER_PARAMETER_CODE);
            rs.setMag(Const.ORDER_PARAMETER_MSG);
            return rs;
        }

        //查找订单是否存在
        Order o = od.selectOne(oid);


        //如果订单不存在
        if (o == null) {
            rs.setStatus(Const.ORDER_NO_CODE);
            rs.setMag(Const.ORDER_NO_MSG);
            return rs;
        }

        //订单状态
        if (o.getStatus() == 1) {
            rs.setStatus(Const.ORDER_DELIVER_CODE);
            rs.setData(o);
            rs.setMag(Const.ORDER_DELIVER_MSG);
        } else if (o.getStatus() == 2) {
            rs.setStatus(Const.ORDER_SIGN_CODE);
            rs.setData(o);
            rs.setMag(Const.ORDER_SIGN_MSG);
        }

        //订单发货
        int row = od.updateByOid(oid);

        if(row <= 0){
            rs.setStatus(106);
            rs.setMag("发货失败");
        }

        rs.setStatus(0);
        rs.setMag("发货成功");
        return rs;

    }

}
