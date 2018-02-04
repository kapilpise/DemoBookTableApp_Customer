package com.smartdatainc.managers;

import android.content.Context;

import com.smartdatainc.async.ApiClient;
import com.smartdatainc.dataobject.AppInstance;
import com.smartdatainc.dataobject.HotelMenuModal;
import com.smartdatainc.dataobject.HotelModal;
import com.smartdatainc.interfaces.ApiInterface;
import com.smartdatainc.interfaces.ServiceRedirection;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.ResponseCodes;
import com.smartdatainc.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import sdei.support.lib.interfaces.CallBack;

/**
 * Created by Anurag Sethi
 * The class will handle all the implementations related to the login operations
 */
public class HotelMenuManager implements CallBack {

    private final static String TAG = HotelListManager.class.getSimpleName();
    Context mContext;
    Utility utilObj;
    CommunicationManager mCommunicationManager;
    ServiceRedirection serviceRedirectionObj;
    int mTasksId;
    //Retrofit Interface
    ApiInterface apiService;
    String authentication = "";// you should change Authentication according to your requirement

    public HotelMenuManager(Context contextObj, ServiceRedirection successRedirectionListener) {

        mContext = contextObj;
        utilObj = new Utility(contextObj);
        serviceRedirectionObj = successRedirectionListener;
        apiService = ApiClient.createService(ApiInterface.class, authentication);
    }


    public void getHotelMenuList(HotelModal hotelModal) {

        mCommunicationManager = new CommunicationManager(this.mContext);
        mTasksId = Constants.TaskID.GET_HOTEL_MENU_LIST_TASK_ID;
        Call call = apiService.getMenuCardDetails(hotelModal.getId());
        mCommunicationManager.CallWebService(this, mTasksId, call);
    }

    @Override
    public void onResult(Response data, int tasksID, int statusCode, String message) {

        if (Constants.TaskID.GET_HOTEL_MENU_LIST_TASK_ID == tasksID) {
            if (data != null) {
                if (statusCode == ResponseCodes.Success) {
                    //AppInstance.hotelModal = (HotelModal) data.body();
                    AppInstance.hotelMenuModal = (ArrayList<HotelMenuModal>) data.body();

                    serviceRedirectionObj.onSuccessRedirection(tasksID);
                } else {
                    serviceRedirectionObj.onFailureRedirection(message);
                }
            } else {
                serviceRedirectionObj.onFailureRedirection(message);
            }
        }

    }
}
