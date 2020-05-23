package com.smartseat.workgroup.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smartseat.workgroup.R;

/**
 * 调节页面
 *
 * @author jx
 */
public class AdjustFragment extends Fragment {

    private View mViewKaobei;
    private ImageView mIvKaobeiSelected;

    private View mViewToutuo;
    private ImageView mIvToutuoSelected;

    private View mViewYaotuo;
    private ImageView mIvYaotuoSelected;

    private View mViewTuituo;
    private ImageView mIvTuituoSelected;

    private View mViewQianhou;
    private ImageView mIvQianhouSelected;

    public static AdjustFragment newInstance() {
        return new AdjustFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //通过参数中的布局填充获取对应布局
        View view = inflater.inflate(R.layout.activity_adjust_fragment, container, false);
        initView(view);
        initEvent();
        return view;

    }

    private void initView(View view) {
//        mViewKaobei = view.findViewById(R.id.v_kaobei);
//        mIvKaobeiSelected = view.findViewById(R.id.iv_kaobei_selected);
//        mViewToutuo = view.findViewById(R.id.v_toutuo);
//        mIvToutuoSelected = view.findViewById(R.id.iv_toutuo_selected);
//        mViewYaotuo = view.findViewById(R.id.v_yaotuo);
//        mIvYaotuoSelected = view.findViewById(R.id.iv_yaotuo_selected);
//        mViewTuituo = view.findViewById(R.id.v_tuituo);
//        mIvTuituoSelected = view.findViewById(R.id.iv_tuituo_selected);
//        mViewQianhou = view.findViewById(R.id.v_qianhou);
//        mIvQianhouSelected = view.findViewById(R.id.iv_qianhou_selected);
    }

    private void initEvent() {

    }
}
