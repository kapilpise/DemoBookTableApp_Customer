package com.smartdatainc.dataobject;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by roshanshendre on 24/1/18.
 */

public class OrderSearchModel implements Parcelable {

    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
    @SerializedName("OrderID")
    @Expose
    private Integer orderID;
    @SerializedName("HotelId")
    @Expose
    private Integer hotelId;
    @SerializedName("DishId")
    @Expose
    private Integer dishId;
    @SerializedName("IsApproveStatus")
    @Expose
    private Integer isApproveStatus;
    @SerializedName("ApprovalName")
    @Expose
    private String approvalName;
    @SerializedName("OrderPaymentID")
    @Expose
    private Integer orderPaymentID;
    @SerializedName("Quantity")
    @Expose
    private int quantity;

    protected OrderSearchModel(Parcel in) {
        if (in.readByte() == 0) {
            customerID = null;
        } else {
            customerID = in.readInt();
        }
        if (in.readByte() == 0) {
            orderID = null;
        } else {
            orderID = in.readInt();
        }
        if (in.readByte() == 0) {
            hotelId = null;
        } else {
            hotelId = in.readInt();
        }
        if (in.readByte() == 0) {
            dishId = null;
        } else {
            dishId = in.readInt();
        }
        if (in.readByte() == 0) {
            isApproveStatus = null;
        } else {
            isApproveStatus = in.readInt();
        }
        approvalName = in.readString();
        if (in.readByte() == 0) {
            orderPaymentID = null;
        } else {
            orderPaymentID = in.readInt();
        }
    }

    public static final Creator<OrderSearchModel> CREATOR = new Creator<OrderSearchModel>() {
        @Override
        public OrderSearchModel createFromParcel(Parcel in) {
            return new OrderSearchModel(in);
        }

        @Override
        public OrderSearchModel[] newArray(int size) {
            return new OrderSearchModel[size];
        }
    };

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getIsApproveStatus() {
        return isApproveStatus;
    }

    public void setIsApproveStatus(Integer isApproveStatus) {
        this.isApproveStatus = isApproveStatus;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public Integer getOrderPaymentID() {
        return orderPaymentID;
    }

    public void setOrderPaymentID(Integer orderPaymentID) {
        this.orderPaymentID = orderPaymentID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (customerID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(customerID);
        }
        if (orderID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orderID);
        }
        if (hotelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hotelId);
        }
        if (dishId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dishId);
        }
        if (isApproveStatus == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isApproveStatus);
        }
        dest.writeString(approvalName);
        if (orderPaymentID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(orderPaymentID);
        }
    }
}