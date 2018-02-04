package com.smartdatainc.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartdatainc.dataobject.OrderSearchModel;
import com.smartdatainc.fudo.R;
import com.smartdatainc.managers.OrderManager;

import java.util.ArrayList;

/**
 * Created by aniketraut on 23/1/18.
 */

public class SearchOrderListAdapter extends RecyclerView.Adapter<SearchOrderListAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<OrderSearchModel> orderSearchModelList;
    OrderManager mOrderManager;

    public SearchOrderListAdapter(Context context, ArrayList<OrderSearchModel> orderSearchModelList, OrderManager orderManager) {
        this.context = context;
        this.orderSearchModelList = orderSearchModelList;
        layoutInflater = LayoutInflater.from(context);
        mOrderManager = orderManager;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_table_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OrderSearchModel orderSearchModel = orderSearchModelList.get(position);
        holder.hotelName.setText("Order ID :" + String.valueOf(orderSearchModel.getOrderID()));
        holder.totalTable.setText("Name :" + orderSearchModel.getApprovalName());

        String status = "NA";
        int color = Color.RED;

        if (orderSearchModel.getIsApproveStatus() == 1) {
            status = "Pending";
        } else if (orderSearchModel.getIsApproveStatus() == 0) {
            status = "Confirmed";
        } else {
            status = "Rejected";
            color = Color.GREEN;
        }
        holder.totalCapacity.setText("Order Status :" + status);
        holder.totalCapacity.setTextColor(color);


    /*    holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TableActivity
                        .class).putExtra("hotel",hotelModel.getId()));
            }
        });
*/

        holder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showCancelDialog(orderSearchModel);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void showCancelDialog(final OrderSearchModel orderSearchModel) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                context);
        alert.setTitle("Cancel Order");
        alert.setMessage("Are you sure to cancel this order?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
//                    mOrderManager.cancelOrder(orderDetailsEntities);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
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

    @Override
    public int getItemCount() {
        return orderSearchModelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView hotelName, totalTable, totalCapacity;
        LinearLayout llEditDelete;
        ImageView imgEdit, imgCancel;


        public MyViewHolder(View view) {
            super(view);
            hotelName = (TextView) view.findViewById(R.id.txtHotelName);
            totalTable = (TextView) view.findViewById(R.id.totalTable);
            totalCapacity = (TextView) view.findViewById(R.id.tableCapacity);
            llEditDelete = (LinearLayout) view.findViewById(R.id.llEditDelete);
            imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
            imgCancel = (ImageView) view.findViewById(R.id.imgCancel);
            llEditDelete.setVisibility(View.VISIBLE);
        }
    }
}
