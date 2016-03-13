package cn.zlpro.cn.instanmaterial.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Xiao_Bailong on 2016/3/13.
 */
public class LMRecyclerView extends RecyclerView {
    private String TAG = this.getClass().getSimpleName();
    private boolean isScrollingToBottom = true;
    private FloatingActionButton floatingActionButton;
    private LoadMoreListener listener;

    public LMRecyclerView(Context context) {
        super(context);
    }

    public LMRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LMRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        //这里可以判断是向上滑动还是向下滑动
        isScrollingToBottom = dy > 0;
    }

    @Override
    public void onScrollStateChanged(int state) {
        //是否需要下拉加载
        /**
         * 1. 滚动停止
         * 2. 当前显示的是最后一个Item
         * 3. 回调加载更多
         */

        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = layoutManager.getItemCount();
            if (lastVisibleItem == (totalItemCount - 1) && isScrollingToBottom) {
                if (listener != null)
                    listener.loadMore();
            }


        }
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.listener = loadMoreListener;
    }

    public interface LoadMoreListener {
        void loadMore();
    }
}
