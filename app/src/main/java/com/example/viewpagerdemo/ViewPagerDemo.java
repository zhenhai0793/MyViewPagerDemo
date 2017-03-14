package com.example.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ViewPagerDemo extends Activity {

    private View view1, view2, view3;
    private ViewPager viewPager;
    private PagerTitleStrip pagerTitleStrip;
    private PagerTabStrip pagerTabStrip;
    private List<View> viewList;
    private List<String> titleList;
    private Button weibo_button;
    private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //pagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);
        pagerTabStrip=(PagerTabStrip) findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.gold));
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.azure));
        pagerTabStrip.setTextSpacing(50);
		/*
		weibo_button=(Button) findViewById(R.id.button1);
		weibo_button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				intent=new Intent(ViewPagerDemo.this,WeiBoActivity.class);
				startActivity(intent);
			}
		});
		*/

        LayoutInflater lf = getLayoutInflater().from(this);
        view1 = lf.inflate(R.layout.layout1, null);
        view2 = lf.inflate(R.layout.layout2, null);
        view3 = lf.inflate(R.layout.layout3, null);

        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        titleList = new ArrayList<String>();
        titleList.add("主页");
        titleList.add("直播");
        titleList.add("设置");

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));

            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return titleList.get(position);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                weibo_button=(Button) findViewById(R.id.button1);
                weibo_button.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        intent=new Intent(ViewPagerDemo.this,WeiBoActivity.class);
                        startActivity(intent);
                    }
                });
                return viewList.get(position);
            }

        };
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_pager_demo, menu);
        return true;
    }

}
