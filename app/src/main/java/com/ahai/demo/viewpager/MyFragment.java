package com.ahai.demo.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhenhai.fzh on 17/3/15.
 */

public class MyFragment extends Fragment {

    public static final String key_entity = "entity";

    private MyEntity entity;
    private static String TAG = "MyFragment";

    public MyFragment(){

    }

    public static MyFragment newInstance(MyEntity entity) {

        Log.d(TAG, "newInstance " + entity.getText());

        Bundle bundle = new Bundle();
        bundle.putSerializable(key_entity, entity);

        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate " + entity.getText());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated " + entity.getText());
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView " + entity.getText());
        View view = inflater.inflate(R.layout.viewpager_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        // imageView.setImageResource(entity.getImgResId());
        textView.setText(entity.getText());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        entity = (MyEntity) getArguments().getSerializable(key_entity);
        Log.d(TAG, "onAttach " + entity.getText());
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach " + entity.getText());
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy " + entity.getText());
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView " + entity.getText());
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart " + entity.getText());
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop " + entity.getText());
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause " + entity.getText());
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume " + entity.getText());
        super.onResume();
    }
}
