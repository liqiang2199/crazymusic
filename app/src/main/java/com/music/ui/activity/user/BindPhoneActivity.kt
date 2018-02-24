package com.music.ui.activity.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.framework.utils.XToastUtil
import com.lzy.okgo.OkGo
import com.lzy.okgo.convert.StringConvert
import com.lzy.okgo.model.Response
import com.lzy.okrx2.adapter.ObservableResponse
import com.music.R
import com.music.api.API
import com.music.model.jsonbeen.SmsSendCodeBeen
import com.music.ui.activity.BaseActivity
import com.music.utils.CountDownHelper
import com.music.utils.UIHelper
import com.music.utils.UtilsTools
import com.wega.library.loadingDialog.LoadingDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.util.*

@SuppressLint("Registered")
/**
 * Created by Administrator on 2018/1/28.
 * 绑定手机号
 */
class BindPhoneActivity :BaseActivity(),View.OnClickListener {

    private var bind_phone:EditText ?= null
    private var bind_sms_code:EditText ?= null
    private var tv_send_sms:TextView ?= null
    private var submit:Button ?= null

    private var phoneNumber:String ?= null
    private var smsCode:String ?= null
    private var code:String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_set_account_bind_phone)
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){

        bind_phone = findViewById(R.id.bind_phone)
        bind_sms_code = findViewById(R.id.bind_sms_code)
        tv_send_sms = findViewById(R.id.tv_send_sms)
        submit = findViewById(R.id.submit)

        tv_send_sms?.setOnClickListener(this)
        submit?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.tv_send_sms){
            //发送短信
            phoneNumber = bind_phone!!.text.toString()
            if (TextUtils.isEmpty(phoneNumber)) {
                UIHelper.showToast(mContext, getString(R.string.tip_phone_number_can_not_be_empty))
                return
            }
            if (!UtilsTools.isPhoneNum(phoneNumber)){
                UIHelper.showToast(mContext, getString(R.string.tip_phone_is_sure))
                return
            }

            http_GetSmsCode()
        }
        if (v.id == R.id.submit){
            //提交绑定的手机号
            phoneNumber = bind_phone!!.text.toString()
            if (TextUtils.isEmpty(phoneNumber)) {
                UIHelper.showToast(mContext, getString(R.string.tip_phone_number_can_not_be_empty))
                return
            }
            if (!UtilsTools.isPhoneNum(phoneNumber)){
                UIHelper.showToast(mContext, getString(R.string.tip_phone_is_sure))
                return
            }
            smsCode = bind_sms_code!!.text.toString()
            if (TextUtils.isEmpty(smsCode)) {
                UIHelper.showToast(mContext, getString(R.string.tip_verification_code_can_not_be_empty))
                return
            }
            if (code != smsCode){
                UIHelper.showToast(mContext, getString(R.string.tip_sms_send_code))
                return
            }
            if (!UtilsTools.isSixLength(smsCode)) {
                UIHelper.showToast(mContext, getString(R.string.tip_verification_six))
                return
            }

            http_SubmitPhone()
        }
    }

    /**
     * 绑定手机号码 发送短信
     */
    private fun http_GetSmsCode(){
        mLoadingDialog = LoadingDialog(this)
        val params = TreeMap<String, String>()
        params.put("phone",phoneNumber!!)
        val jsonObject = JSONObject(params)

        OkGo.post<String>(API.USER_BINDPHONECODE)
                .tag(this)
                .upJson(jsonObject)
                .converter(StringConvert())
                .adapt(ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe({
                    mLoadingDialog.loading(getString(R.string.Toast_send_sms_ing))
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Response<String>> {

                    private var disposable: Disposable? = null

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(stringResponse: Response<String>) {
                        val msg = stringResponse.body()
                        Log.v("BindPhone",msg)
                        try {
                            val responseBeen = newGson.fromJson(msg, SmsSendCodeBeen::class.java)
                            val code = responseBeen.code
                            if (!TextUtils.isEmpty(code) && code == "0"){
                                this@BindPhoneActivity.code = responseBeen.data!!.code
                                Log.e("code",responseBeen.data!!.code+"  this@BindPhoneActivity.code        "+this@BindPhoneActivity.code)
                                XToastUtil.showToast(this@BindPhoneActivity, R.string.Toast_send_sms)
                                // 只有成功 才开始倒计时
                                val helper = CountDownHelper(tv_send_sms, getString(R.string.send_verification_code),
                                        getString(R.string.resend_verification_code), 60, 1)
                                helper.setOnFinishListener {
                                    //完成回调
                                }
                                helper.start()
                            }else{
                                XToastUtil.showToast(this@BindPhoneActivity, responseBeen.msg)
                            }

                        }catch (e:Exception){
                            onError(e)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        UIHelper.showToast(mContext, resources.getString(R.string.system_error))
                        mLoadingDialog.cancel()
                    }

                    override fun onComplete() {
                        disposable!!.dispose()
                        mLoadingDialog.cancel()
                    }
                })

    }
    //提交绑定的 手机号
    private fun http_SubmitPhone(){
        mLoadingDialog = LoadingDialog(this)
        val params = TreeMap<String, String>()
        params.put("phone",phoneNumber!!)
        params.put("code",smsCode!!)
        val jsonObject = JSONObject(params)

        OkGo.post<String>(API.USER_BINDPHONE)
                .tag(this)
                .upJson(jsonObject)
                .converter(StringConvert())
                .adapt(ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe({
                    mLoadingDialog.loading(getString(R.string.Toast_bind_phone_ing))
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Response<String>> {

                    private var disposable: Disposable? = null

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(stringResponse: Response<String>) {
                        val msg = stringResponse.body()
                        Log.v("BindPhoneSubmit",msg)
                        try {
                            finish()

                        }catch (e:Exception){
                            onError(e)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        UIHelper.showToast(mContext, resources.getString(R.string.system_error))
                        mLoadingDialog.cancel()
                    }

                    override fun onComplete() {
                        disposable!!.dispose()
                        mLoadingDialog.cancel()
                    }
                })
    }


}