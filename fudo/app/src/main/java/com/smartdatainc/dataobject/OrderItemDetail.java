package com.smartdatainc.dataobject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kapilpise on 3/2/18.
 */

public class OrderItemDetail  implements Serializable {
    @SerializedName("OrderId")
    @Expose
    private Integer orderId;
    @SerializedName("TableId")
    @Expose
    private Integer tableId;
    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
    @SerializedName("HotelId")
    @Expose
    private Integer hotelId;
    @SerializedName("DishId")
    @Expose
    private Integer dishId;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("DishName")
    @Expose
    private String dishName;
    @SerializedName("DishUnitPrice")
    @Expose
    private Double dishUnitPrice;
    @SerializedName("DishTotalAmount")
    @Expose
    private Double dishTotalAmount;
    @SerializedName("IsVeg")
    @Expose
    private Integer isVeg;
    @SerializedName("DishAmount")
    @Expose
    private Integer dishAmount;
    @SerializedName("ImagePath")
    @Expose
    private String imagePath;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Double getDishUnitPrice() {
        return dishUnitPrice;
    }

    public void setDishUnitPrice(Double dishUnitPrice) {
        this.dishUnitPrice = dishUnitPrice;
    }

    public Double getDishTotalAmount() {
        return dishTotalAmount;
    }

    public void setDishTotalAmount(Double dishTotalAmount) {
        this.dishTotalAmount = dishTotalAmount;
    }

    public Integer getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Integer isVeg) {
        this.isVeg = isVeg;
    }

    public Integer getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(Integer dishAmount) {
        this.dishAmount = dishAmount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
