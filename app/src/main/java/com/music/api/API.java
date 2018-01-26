package com.music.api;

public class API {

    public static String HOST = "http://47.95.215.170:8080/crazyMusic_web";

    /**
     * 用户模块
     */
    public static String USER_LOGIN = HOST + "/user/login";
    public static String USER_REGISTCODE = HOST + "/user/registCode";
    public static String USER_VALIDCODEANDCREATE = HOST + "/user/validCodeAndCreate";
    public static String USER_APPLYTEACHER = HOST + "/user/applyTeacher";
    public static String USER_FINDPWDCODE = HOST + "/user/findPwdCode";
    public static String USER_UPDATEPWDBYCODE = HOST + "/user/updatePwdByCode";
    public static String USER_UPDATESAVEUSERREGION = HOST + "/user/updateSaveUserRegion";
    public static String USER_SETUSERCONFIRMDEFAULTADDR = HOST + "/user/setUserConfirmDefaultAddr";
    public static String USER_DELETEUSERCONFIRMADDR = HOST + "/user/deleteUserConfirmAddr";
    public static String USER_GETUSERDEFAULADDR = HOST + "/user/getUserDefaulAddr";
    public static String USER_UPDATEUSERINFO = HOST + "/user/updateUserInfo";
    public static String USER_GETUSERCONFIRMADDRS = HOST + "/user/getUserConfirmAddrs";
    public static String USER_ADDUPDATEUSERCONFIRMADDR = HOST + "/user/addUpdateUserConfirmAddr";
    public static String USER_UPDATEUSERSKILLED = HOST + "/user/updateUserSkilled";
    public static String USER_SKILLEDLIST = HOST + "/user/skilledList";
    public static String USER_GETUSERSKILLLIST = HOST + "/user/getUserSkillList";
    public static String COUPON_RANDOMGETCOUPONS = HOST + "/coupon/randomGetCoupons";
    //获取优惠券列表
    public static String COUPON_GETUSERCOUPONS = HOST + "/coupon/getUserCoupons";
    public static String COUPON_RECIEVECOUPONS = HOST + "/coupon/recieveCoupons";

    /**
     * 社区模块
     */
    public static String COMMUNITY_COMMUNITYADD = HOST + "/community/communityAdd";
}
