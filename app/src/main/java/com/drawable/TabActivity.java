package com.drawable;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


/**
 * Created by TSM on 2016/10/26.
 */

public class TabActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);

        //添加tab选项
        ActionBar.Tab tab = actionBar.newTab()
                .setText("计科学院")
                .setTabListener(
                         new TabListener<Fragment1>(this,"film1",
                                Fragment1.class)) ;
        actionBar.addTab(tab);

        tab = actionBar
                .newTab()
                .setText("经济学院")
                .setTabListener(
                         new TabListener<Fragment2>(this,"film2",
                                Fragment2.class));
        actionBar.addTab(tab);

        tab = actionBar
                .newTab()
                .setText("外国语学院")
                .setTabListener(
                         new TabListener<Fragment3>(this,"film3",
                                Fragment3.class));
        actionBar.addTab(tab);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.colour_menu,menu);
        //MenuItem shareItem = menu.findItem(R.id.action_share);
        //ShareActionProvider provider = (ShareActionProvider) shareItem.getActionProvider();
        //provider.setShareIntent(getDefaultIntent());
        return super.onCreateOptionsMenu(menu);
    }

   /* private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image*//**//*");
        return intent;
    }*/

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if(NavUtils.shouldUpRecreateTask(this,upIntent)){
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this,upIntent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean isFinishing() {
        return super.isFinishing();
    }
}




























