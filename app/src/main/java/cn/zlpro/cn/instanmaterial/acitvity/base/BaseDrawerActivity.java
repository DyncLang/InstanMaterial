package cn.zlpro.cn.instanmaterial.acitvity.base;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import cn.zlpro.cn.instanmaterial.R;

/**
 * Created by 小白龙 on 2016/1/10 0010.
 */
public class BaseDrawerActivity extends BaseActivity {


    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    NavigationView mNavigationView;

    @Override
    public void setContentView( int layoutResID) {
        super.setContentViewWithoutInject(R.layout.activity_drawer);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.flContentRoot);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        Logger.i("setContentViewBaseDrawerActivity");
        bindViews();
        setupHeader();
    }

    @Override
    public void setupToolbar() {
        super.setupToolbar();
        if (getToolbar()!=null){
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    private void setupHeader() {

    }
}
