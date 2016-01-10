package cn.zlpro.cn.instanmaterial.acitvity.base;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.BindString;
import cn.zlpro.cn.instanmaterial.R;
import cn.zlpro.cn.instanmaterial.helper.CircleTransformation;

/**
 * Created by 小白龙 on 2016/1/10 0010.
 */
public class BaseDrawerActivity extends BaseActivity {


    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.vNavigation)
    NavigationView mNavigationView;

    @BindDimen(R.dimen.global_menu_avatar_size)
    int avatarSize;
    @BindString(R.string.user_profile_photo)
    String profilePhoto;

    @Override
    public void setContentView( int layoutResID) {
        super.setContentViewWithoutInject(R.layout.activity_drawer);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.flContentRoot);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
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
        View headerView = mNavigationView.getHeaderView(0);
        ImageView ivMenuUserProfilePhoto = (ImageView) headerView.findViewById(R.id.ivMenuUserProfilePhoto);
        LinearLayout llGlobalMenuHeader = (LinearLayout) headerView.findViewById(R.id.llGlobalMenuHeader);
        llGlobalMenuHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("HerderView"+profilePhoto);
            }
        });

        //加载我的头像
        Picasso.with(this)
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(ivMenuUserProfilePhoto);

    }
}
