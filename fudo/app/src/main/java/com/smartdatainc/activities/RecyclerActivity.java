package com.smartdatainc.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


import com.smartdatainc.utils.Utility;
import com.smartdatainc.dataobject.RecycleView;
import com.smartdatainc.adapters.RecycleViewAdapter;


import com.smartdatainc.fudo.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashutoshm on 29/8/16.
 */
public class RecyclerActivity extends AppActivity {

    private List<RecycleView> recyleList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecycleViewAdapter mAdapter;
     private Utility utilObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initData();


    }


    public void initData() {
        utilObj = new Utility(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RecycleViewAdapter(recyleList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                RecycleView recycleView = recyleList.get(position);
              
             utilObj.showToast(getApplicationContext(), recycleView.getTitle() + " is selected!", 2);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareListData();

    }

    private void prepareListData() {
        recyleList.add(new RecycleView("One"));
        recyleList.add(new RecycleView("two"));
        recyleList.add(new RecycleView("three"));
        recyleList.add(new RecycleView("four"));
        recyleList.add(new RecycleView("five"));
        recyleList.add(new RecycleView("six"));
        recyleList.add(new RecycleView("seven"));
        recyleList.add(new RecycleView("eight"));
        recyleList.add(new RecycleView("nine"));
        recyleList.add(new RecycleView("ten"));
        mAdapter.notifyDataSetChanged();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private RecyclerActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
