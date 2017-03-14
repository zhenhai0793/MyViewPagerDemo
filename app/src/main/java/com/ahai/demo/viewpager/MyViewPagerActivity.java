package com.ahai.demo.viewpager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MyViewPagerActivity extends Activity implements View.OnClickListener {

    private String TAG = "MyViewPagerActivity";

    private ViewPager viewPager;
    private List<View> views;
    private Button button;
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
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == button.getId()) {
            startExitAnim();
        }
    }

    private void startExitAnim() {
        View curChild = viewPager.getChildAt(viewPager.getCurrentItem());
        int toYDelta = curChild.getHeight();
        Log.d(TAG, "toYDelta:"+toYDelta);
        Animation anim = makeTranslateAnimation(0, 0, 0, toYDelta, true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startEnterAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        curChild.startAnimation(anim);
    }

    private void startEnterAnim() {
        int curIndex = viewPager.getCurrentItem();
        Log.d(TAG, "curIndex:"+curIndex+", pageCount:"+viewPager.getChildCount());

        View curChild = viewPager.getChildAt(curIndex);
        View brother1 = viewPager.getChildAt(curIndex - 1);
        View brother2 = viewPager.getChildAt(curIndex + 1);

        int[] loc = new int[2];
        curChild.getLocationOnScreen(loc);
        int xCur = loc[0];

        brother1.getLocationOnScreen(loc);
        int xBrother1 = loc[0];

        brother2.getLocationOnScreen(loc);
        int xBrother2 = loc[0];

        View brother = xBrother1 > xBrother2 ? brother1 : brother2;

        int toXDelta = xCur - (xBrother1 > xBrother2 ? xBrother1 : xBrother2);

        Log.d(TAG, "xCur:"+xCur+", xBrother1:"+xBrother1+", xBrother2:"+xBrother2+", toXDelta:"+toXDelta);
        Animation anim = makeTranslateAnimation(0, toXDelta, 0, 0, true);
        brother.startAnimation(anim);
    }

    private Animation makeTranslateAnimation(float fromX, float toX, float fromY, float toY, boolean fillAfter) {
        Animation animation = new TranslateAnimation(fromX, toX, fromY, toY);
        animation.setDuration(300);
        animation.setFillEnabled(true);
        animation.setFillAfter(fillAfter);
        animation.setInterpolator(new LinearInterpolator());
        return animation;
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
