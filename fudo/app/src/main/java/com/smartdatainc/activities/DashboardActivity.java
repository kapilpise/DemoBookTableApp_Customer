package com.smartdatainc.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smartdatainc.fudo.R;
import com.smartdatainc.utils.Constants;
import com.smartdatainc.utils.Utility;

import android.provider.Settings;
import com.smartdatainc.utils.LocationUtility;
import com.smartdatainc.interfaces.DialogActionCallback;





/**
 * Created by Anurag Sethi
 */
public class DashboardActivity extends Activity {

    private Utility utilObj;
    
    LocationUtility locationUtilityObj;
      private Button btnMapObj;
    
    private Button btnSwipeRefreshObj;
    
    
    
    
    
    
    
    private Button btnRecyclerViewObj;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        initData();
        bindControls();
        
        
        
        
        
    }

    /**
     * Default method of activity life cycle to handle the actions required once the activity starts
     * checks if the network is available or not
     * @return none
     */

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

         //checking location services status
        if(!locationUtilityObj.checkLocationServiceStatus()) {
               utilObj.showAlertDialog(this,R.string.network_service_message_title,R.string.location_service_message,R.string.Ok, 0,new DialogActionCallback() {

                        @Override
                        public void doOnPositive() {
                            // Do what needs to be done on click of positive button
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                        
                        @Override
                        public void doOnNegative() {
                            // Do what needs to be done on click of negative button
                        }
                    });

        }
       
    }

    /**
     * Initializes the objects
     * @return none
     */
    private void initData() {
        utilObj = new Utility(this);
         
         locationUtilityObj = new LocationUtility(this);
      btnMapObj = (Button) findViewById(R.id.btnMap);
         
         btnSwipeRefreshObj = (Button) findViewById(R.id.btnSwipeRefresh);
         
         
         btnRecyclerViewObj = (Button) findViewById(R.id.btnRecyclerView);
         
         
         
         
         
         
    }

    /**
     * Binds the UI controls
     * @return none
     */
    private void bindControls() {

      
                
                
                
                
                
             
        
        //google map
        btnMapObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentObj = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(intentObj);
            }
        });
        
        //Swipe Refresh
        btnSwipeRefreshObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentObj = new Intent(DashboardActivity.this, SwipeRefreshActivity.class);
                startActivity(intentObj);
            }
        });
        
        
        //RecyclerView
        btnRecyclerViewObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentObj = new Intent(DashboardActivity.this, RecyclerActivity.class);
                startActivity(intentObj);
            }
        });
        
        
        
        
    }
    
    
    
    
}
