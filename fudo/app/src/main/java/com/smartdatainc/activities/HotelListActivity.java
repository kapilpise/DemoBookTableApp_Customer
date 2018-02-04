package com.smartdatainc.activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.smartdatainc.adapters.HotelListAdapter;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.HotelModal;

import com.smartdatainc.fudo.R;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.managers.HotelListManager;
import com.smartdatainc.utils.Constants;

import java.util.ArrayList;

public class HotelListActivity extends BaseActivity implements ServiceRedirection,NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView hotelList;
    private ArrayList<HotelModal> hotelModels;
    private HotelModal hotelModel;
    private HotelListAdapter hotelListAdapter;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        hotelList = (RecyclerView) findViewById(R.id.hotel_list);
        hotelModels = new ArrayList<>();
        hotelListAdapter = new HotelListAdapter(HotelListActivity.this, hotelModels);
        hotelList.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        hotelList.setLayoutManager(layoutManager);
        hotelList.setItemAnimator(new DefaultItemAnimator());
        hotelList.setAdapter(hotelListAdapter);

        etSearch=(EditText)findViewById(R.id.etSearch);
        etSearch.setHint("Search Hotel By Name");

            //adding a TextChangedListener
            //to call a method whenever there is some change on the EditText
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

        if(hotelModels!=null && hotelModels.size()!=0){
            ArrayList<HotelModal> filterdNames = new ArrayList<>();

            //looping through existing elements
            for (int i=0;i<hotelModels.size();i++) {
                String s=hotelModels.get(i).getHotelName();
                //if the existing elements contains the search input
                if (s.toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(hotelModels.get(i));
                }
            }

            //calling a method of the adapter class and passing the filtered list
            hotelListAdapter.filterList(filterdNames);
        }
        //new array list that will hold the filtered data

    }






    @Override
    protected void onResume() {
        super.onResume();
        hotelModels.clear();
        // setData();
        loadHotelList();
    }

    private void loadHotelList() {

        HotelListManager hotelListManager = new HotelListManager(HotelListActivity.this, HotelListActivity.this);
        hotelListManager.getHotelList();
    }


    @Override
    public void onSuccessRedirection(int taskID) {
        if (taskID == Constants.TaskID.GET_HOTEL_LIST_TASK_ID) {
                //AppInstance.hotelModal = (HotelModal) dataReceived;
                hotelModels.addAll(AppInstance.hotelModal);
                hotelListAdapter.notifyDataSetChanged();



        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public void onFailureRedirection(String errorMessage) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item1) {
                startActivity(new Intent(HotelListActivity.this,SearchOrderActivity.class));
        } else {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
