package cn.zlpro.cn.instanmaterial.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.R;

/**
 * Created by Xiao_Bailong on 2016/2/29.
 */
public class NewsGanIoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    public NewsGanIoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_gankio, parent);
        return new GanIoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    /**
     * 干货 ViewHolder
     */
    public static class GanIoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_meizi)
        ImageView ivMeiZi;
        @Bind(R.id.rl_gankio)
        RelativeLayout rlGankio;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_desc)
        TextView tvDesc;
        @Bind(R.id.tv_who)
        TextView tvWho;

        public GanIoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
