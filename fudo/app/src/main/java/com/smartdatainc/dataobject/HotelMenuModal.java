package com.smartdatainc.dataobject;

/**
 * Created by roshanshendre on 23/1/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HotelMenuModal implements Parcelable {

    @SerializedName("HotelId")
    @Expose
    private Integer hotelId;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("MenuID")
    @Expose
    private Integer menuID;
    @SerializedName("MenuName")
    @Expose
    private String menuName;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @SerializedName("ImagePath")
    @Expose
    private String imagePath;
    private boolean isSelected;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @SerializedName("Quantity")
    @Expose
    private int quantity =0;
    @SerializedName("MenuDescription")
    @Expose
    private String menuDescription;
    @SerializedName("IsVeg")
    @Expose
    private Integer isVeg;
    @SerializedName("IsAvailable")
    @Expose
    private Integer isAvailable;
    @SerializedName("Price")
    @Expose
    private float price;
    @SerializedName("DisCount")
    @Expose
    private Integer disCount;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;

    protected HotelMenuModal(Parcel in) {
        if (in.readByte() == 0) {
            hotelId = null;
        } else {
            hotelId = in.readInt();
        }
        if (in.readByte() == 0) {
            quantity = 1;
        } else {
            quantity = in.readInt();
        }
        hotelName = in.readString();
        if (in.readByte() == 0) {
            menuID = null;
        } else {
            menuID = in.readInt();
        }
        menuName = in.readString();
        menuDescription = in.readString();
        if (in.readByte() == 0) {
            isVeg = null;
        } else {
            isVeg = in.readInt();
        }
        if (in.readByte() == 0) {
            isAvailable = null;
        } else {
            isAvailable = in.readInt();
        }
        if (in.readByte() == 0) {
            price = 0;
        } else {
            price = in.readInt();
        }
        if (in.readByte() == 0) {
            disCount = null;
        } else {
            disCount = in.readInt();
        }
        createDate = in.readString();
    }

    public static final Creator<HotelMenuModal> CREATOR = new Creator<HotelMenuModal>() {
        @Override
        public HotelMenuModal createFromParcel(Parcel in) {
            return new HotelMenuModal(in);
        }

        @Override
        public HotelMenuModal[] newArray(int size) {
            return new HotelMenuModal[size];
        }
    };

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public Integer getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Integer isVeg) {
        this.isVeg = isVeg;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDisCount() {
        return disCount;
    }

    public void setDisCount(Integer disCount) {
        this.disCount = disCount;
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
        if (hotelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(hotelId);
        }
        if (quantity==0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        dest.writeString(hotelName);
        if (menuID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(menuID);
        }
        dest.writeString(menuName);
        dest.writeString(menuDescription);
        if (isVeg == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isVeg);
        }
        if (isAvailable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isAvailable);
        }
        if (price == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(price);
        }
        if (disCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(disCount);
        }
        dest.writeString(createDate);
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected= isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}

