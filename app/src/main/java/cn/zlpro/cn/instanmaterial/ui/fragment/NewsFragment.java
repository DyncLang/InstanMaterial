package cn.zlpro.cn.instanmaterial.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.ui.fragment.base.BaseFragment;

public class NewsFragment extends BaseFragment {
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
       // ListView viewById = (ListView) view.findViewById(R.id.lv_news);
        //context = getContext();
        //viewById.setAdapter(new myAdapter());
        return view;
    }

    class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return View.inflate(getContext(), R.layout.item_news, null);
        }
    }

}
