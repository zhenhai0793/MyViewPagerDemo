package com.ahai.demo.viewpager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MyViewPagerActivity extends Activity implements View.OnClickListener {

    private String TAG = "MyViewPagerActivity";

    private ViewPager viewPager;
    private List<View> views;
    private Button btnDel;
    private MyViewPagerAdapter pagerAdapter;

    LinkedList<Integer> imgResIds = new LinkedList();

    public void initData() {
        imgResIds.add(R.drawable.image1);
        imgResIds.add(R.drawable.image2);
        imgResIds.add(R.drawable.image3);
        imgResIds.add(R.drawable.image4);
        imgResIds.add(R.drawable.image5);
        imgResIds.add(R.drawable.image6);
        imgResIds.add(R.drawable.image7);
        imgResIds.add(R.drawable.image8);
        imgResIds.add(R.drawable.image9);
        imgResIds.add(R.drawable.image10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_viewpager_activity);
        initData();
        initViews();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        views = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            View view = getLayoutInflater().inflate(R.layout.viewpager_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
            imageView.setImageResource(imgResIds.get(i));
            views.add(view);
        }
        pagerAdapter = new MyViewPagerAdapter(views);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        btnDel = (Button) findViewById(R.id.btn_del_cur_pager);
        btnDel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnDel.getId()) {

        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> views;

        public MyViewPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position), 0);
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
