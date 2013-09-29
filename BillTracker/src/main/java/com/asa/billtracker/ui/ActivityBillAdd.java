package com.asa.billtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.asa.billtracker.R;
import com.asa.billtracker.Utils;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class ActivityBillAdd extends AsaBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        if (savedInstanceState == null) {
            addFragment(FragmentBillsAdd.newInstance(), FragmentBillsAdd.TAG, false, false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Utils.logout(this, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
