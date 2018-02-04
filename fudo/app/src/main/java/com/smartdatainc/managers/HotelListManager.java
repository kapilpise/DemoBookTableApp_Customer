package com.smartdatainc.managers;

import android.content.Context;

import com.smartdatainc.async.ApiClient;
import com.smartdatainc.dataobject.AppInstance;
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
 * Created by aniketraut on 23/1/18.
 */

public class HotelListManager implements CallBack {

    private final static String TAG = HotelListManager.class.getSimpleName();
    Context mContext;
    Utility utilObj;
    CommunicationManager mCommunicationManager;
    ServiceRedirection serviceRedirectionObj;
    int mTasksId;
    //Retrofit Interface
    ApiInterface apiService;
    String authentication = "";// you should change Authentication according to your requirement

    public HotelListManager(Context contextObj, ServiceRedirection successRedirectionListener) {

        mContext = contextObj;
        utilObj = new Utility(contextObj);
        serviceRedirectionObj = successRedirectionListener;
        apiService = ApiClient.createService(ApiInterface.class, authentication);
    }


    public void getHotelList() {

        mCommunicationManager = new CommunicationManager(this.mContext);
        mTasksId = Constants.TaskID.GET_HOTEL_LIST_TASK_ID;
        Call call = apiService.getHotelList();
        mCommunicationManager.CallWebService(this, mTasksId, call);
    }

    @Override
    public void onResult(Response data, int tasksID, int statusCode, String message) {

        if (Constants.TaskID.GET_HOTEL_LIST_TASK_ID == tasksID) {
            if (data != null) {
                if (statusCode == ResponseCodes.Success) {
                    //AppInstance.hotelModal = (HotelModal) data.body();
                    AppInstance.hotelModal = (ArrayList<HotelModal>) data.body();

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
