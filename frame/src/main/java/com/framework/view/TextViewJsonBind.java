package com.framework.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * TextView绑定json数据
 */
public class TextViewJsonBind {
    Map<String, TextView> map = new HashMap<>();
    Context context;
    View rootView;

    /**
     * 初始化
     * @param view
     */
    public TextViewJsonBind(View view) {
        rootView = view;
        context = view.getContext();
        if (view instanceof ViewGroup) {
            getTextViews((ViewGroup) view);
        }
    }

    /**
     * 设置json数据
     * @param jsonObject
     */
    public void setData(JSONObject jsonObject) {
        Set<Map.Entry<String, TextView>> entries = map.entrySet();
        for (Map.Entry<String, TextView> entry : entries) {
            String key=entry.getKey();
            entry.getValue().setText(jsonObject.optString(key));
        }
    }

    private void getTextViews(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TextView) { // 若是TextView记录下
                TextView newDtv = (TextView) view;
                String key = context.getResources().getResourceEntryName(newDtv.getId());
                map.put(key, newDtv);
            } else if (view instanceof ViewGroup) {
                // 若是布局控件（LinearLayout或RelativeLayout）,继续查询子View
                this.getTextViews((ViewGroup) view);
            }
        }
    }
}
