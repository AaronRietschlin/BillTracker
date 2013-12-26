package com.asa.billtracker.ui.start;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asa.billtracker.AppData;
import com.asa.billtracker.R;
import com.asa.billtracker.ui.AsaBaseFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Aaron on 9/28/13.
 */
public class FragmentHouseJoin extends AsaBaseFragment {
    public static final String TAG = "FragmentHouseJoin";
    @InjectView(R.id.btn_positive)
    Button mBtnJoin;
    @InjectView(R.id.btn_negative)
    Button mBtnSkip;
    @InjectView(R.id.house_join_btn_add_house)
    TextView mBtnAddHouse;
    @InjectView(R.id.house_join_field_id)
    EditText mFieldId;

    private int mFromWhereItCame;

    public FragmentHouseJoin() {
    }

    public static FragmentHouseJoin newInstance(int from) {
        FragmentHouseJoin frag = new FragmentHouseJoin();
        Bundle args = new Bundle();
        args.putInt(AppData.Extras.HOUSE_FROM, from);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args == null) {
            throw new IllegalStateException("You must pass where this fragment was entered from.");
        }

        mFromWhereItCame = args.getInt(AppData.Extras.HOUSE_FROM, -1);

        if (mFromWhereItCame == -1) {
            throw new IllegalStateException("You must pass where this fragment was entered from.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_house_join, container, false);
        ButterKnife.inject(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mFromWhereItCame == AppData.HOUSE_FROM_MAIN) {
            mBtnSkip.setVisibility(View.GONE);
        }
        mFieldId.setText("V35BYB9cRX");
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.setTitle(R.string.title_house_join);
    }

    @OnClick(R.id.btn_positive)
    public void onJoinClicked() {
        String id = mFieldId.getText().toString();
        if (TextUtils.isEmpty(id)) {
            mFieldId.setError(getString(R.string.house_join_field_error));
            mFieldId.requestFocus();
            return;
        }

        ParseQuery<ParseObject> query = new ParseQuery(AppData.ParseTables.HOUSE);
        query.whereEqualTo("objectId", id);
        mActivity.setActionBarProgressVisibility(true);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                mActivity.setActionBarProgressVisibility(false);
                if (e == null) {
                    if (parseObjects == null || parseObjects.size() == 0) {
                        Crouton.makeText(mActivity, R.string.house_error_no_houses_found, Style.ALERT).show();
                        return;
                    }
                    Log.d(TAG, "Found.");
                } else {
                    Crouton.makeText(mActivity, R.string.house_error_occurred, Style.ALERT).show();
                    Log.e(TAG, "Parse error.", e);
                }
            }
        });
    }
}
