package com.music.ui.activity.user

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.framework.utils.XToastUtil
import com.framework.view.recyclerView.XRecyclerView
import com.lzy.okgo.OkGo
import com.lzy.okgo.convert.StringConvert
import com.lzy.okgo.model.Response
import com.lzy.okrx2.adapter.ObservableResponse
import com.music.R
import com.music.api.API
import com.music.model.busbeen.AddBankBus
import com.music.model.busbeen.LoginFinishBus
import com.music.model.jsonbeen.BankListBeen
import com.music.model.jsonbeen.CouponsListBeen
import com.music.ui.activity.BaseActivity
import com.music.ui.dialog.DialogCommonTip
import com.music.ui.holder.AddBankHolder
import com.music.ui.holder.BankListHolder
import com.music.ui.holder.CouponsHolder
import com.music.utils.UIHelper
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.wega.library.loadingDialog.LoadingDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.Subscribe
import org.json.JSONObject
import java.util.*

@SuppressLint("Registered")
/**
 * Created by Administrator on 2018/1/27.
 * 银行卡列表
 */
class BankListManageActivity :BaseActivity(), DialogCommonTip.DialogOnClickSubmit {
    override fun onDialogClickSubmit() {
        mLoadingDialog = LoadingDialog(this)
        mLoadingDialog!!.show()

        val params = TreeMap<String, String>()
        params.put("id","")
        val jsonObject = JSONObject(params)

        OkGo.post<String>(API.USER_ACCOUNTDELETECARD)
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
                        Log.v("BankList",msg)
                        mLoadingDialog.cancel()
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

    override fun onDialogClickCancel() {
    }

    private var recyclerview: XRecyclerView?= null
    private var refresh_layout: SmartRefreshLayout?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_goods_search)
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        recyclerview = findViewById(R.id.mRecyclerEntityView)
        recyclerview!!.setBackgroundColor(Color.parseColor("#444444"))
        Width_Height()

    }

    override fun onResume() {
        super.onResume()
        //获取银行卡列表
        initData()
    }

    /**
     * 获取银行卡列表
     */
    private fun initData (){
        mLoadingDialog = LoadingDialog(this)
        mLoadingDialog!!.show()

        val params = TreeMap<String, String>()
        val jsonObject = JSONObject(params)

        OkGo.post<String>(API.USER_ACCOUNTCARDLIST)
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
                            Log.v("BankList",msg)

                            val responseBeen = newGson.fromJson(msg, BankListBeen::class.java)
                            val code = responseBeen.code
                            if (!TextUtils.isEmpty(code) && code == "0") {
                                recyclerview!!.recyclerView.layoutManager = LinearLayoutManager(this@BankListManageActivity)
                                recyclerview!!.adapter.bindHolder(BankListHolder())
                                recyclerview!!.adapter.bindHolder(AddBankHolder())
                                if (responseBeen.data!!.size > 0 ){
                                    recyclerview!!.adapter.setData(0, responseBeen.data!!)
                                    recyclerview!!.adapter.setData(1, "")
                                }
                                //else{
                                //无数据显示
                                //}

                            } else {
                                XToastUtil.showToast(this@BankListManageActivity, responseBeen.msg)
                            }
                            mLoadingDialog.cancel()
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

    @Subscribe
    fun onEventMainThread(event: AddBankBus?) {
        if (event != null) {
            if (event.index == 1){
                startActivity(Intent(mContext, AddBankActivity::class.java))
            }else{
                val  dialogCommonDelet = DialogCommonTip(mContext,R.style.AppTheme_bottomDialog,width,height)
                dialogCommonDelet.setDialogTitle("是否删除此卡？").setDialogCancel("取消")
                        .setDialogSubmit("确定").setDialogOnClick(this).show()
            }

        }
    }

}