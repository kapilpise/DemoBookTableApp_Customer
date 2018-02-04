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
 * Created by Anurag Sethi
 * The class will handle all the implementations related to the login operations
 */
public class HotelManager implements CallBack {

    Context context;
    Utility utilObj;
    CommunicationManager commObj;
    ServiceRedirection serviceRedirectionObj;
    int tasksID;

      //Retrofit Interface
    ApiInterface apiService;
    String authentication="12345"; // you should change Authentication according to your requirement


    /**
     * Constructor
     * @param contextObj  The Context from where the method is called
     * @param successRedirectionListener The listener interface for receiving action events
     * @return none
     */
    public HotelManager(Context contextObj, ServiceRedirection successRedirectionListener) {
        context = contextObj;
        utilObj = new Utility(contextObj);
        serviceRedirectionObj = successRedirectionListener;
        
         apiService = ApiClient.createService(ApiInterface.class, authentication);
    }

    /**
     * Calls the Web Service of authenticateLogin
     * @param hotelModal  having the required data
     * @return none
     */
    public void authenticateLogin(HotelModal hotelModal) {
        commObj = new CommunicationManager(this.context);
        tasksID = Constants.TaskID.GET_HOTEL_LIST_TASK_ID;

        Call call = apiService.getHotelList(hotelModal);
        commObj.CallWebService(this,tasksID,call);
    }




    
    /**
     * The interface method implemented in the java files
     *
     * @param data the result returned by the web service
     * @param tasksID the ID to differential multiple webservice calls
     * @param statusCode the statusCode returned by the web service
     * @param message the message returned by the web service
     * @return none
     * @since 2014-08-28
     */


    @Override
    public void onResult(Response data, int tasksID, int statusCode, String message) {


        if(tasksID == Constants.TaskID.GET_HOTEL_LIST_TASK_ID) {
            if (data != null) {
                if(statusCode== ResponseCodes.Success) {
                    AppInstance.hotelModal = (ArrayList<HotelModal>) data.body();

                    serviceRedirectionObj.onSuccessRedirection(tasksID);
                }else{
                    serviceRedirectionObj.onFailureRedirection(message);
                }
            }
            else{
                serviceRedirectionObj.onFailureRedirection(message);
            }
        }

        else if(tasksID == Constants.TaskID.FORGOT_PASSWORD_TASK_ID) {
            if (data != null) {

                if(statusCode== ResponseCodes.Success) {

                }else{
                    serviceRedirectionObj.onFailureRedirection(message);
                }

            }
            else{
                serviceRedirectionObj.onFailureRedirection(message);
            }

        }
    }
}
