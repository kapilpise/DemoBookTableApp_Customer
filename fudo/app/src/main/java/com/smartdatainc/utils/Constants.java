package com.smartdatainc.utils;

/**
 * Created by Anurag Sethi
 * The class handles the constants used in the application
 */
public class Constants {

    /**
     * Handles the SplashScreen constants
     */
    public static class SplashScreen {
        /**
         * The parameter is used to manage the splash screen delay
         */
        public static int SPLASH_DELAY_LENGTH = 3000;
    }


    /**
     * Handles webservice constants
     */
    public static class WebServices {

        public static String WS_IP_ADDRESS = "";
        public static String WS_BASE_URL = "http://108.168.203.227/bookmytable/api/Hotel/";

        public static final String WS_GET_HOTEL_LIST = "GetRestaurantDetailsList";

        //public static final String WS_GET_TABLE_LIST =  "GetRestaurantTablesDetailsList";
        public static final String WS_GET_TABLE_LIST = "GetRestaurantTablesDetailsList";

        public static final String WS_USER_AUTHENTICATION = "users/users/login";

        public static final String WS_SWIPE_REFRESH_EXAMPLE_URL = "swipe/swipe/swipeRefreshMovieList";

        public static final String WS_POST_ORDER = "AddTablesAndOrderBooking";

        public static final String WS_GET_SEARCH_ORDER = "GetOrderDetails";

        public static final String WS_HOTEL_LIST = "GetRestaurantDetailsList";
        public static final String WS_MENU_CARD_DETAILS = "GetRestaurantMenuCardDetailsList";

        public static final String WS_POST_CANCEL_ORDER = "CancelOrder";
        public static final String WS_POST_UPDATE_ORDER = "UpdateExistingOrder";
    }

    /**
     * Handles the TaskIDs so as to differentiate the web service return values
     */

    public static class TaskID {


        public static int LOGIN_TASK_ID = 100;

        public static int FORGOT_PASSWORD_TASK_ID = 101;

        public static int SWIPE_REFRESH_TASK_ID = 105;

        public static int GET_HOTEL_LIST_TASK_ID = 201;

        public static int GET_HOTEL_TABLE_LIST_TASK_ID = 202;

        public static int GET_HOTEL_MENU_LIST_TASK_ID = 203;

        public static int POST_CONFIRM_ORDER = 204;

        public static int GET_ORDER_SEARCH = 205;

        public static int CANCEL_ORDER = 306;
        public static final int MODIFY_ORDER = 307;

    }

    /**
     * Handles the ButtonTags so as to differentiate them in showAlertDialog()
     */

    public static class ButtonTags {

        public static String TAG_NETWORK_SERVICE_ENABLE = "network services";
        public static String TAG_LOCATION_SERVICE_ENABLE = "location services";
    }

    /**
     * Handles the JSON Parsing
     */
    public static class JsonParsing {
        public static int PARSING_JSON_FOR_MESSAGE_ID = 1;
        public static int PARSING_JSON_FOR_RESULT = 2;
    }

    /**
     * Handles the DebugLog constants
     */
    public static class DebugLog {
        /**
         * Will be the name of the project
         */
        public static String APP_TAG = "fudo";
        /**
         * APP_MODE = 1 means Debug Mode
         * APP_MODE = 0 means Live Mode
         * Must change to 0 when going live
         */
        public static int APP_MODE = 1;
        /**
         * Name of the directory in which log file needs to be saved
         */
        public static String APP_ERROR_DIR_NAME = "fudo";
        /**
         * Name of the log file
         */
        public static String APP_ERROR_LOG_FILE_NAME = "log.txt";
    }

    /**
     * Handles the constant for Google Play Services
     */
    public static class GooglePlayService {
        public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    }

    /**
     * Handles the requestCodes
     */
    public static class RequestCodes {


    }


}
