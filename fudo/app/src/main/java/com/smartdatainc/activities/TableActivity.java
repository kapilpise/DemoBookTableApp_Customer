package com.smartdatainc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smartdatainc.adapters.TableListAdapter;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.HotelModal;
import com.smartdatainc.dataobject.HotelTableListModal;
import com.smartdatainc.fudo.R;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.managers.HotelTableManager;
import com.smartdatainc.utils.Utility;

import java.util.ArrayList;

/**
 * Created by ashutoshm on 29/8/16.
 */
public class TableActivity extends BaseActivity implements ServiceRedirection {

    private ArrayList<HotelTableListModal> hotelTableModalList = new ArrayList<>();

    private RecyclerView recyclerView;
    private TableListAdapter mAdapter;
    private Utility utilObj;
    int hotelID;
    private HotelModal hotelModal;
    private Context mContext;
    private HotelTableManager hotelTableManager;
    private EditText etSearch;
    TextView txtHotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtHotelName = (TextView) findViewById(R.id.txtHotelName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mContext = TableActivity.this;
        hotelTableManager = new HotelTableManager(this, this);

        //hotelID=getIntent().getIntExtra("hotelID",0);
        hotelModal = getIntent().getParcelableExtra("hotel");
        txtHotelName.setVisibility(View.VISIBLE);
        txtHotelName.setText(hotelModal.getHotelName());
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
                try {
                    HotelTableListModal recycleView = hotelTableModalList.get(position);

                    //utilObj.showToast(getApplicationContext(), recycleView.getHotelName() + " is selected!", 2);
                    startActivity(
                            new Intent(TableActivity.this, DishListActivity.class)
                                    .putExtra("table", recycleView)
                                    .putExtra("hotel", hotelModal));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
        etSearch.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        etSearch.setSingleLine(true);
        etSearch.setHint("Search Table by capacity remaining");
        etSearch.setVisibility(View.GONE);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });


    }


    private void filter(String text) {

        if (hotelTableModalList != null && hotelTableModalList.size() != 0 && !TextUtils.isEmpty(text.trim())) {
            ArrayList<HotelTableListModal> filterdNames = new ArrayList<>();

            //looping through existing elements
            for (int i = 0; i < hotelTableModalList.size(); i++) {
                int s = hotelTableModalList.get(i).getTableCapacity();
                //if the existing elements contains the search input
                if (s == Integer.parseInt(text)) {
                    //adding the element to filtered list
                    filterdNames.add(hotelTableModalList.get(i));
                }
            }

            //calling a method of the adapter class and passing the filtered list
            mAdapter.filterList(filterdNames);
        }
        //new array list that will hold the filtered data

    }


    @Override
    protected void onResume() {
        super.onResume();
        hotelTableModalList.clear();
        setData();
    }

    private void setData() {

        hotelTableManager.getHotelTableList(hotelModal.getId());
    }

    @Override
    public void onSuccessRedirection(int taskID) {
        hotelTableModalList = AppInstance.hotelTableListModal;
        if (hotelTableModalList != null) {
            mAdapter = new TableListAdapter(mContext, hotelTableModalList, hotelModal);
            recyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    public void onFailureRedirection(String errorMessage) {

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private TableActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final TableActivity.ClickListener clickListener) {
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
