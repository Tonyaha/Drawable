package com.drawable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TSM on 2016/10/16.
 */
public class ListView_Activity extends Activity {
    private ListView listView1;
    private List<Map<String,Object>>dataList;
    private SimpleAdapter simpleAdapter;

    private Calendar calendar; //获取当前时期（到秒）
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

         /*
           年月日——秒
         */
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        setTitle(" 通讯录       " + year + "-" + month + "-" + day + "   " + hour + ":" + minute);

        listView1 = (ListView) findViewById(R.id.listView_item);
        dataList = new ArrayList<Map<String, Object>>();
        simpleAdapter = new SimpleAdapter(this, getDataList(), R.layout.item_listview, new String[]{"image", "name", "telephone", "checked"},
                new int[]{R.id.list_image1, R.id.list_text1, R.id.list_text2, R.id.checkBox1});
        listView1.setAdapter(simpleAdapter);

      /*  MyOnItemClickListener myOnItemClickListener = new MyOnItemClickListener();
        listView1.setOnItemClickListener(myOnItemClickListener);*/

        MyOnScrollListener myOnScrollListener = new MyOnScrollListener();
        listView1.setOnScrollListener(myOnScrollListener);
    }

    //初始化数据
    private List<Map<String, Object>> getDataList() {
        SchoolBean bean = new SchoolBean();
        for(int i=0;i<20;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.drawable.icon);
            map.put("name", bean.getName());
            map.put("telephone", "");//bean.getTelephone());
            map.put("checked", bean.getChecked());

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", R.drawable.icon);
            map1.put("name", bean.getName());
            map1.put("telephone",  "");//bean.getTelephone());
            map1.put("checked", bean.getChecked());

            dataList.add(map);
            dataList.add(map1);
        }
        return dataList;
    }

   /* public class MyOnItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String text = listView1.getItemAtPosition(position)+"";  //获取被点击的位置
            Toast.makeText(ListView_Activity.this, "position="+position + "text="+text,Toast.LENGTH_SHORT).show();  //位置，内容，时长  .show() 是Toast的打印方法
        }
    }*/

    public class MyOnScrollListener implements AbsListView.OnScrollListener{

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            SchoolBean bean = new SchoolBean();
            switch (scrollState){
                case SCROLL_STATE_FLING:
                    Log.i("Tag","手指 离开 屏幕，视图正在以 惯性滑动");
                    Map<String,Object>map = new HashMap<String,Object>(); //new 一个Map对象
                    //视图中的每一行都是一个简单适配器的数据源 dataList中的  Map
                    map.put("image", R.drawable.icon);
                    map.put("name", bean.getName());
                    map.put("telephone", "");// bean.getTelephone());
                    map.put("checked", bean.getChecked());
                    dataList.add(map); //加载到数据源
                    simpleAdapter.notifyDataSetChanged();//通知 UI 进行刷新数据  熟练运用
                    break;
                case SCROLL_STATE_IDLE:
                    Log.i("Tag","视图已经停止滑动");
                    Map<String,Object>map1 = new HashMap<String,Object>(); //new 一个Map对象
                    //视图中的每一行都是一个简单适配器的数据源 dataList中的  Map
                    map1.put("image", R.drawable.icon);
                    map1.put("name", bean.getName());
                    map1.put("telephone",  "");//bean.getTelephone());
                    map1.put("checked", bean.getChecked());
                    dataList.add(map1); //加载到数据源
                    simpleAdapter.notifyDataSetChanged();//通知 UI 进行刷新数据  熟练运用
                    break;
                case SCROLL_STATE_TOUCH_SCROLL:
                    Log.i("Tag", "手指没离开屏幕，视图正在滑动");
                    Map<String, Object> map2 = new HashMap<String, Object>(); //new 一个Map对象
                    //视图中的每一行都是一个简单适配器的数据源 dataList中的  Map
                    map2.put("image",R.drawable.icon);
                    map2.put("name", bean.getName());
                    map2.put("telephone",  "");//bean.getTelephone());
                    map2.put("checked", bean.getChecked());
                    dataList.add(map2); //加载到数据源
                    simpleAdapter.notifyDataSetChanged();//通知 UI 进行刷新数据  熟练运用
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
