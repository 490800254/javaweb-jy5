package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {
    static UserDao ud = new UserDao();

    //查看全部用户
    public ResponseCode<Users> selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "1";
        }
        List<Users> u = ud.selectAll(pageSize, pageNum);

        //如果集合元素为空
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")) {
            rs.setStatus(Const.USER_MISTAKEN_CODE);
            rs.setMag(Const.USER_MISTAKEN_MSG);
            return rs;
        }

        //查找用户是否存在
        Users u = ud.selectOne(username,password);

        //如果用户不存在
        if(u == null){
            rs.setStatus(Const.USER_MISTAKEN_CODE);
            rs.setMag(Const.USER_MISTAKEN_MSG);
            return rs;
        }

        //用户权限
        if(u.getType() != 1){
            rs.setStatus(Const.USER_UNOPERATE_CODE);
            rs.setData(u);
            rs.setMag(Const.USER_UNOPERATE_MSG);
            return rs;
        }

        rs.setStatus(0);
        rs.setData(u);
        return rs;
    }

    //用户禁用
    public ResponseCode selectOne(String uids) {
        ResponseCode rs = new ResponseCode();
        if (uids == null || uids.equals("") ) {
            rs.setStatus(Const.USER_PARAMETER_CODE);
            rs.setMag(Const.USER_PARAMETER_MSG);
            return rs;
        }

        //字符串转数值
        Integer uid =null;
        try {
            uid = Integer.parseInt(uids);
        }catch(Exception e){
            rs.setStatus(105);
            rs.setMag("输入非法参数");
            return rs;
        }


        //查找用户是否存在
        Users u = ud.selectOne(uid);

        //如果用户不存在
        if(u == null){
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMag(Const.USER_NO_MSG);
            return rs;
        }

        //用户禁用情况
        if(u.getStats() == 1){
            rs.setStatus(Const.USER_DISABLE_CODE);
            rs.setMag(Const.USER_DISABLE_MSG);
            return rs;
        }

        //禁用用户
        int row = ud.updateByUid(uid);

        if(row <= 0){
            rs.setStatus(106);
            rs.setMag("用户禁用失败");
        }

        rs.setStatus(0);
        rs.setData(row);
        return rs;
    }

}
