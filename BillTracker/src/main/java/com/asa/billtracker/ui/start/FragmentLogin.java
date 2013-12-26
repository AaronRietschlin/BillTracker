package com.asa.billtracker.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.asa.billtracker.R;
import com.asa.billtracker.ui.AsaBaseFragment;
import com.asa.billtracker.ui.MainActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Aaron on 9/28/13.
 */
public class FragmentLogin extends AsaBaseFragment {
    public static final String TAG = "FragmentLogin";
    @InjectView(R.id.btn_positive)
    Button mBtnLogin;
    @InjectView(R.id.btn_negative)
    Button mBtnSkip;
    @InjectView(R.id.login_btn_register)
    TextView mBtnRegister;
    @InjectView(R.id.login_field_email)
    EditText mFieldEmail;
    @InjectView(R.id.login_field_password)
    EditText mFieldPassword;

    public FragmentLogin() {
    }

    public static FragmentLogin newInstance() {
        FragmentLogin frag = new FragmentLogin();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Views.inject(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnLogin.setText(R.string.login_login);
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

    @OnClick(R.id.login_btn_register)
    public void registerClicked() {
        mActivity.replaceFragment(FragmentRegister.newInstance(), FragmentRegister.TAG, true, true);
    }

    @OnClick(R.id.btn_positive)
    public void loginClicked() {
        String email = mFieldEmail.getText().toString();
        String password = mFieldPassword.getText().toString();
        if (checkIfCanProceed(email, password)) {
            loginToParse(email, password);
        }
    }

    @OnClick(R.id.btn_negative)
    public void skipClicked(){
        Crouton.makeText(mActivity, "You chose to skip. Unfortunately, we can't do that yet.", Style.CONFIRM).show();
    }

    private boolean checkIfCanProceed(String email, String password) {
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            mFieldPassword.setError(getString(R.string.login_error_password_req));
            focusView = mFieldPassword;
        }
        if (TextUtils.isEmpty(email)) {
            mFieldEmail.setError(getString(R.string.login_error_email_req));
            focusView = mFieldEmail;
        }
        if (focusView != null) {
            focusView.requestFocus();
            return false;
        }
        return true;
    }
}
