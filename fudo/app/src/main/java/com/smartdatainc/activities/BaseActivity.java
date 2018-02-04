package com.smartdatainc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toolbar;

/**
 * Created by roshanshendre on 22/1/18.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        //Toast.makeText(MainActivity.this, "Hi", Toast.LENGTH_SHORT).show();
        return true;
    }
}