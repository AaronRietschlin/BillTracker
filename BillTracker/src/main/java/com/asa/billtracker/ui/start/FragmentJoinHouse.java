package com.asa.billtracker.ui.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asa.billtracker.R;
import com.asa.billtracker.ui.AsaBaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Aaron on 9/28/13.
 */
public class FragmentJoinHouse extends AsaBaseFragment {
    public static final String TAG = "FragmentJoinHouse";
    @InjectView(R.id.btn_positive)
    Button mBtnLogin;
    @InjectView(R.id.login_btn_register)
    TextView mBtnRegister;
    @InjectView(R.id.login_field_password)
    EditText mFieldPassword;

    public FragmentJoinHouse() {
    }

    public static FragmentJoinHouse newInstance() {
        FragmentJoinHouse frag = new FragmentJoinHouse();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_house_join, container, false);
        ButterKnife.inject(this, v);

        return v;
    }

}
