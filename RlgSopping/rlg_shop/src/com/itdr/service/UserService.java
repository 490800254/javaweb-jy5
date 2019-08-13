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
        rs = ResponseCode.successRS(0,u);
        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = new ResponseCode();
        if (username == null || username.equals("") || password == null || password.equals("")) {
            rs = ResponseCode.defeatedRS(Const.USER_MISTAKEN_CODE,Const.USER_MISTAKEN_MSG);
            return rs;
        }

        //查找用户是否存在
        Users u = ud.selectOne(username, password);

        //如果用户不存在
        if (u == null) {
            rs = ResponseCode.defeatedRS(Const.USER_MISTAKEN_CODE,Const.USER_MISTAKEN_MSG);
            return rs;
        }

        //用户权限
        if (u.getType() != 1) {
            rs = ResponseCode.SorDRS(Const.USER_UNOPERATE_CODE,u,Const.USER_UNOPERATE_MSG);
            return rs;
        }

        rs = ResponseCode.successRS(0,u);
        return rs;
    }

    //用户禁用与启用
    public ResponseCode selectOne(String uids, int status) {
        ResponseCode rs = new ResponseCode();
        if (uids == null || uids.equals("")) {
            rs = ResponseCode.defeatedRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rs;
        }

        //字符串转数值
        Integer uid = null;
        try {
            uid = Integer.parseInt(uids);
        } catch (Exception e) {
            rs = ResponseCode.defeatedRS(105,"输入非法参数");
            return rs;
        }


        //查找用户是否存在
        Users u = ud.selectOne(uid);

        //如果用户不存在
        if (u == null) {
            rs = ResponseCode.defeatedRS(Const.USER_NO_CODE,Const.USER_NO_MSG);
            return rs;
        }
        int row = 0;
        //判断禁用状态
        if (status == 0) {
            //用户禁用情况
            if (u.getStatus() == 0) {
                rs = ResponseCode.defeatedRS(Const.USER_DISABLE_CODE,Const.USER_DISABLE_MSG);
                return rs;
            }

            //禁用用户
            row = ud.updateByUid(uid);
        }
        if (status == 1){
            //用户启用情况
            if (u.getStatus() == 1) {
                rs = ResponseCode.defeatedRS(Const.USER_SYART_CODE,Const.USER_START_MSG);
                return rs;
            }

            //启用用户
            row = ud.updateByUid1(uid);
        }


        if (row <= 0) {
            rs = ResponseCode.defeatedRS(106,"用户状态修改失败");
        }

        rs = ResponseCode.defeatedRS(0,"用户状态修改成功");
        return rs;
    }

}
