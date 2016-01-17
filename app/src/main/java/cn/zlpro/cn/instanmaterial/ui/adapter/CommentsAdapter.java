package cn.zlpro.cn.instanmaterial.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.ui.helper.RoundedTransformation;

/**
 * Created by 小白龙 on 2015/12/27 0027.
 */
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private int itemsCount=5;
    private int avatarSize;
    public CommentsAdapter(Context context)
    {
       this.context = context;
        //初始化设置图片的大小
        avatarSize = context.getResources().getDimensionPixelSize(R.dimen.btn_fab_size);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentsViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        CommentsViewHolder commentsViewHolder = (CommentsViewHolder) holder;
        switch (position%3)
        {
            case 0:
                commentsViewHolder.tvComment.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
                break;
            case 1:
                commentsViewHolder.tvComment.setText("Cupcake ipsum dolor sit amet bear claw.");
                break;
            case 2:
                commentsViewHolder.tvComment.setText("Cupcake ipsum dolor sit. Amet gingerbread cupcake. Gummies ice cream dessert icing marzipan apple pie dessert sugar plum.");
                break;
        }

        Picasso.with(context)
                .load(R.drawable.ic_launcher)
                .centerCrop()
                .resize(avatarSize, avatarSize)
                .transform(new RoundedTransformation())
                .into(commentsViewHolder.ivUserAvator);

    }

    @Override
    public int getItemCount()
    {
        return itemsCount;
    }

    public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.ivUserAvatar)
       public ImageView ivUserAvator;
        @Bind(R.id.tvComment)
        public TextView tvComment;

        public CommentsViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
