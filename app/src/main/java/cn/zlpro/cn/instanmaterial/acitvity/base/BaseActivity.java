package cn.zlpro.cn.instanmaterial.acitvity.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.zlpro.cn.instanmaterial.R;

/**
 * Created by zhulang on 2016/1/10 0010.
 */
public class BaseActivity extends AppCompatActivity {
    Context context;
    private MenuItem inboxMenuItem;
    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Bind(R.id.ivLogo)
    ImageView ivLogo;

    @Override
    public void setContentView( int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    public Context getContext() {
        return context;
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        setupToolbar();
    }

    public void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        }
    }

    public void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);
        inboxMenuItem.setActionView(R.layout.menu_item_view);
        return true;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Nullable
    public ImageView getIvLogo() {
        return ivLogo;
    }

    public MenuItem getInboxMenuItem() {
        return inboxMenuItem;
    }
}
