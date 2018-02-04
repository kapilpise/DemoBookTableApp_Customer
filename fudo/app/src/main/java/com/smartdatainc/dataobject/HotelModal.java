package com.smartdatainc.dataobject;

/**
 * Created by roshanshendre on 23/1/18.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelModal implements Parcelable{

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
    @SerializedName("CreateDate")
    @Expose
    private String createDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public HotelModal() {
    }

    /**
     *
     * @param id
     * @param emailID
     * @param phoneNumber
     * @param address
     * @param hotelName
     * @param rating
     * @param contactPersoneName
     * @param createDate
     * @param openintHours
     * @param country
     * @param city
     */
    public HotelModal(Integer id, String hotelName, String contactPersoneName, String address, String city, String country, Integer rating, String phoneNumber, String emailID, String openintHours, String createDate) {
        super();
        this.id = id;
        this.hotelName = hotelName;
        this.contactPersoneName = contactPersoneName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
        this.openintHours = openintHours;
        this.createDate = createDate;
    }

    protected HotelModal(Parcel in) {
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
        createDate = in.readString();
    }

    public static final Creator<HotelModal> CREATOR = new Creator<HotelModal>() {
        @Override
        public HotelModal createFromParcel(Parcel in) {
            return new HotelModal(in);
        }

        @Override
        public HotelModal[] newArray(int size) {
            return new HotelModal[size];
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
        dest.writeString(createDate);
    }
}


