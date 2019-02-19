package com.example.android.parseinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    private EditText newUsernameEt;
    private EditText newPasswordEt;
    private EditText newEmailEt;
    private Button submitBtn;
    private Button clearBtn;

    //private ParseUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        newUsernameEt = findViewById(R.id.newUsername_et);
        newPasswordEt = findViewById(R.id.newPassword_et);
        newEmailEt = findViewById(R.id.newEmail_et);
        submitBtn = findViewById(R.id.submit_btn);
        clearBtn = findViewById(R.id.clear_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Submit: Submission done!");
                String newUsername = newUsernameEt.getText().toString();
                String newPassword = newPasswordEt.getText().toString();
                String newEmail = newEmailEt.getText().toString();
                submitInformation(newUsername, newPassword, newEmail);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }

    private void submitInformation(String newUsername, String newPassword, String newEmail) {
        ParseUser newUser = new ParseUser();

        newUser.setUsername(newUsername);
        newUser.setPassword(newPassword);
        newUser.setEmail(newEmail);
        //newUser.put("handle", newUser);

        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with sign up");
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

    void clearFields() {
        newUsernameEt.setText("");
        newPasswordEt.setText("");
        newEmailEt.setText("");
    }
}
