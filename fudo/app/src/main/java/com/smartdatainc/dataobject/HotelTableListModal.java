package com.smartdatainc.dataobject;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by roshanshendre on 30/1/18.
 */

public class HotelTableListModal implements Parcelable {

    @SerializedName("Id")
@Expose
private Integer id;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("TotalTable")
    @Expose
    private Integer totalTable;
    @SerializedName("TableCapacity")
    @Expose
    private Integer tableCapacity;
    @SerializedName("ISBooked")
    @Expose
    private Boolean iSBooked;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;

    protected HotelTableListModal(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        hotelName = in.readString();
        if (in.readByte() == 0) {
            totalTable = null;
        } else {
            totalTable = in.readInt();
        }
        if (in.readByte() == 0) {
            tableCapacity = null;
        } else {
            tableCapacity = in.readInt();
        }
        byte tmpISBooked = in.readByte();
        iSBooked = tmpISBooked == 0 ? null : tmpISBooked == 1;
        createDate = in.readString();
    }

    public static final Creator<HotelTableListModal> CREATOR = new Creator<HotelTableListModal>() {
        @Override
        public HotelTableListModal createFromParcel(Parcel in) {
            return new HotelTableListModal(in);
        }

        @Override
        public HotelTableListModal[] newArray(int size) {
            return new HotelTableListModal[size];
        }
    };

    public Boolean getiSBooked() {
        return iSBooked;
    }

    public void setiSBooked(Boolean iSBooked) {
        this.iSBooked = iSBooked;
    }

    /**

     * No args constructor for use in serialization
     *
     */
    public HotelTableListModal() {
    }

    /**
     *
     * @param id
     * @param tableCapacity
     * @param hotelName
     * @param createDate
     * @param totalTable
     * @param iSBooked
     */
    public HotelTableListModal(Integer id, String hotelName, Integer totalTable, Integer tableCapacity, Boolean iSBooked, String createDate) {
        super();
        this.id = id;
        this.hotelName = hotelName;
        this.totalTable = totalTable;
        this.tableCapacity = tableCapacity;
        this.iSBooked = iSBooked;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getTotalTable() {
        return totalTable;
    }

    public void setTotalTable(Integer totalTable) {
        this.totalTable = totalTable;
    }

    public Integer getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(Integer tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public Boolean getISBooked() {
        return iSBooked;
    }

    public void setISBooked(Boolean iSBooked) {
        this.iSBooked = iSBooked;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(hotelName);
        if (totalTable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalTable);
        }
        if (tableCapacity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(tableCapacity);
        }
        dest.writeByte((byte) (iSBooked == null ? 0 : iSBooked ? 1 : 2));
        dest.writeString(createDate);
    }
}
