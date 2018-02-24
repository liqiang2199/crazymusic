package com.music.model.jsonbeen

/**
 * Created by empty cup on 2018/2/24.
 * 短信发送 验证
 */
class SmsSendCodeBeen {

    var code:String ?= null
    var data:SmsSendCodeData ?= null
    var msg:String ?= null

    class SmsSendCodeData{
        var code:String ?= null
    }

}