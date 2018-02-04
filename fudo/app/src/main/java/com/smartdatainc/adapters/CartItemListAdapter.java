package com.smartdatainc.adapters;
/**
 * Created by ashutoshm on 29/8/16.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartdatainc.dataobject.RecycleView;
import com.smartdatainc.fudo.R;

import java.util.List;

public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.MyViewHolder> {

    private List<RecycleView> recyleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
       }
    }


    public CartItemListAdapter(List<RecycleView> recyleList) {
        this.recyleList = recyleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cartlist_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecycleView recyleView = recyleList.get(position);
        holder.title.setText(recyleView.getTitle());
    }

    @Override
    public int getItemCount() {
        return recyleList.size();
    }
}
