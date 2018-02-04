package com.smartdatainc.dataobject;

/**
 * Created by roshanshendre on 23/1/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderDetailsEntities implements Parcelable {

    @SerializedName("HotelId")
    @Expose
    private Integer HotelId;

    @SerializedName("TableID")
    @Expose
    private Integer TableID;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @SerializedName("CustomerName")
    @Expose
    private String customerName;

    @SerializedName("TotalQuantity")
    @Expose
    private Integer TotalQuantity;

    @SerializedName("MenuList")
    @Expose
    private ArrayList<HotelMenuModal> hotelMenuModal;

    @SerializedName("EmailID")
    @Expose
    private String EmailID;

    @SerializedName("TotalAmount")
    @Expose
    private double TotalAmount;

    public int getIsApproveStatus() {
        return IsApproveStatus;
    }

    public void setIsApproveStatus(int isApproveStatus) {
        IsApproveStatus = isApproveStatus;
    }

    @SerializedName("IsApproveStatus")
    @Expose
    private int IsApproveStatus;

    public OrderDetailsEntities() {
    }

    public OrderDetailsEntities(Integer hotelId, Integer tableID, Integer totalQuantity, ArrayList<HotelMenuModal> hotelMenuModal, String emailID, double totalAmount) {
        HotelId = hotelId;
        TableID = tableID;
        TotalQuantity = totalQuantity;
        this.hotelMenuModal = hotelMenuModal;
        EmailID = emailID;
        TotalAmount = totalAmount;
    }

    protected OrderDetailsEntities(Parcel in) {
        if (in.readByte() == 0) {
            HotelId = null;
        } else {
            HotelId = in.readInt();
        }
        if (in.readByte() == 0) {
            TableID = null;
        } else {
            TableID = in.readInt();
        }
        if (in.readByte() == 0) {
            TotalQuantity = null;
        } else {
            TotalQuantity = in.readInt();
        }
        hotelMenuModal = in.createTypedArrayList(HotelMenuModal.CREATOR);
        EmailID = in.readString();
        TotalAmount = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (HotelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(HotelId);
        }
        if (TableID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(TableID);
        }
        if (TotalQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(TotalQuantity);
        }
        dest.writeTypedList(hotelMenuModal);
        dest.writeString(EmailID);
        dest.writeDouble(TotalAmount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderDetailsEntities> CREATOR = new Creator<OrderDetailsEntities>() {
        @Override
        public OrderDetailsEntities createFromParcel(Parcel in) {
            return new OrderDetailsEntities(in);
        }

        @Override
        public OrderDetailsEntities[] newArray(int size) {
            return new OrderDetailsEntities[size];
        }
    };

    public Integer getHotelId() {
        return HotelId;
    }

    public void setHotelId(Integer hotelId) {
        HotelId = hotelId;
    }

    public Integer getTableID() {
        return TableID;
    }

    public void setTableID(Integer tableID) {
        TableID = tableID;
    }

    public Integer getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public ArrayList<HotelMenuModal> getHotelMenuModal() {
        return hotelMenuModal;
    }

    public void setHotelMenuModal(ArrayList<HotelMenuModal> hotelMenuModal) {
        this.hotelMenuModal = hotelMenuModal;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }
}