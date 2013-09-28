package com.asa.billtracker;

import android.app.Activity;
import android.content.Intent;

import com.asa.billtracker.ui.ActivityLogin;
import com.parse.ParseUser;

/**
 * Created by Aaron on 9/28/13.
 */
public class Utils {

    public static void logout(Activity context, boolean finish) {
        ParseUser.logOut();
        Intent intent = new Intent(context, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        if (finish) {
            context.finish();
        }
    }

}
