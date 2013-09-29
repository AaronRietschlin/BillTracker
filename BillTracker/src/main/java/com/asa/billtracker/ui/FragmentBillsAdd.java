package com.asa.billtracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
public class FragmentBillsAdd extends AsaBaseFragment {
    public static final String TAG = "FragmentBillsAll";

    @InjectView(R.id.bills_list)
    ListView mList;
    @InjectView(android.R.id.empty)
    TextView mEmptyView;

    public FragmentBillsAdd() {
    }

    public static FragmentBillsAdd newInstance() {
        FragmentBillsAdd frag = new FragmentBillsAdd();
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
}
