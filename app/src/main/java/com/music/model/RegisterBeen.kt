package com.music.model

/**
 * Created by empty cup on 2018/1/25.
 * 注册
 */
class RegisterBeen {
    var code:String ?= null
    var data:RegisterBeenData ?= null
    var msg:String ?= null

    class RegisterBeenData{
        var head_img:String ?= null
        var id:String ?= null
        var nick_name:String ?= null
        var phone:String ?= null
//        var region:String ?= null//用户地区数据 需要用户设置后才有
        var roleState:String ?= null//用户地区数据 需要用户设置后才有
        var sex:String ?= null//默认男
        var sign:String ?= null//签名
        var token:String ?= null//用户地区数据 需要用户设置后才有
    }

}