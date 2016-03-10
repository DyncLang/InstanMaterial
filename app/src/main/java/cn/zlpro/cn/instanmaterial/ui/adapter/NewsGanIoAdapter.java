package cn.zlpro.cn.instanmaterial.ui.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
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
import cn.zlpro.cn.instanmaterial.utli.DateUtil;

/**
 * Created by Xiao_Bailong on 2016/2/29.
 */
public class NewsGanIoAdapter extends RecyclerView.Adapter<NewsGanIoAdapter.GanIoViewHolder> {

    private Context context;
    private List<MEIZI> meiziList;
    private int avatarSize;
    private int lastPosition;

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

        //设置随机的预加载颜色
        holder.card.setTag(meizi);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        holder.ivMeiZi.setBackgroundColor(Color.argb(204, red, green, blue));
        holder.tvWho.setText(meizi.who);
        holder.tvTime.setText(DateUtil.toDateTimeStr(meizi.publishedAt));
        //设置图片大小；
        Picasso.with(context)
                .load(meizi.url)
                .into(holder.ivMeiZi);

        //设置条目显示动画
        showItemAnimation(holder, position);
    }

    @Override
    public int getItemCount() {
        return meiziList.size();
    }


    private void showItemAnimation(GanIoViewHolder holder, int position) {
        if (position > lastPosition) {
            lastPosition = position;
            ObjectAnimator.ofFloat(holder.card, "translationY", 1f * holder.card.getHeight(), 0f)
                    .setDuration(1000)
                    .start();
        }
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

       /* @OnClick (R.id.iv_meizi)
        void meiziClick() {
            ShareElement.shareDrawable = ivMeiZi.getDrawable();
            Intent intent = new Intent(context, MeizhiActivity.class);
            intent.putExtra(PanConfig.MEIZI,(Serializable) card.getTag());
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context,ivMeizi,PanConfig.TRANSLATE_GIRL_VIEW);
            ActivityCompat.startActivity((Activity) context,intent,optionsCompat.toBundle());
        }*/



        View card;
        public GanIoViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

}
