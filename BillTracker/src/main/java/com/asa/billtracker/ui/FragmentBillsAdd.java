package com.asa.billtracker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.asa.billtracker.R;
import com.asa.billtracker.model.Bill;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

/**
 * Created by Aaron on 9/28/13.
 */
public class FragmentBillsAdd extends AsaBaseFragment {
    public static final String TAG = "FragmentBillsAll";
    @InjectView(R.id.bills_add_spinner_category)
    Spinner mSpinnerCategories;
    @InjectView(R.id.bills_add_amount)
    EditText mFieldAmount;
    @InjectView(R.id.bills_add_btn_cancel)
    Button mBtnCancel;
    @InjectView(R.id.bills_add_btn_add_bill)
    Button mBtnAdd;
    private ArrayAdapter<CharSequence> mAdapter;

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
        View v = inflater.inflate(R.layout.fragment_bills_add, container, false);
        Views.inject(this, v);

        return v;
    }

    private void setupSpinner() {
        mAdapter = ArrayAdapter.createFromResource(mActivity, R.array.categories, android.R.layout.simple_list_item_1);
        mSpinnerCategories.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSpinner();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @OnClick(R.id.bills_add_btn_cancel)
    public void cancelClicked() {
        mActivity.setResult(Activity.RESULT_CANCELED);
        mActivity.finish();
    }

    @OnClick(R.id.bills_add_btn_add_bill)
    public void addClicked() {
        String amountStr = mFieldAmount.getText().toString();
        if (TextUtils.isEmpty(amountStr)) {
            return;
        }
        double amount = 0;
        try {
            amount = Double.valueOf(amountStr);
        } catch (NumberFormatException e) {
            return;
        }
        String category = (String) mSpinnerCategories.getSelectedItem();
        category = category.toLowerCase();
        Bill bill = new Bill();
        bill.setCategory(category);
        bill.setAmount(amount);

        ParseObject o = bill.toParseObject();
        o.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    // TODO - Good!
                    mActivity.setResult(Activity.RESULT_OK);
                    mActivity.finish();
                }else{
                    // TODO - fail
                }
            }
        });
    }
}
