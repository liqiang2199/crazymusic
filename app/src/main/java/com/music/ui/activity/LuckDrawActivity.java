package com.music.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.music.R;
import com.music.view.luckdraw.LuckyMonkeyPanelView;

import java.util.Random;

/**
 * 活动抽奖
 */
public class LuckDrawActivity extends BaseActivity {

    private LuckyMonkeyPanelView lucky_panel;
    private Button btn_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_luck_draw);
        super.onCreate(savedInstanceState);

        lucky_panel = (LuckyMonkeyPanelView) findViewById(R.id.lucky_panel);
        btn_action = (Button) findViewById(R.id.btn_action);
        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lucky_panel.isGameRunning()) {
                    lucky_panel.startGame();
                } else {
                    int stayIndex = new Random().nextInt(8);
                    Log.e("LuckyMonkeyPanelView", "====stay===" + stayIndex);
                    lucky_panel.tryToStop(stayIndex);
                }
            }
        });
    }
}
