package cn.zlpro.cn.instanmaterial.acitvity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.adapter.FeedAdapter;
import cn.zlpro.cn.instanmaterial.utli.Utils;

public class MainActivity extends AppCompatActivity implements FeedAdapter.onFeedItemClickListener
{
    private MenuItem inboxMenuItem;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rvFeed)
    RecyclerView rvFeed;
    @Bind(R.id.btnCreate)
    ImageButton btnCreate;
    @Bind(R.id.ivLogo)
    ImageView ivLogo;

    private FeedAdapter feedAdapter;

    private boolean pendingIntroAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        }
        setupToolbar();
        setupFeed();

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        //设置toolber左边的图片
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvFeed.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(this);
        feedAdapter.setonFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);
        inboxMenuItem.setActionView(R.layout.menu_item_view);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }


    private long ANIM_DURATION_TOOLBAR = 300;
    private void startIntroAnimation() {

        //出现动画的逻辑：
        //1.android　onCreate的时候我们是看不到界面的，所以我们可以在这里设置动画。
        //2.在菜单创建并且执行结束以后在执行我们目前可以看到的其他动画。
        //3.设置时间差。这样才会有不断出现的效果。
        btnCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        int actionbarSize = Utils.dpToPx(56);

        toolbar.setTranslationY(-actionbarSize);
        ivLogo.setTranslationY(-actionbarSize);
        inboxMenuItem.getActionView().setTranslationY(-actionbarSize);
        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300).start();
        ivLogo.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400).start();
        inboxMenuItem.getActionView().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation();
                    }
                })
                .start();
    }

    private static final int ANIM_DURATION_FAB = 400;

    private void startContentAnimation() {
        btnCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
        feedAdapter.updateItems();
    }

    @Override
    public void onCommentsClick(View v, int position)
    {
        //跳转到评论列表
        //传递动画过去设置不同的列表
        int[] startingLaocation = new int[2];
        v.getLocationOnScreen(startingLaocation);
        Intent intent = new Intent(MainActivity.this,CommentsActivity.class);
        //设置Y的坐标过去。
        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION,startingLaocation[1]);
        startActivity(intent);

        //关闭两者间的过度效果。
        overridePendingTransition(0,0);
    }
}
