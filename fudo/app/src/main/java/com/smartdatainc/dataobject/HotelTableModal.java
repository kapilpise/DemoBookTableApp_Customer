package com.smartdatainc.dataobject;

/**
 * Created by roshanshendre on 23/1/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelTableModal implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("ContactPersoneName")
    @Expose
    private String contactPersoneName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Rating")
    @Expose
    private Integer rating;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("EmailID")
    @Expose
    private String emailID;
    @SerializedName("OpenintHours")
    @Expose
    private String openintHours;
    @SerializedName("TotalTable")
    @Expose
    private Integer totalTable;
    @SerializedName("TableCapacity")
    @Expose
    private Integer tableCapacity;
    @SerializedName("TableAvailable")
    @Expose
    private Integer tableAvailable;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;

    protected HotelTableModal(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        hotelName = in.readString();
        contactPersoneName = in.readString();
        address = in.readString();
        city = in.readString();
        country = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readInt();
        }
        phoneNumber = in.readString();
        emailID = in.readString();
        openintHours = in.readString();
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
        if (in.readByte() == 0) {
            tableAvailable = null;
        } else {
            tableAvailable = in.readInt();
        }
        createDate = in.readString();
    }

    public static final Creator<HotelTableModal> CREATOR = new Creator<HotelTableModal>() {
        @Override
        public HotelTableModal createFromParcel(Parcel in) {
            return new HotelTableModal(in);
        }

        @Override
        public HotelTableModal[] newArray(int size) {
            return new HotelTableModal[size];
        }
    };

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

    public String getContactPersoneName() {
        return contactPersoneName;
    }

    public void setContactPersoneName(String contactPersoneName) {
        this.contactPersoneName = contactPersoneName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getOpenintHours() {
        return openintHours;
    }

    public void setOpenintHours(String openintHours) {
        this.openintHours = openintHours;
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

    public Integer getTableAvailable() {
        return tableAvailable;
    }

    public void setTableAvailable(Integer tableAvailable) {
        this.tableAvailable = tableAvailable;
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
        dest.writeString(contactPersoneName);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(country);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rating);
        }
        dest.writeString(phoneNumber);
        dest.writeString(emailID);
        dest.writeString(openintHours);
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
        if (tableAvailable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(tableAvailable);
        }
        dest.writeString(createDate);
    }
}