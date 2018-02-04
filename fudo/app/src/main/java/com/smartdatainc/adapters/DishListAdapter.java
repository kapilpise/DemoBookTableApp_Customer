package com.smartdatainc.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartdatainc.activities.DishListActivity;
import com.smartdatainc.dataobject.HotelMenuModal;
import com.smartdatainc.fudo.R;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.Utility;

import java.util.ArrayList;

/**
 * Created by aniketraut on 23/1/18.
 */

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<HotelMenuModal> dishModels;
    private ArrayList<HotelMenuModal> orderdMenuList = new ArrayList<>();
    private IUpdatePrice iUpdatePrice;

    public DishListAdapter(Context context, ArrayList<HotelMenuModal> dishModels) {
        this.context = context;
        this.dishModels = dishModels;
        layoutInflater = LayoutInflater.from(context);
        iUpdatePrice = (DishListActivity) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_dish, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final HotelMenuModal dishModel = dishModels.get(position);

        holder.dishName.setText(dishModel.getMenuName());
        holder.dishDescription.setText(dishModel.getMenuDescription());
        holder.dishPrice.setText("$ " + dishModel.getPrice());

//        if (position % 2 == 0) {
//            Utility.loadImageDrawable(context, holder.mImageView, R.drawable.restaurant_first);
//        } else {
//            Utility.loadImageDrawable(context, holder.mImageView, R.drawable.restaurant_second);
//        }

        if(dishModel.getImagePath()!=null&&! dishModel.getImagePath().isEmpty()){
            String url= Constants.WebServices.WS_IMAGE_BASE_URL+dishModel.getImagePath().split("/Content/")[1];
            Utility.loadImageFromUrl(context, holder.mImageView, android.R.color.darker_gray,url);
        }
        else{
            Utility.loadImageDrawable(context, holder.mImageView, R.drawable.restaurant_first);
        }

        if (dishModel.getIsVeg() == 1) {
            holder.mImgdishType.setImageResource(R.drawable.icn_veg);
        } else {
            holder.mImgdishType.setImageResource(R.drawable.icn_nveg);
        }

        holder.tvQuantity.setText("" + dishModel.getQuantity());
        float totalPrice = 0;
        if (dishModel.getQuantity() > 0) {
            totalPrice = dishModel.getPrice() * dishModel.getQuantity();
            holder.tvTotalOfItem.setVisibility(View.VISIBLE);
        } else {
            holder.tvTotalOfItem.setVisibility(View.GONE);
        }
        holder.tvTotalOfItem.setText("Item total price: $" + totalPrice);

        holder.addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = holder.addDish.getText().toString();
                if (str.equalsIgnoreCase("Add")) {
                    holder.addDish.setText("Remove");
                    dishModel.setIsSelected(true);

//                    orderdMenuList.add(position, dishModel);

                } else {
                    holder.addDish.setText("Add");
                    dishModel.setIsSelected(false);
//                    orderdMenuList.remove(position);
                }
                notifyDataSetChanged();
            }
        });

        holder.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dishModel.getQuantity() >= 0) {
                    dishModel.setQuantity(dishModel.getQuantity() + 1);
                    notifyDataSetChanged();
                } else if (dishModel.getQuantity() == 1) {
//                    showRemoveDialog(dishModel);
                }
                if (iUpdatePrice != null)
                    iUpdatePrice.updateTotalPrice();
            }
        });


        holder.tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dishModel.getQuantity() > 1) {
                    dishModel.setQuantity(dishModel.getQuantity() - 1);
                    notifyDataSetChanged();
                    if (iUpdatePrice != null)
                        iUpdatePrice.updateTotalPrice();
                } else if (dishModel.getQuantity() == 1) {
                    showRemoveDialog(dishModel);
//                    String str = holder.addDish.getText().toString();
//                    if (!str.equalsIgnoreCase("Add")) {
//                        holder.addDish.setText("Add");
//                        orderdMenuList.remove(position);
//                    } else {
//
//                    }
                }

            }
        });
    }

    public void filterList(ArrayList<HotelMenuModal> dishModels) {
        this.dishModels = dishModels;
        notifyDataSetChanged();
    }

    private void showRemoveDialog(final HotelMenuModal orderItemDetail) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                context);
        alert.setTitle("Remove!!");
        alert.setMessage("Are you sure to remove this item");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    orderItemDetail.setQuantity(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
                if (iUpdatePrice != null)
                    iUpdatePrice.updateTotalPrice();
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
        return dishModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dishName, dishDescription, dishPrice, addDish;
        private ImageView mImageView, mImgdishType;
        public TextView tvQuantity;
        public TextView tvTotalOfItem;
        public TextView tvAdd;
        public TextView tvRemove;


        public MyViewHolder(View view) {
            super(view);
            dishName = (TextView) view.findViewById(R.id.dish_name);
            dishDescription = (TextView) view.findViewById(R.id.dish_desc);
            dishPrice = (TextView) view.findViewById(R.id.dish_price);
            addDish = (TextView) view.findViewById(R.id.add_dish);
            addDish.setVisibility(View.GONE);
            mImgdishType = (ImageView) view.findViewById(R.id.menu_type);
            mImageView = (ImageView) view.findViewById(R.id.dish_image);
            tvQuantity = (TextView) view.findViewById(R.id.txtQuantity);
            tvTotalOfItem = (TextView) view.findViewById(R.id.tvTotalOfItem);
            tvAdd = (TextView) view.findViewById(R.id.txtAdd);
            tvRemove = (TextView) view.findViewById(R.id.txtRemove);

        }
    }


    public interface IUpdatePrice {
        void updateTotalPrice();
    }

    public ArrayList<HotelMenuModal> getOrderdMenuList() {

        ArrayList<HotelMenuModal> hotelMenuModals = new ArrayList<>();

        for (int i = 0; i < dishModels.size(); i++) {
            if (dishModels.get(i).getQuantity() > 0) {
                hotelMenuModals.add(dishModels.get(i));
            }
        }
        return hotelMenuModals;
    }
}
