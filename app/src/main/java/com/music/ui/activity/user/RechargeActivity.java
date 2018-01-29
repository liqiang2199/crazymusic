package com.music.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.music.R;
import com.music.model.busbeen.AddBankChoiceBus;
import com.music.model.busbeen.LoginFinishBus;
import com.music.ui.activity.BaseActivity;
import com.music.ui.dialog.BankChoiceList;
import com.music.ui.holder.DialogChoiceBankHolder;

import org.greenrobot.eventbus.Subscribe;

/**
 * 提现
 */
public class RechargeActivity extends BaseActivity implements View.OnClickListener{

    private RadioGroup radioGroup;
    private EditText edtMoney;
    private TextView tvBalance;
    private Button btnRecharge;
    private LinearLayout linear_choice_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recharge);
        super.onCreate(savedInstanceState);

        initViews();
    }

    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        edtMoney = (EditText) findViewById(R.id.edt_money);
        tvBalance = (TextView) findViewById(R.id.tv_balance);
        btnRecharge = (Button) findViewById(R.id.btn_recharge);
        linear_choice_bank = (LinearLayout) findViewById(R.id.linear_choice_bank);

        Width_Height();

        btnRecharge.setOnClickListener(this);
        linear_choice_bank.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.linear_choice_bank:
                //点击选择银行卡
                BankChoiceList dialogChoiceBankHolder = new BankChoiceList(mContext,R.style.ScreenDialogStyle,width,height);
                dialogChoiceBankHolder.show();
                break;
        }
    }

    @Subscribe
    public void AddBankChoiceBus(AddBankChoiceBus event){
        if (event != null){
            //跳转 添加银行卡
            startActivity(new Intent(mContext, AddBankActivity.class));
        }
    }

}
