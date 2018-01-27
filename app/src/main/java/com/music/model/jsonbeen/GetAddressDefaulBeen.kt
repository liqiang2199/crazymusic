package com.music.model.jsonbeen

/**
 * Created by Administrator on 2018/1/27.
 * 获取用户 默认的地址
 */
class GetAddressDefaulBeen {
    var code:String ?= null
    var data:AddressDefaulBeen ?= null
    var msg:String ?= null

    class AddressDefaulBeen{
        var addr_detail:String ?= null
        var city:String ?= null
        var city_code:String ?= null
        var district:String ?= null
        var district_code:String ?= null
        var id:String ?= null
        var name:String ?= null
        var phone:String ?= null
        var province:String ?= null
        var province_code:String ?= null
    }
}