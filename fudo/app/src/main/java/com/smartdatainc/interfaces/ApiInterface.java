package com.smartdatainc.interfaces;





import com.smartdatainc.dataobject.CancelOrderRequestModel;
import com.smartdatainc.dataobject.HotelMenuModal;
import com.smartdatainc.dataobject.HotelModal;
import com.smartdatainc.dataobject.HotelTableListModal;
import com.smartdatainc.dataobject.HotelTableModal;
import com.smartdatainc.dataobject.OrderDetailsEntities;
import com.smartdatainc.dataobject.OrderSearchModel;
import com.smartdatainc.dataobject.OrderSearchResponseModel;
import com.smartdatainc.utils.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ashutoshm on 19/7/16.
 * The interface method implemented in the java(Manager) files
 */
public interface ApiInterface {

    
    
    
    
    
    /**
     * For post method  use @POST
     *  @Body User model class data
     * @return
     */

    @POST(Constants.WebServices.WS_USER_AUTHENTICATION)
    Call<HotelModal> getHotelList(@Body HotelModal user);

    @POST(Constants.WebServices.WS_POST_ORDER)
    Call<ArrayList<OrderSearchModel>> sendOrder(@Body OrderDetailsEntities orderDetailsEntities);


    @GET(Constants.WebServices.WS_GET_SEARCH_ORDER)
    Call<OrderSearchResponseModel> getSearchedOrder(@Query("orderId") int orderID, @Query("emailId") String emailId);


    @POST(Constants.WebServices.WS_GET_TABLE_LIST)
    Call<HotelTableModal> getHotelTableList(@Body HotelTableModal user);


    @GET(Constants.WebServices.WS_GET_TABLE_LIST)
    Call<ArrayList<HotelTableListModal>> getHotelTableListByHotelID(@Query("hotelId") int hotelID);


    @POST(Constants.WebServices.WS_USER_AUTHENTICATION)
    Call<HotelMenuModal> getHotelMenuList(@Body HotelMenuModal user);


    @POST(Constants.WebServices.WS_SWIPE_REFRESH_EXAMPLE_URL)
    Call<String> getSwipeRefresh(@Body String swipeRefresh);

    @GET(Constants.WebServices.WS_HOTEL_LIST)
    Call<ArrayList<HotelModal>> getHotelList();


    @GET(Constants.WebServices.WS_MENU_CARD_DETAILS)
    Call<ArrayList<HotelMenuModal>> getMenuCardDetails(@Query("hotelId") int hotelID);

    @POST(Constants.WebServices.WS_POST_CANCEL_ORDER)
    Call <String> cancelOrder(@Body CancelOrderRequestModel cancelOrderRequestModel);

    @POST(Constants.WebServices.WS_POST_UPDATE_ORDER)
    Call <String> modifyOrder(@Body OrderSearchResponseModel responseModel);
}