package com.music.ui.activity.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.framework.utils.XToastUtil
import com.lzy.okgo.OkGo
import com.lzy.okgo.convert.StringConvert
import com.lzy.okgo.model.Response
import com.lzy.okrx2.adapter.ObservableResponse
import com.music.R
import com.music.api.API
import com.music.model.ResponseBeen
import com.music.model.jsonbeen.AddBankBeen
import com.music.model.jsonbeen.CouponsListBeen
import com.music.ui.activity.BaseActivity
import com.music.ui.holder.CouponsHolder
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
 * Created by Administrator on 2018/1/27.
 * 添加 银行卡
 * 农业银行
 */
class AddBankActivity :BaseActivity(){

    private var bank_user_Name:EditText ?= null
    private var bank_num:EditText ?= null
    private var bank_open_Name:EditText ?= null
    private var submit:Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_add_bank)
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun  initView(){
        bank_user_Name = findViewById(R.id.bank_user_Name)
        bank_num = findViewById(R.id.bank_num)
        bank_open_Name = findViewById(R.id.bank_open_Name)
        submit = findViewById(R.id.submit)

        submit!!.setOnClickListener{
            if (UtilsTools.isStringNull(bank_user_Name!!.text.toString())){
                XToastUtil.showToast(mContext,R.string.bank_use_Name_tip)
                return@setOnClickListener
            }
            if (UtilsTools.isStringNull(bank_num!!.text.toString())){
                XToastUtil.showToast(mContext,R.string.bank_num_tip)
                return@setOnClickListener
            }
            if (UtilsTools.isStringNull(bank_open_Name!!.text.toString())){
                XToastUtil.showToast(mContext,R.string.bank_open_Name_tip)
                return@setOnClickListener
            }
            initData()
        }

    }

    private fun initData (){
        mLoadingDialog = LoadingDialog(this)
        mLoadingDialog!!.show()

        val params = TreeMap<String, String>()
        params.put("account_name",bank_user_Name!!.text.toString())
        params.put("account_num",bank_num!!.text.toString())
        params.put("user_name",bank_user_Name!!.text.toString())
        val jsonObject = JSONObject(params)

        OkGo.post<String>(API.USER_ADDUSERACCOUNTCARD)
                .tag(this)
                .upJson(jsonObject)
                .converter(StringConvert())
                .adapt(ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe({ })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Response<String>> {

                    private var disposable: Disposable? = null

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(stringResponse: Response<String>) {
                        val msg = stringResponse.body()
                        try {
                            Log.v("musicCoupons",msg)

                            val responseBeen = newGson.fromJson(msg, AddBankBeen::class.java)
                            val code = responseBeen.code
                            XToastUtil.showToast(this@AddBankActivity, responseBeen.msg)
                            mLoadingDialog.cancel()
                            if (!TextUtils.isEmpty(code) && code == "0") {
                                finish()
                            }
                        } catch (e: Exception) {
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

    override fun onDestroy() {
        super.onDestroy()
        if (mLoadingDialog != null){
            mLoadingDialog!!.cancel()
            mLoadingDialog = null
        }
    }
}