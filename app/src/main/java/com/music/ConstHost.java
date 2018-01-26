package com.music;


/**
 * 网络请求 配置
 * 静态变量
 */
public final class ConstHost {

    //缓存名称
    public static final String REMEMBER_LOGIN = "REMEMBER_LOGIN";
    public static final String USER_INFO = "USER_INFO";

    /**
     * 服务器基地址--测试地址
     */
//    public static final String BASE_HOST = "http://24087e8b.nat123.cc/crazyMusic_web/";
    public static final String BASE_HOST = "http://47.95.215.170:8080/crazyMusic_web/";

    /**
     * 获取加密数据
     */
    public static final String GET_KEY = BASE_HOST + "index/getKey";

    //FIXME:USER-MODULE(会员部分)
    public static final String LOGIN = BASE_HOST + "user/login";

    //FIXME:SHOP-MODULE(商城)
    //商城首页
    public static final String SHOP_INDEX = BASE_HOST + "mall/indexData";
    //商品详情
    public static final String SHOP_GOODS_INFO = BASE_HOST + "mall/productDetail";
    //分类列表
    public static final String SHOP_PRODUCE_LIST = BASE_HOST + "mall/productList";
    //
    public static final String SHOP_CLASSES = BASE_HOST + "mall/productTypeList";
    public static final String SHOP_CLASSES_ALL = BASE_HOST + "mall/productTypeListAll";
    //订单列表
    public static final String SHOP_ORDER_LIST = BASE_HOST + "order/listOrders";
    //删除订单
    public static final String ORDER_DELETE = BASE_HOST + "order/delOrder";
    //确认订单
    public static final String ORDER_CREATE = BASE_HOST + "order/createOrder";
    //购物车列表
    public static final String SHOP_CAR_LIST = BASE_HOST + "mall/cardList";
    //购物车列表删除
    public static final String SHOP_CARD_DEL = BASE_HOST + "mall/cardDelete";
    //加入购物车 传入product_id，product_attrs，number，product_name，product_img，payment
    public static final String SHOP_CAR_ADD = BASE_HOST + "mall/joinCard";

    //FIXME:SHEQU-MODULE(社区)
    //社区列表
    public static final String COMMUNITY_LIST = BASE_HOST + "community/communityList";
    //获取帖子评论列表
    public static final String COMMUNITY_EVALUATE_LIST = BASE_HOST + "community/communityEvaluateList";
    //社区发帖接口
    public static final String COMMUNITY_ADD = BASE_HOST + "community/communityAdd";
    //点赞接口
    public static final String COMMUNITY_PREFECT = BASE_HOST + "community/communityPerfect";
    //取消点赞接口
    public static final String COMMUNITY_CANCEL_PREFECT = BASE_HOST + "community/cancelCommunityPerfect";
    //评论接口
    public static final String COMMUNITY_EVALUATE_ADD = BASE_HOST + "community/evaluateAdd";
    //添加到收藏
    public static final String COLLECTION_ADD = BASE_HOST + "mall/collectAdd";
}
