package com.music.model.jsonbeen

/**
 * Created by Administrator on 2018/1/27.
 * 银行卡列表
 */
class BankListBeen{
    var code:String ?= null
    var data: ArrayList<BankList>?= null
    var msg:String ?= null

    class BankList{
        var account_name:String ?= null
        var account_num:String ?= null
        var user_name:String ?= null
        var id:String ?= null
    }
}