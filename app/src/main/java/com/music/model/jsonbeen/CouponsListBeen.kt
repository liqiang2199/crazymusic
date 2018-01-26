package com.music.model.jsonbeen

/**
 * Created by empty cup on 2018/1/26.
 *
 * 卡劵 列表
 */
class CouponsListBeen {

    var code:String ?= null
    var data:ArrayList<CouponsBeen> ?= null
    var msg:String ?= null


    class CouponsBeen{
        var coupon_id:String ?= null
        var expire_date:String ?= null
        var full_range:String ?= null
        var img:String ?= null
        var name:String ?= null
        var payment:String ?= null
        var state:String ?= null
        var type:String ?= null
    }


}