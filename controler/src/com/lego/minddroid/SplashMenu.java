

package com.lego.minddroid;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class SplashMenu extends Activity {


    public static final String MINDDROID_PREFS = "Mprefs";
    public static final String MINDDROID_ROBOT_TYPE = "MrobotType";



    public static void quitApplication() {
     
        splashMenu.finish();

    }

    private View splashMenuView;

    private static Activity splashMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     
       
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      
        splashMenuView = new SplashMenuView(getApplicationContext(), this);
        setContentView(splashMenuView);
        splashMenu = this;
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onPause() {
     
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }







}
