package com.itdr.common;

public class Const {
    //用户相关状态
    public static final Integer USER_PARAMETER_CODE=101;
    public static final String USER_PARAMETER_MSG="参数为空";

    public static final Integer USER_NO_CODE=102;
    public static final String USER_NO_MSG="用户不存在";

    public static final Integer USER_DISABLE_CODE=103;
    public static final String USER_DISABLE_MSG="用户已经禁用";

    public static final Integer USER_MISTAKEN_CODE=104;
    public static final String USER_MISTAKEN_MSG="账户或密码错误";

    public static final Integer USER_UNOPERATE_CODE=105;
    public static final String USER_UNOPERATE_MSG="没有操作权限";

    //商品相关状态
    public static final Integer PRODUCT_PARAMETER_CODE=201;
    public static final String PRODUCT_PARAMETER_MSG="参数为空";

    public static final Integer PRODUCT_NO_CODE=202;
    public static final String PRODUCT_NO_MSG="商品不存在";

    public static final Integer PRODUCT_DISABLE_CODE=203;
    public static final String PRODUCT_DISABLE_MSG="商品已经下架";

    //订单相关状态
    public static final Integer ORDER_PARAMETER_CODE=301;
    public static final String ORDER_PARAMETER_MSG="参数为空";

    public static final Integer ORDER_NO_CODE=302;
    public static final String ORDER_NO_MSG="订单不存在";

    public static final Integer ORDER_DISABLE_CODE=303;
    public static final String ORDER_DISABLE_MSG="订单已过期";

    public static final Integer ORDER_NONPAYMENT_CODE=304;
    public static final String ORDER_NONPAYMENT_MSG="订单未支付";

    public static final Integer ORDER_PAYMENT_CODE=305;
    public static final String ORDER_PAYMENT_MSG="订单已支付";

    public static final Integer ORDER_UNDELIVER_CODE=306;
    public static final String ORDER_UNDELIVER_MSG="订单待发货";

    public static final Integer ORDER_DELIVER_CODE=307;
    public static final String ORDER_DELIVER_MSG="订单已发货";

    public static final Integer ORDER_SIGN_CODE=308;
    public static final String ORDER_SIGN_MSG="订单已签收";

    //商品分类相关状态
    public static final Integer CATEGORY_PARAMETER_CODE=401;
    public static final String CATEGORY_PARAMETER_MSG="参数为空";

    public static final Integer CATEGORY_ERROR_CODE=402;
    public static final String CATEGORY_ERROR_MSG="输入非法参数";

    public static final Integer CATEGORY_NO_CODE=403;
    public static final String CATEGORY_NO_MSG="品类不存在";

    public static final Integer CATEGORY_IN_CODE=404;
    public static final String CATEGORY_IN_MSG="品类存在";

}
