package com.asa.billtracker.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.asa.billtracker.R;
import com.asa.billtracker.ui.AsaBaseFragment;
import com.asa.billtracker.ui.MainActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by aaron on 11/15/13.
 */
public class FragmentRegister extends AsaBaseFragment {
    public static final String TAG = "FragmentRegister";

    @InjectView(R.id.btn_positive)
    TextView mBtnRegister;
    @InjectView(R.id.btn_negative)
    TextView mBtnNegative;
    @InjectView(R.id.reg_field_email)
    EditText mFieldEmail;
    @InjectView(R.id.reg_field_password)
    EditText mFieldPassword;
    @InjectView(R.id.reg_field_password_confirm)
    EditText mFieldPasswordConfirm;

    public FragmentRegister() {
    }

    public static FragmentRegister newInstance() {
        FragmentRegister frag = new FragmentRegister();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.inject(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnNegative.setVisibility(View.GONE);
        mBtnRegister.setText(R.string.login_register);
    }

    private void registerToParse(String email, String password) {
        ParseUser user = new ParseUser();
        user.setEmail(email);
        user.setUsername(email);
        user.setPassword(password);
        mActivity.setActionBarProgressVisibility(true);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    startActivity(new Intent(mActivity, MainActivity.class));
                    mActivity.finish();
                } else {
                    // TODO - Failure
                }
                mActivity.setActionBarProgressVisibility(false);
            }
        });
    }

    private void loginToParse(String username, String password) {
        mActivity.setActionBarProgressVisibility(true);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    // TODO - LOGGED IN
                    startActivity(new Intent(mActivity, MainActivity.class));
                    mActivity.finish();
                }
                mActivity.setActionBarProgressVisibility(false);
            }
        });
    }

    @OnClick(R.id.btn_positive)
    public void registerClicked() {
        String email = mFieldEmail.getText().toString();
        String password = mFieldPassword.getText().toString();
        String confirmPassword = mFieldPasswordConfirm.getText().toString();
        if (checkIfCanProceed(email, password, confirmPassword)) {
            registerToParse(email, password);
        }
    }

    private boolean checkIfCanProceed(String email, String password, String confirmPassword) {
        View focusView = null;
        // Check to make sure none of the fields are empty.
        if (TextUtils.isEmpty(confirmPassword)) {
            mFieldPasswordConfirm.setError(getString(R.string.login_error_password_confirm_req));
            focusView = mFieldPasswordConfirm;
        }
        if (TextUtils.isEmpty(password)) {
            mFieldPassword.setError(getString(R.string.login_error_password_req));
            focusView = mFieldPassword;
        }
        if (TextUtils.isEmpty(email)) {
            mFieldEmail.setError(getString(R.string.login_error_email_req));
            focusView = mFieldEmail;
        }

        if (!TextUtils.isEmpty(confirmPassword) && !TextUtils.isEmpty(password) && !TextUtils.equals(confirmPassword, password)) {
            mFieldPassword.setError(getString(R.string.login_error_password_no_match));
            focusView = mFieldPassword;
        }

        if (focusView != null) {
            focusView.requestFocus();
            return false;
        }
        return true;
    }

}
