package com.asa.billtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.asa.billtracker.R;
import com.asa.billtracker.Utils;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends AsaBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        setupParse();

        if (isFinishing()) {
            return;
        }


        if (savedInstanceState == null) {
            addFragment(FragmentBillsAll.newInstance(), FragmentBillsAll.TAG, false, false);
        }
    }

    private void setupParse() {
        // TODO - THIS SHOULD GO IN THE LAUNCH ACTIVITY!
        ParseAnalytics.trackAppOpened(getIntent());

        // Check the logged in user
        ParseUser user = ParseUser.getCurrentUser();
        if (user == null) {
            // Not logged in
            startActivity(new Intent(this, ActivityLogin.class));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
