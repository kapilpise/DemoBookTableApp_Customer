package com.smartdatainc.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartdatainc.adapters.CartItemListAdapter;
import com.smartdatainc.dataobject.RecycleView;
import com.smartdatainc.fudo.R;
import com.smartdatainc.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashutoshm on 29/8/16.
 */
public class CartItemListActivity extends BaseActivity implements View.OnClickListener {

    private List<RecycleView> recyleList = new ArrayList<>();

    private RecyclerView recyclerView;
    private CartItemListAdapter mAdapter;
     private Utility utilObj;
     private TextView mConfirmOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mConfirmOrder=(TextView) findViewById(R.id.text_action_bottom2);
        mConfirmOrder.setOnClickListener(this);
        setCartLayout();
        initData();


    }


    public void initData() {
        utilObj = new Utility(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mAdapter = new CartItemListAdapter(recyleList);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.text_action_bottom2:
                    openConfirmDialog();
                break;

                default:
                    break;
        }
    }

    public void openConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_confirm_order, null))
                // Add action buttons
                .setPositiveButton(R.string.emailID, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private CartItemListActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final CartItemListActivity.ClickListener clickListener) {
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

    protected void setCartLayout(){
        LinearLayout layoutCartItems = (LinearLayout) findViewById(R.id.layout_items);
        LinearLayout layoutCartPayments = (LinearLayout) findViewById(R.id.layout_payment);
        LinearLayout layoutCartNoItems = (LinearLayout) findViewById(R.id.layout_cart_empty);

        layoutCartNoItems.setVisibility(View.GONE);
        layoutCartItems.setVisibility(View.VISIBLE);
        layoutCartPayments.setVisibility(View.VISIBLE);
/*
        if(MainActivity.notificationCountCart >0){
            layoutCartNoItems.setVisibility(View.GONE);
            layoutCartItems.setVisibility(View.VISIBLE);
            layoutCartPayments.setVisibility(View.VISIBLE);
        }else {
            layoutCartNoItems.setVisibility(View.VISIBLE);
            layoutCartItems.setVisibility(View.GONE);
            layoutCartPayments.setVisibility(View.GONE);

            Button bStartShopping = (Button) findViewById(R.id.bAddNew);
            bStartShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }*/
    }

}
