package com.asa.billtracker.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.asa.billtracker.AppData;
import com.asa.billtracker.AsaBaseAdapter;
import com.asa.billtracker.BillApplication;
import com.asa.billtracker.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.InjectView;
import butterknife.Views;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Aaron on 9/28/13.
 */
public class FragmentBillsAll extends AsaBaseFragment {
    public static final String TAG = "FragmentBillsAll";
    @InjectView(R.id.bills_list)
    ListView mList;
    @InjectView(android.R.id.empty)
    TextView mEmptyView;

    private BillsAdapter mAdapter;

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
        createAdapterIfNecessary();
        getBills();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivityForResult(new Intent(mActivity, ActivityBillAdd.class), AppData.ActivityResult.ADD_BILL);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == AppData.ActivityResult.ADD_BILL) {
            Crouton.makeText(mActivity, mActivity.getString(R.string.bill_add_result_success), Style.CONFIRM).show();
            getBills();
        }
    }

    private void getBills() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(AppData.ParseTables.BILLS);
        query.whereEqualTo("debug", BillApplication.DEBUG);
        mActivity.setActionBarProgressVisibility(true);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e == null){
                    createAdapterIfNecessary();
                    mAdapter.addAll(parseObjects, true, true);
                }else{
                    Crouton.makeText(mActivity, "There was an error retrieving your bills! :(", Style.ALERT).show();
                }
                mActivity.setActionBarProgressVisibility(false);
            }
        });
    }

    private void createAdapterIfNecessary(){
        if(mAdapter == null){
            mAdapter = new BillsAdapter(mActivity);
            mList.setAdapter(mAdapter);
        }
        if(mList.getAdapter() == null){
            mList.setAdapter(mAdapter);
        }
    }

    static class ViewHolder {
        TextView amount;
        TextView category;
        TextView date;
    }

    private class BillsAdapter extends AsaBaseAdapter<ParseObject> {

        public BillsAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_bill, parent, false);
                holder = new ViewHolder();
                holder.amount = (TextView) convertView.findViewById(R.id.item_bill_tv_amount);
                holder.category = (TextView) convertView.findViewById(R.id.item_bill_tv_category);
                holder.date = (TextView) convertView.findViewById(R.id.item_bill_tv_date);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ParseObject o = items.get(position);
            holder.amount.setText("$" + o.getNumber("amount"));
            String categoryName = o.getString("category");
            holder.category.setText(categoryName);

            return convertView;
        }
    }

}
