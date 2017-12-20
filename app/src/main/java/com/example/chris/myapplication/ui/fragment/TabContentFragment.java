//package com.example.chris.myapplication.ui.fragment;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.example.chris.myapplication.R;
//import com.example.chris.myapplication.ui.base.BaseFragment;
//
//import butterknife.Bind;
//
///**
// * 类描述：
// * 创建人 Chris
// * 创建时间：2017/12/12 9:34
// */
//
//public class TabContentFragment extends BaseFragment {
//
//    private static final String EXTRA_CONTENT = "content";
//    @Bind(R.id.tv_content)
//    TextView tvContent;
//
//    public static TabContentFragment newInstance(String content) {
//        Bundle arguments = new Bundle();
//        arguments.putString(EXTRA_CONTENT, content);
//        TabContentFragment tabContentFragment = new TabContentFragment();
//        tabContentFragment.setArguments(arguments);
//        return tabContentFragment;
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_tab_content;
//    }
//
//    @Override
//    protected void initData() {
//        tvContent.setText(getArguments().getString(EXTRA_CONTENT));
//    }
//}
