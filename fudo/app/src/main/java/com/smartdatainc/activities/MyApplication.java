package com.smartdatainc.activities;

import android.app.Application;

import com.smartdatainc.utils.Constants;



import android.support.multidex.MultiDex;import android.content.Context;

/**
 * Created by Anurag Sethi
 * The class will start once the application will start and will set the Splunk Key for handling
 * Bugsense issues 
 */

public class MyApplication extends Application {

   
   
   
   
    @Override
    public void onCreate() {
        super.onCreate();
        
        
    }
    
    
    
    
    
   
    
   // this is used for lower version devices
     @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    
}
