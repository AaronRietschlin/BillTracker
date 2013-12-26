package com.asa.billtracker.ui.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.asa.billtracker.AppData;
import com.asa.billtracker.R;
import com.asa.billtracker.ui.AsaBaseActivity;

public class ActivityHouse extends AsaBaseActivity {

    public static Intent createLaunchIntent(Context context, int from, int type){
        Intent intent = new Intent(context, ActivityHouse.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(AppData.Extras.HOUSE_FROM, from);
        intent.putExtra(AppData.Extras.HOUSE_TYPE, type);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        // Either join or add.
        int houseType = getIntent().getIntExtra(AppData.Extras.HOUSE_TYPE, -1);
        // Either reg or main
        int houseFrom = getIntent().getIntExtra(AppData.Extras.HOUSE_FROM, -1);

        if (savedInstanceState == null) {
            if (houseType == AppData.HOUSE_TYPE_ADD) {
//            addFragment(FragmentLogin.newInstance(), FragmentLogin.TAG, false, false);
            } else if (houseType == AppData.HOUSE_TYPE_JOIN) {
                addFragment(FragmentJoinHouse.newInstance(houseFrom), FragmentLogin.TAG, false, false);
            }
        }
    }

}
