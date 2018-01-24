package com.music.ui.activity.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.music.R;
import com.music.ui.activity.BaseActivity;

/**
 * 提现
 */
public class RechargeActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private EditText edtMoney;
    private TextView tvBalance;
    private Button btnRecharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recharge);
        super.onCreate(savedInstanceState);
    }

    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        edtMoney = (EditText) findViewById(R.id.edt_money);
        tvBalance = (TextView) findViewById(R.id.tv_balance);
        btnRecharge = (Button) findViewById(R.id.btn_recharge);

        btnRecharge.setOnClickListener(this);
    }
}
