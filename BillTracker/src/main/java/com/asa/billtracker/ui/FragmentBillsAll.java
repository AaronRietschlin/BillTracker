package com.asa.billtracker.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.asa.billtracker.R;

import butterknife.InjectView;
import butterknife.Views;

/**
 * Created by Aaron on 9/28/13.
 */
public class FragmentBillsAll extends AsaBaseFragment {
    public static final String TAG = "FragmentBillsAll";

    @InjectView(R.id.bills_list)
    ListView mList;
    @InjectView(android.R.id.empty)
    TextView mEmptyView;

    public FragmentBillsAll() {
    }

    public static FragmentBillsAll newInstance() {
        FragmentBillsAll frag = new FragmentBillsAll();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bills_all, container, false);
        Views.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList.setEmptyView(mEmptyView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.bills, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(mActivity, ActivityBillAdd.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
