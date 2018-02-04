package com.smartdatainc.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartdatainc.activities.DishListActivity;
import com.smartdatainc.activities.HotelListActivity;
import com.smartdatainc.activities.TableActivity;
import com.smartdatainc.activities.TableListActivity;
import com.smartdatainc.dataobject.HotelModal;
import com.smartdatainc.fudo.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aniketraut on 23/1/18.
 */

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<HotelModal> hotelModels;

    public HotelListAdapter(Context context, ArrayList<HotelModal> hotelModels) {
        this.context = context;
        this.hotelModels = hotelModels;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_hotel, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final HotelModal hotelModel = hotelModels.get(position);
        holder.hotelName.setText(hotelModel.getHotelName());
        holder.hotelAddress.setText(hotelModel.getAddress());
        holder.openingTime.setText("Opening Hours :"+hotelModel.getOpenintHours());
        if (position % 2 == 0) {
            holder.mImageView.setBackgroundResource(R.drawable.restaurant_first);
        } else {
            holder.mImageView.setBackgroundResource(R.drawable.restaurant_second);
        }

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TableActivity
                        .class).putExtra("hotel",hotelModel));


            }
        });

    }

    @Override
    public int getItemCount() {
        return hotelModels.size();
    }

    public void filterList(ArrayList<HotelModal> hotelModels) {
        this.hotelModels = hotelModels;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView hotelName, hotelAddress, openingTime;
        private ImageView mImageView;

        public MyViewHolder(View view) {
            super(view);
            hotelName = (TextView) view.findViewById(R.id.hotel_name);
            hotelAddress = (TextView) view.findViewById(R.id.hotel_addr);
            openingTime = (TextView) view.findViewById(R.id.opening_time);
            mImageView = (ImageView) view.findViewById(R.id.image1);

        }
    }
}
