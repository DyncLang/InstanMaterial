package cn.zlpro.cn.instanmaterial.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.ui.fragment.base.BaseFragment;

public class NewsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        return view;
    }


}
