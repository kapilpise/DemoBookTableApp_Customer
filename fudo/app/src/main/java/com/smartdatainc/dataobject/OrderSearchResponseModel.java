package com.smartdatainc.dataobject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kapilpise on 3/2/18.
 */

public class OrderSearchResponseModel implements Serializable {
    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("OrderItemDetails")
    @Expose
    private List<OrderItemDetail> orderItemDetails = null;
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
    private Object approvalName;
    @SerializedName("TotalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("TableID")
    @Expose
    private Integer tableID;
    @SerializedName("TotalQuantity")
    @Expose
    private Integer totalQuantity;
    @SerializedName("EmailID")
    @Expose
    private String emailID;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public List<OrderItemDetail> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(List<OrderItemDetail> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
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

    public Object getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(Object approvalName) {
        this.approvalName = approvalName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
