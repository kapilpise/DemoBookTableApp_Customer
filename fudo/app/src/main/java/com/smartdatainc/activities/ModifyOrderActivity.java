package com.smartdatainc.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartdatainc.adapters.ModifyOrderAdapter;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.CancelOrderRequestModel;
import com.smartdatainc.dataobject.HotelMenuModal;
import com.smartdatainc.dataobject.HotelModal;
import com.smartdatainc.dataobject.HotelTableListModal;
import com.smartdatainc.dataobject.OrderItemDetail;
import com.smartdatainc.dataobject.OrderSearchResponseModel;
import com.smartdatainc.fudo.R;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.managers.OrderManager;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.Utility;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ModifyOrderActivity extends BaseActivity implements ServiceRedirection, View.OnClickListener, ModifyOrderAdapter.IUpdatePrice {

    private RecyclerView hotelList;
    private ArrayList<OrderItemDetail> dishModels;
    private ArrayList<OrderItemDetail> dishModelsNew;
    private HotelMenuModal dishModel;
    private ModifyOrderAdapter dishListAdapter;
    private Button btnConfirmOrder, btnCancel;
    private HotelModal hotelModal;
    private HotelTableListModal hotelTableModal;
    private OrderManager orderManager;
    private EditText etSearch;
    private TextView tvTotal;
    Utility utility;
    OrderSearchResponseModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        utility = new Utility(this);
        btnConfirmOrder = (Button) findViewById(R.id.btnConfirmOrder);
        btnConfirmOrder.setText("Update Order");
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.VISIBLE);
        btnConfirmOrder.setOnClickListener(this);
        tvTotal.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        hotelList = (RecyclerView) findViewById(R.id.hotel_list);

        try {
            dishModels = new ArrayList<>();
            data = (OrderSearchResponseModel) getIntent().getSerializableExtra("Data");
            dishModels.addAll(data.getOrderItemDetails());

            dishModelsNew = new ArrayList<>();
            for (int i = 0; i < dishModels.size(); i++) {
                OrderItemDetail orderItemDetail = dishModels.get(i);
                dishModelsNew.add(orderItemDetail);
            }

            hotelList.setLayoutManager(new LinearLayoutManager(this));
            hotelList.setItemAnimator(new DefaultItemAnimator());
            hotelList.setHasFixedSize(true);

            dishModels.clear();
            dishModels.addAll(dishModelsNew);
            dishListAdapter = new ModifyOrderAdapter(ModifyOrderActivity.this, dishModels);
            dishListAdapter.notifyDataSetChanged();
            hotelList.setAdapter(dishListAdapter);


//        hotelModal = getIntent().getParcelableExtra("hotel");
//        hotelTableModal = getIntent().getParcelableExtra("table");
            orderManager = new OrderManager(this, this);


            if (data.getIsApproveStatus() == 1) {
                btnConfirmOrder.setText("Order Accepted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setSingleLine(true);
        etSearch.setHint("Search Menu Item by Name");
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

        calculateTotalPrice();
    }


    private void filter(String text) {

        if (dishModels != null && dishModels.size() != 0) {
            ArrayList<OrderItemDetail> filterdNames = new ArrayList<>();

            //looping through existing elements
            for (int i = 0; i < dishModels.size(); i++) {
                String s = dishModels.get(i).getDishName();
                //if the existing elements contains the search input
                if (s.toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(dishModels.get(i));
                }
            }

            //calling a method of the adapter class and passing the filtered list
//            dishListAdapterfilterList(filterdNames);
        }
        //new array list that will hold the filtered data

    }


    @Override
    protected void onResume() {
        super.onResume();
//        loadMenuList();
    }

    @Override
    public void onSuccessRedirection(int taskID) {

        utility.stopLoader();
        if (taskID == Constants.TaskID.GET_HOTEL_MENU_LIST_TASK_ID) {
            //AppInstance.hotelModal = (HotelModal) dataReceived;
//            dishModels.addAll(AppInstance.hotelMenuModal);
//            dishListAdapter.notifyDataSetChanged();


        } else if (taskID == Constants.TaskID.POST_CONFIRM_ORDER) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your Order ID " + AppInstance.orderSearchModelList.get(0).getOrderID() + " has been proccessed successfully")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            //do things
                            Intent intent = new Intent(ModifyOrderActivity.this, HotelListActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish(); // call this to finish the current activity
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (taskID == Constants.TaskID.CANCEL_ORDER) {
            if (AppInstance.cancelOrderMessage != null && !AppInstance.cancelOrderMessage.isEmpty()) {
                Toast.makeText(this, "" + AppInstance.cancelOrderMessage, Toast.LENGTH_LONG).show();
                finish();
            }
        } else if (taskID == Constants.TaskID.MODIFY_ORDER) {
            if (AppInstance.modifyOrderMessage != null && !AppInstance.modifyOrderMessage.isEmpty()) {
                if (AppInstance.modifyOrderMessage.equalsIgnoreCase("Success")) {
                    Toast.makeText(this, "Order Updated Successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }

    @Override
    public void onFailureRedirection(String errorMessage) {
        utility.stopLoader();
        Toast.makeText(this, "Failure Error : " + errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirmOrder:
                if (data.getIsApproveStatus() == 0)
                    openModifyOrderDialog();
                else {
                    utility.showToast(this, "Cannot modify accepted order", Toast.LENGTH_LONG);
                }
                break;
            case R.id.tvTotal:
                if (data.getIsApproveStatus() == 0)
                    openModifyOrderDialog();
                else {
                    utility.showToast(this, "Cannot modify accepted order", Toast.LENGTH_LONG);
                }
                break;
            case R.id.btnCancel:
                cancelDialog();
                break;

            default:
                break;
        }
    }

    private void openModifyOrderDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        alert.setTitle("Modify Order");
        alert.setMessage("Are you sure to modify this order");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    utility.startLoader(ModifyOrderActivity.this, R.drawable.image_for_rotation);
                    modifyOrder();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    private void cancelDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        alert.setTitle("Cancel Order");
        if (data.getIsApproveStatus() == 1) {
            alert.setMessage("Your order is accepted, are you sure to cancel this order?");
        } else
            alert.setMessage("Are you sure to cancel this order");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    utility.startLoader(ModifyOrderActivity.this, R.drawable.image_for_rotation);
                    cancelOrder();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void cancelOrder() {
        CancelOrderRequestModel cancelOrderRequestModel = new CancelOrderRequestModel();
        cancelOrderRequestModel.setOrderId(data.getOrderID());
        cancelOrderRequestModel.setUserType("Customer");
        orderManager.cancelOrder(cancelOrderRequestModel);
    }

    private void modifyOrder() {
        OrderSearchResponseModel responseModel = data;
        responseModel.setUserType("Customer");

        for (int i = 0; i < dishModels.size(); i++) {
            dishModels.get(i).setDishTotalAmount((dishModels.get(i).getDishUnitPrice() * dishModels.get(i).getQuantity()));
        }
        data.setOrderItemDetails(dishModels);
        data.setTotalAmount(total);
        orderManager.modifyOrder(responseModel);
    }


    private boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public double getTotalPrice(ArrayList<HotelMenuModal> menuList) {
        double total_sum = 0;

        for (int i = 0; i < menuList.size(); i++) {
            total_sum = total_sum + menuList.get(i).getPrice();
        }

        return total_sum;

    }

    double total = 0;

    private void calculateTotalPrice() {
        total=0;
        ArrayList<OrderItemDetail> orderItemDetails = dishListAdapter.getOrderdMenuList();
        if (orderItemDetails != null && orderItemDetails.size() > 0) {
            for (int i = 0; i < orderItemDetails.size(); i++) {
                if (orderItemDetails.get(i).getQuantity() > 0)
                    total += (orderItemDetails.get(i).getDishUnitPrice() * orderItemDetails.get(i).getQuantity());
                else
                    total += (orderItemDetails.get(i).getDishUnitPrice());
            }
        }
        tvTotal.setText("Total: $ " +String.format("%.2f", total) );
    }

    @Override
    public void updateTotalPrice() {
        calculateTotalPrice();
    }
}
