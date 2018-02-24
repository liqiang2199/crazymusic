package com.music.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.R;
import com.music.common.Constants;
import com.music.model.busbeen.LoginFinishBus;
import com.music.ui.activity.shop.OrderListActivity;
import com.music.ui.activity.user.AddressManagementActivity;
import com.music.ui.activity.user.ApplyForTeacherActivity;
import com.music.ui.activity.user.CollectGoodsActivity;
import com.music.ui.activity.user.LoginActivity;
import com.music.ui.activity.user.MessgeActivity;
import com.music.ui.activity.user.MyCoursesActivity;
import com.music.ui.activity.user.MyWalletActivity;
import com.music.ui.activity.user.SetActivity;
import com.music.ui.activity.user.UserInfoActivity;
import com.music.utils.CacheUtil;
import com.music.utils.UIHelper;
import com.music.utils.UtilsTools;

import org.greenrobot.eventbus.Subscribe;

/**
 * 个人中心
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    protected View rootView;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvMyCourses;
    private TextView tvMyVideo;
    private TextView tvVideoCollection;
    private TextView tvMyCommunity;
    private RelativeLayout rlytMyWallet;
    private TextView tvMyBalance;
    private TextView tvMyOrder;
    private TextView tvProductCollection;
    private TextView tvApplyForTeacher;
    private TextView tvAddressManagement;
    private TextView tvSetUp;
    private RelativeLayout relative_useInfo;
    private ImageView info_image;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_my, null, false);
        }
        initView();
        return rootView;
    }

    private void initView() {
        ivAvatar = (ImageView) rootView.findViewById(R.id.iv_avatar);
        tvName = (TextView) rootView.findViewById(R.id.tv_name);
        tvMyCourses = (TextView) rootView.findViewById(R.id.tv_my_courses);
        tvMyVideo = (TextView) rootView.findViewById(R.id.tv_my_video);
        tvVideoCollection = (TextView) rootView.findViewById(R.id.tv_video_collection);
        tvMyCommunity = (TextView) rootView.findViewById(R.id.tv_my_community);
        rlytMyWallet = (RelativeLayout) rootView.findViewById(R.id.rlyt_my_wallet);
        tvMyBalance = (TextView) rootView.findViewById(R.id.tv_my_balance);
        tvMyOrder = (TextView) rootView.findViewById(R.id.tv_my_order);
        tvProductCollection = (TextView) rootView.findViewById(R.id.tv_product_collection);
        tvApplyForTeacher = (TextView) rootView.findViewById(R.id.tv_apply_for_teacher);
        tvAddressManagement = (TextView) rootView.findViewById(R.id.tv_address_management);
        tvSetUp = (TextView) rootView.findViewById(R.id.tv_set_up);

        relative_useInfo = (RelativeLayout)rootView.findViewById(R.id.relative_useInfo);
        info_image = rootView.findViewById(R.id.info_image);

        ivAvatar.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvMyCourses.setOnClickListener(this);
        tvMyVideo.setOnClickListener(this);
        tvVideoCollection.setOnClickListener(this);
        tvMyCommunity.setOnClickListener(this);
        rlytMyWallet.setOnClickListener(this);
        tvMyOrder.setOnClickListener(this);
        tvProductCollection.setOnClickListener(this);
        tvApplyForTeacher.setOnClickListener(this);
        tvAddressManagement.setOnClickListener(this);
        tvSetUp.setOnClickListener(this);
        relative_useInfo.setOnClickListener(this);
        info_image.setOnClickListener(this);

        tvName.setText(R.string.login_or_register);
        tvMyBalance.setText("");
    }

    @Override
    public void onClick(View v) {
        String token = CacheUtil.get(Constants.TOKEN);
//        if (TextUtils.isEmpty(token)) {
//            UIHelper.showToast(getActivity(), getString(R.string.you_have_not_landed_please_login_to_operate));
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//        } else {
        switch (v.getId()) {
            case R.id.tv_name:
            case R.id.iv_avatar:
            case R.id.relative_useInfo:
                //个人信息
                if (UtilsTools.isStringNull(UtilsTools.getReadCacheUtilData(Constants.TOKEN))){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.tv_my_courses:
                //我的课程
                startActivity(new Intent(getActivity(), MyCoursesActivity.class));
                break;
            case R.id.tv_my_video:
                //我的视频
                break;
            case R.id.tv_video_collection:
                //视频收藏
                break;
            case R.id.tv_my_community:
                //我的社区
                break;
            case R.id.rlyt_my_wallet:
                //我的钱包
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.tv_my_order:
                //我的订单
                startActivity(new Intent(getActivity(), OrderListActivity.class));
                break;
            case R.id.tv_product_collection:
                //商品收藏
                startActivity(new Intent(getActivity(), CollectGoodsActivity.class));
                break;
            case R.id.tv_apply_for_teacher:
                //成为老师
                startActivity(new Intent(getActivity(), ApplyForTeacherActivity.class));
                break;
            case R.id.tv_address_management:
                //地址管理
                startActivity(new Intent(getActivity(), AddressManagementActivity.class));
                break;
            case R.id.tv_set_up:
                //设置
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;
            case R.id.info_image:
                startActivity(new Intent(getActivity(), MessgeActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (tvName != null){
            if (UtilsTools.isStringNull(UtilsTools.getReadCacheUtilData(Constants.NINCKNAME))){
                tvName.setText(R.string.login_or_register);
            }else{
                tvName.setText(UtilsTools.getReadCacheUtilData(Constants.NINCKNAME));
            }

        }
    }

}
