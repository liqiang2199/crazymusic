package com.music.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.R;
import com.music.ui.activity.BaseActivity;

/**
 * 我的钱包
 */
public class MyWalletActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvBalance;
    private TextView tvWithdraw;
    private TextView tvRecharge;
    private RelativeLayout rlytCoupon;
    private RelativeLayout rlyt_bankList;
    private TextView tvCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setContentView(R.layout.activity_my_wallet);
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        tvBalance = (TextView)findViewById( R.id.tv_balance );
        tvWithdraw = (TextView)findViewById( R.id.tv_withdraw );
        tvRecharge = (TextView)findViewById( R.id.tv_recharge );
        rlytCoupon = (RelativeLayout)findViewById( R.id.rlyt_coupon );
        rlyt_bankList = (RelativeLayout)findViewById( R.id.rlyt_bankList );
        tvCoupon = (TextView)findViewById( R.id.tv_coupon );

        tvWithdraw.setOnClickListener(this);
        tvRecharge.setOnClickListener(this);
        rlytCoupon.setOnClickListener(this);
        rlyt_bankList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_withdraw:
                //提现
                startActivity(new Intent(this,WithdrawActivity.class ));
                break;
            case R.id.tv_recharge:
                //提现
                startActivity(new Intent(this,RechargeActivity.class ));
                break;
            case R.id.rlyt_coupon:
                //优惠券
                startActivity(new Intent(mContext,CouponActivity.class));
                break;
            case R.id.rlyt_bankList:
                //银行卡管理
                startActivity(new Intent(mContext,BankListManageActivity.class));
                break;
        }
    }


}
