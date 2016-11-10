package com.drawable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    /*左右滑动 布局
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle; // toggle   logo切换*/

    private Button bt_dialog;
    private ImageButton iv_showImage1;
    private ImageButton iv_showImage2;
    private SeekBar sb_zoom;
    private ImageButton cal_showImage3;
    private ImageButton tab_showImage4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //标题栏导航
        ActionBar actionBar = getSupportActionBar();
        //设置不显示标题
        actionBar.setDisplayShowTitleEnabled(false);
        //设置显示logo
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setLogo(R.mipmap.icon);

       /*设置actionbar背景
        Drawable background = getResources().getDrawable(R.drawable.main_title_bg);
        actionBar.setBackgroundDrawable(background);
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        bt_dialog = (Button) findViewById(R.id.btn_dialog);
        iv_showImage1 = (ImageButton) findViewById(R.id.seek_imageView);
        iv_showImage2 = (ImageButton) findViewById(R.id.seek_imageView2);
        sb_zoom = (SeekBar) findViewById(R.id.seek_Bar);
        cal_showImage3 = (ImageButton) findViewById(R.id.calculator_imageView);
        tab_showImage4 = (ImageButton) findViewById(R.id.tab_imageView);

        //设置图片的初始高度
        final int orgHeight = 60;
        final int orgWeight = 104;
        iv_showImage1.setLayoutParams(new LinearLayout.LayoutParams(orgWeight, orgHeight));
        iv_showImage2.setLayoutParams(new LinearLayout.LayoutParams(orgWeight, orgHeight));
        cal_showImage3.setLayoutParams(new LinearLayout.LayoutParams(orgWeight, orgHeight));

        //设置图片的上下文菜单
        registerForContextMenu(iv_showImage1);
        registerForContextMenu(iv_showImage2);
        registerForContextMenu(cal_showImage3);

        //设置拖动条的回调事件
        sb_zoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iv_showImage1.setLayoutParams(new LinearLayout.LayoutParams(orgWeight * progress,
                        orgHeight * progress));
                iv_showImage1.invalidate();

                iv_showImage2.setLayoutParams(new LinearLayout.LayoutParams(orgWeight * progress,
                        orgHeight * progress));
                iv_showImage2.invalidate();

                cal_showImage3.setLayoutParams(new LinearLayout.LayoutParams(orgWeight * progress,
                        orgHeight * progress));
                cal_showImage3.invalidate();

                tab_showImage4.setLayoutParams(new LinearLayout.LayoutParams(orgWeight * progress,
                        orgHeight * progress));
                tab_showImage4.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //设置按钮点击事件
        bt_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt_dialog.getText().toString().equals("弹出对话框")){
                    bt_dialog.setText("已弹出对话框");
                }else{
                    bt_dialog.setText("弹出对话框");
                }
                Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("我的对话框")
                        .setMessage("这是一个测试程序")
                        .setIcon(R.drawable.icon)
                        .create();
                dialog.show();
            }
        });


        iv_showImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Colour_Activity.class);
                startActivity(intent);
                //finish();  销毁 MainActivity
            }
        });

        iv_showImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListView_Activity.class);
                startActivity(intent);
                //finish();  销毁 MainActivity
            }
        });

        cal_showImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculator_Activity.class);
                startActivity(intent);
                //finish();  销毁 MainActivity
            }
        });

        tab_showImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TabActivity.class);
                startActivity(intent);
            }
        });


        /*左右滑动 布局

        // toggle   logo切换
        this.mActionBarDrawerToggle = new ActionBarDrawerToggle(this,
                this.mDrawerLayout, R.string.drawer_open, R.string.drawer_close);*/
    }


/*
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

    public class NetEaseDrawerListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerClosed(View arg0) {
            mActionBarDrawerToggle.onDrawerClosed(arg0);
        }

        @Override
        public void onDrawerOpened(View arg0) {
            mActionBarDrawerToggle.onDrawerOpened(arg0);
        }

        @Override
        public void onDrawerSlide(View arg0, float arg1) {
            mActionBarDrawerToggle.onDrawerSlide(arg0, arg1);
        }

        @Override
        public void onDrawerStateChanged(int arg0) {
            mActionBarDrawerToggle.onDrawerStateChanged(arg0);
        }
    }*/


    /*  菜单定义  */

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
   /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        //MenuItem shareItem = menu.findItem(R.id.share);
       // ShareActionProvider provider = (ShareActionProvider) shareItem.getActionProvider();
        //provider.setShareIntent(getDefaultIntent());
        return super.onCreateOptionsMenu(menu);
    }
  /*  private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image*//**//**//**//*");
        return intent;
    }*/
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                finish();
                return true;
            case R.id.user:
                Toast.makeText(this, "你点击了“用户”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(this, "你点击了“设置”按键！", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //*直接拷贝下面代码*//**//*
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("信息操作");
        super.getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item01:
                Toast.makeText(this, "第一个菜单被选中", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_item02:
                Toast.makeText(this, "第二个菜单被选中", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_item03:
                Toast.makeText(this, "第三个菜单被选中", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}


