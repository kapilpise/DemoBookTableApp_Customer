package com.smartdatainc.dataobject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kapilpise on 4/2/18.
 */

public class CancelOrderRequestModel implements Serializable {

    @SerializedName("OrderId")
    @Expose
    private Integer orderId;
    @SerializedName("UserType")
    @Expose
    private String userType;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
