package com.smartdatainc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartdatainc.adapters.SearchOrderListAdapter;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.HotelModal;
import com.smartdatainc.dataobject.OrderSearchModel;
import com.smartdatainc.fudo.R;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.managers.OrderManager;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.Utility;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ashutoshm on 29/8/16.
 */
public class SearchOrderActivity extends BaseActivity implements ServiceRedirection, View.OnClickListener {

    private ArrayList<OrderSearchModel> orderSearchModelList = new ArrayList<>();

    private RecyclerView recyclerView;
    private SearchOrderListAdapter mAdapter;
    private Utility utilObj;
    int hotelID;
    private HotelModal hotelModal;
    private Context mContext;
    private OrderManager orderManager;
    private EditText etOrderID, etEmaild;
    private Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etOrderID = (EditText) findViewById(R.id.etOrderID);
        etEmaild = (EditText) findViewById(R.id.etEmail);
        btnSearch = (Button) findViewById(R.id.btnSearchOrder);
        btnSearch.setOnClickListener(this);

        mContext = SearchOrderActivity.this;
        orderManager = new OrderManager(this, this);

        //hotelID=getIntent().getIntExtra("hotelID",0);

        initData();


    }


    public void initData() {
        utilObj = new Utility(this);
        recyclerView = (RecyclerView) findViewById(R.id.hotel_list);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    @Override
    protected void onResume() {
        super.onResume();
        orderSearchModelList.clear();
        //setData();
    }

    private void setData(int orderID, String emailID) {
        utilObj.startLoader(this, R.drawable.image_for_rotation);
        orderManager.searchOrder(orderID, emailID);
    }

    @Override
    public void onSuccessRedirection(int taskID) {
        utilObj.stopLoader();
        utilObj.hideVirtualKeyboard(mContext);

        if (taskID == Constants.TaskID.GET_ORDER_SEARCH) {
            orderSearchModelList = AppInstance.orderSearchModelList;
            if (AppInstance.orderSearchResponseModelList != null) {
                if (AppInstance.orderSearchResponseModelList.getOrderID() == 0) {
                    if (AppInstance.orderSearchResponseModelList.getOrderItemDetails() == null || AppInstance.orderSearchResponseModelList.getOrderItemDetails().size() == 0) {
                        Toast.makeText(mContext, "Order not found", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                startActivity(new Intent(this, ModifyOrderActivity.class)
                        .putExtra("Data", (Serializable) AppInstance.orderSearchResponseModelList)
                );
            }
        } else if (taskID == Constants.TaskID.CANCEL_ORDER) {
//            Toast.makeText(mContext, "Cancelled", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onFailureRedirection(String errorMessage) {
        utilObj.stopLoader();
        utilObj.hideVirtualKeyboard(mContext);
        Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSearchOrder:

                String orderID = etOrderID.getText().toString().trim();
                String emailID = etEmaild.getText().toString().trim();
                if (!TextUtils.isEmpty(orderID) && !TextUtils.isEmpty(emailID)) {
                    setData(Integer.parseInt(orderID), emailID);
                } else {
                    Toast.makeText(mContext, "Please provide complete details!!", Toast.LENGTH_SHORT).show();
                }

                break;

            default:

                break;
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SearchOrderActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SearchOrderActivity.ClickListener clickListener) {
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
