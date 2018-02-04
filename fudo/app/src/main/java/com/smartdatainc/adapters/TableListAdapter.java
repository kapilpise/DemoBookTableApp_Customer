package com.smartdatainc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartdatainc.dataobject.HotelModal;
import com.smartdatainc.dataobject.HotelTableListModal;
import com.smartdatainc.fudo.R;

import java.util.ArrayList;

/**
 * Created by aniketraut on 23/1/18.
 */

public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<HotelTableListModal> hotelTableModalList;
    private HotelModal hotelModal;

    public TableListAdapter(Context context, ArrayList<HotelTableListModal> hotelTableModalList, HotelModal hotelModal) {
        this.context = context;
        this.hotelTableModalList = hotelTableModalList;
        this.hotelModal = hotelModal;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_table_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final HotelTableListModal hotelTableModal = hotelTableModalList.get(position);
        holder.hotelName.setText(hotelModal.getHotelName());
        holder.totalCapacity.setText("Table No :" + (position + 1));

        holder.totalTable.setText("Seat Capacity: " + hotelTableModal.getTableCapacity());
    /*    holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TableActivity
                        .class).putExtra("hotel",hotelModel.getId()));
            }
        });
*/
    }

    public void filterList(ArrayList<HotelTableListModal> hotelTableModalList) {
        this.hotelTableModalList = hotelTableModalList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return hotelTableModalList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView hotelName, totalTable, totalCapacity;


        public MyViewHolder(View view) {
            super(view);
            hotelName = (TextView) view.findViewById(R.id.txtHotelName);
            hotelName.setVisibility(View.GONE);
            totalTable = (TextView) view.findViewById(R.id.totalTable);
            totalCapacity = (TextView) view.findViewById(R.id.tableCapacity);


        }
    }
}
