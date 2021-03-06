package com.example.android.parseinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            goMainActivity();
        } else {
            setContentView(R.layout.activity_login);

            usernameEt = findViewById(R.id.username_et);
            passwordEt = findViewById(R.id.password_et);
            loginBtn = findViewById(R.id.login_btn);
            signupBtn = findViewById(R.id.signup_btn);

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = usernameEt.getText().toString();
                    String password = passwordEt.getText().toString();
                    login(username, password);
                }
            });
            signupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goSignUpActivity();
                }
            });
        }
    }

    private void goSignUpActivity() {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "done: Issue with login");
                    e.printStackTrace();
                    return;
                }
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
