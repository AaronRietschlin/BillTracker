package com.asa.billtracker.ui;

import android.os.Bundle;

import com.asa.billtracker.R;

public class ActivityLogin extends AsaBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        if (savedInstanceState == null) {
            addFragment(FragmentLogin.newInstance(), FragmentLogin.TAG, false, false);
        }
    }

}
