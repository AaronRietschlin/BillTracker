package com.asa.billtracker;

import android.app.Application;

import com.asa.billtracker.ui.MainActivity;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * Created by Aaron on 9/27/13.
 */
public class BillApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(getApplicationContext(), AppData.PARSE_APP_ID, AppData.PARSE_CLIENT_ID);

        // Enabling Push Notifications
        // TODO - Change this to the launch activity
        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
