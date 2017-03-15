package com.ahai.demo.viewpager;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MyViewPagerActivity extends FragmentActivity implements View.OnClickListener {

    private String TAG = "MyViewPagerActivity";

    private ViewPager viewPager;
    private Button button;
    private MyFragmentStatePagerAdapter pagerAdapter;
    private List<MyEntity> entities;
    private List<Fragment> fragments;

    public void init() {

        entities = new LinkedList<>();
        entities.add(new MyEntity(R.drawable.image1, "1"));
        entities.add(new MyEntity(R.drawable.image2, "2"));
        entities.add(new MyEntity(R.drawable.image3, "3"));
        entities.add(new MyEntity(R.drawable.image4, "4"));
        entities.add(new MyEntity(R.drawable.image5, "5"));
        entities.add(new MyEntity(R.drawable.image6, "6"));
        entities.add(new MyEntity(R.drawable.image7, "7"));
        entities.add(new MyEntity(R.drawable.image8, "8"));
        entities.add(new MyEntity(R.drawable.image9, "9"));
        entities.add(new MyEntity(R.drawable.image10, "10"));

        fragments = new LinkedList<>();
        for(int i=0;i<entities.size();i++) {
            fragments.add(MyFragment.newInstance(entities.get(i)));
        }
        pagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_viewpager_activity);
        init();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == button.getId()) {
            watchViewPager();
            startExitAnim();
        }
    }

    private View[] mChildren = new View[3];
    private View mCurChild = null;

    private void watchViewPager() {
        Log.d(TAG, "watchViewPager");

        int curIndex = viewPager.getCurrentItem();
        int childCount = viewPager.getChildCount();

        Log.d(TAG, "curIndex:"+curIndex+", childCount:"+childCount);

        mChildren = new View[3];

        switch (childCount) {
            case 0:
                return;
            case 1:
                mChildren[0] = viewPager.getChildAt(0);
            case 2:
                mChildren[0] = viewPager.getChildAt(0);
                mChildren[1] = viewPager.getChildAt(1);
            case 3:
                mChildren[0] = viewPager.getChildAt(0);
                mChildren[1] = viewPager.getChildAt(1);
                mChildren[2] = viewPager.getChildAt(2);
        }

        for(int i=0;i<mChildren.length;i++){
            View view = mChildren[i];
            if(view != null) {
                int[] loc = new int[2];
                view.getLocationOnScreen(loc);
                Log.d(TAG, "children["+i+"] x:"+loc[0]+", width:"+view.getWidth());
                if(loc[0] > 0 && loc[0] < view.getWidth()) {
                    mCurChild = view;
                    break;
                }
            }
        }

        Log.d(TAG, "mCurChild:"+getViewString(mCurChild));

        for(int i = 0; i< mChildren.length; i++) {
            Log.d(TAG, "mChildren["+i+"]:"+getViewString(mChildren[i]));
        }
    }

    private String getViewString(View view) {
        if(view == null) {
            return "null";
        }
        return view.getClass().getSimpleName()+"@"+view.hashCode()+", left:"+view.getLeft();
    }

    private void startExitAnim() {
        Log.d(TAG, "startExitAnim");

        int toYDelta = mCurChild.getHeight();

        Log.d(TAG, "toYDelta:"+toYDelta);

        Animation anim = makeTranslateAnimation(0, 0, 0, toYDelta, true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(viewPager.getChildCount() > 1) {
                    startEnterAnim();
                } else {
                    pagerAdapter.removeItem(viewPager.getCurrentItem());
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mCurChild.startAnimation(anim);
    }

    private void startEnterAnim() {

        Log.d(TAG, "startEnterAnim");

        View viewEnter = null;

        switch (viewPager.getChildCount()) {
            case 2: {
                if(mChildren[0].hashCode() == mCurChild.hashCode()) {
                    viewEnter = mChildren[1];
                } else {
                    viewEnter = mChildren[0];
                }
                break;
            }
            case 3: {
                if(mChildren[0].hashCode() == mCurChild.hashCode()) {
                    viewEnter = mChildren[1].getLeft() > mChildren[2].getLeft() ? mChildren[1] : mChildren[2];
                } else if(mChildren[1].hashCode() == mCurChild.hashCode()) {
                    viewEnter = mChildren[0].getLeft() > mChildren[2].getLeft() ? mChildren[0] : mChildren[2];
                } else {
                    viewEnter = mChildren[1].getLeft() > mChildren[0].getLeft() ? mChildren[1] : mChildren[0];
                }
                break;
            }
        }

        int[] loc = new int[2];
        mCurChild.getLocationOnScreen(loc);
        int xCur = loc[0];

        viewEnter.getLocationOnScreen(loc);
        int xViewEnter = loc[0];

        int toXDelta = xCur - xViewEnter;

        Log.d(TAG, "xCur:"+xCur+", xViewEnter:"+xViewEnter+", toXDelta:"+toXDelta);

        Animation animEnter = makeTranslateAnimation(0, toXDelta, 0, 0, false);
        animEnter.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                pagerAdapter.removeItem(viewPager.getCurrentItem());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewEnter.startAnimation(animEnter);
    }

    private Animation makeTranslateAnimation(float fromX, float toX, float fromY, float toY, boolean fillAfter) {
        Animation animation = new TranslateAnimation(fromX, toX, fromY, toY);
        animation.setDuration(300);
        animation.setFillEnabled(true);
        animation.setFillAfter(fillAfter);
        animation.setInterpolator(new LinearInterpolator());
        return animation;
    }

    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            // return super.getItemPosition(object);
            return POSITION_NONE;
        }

        public void removeItem(int index) {
            Log.d(TAG, "removeItem index:"+index + ", total:"+fragments.size());
            if(index >= 0 && index < fragments.size()) {
                fragments.remove(index);
                notifyDataSetChanged();
            }
        }
    }
}
