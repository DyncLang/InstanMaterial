package cn.zlpro.cn.instanmaterial.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.model.enter.MEIZI;

/**
 * Created by Xiao_Bailong on 2016/2/29.
 */
public class NewsGanIoAdapter extends RecyclerView.Adapter<NewsGanIoAdapter.GanIoViewHolder> {

    private Context context;
    private List<MEIZI> meiziList;
    private int avatarSize;

    public NewsGanIoAdapter(Context context, List<MEIZI> meiziList) {
        this.context = context;
        this.meiziList = meiziList;
        avatarSize = context.getResources().getDimensionPixelSize(R.dimen.btn_fab_size);
    }

    @Override
    public NewsGanIoAdapter.GanIoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_gankio, parent, false);
        return new GanIoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GanIoViewHolder holder, int position) {
        MEIZI meizi = meiziList.get(position);
        //设置图片大小；
        Picasso.with(context)
                .load(meizi.getUrl())
                .into(holder.ivMeiZi);
    }

    @Override
    public int getItemCount() {
        return meiziList.size();
    }

    /**
     * 干货 ViewHolder
     */
    public static class GanIoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_meizi)
        public ImageView ivMeiZi;
        @Bind(R.id.rl_gankio)
        public RelativeLayout rlGankio;
        @Bind(R.id.tv_time)
        public TextView tvTime;
        @Bind(R.id.tv_desc)
        public TextView tvDesc;
        @Bind(R.id.tv_who)
        public TextView tvWho;

        public GanIoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
