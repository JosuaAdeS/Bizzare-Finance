package com.example.bizzarefinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText rfullName, rNIK, rHP, rEmail, rUsername, rPassword, rConfirm;
    private TextInputLayout lfullName, lNIK, lHP, lEmail, lUsername, lPassword, lConfirm;
    private Button btnRegis;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rfullName = findViewById(R.id.regisFullname2);
        rNIK = findViewById(R.id.regisNIK2);
        rHP = findViewById(R.id.regisHandphone2);
        rEmail = findViewById(R.id.regisEmail2);
        rUsername = findViewById(R.id.regisUsername2);
        rPassword = findViewById(R.id.regisPassword2);
        rConfirm = findViewById(R.id.regisConfirmPassword2);

        lfullName = findViewById(R.id.regisFullname);
        lNIK = findViewById(R.id.regisNIK);
        lHP = findViewById(R.id.regisHandphone);
        lEmail = findViewById(R.id.regisEmail);
        lUsername = findViewById(R.id.regisUsername);
        lPassword = findViewById(R.id.regisPassword);
        lConfirm = findViewById(R.id.regisConfirmPassword);

        btnRegis = findViewById(R.id.btnSignUp);

        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/");

        rfullName.addTextChangedListener(setupLabelError);
        rNIK.addTextChangedListener(setupLabelError);
        rHP.addTextChangedListener(setupLabelError);
        rEmail.addTextChangedListener(setupLabelError);
        rUsername.addTextChangedListener(setupLabelError);
        rPassword.addTextChangedListener(setupLabelError);
        rConfirm.addTextChangedListener(setupLabelError);

        lfullName.setError("Please Fill the Blank");
        lNIK.setError("Please Fill the Blank");
        lHP.setError("Please Fill the Blank");
        lEmail.setError("Please Fill the Blank");
        lUsername.setError("Please Fill the Blank");
        lPassword.setError("Please Fill the Blank");
        lConfirm.setError("Please Fill the Blank");

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rfullName.getText().toString();
                String nik = rNIK.getText().toString();
                String hp = rHP.getText().toString();
                String username = rUsername.getText().toString();
                String email = rEmail.getText().toString();
                String password = rPassword.getText().toString();
                String confirm = rConfirm.getText().toString();
                if (name.equals("") || nik.equals("") || hp.equals("") || username.equals("") || email.equals("")
                        || password.equals("") || !(password.equals(confirm)) || password.length()<=6) {
                    Toast.makeText(RegisterActivity.this, "Please check your form carefully",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Log.d(TAG, "email adalah : ?" + email);
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "createUserWithEmail:success");
                                        Toast.makeText(RegisterActivity.this, "Register Successfull",
                                                Toast.LENGTH_SHORT).show();
                                        UserDetail userDetail = new UserDetail(name, nik, hp, username);
                                        UserDetail.uid = task.getResult().getUser().getUid();
                                        firebaseDatabase.getReference(UserDetail.uid).setValue(userDetail)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                    } else {
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this, "Register Failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private TextWatcher setupLabelError = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String name = rfullName.getText().toString();
            String nik = rNIK.getText().toString();
            String hp = rHP.getText().toString().trim();
            String username = rUsername.getText().toString().trim();
            String email = rEmail.getText().toString().trim();
            String pass = rPassword.getText().toString().trim();
            String confirm = rConfirm.getText().toString().trim();

            if (name.equals("")) {
                lfullName.setError("Please Fill the Blank");
            } else {
                lfullName.setError(null);
            }

            if (nik.equals("")) {
                lNIK.setError("Please Fill the Blank");
            } else {
                lNIK.setError(null);
            }

            if (hp.equals("")) {
                lHP.setError("Please Fill the Blank");
            } else {
                lHP.setError(null);
            }

            if (username.equals("")) {
                lUsername.setError("Please Fill the Blank");
            } else {
                lUsername.setError(null);
            }

            if (email.equals("")) {
                lEmail.setError("Please Fill the Blank");
            } else {
                lEmail.setError(null);
            }

            if (pass.equals("")) {
                lPassword.setError("Please fill the blank");
            } else if (pass.length() <= 6) {
                lPassword.setError("Password at least has 6 character");
            } else {
                lPassword.setError(null);
            }

            if (confirm.equals("")) {
                lConfirm.setError("Please fill the blank");
            } else if (confirm.equals(pass)) {
                lConfirm.setError(null);
            } else {
                lConfirm.setError("Password doesn't match");
            }
        }
    };

    private void reload() {
    }
}