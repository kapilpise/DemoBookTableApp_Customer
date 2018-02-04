package com.smartdatainc.dataobject;

import com.smartdatainc.utils.Constants;

import debug.LogUtility;

import java.util.ArrayList;
                                import java.util.List;

/**
 * Created by Anurag Sethi
 * The class is defined on single instance.
 */
public class AppInstance {

    private static AppInstance appInstance = null;
    public static LogUtility logObj;
    public static ArrayList<HotelTableModal> hotelTableModal;
    public static ArrayList<HotelTableListModal> hotelTableListModal;

    public static ArrayList<HotelModal> hotelModal;
    public static ArrayList<HotelMenuModal> hotelMenuModal;
    public static ArrayList<OrderSearchModel> orderSearchModelList;
    public static OrderSearchResponseModel orderSearchResponseModelList;
    public static ArrayList<HotelMenuModal> orderSearchMenuModelList;



    public static List<SwipeRefresh> swipeRefreshListObj;
    public static String cancelOrderMessage="";
    public static String modifyOrderMessage="";

    /**
     * To initialize the appInstance Object
     * @return singleton instance
     */

    public static synchronized AppInstance getAppInstance() {
        if(appInstance == null) {
            appInstance = new AppInstance();

            /**
             * The object will manage the Resturants information
             */
            hotelModal = new ArrayList<>();


            /**
             * The object will manage the Resturants information
             */
            hotelTableModal = new ArrayList<>();

            /**
             * The object will manage the Resturants information
             */
            hotelMenuModal = new ArrayList<>();

            /**
             * The object will get search order information
             */
            orderSearchModelList = new ArrayList<>();

            /**
            * The object will manage the swipeToRefresh arrayList
            */
            swipeRefreshListObj = new ArrayList<SwipeRefresh>();

            /**
             * the object will manage the logs in the logcat
             */
            logObj = new LogUtility(Constants.DebugLog.APP_MODE, Constants.DebugLog.APP_TAG);
        }

        return appInstance;
    }

}
