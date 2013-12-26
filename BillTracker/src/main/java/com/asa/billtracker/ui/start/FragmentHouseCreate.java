package com.asa.billtracker.ui.start;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.asa.billtracker.R;
import com.asa.billtracker.model.House;
import com.asa.billtracker.ui.AsaBaseFragment;
import com.asa.billtracker.utils.LogUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Fragment for joining a house.
 */
public class FragmentHouseCreate extends AsaBaseFragment {
    public static final String TAG = LogUtils.makeLogTag("FragmentHouseCreate");

    @InjectView(R.id.house_create_field_name)
    EditText mFieldName;
    @InjectView(R.id.house_create_field_address)
    EditText mFieldAddress;
    @InjectView(R.id.btn_negative)
    Button mBtnSkip;

    public static FragmentHouseCreate newInstance() {
        FragmentHouseCreate frag = new FragmentHouseCreate();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_house_create, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.setTitle(R.string.title_house_create);
    }

    @OnClick(R.id.btn_positive)
    public void createClicked() {
        if (!isAdded()) {
            return;
        }
        String name = mFieldName.getText().toString();
        String address = mFieldName.getText().toString();
        View focusView = null;

        if (TextUtils.isEmpty(address)) {
            focusView = mFieldAddress;
            mFieldAddress.setError(getString(R.string.house_create_error_address_req));
        }

        if (TextUtils.isEmpty(name)) {
            focusView = mFieldName;
            mFieldName.setError(getString(R.string.house_create_error_name_req));
        }

        if (focusView != null) {
            focusView.requestFocus();
        } else {
            createHouse(name, address);
        }
    }

    private void createHouse(String name, String address) {
        House house = new House();
        house.setAddress(address);
        house.setName(name);
        ParseObject obj = house.toParseObject();
        mActivity.setActionBarProgressVisibility(true);
        obj.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Crouton.makeText(mActivity, "House created!", Style.CONFIRM).show();
                    // TODO - go to the main activity
                } else {
                    Crouton.makeText(mActivity, "An error occurred creating the houseo", Style.ALERT).show();
                    LogUtils.LOGE(TAG, "", e);
                }
                mActivity.setActionBarProgressVisibility(false);
            }
        });
    }

}
