package com.example.bizzarefinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private TextInputEditText loginEmail, loginPassword;
    private TextInputLayout lEmail, lPassword;
    private Button btnRegister, btnLogin;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEmail = findViewById(R.id.loginEmail2);
        loginPassword = findViewById(R.id.loginPassword2);
        lEmail = findViewById(R.id.loginEmail);
        lPassword = findViewById(R.id.loginPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegis);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/");

        loginEmail.addTextChangedListener(setupLabelError);
        loginPassword.addTextChangedListener(setupLabelError);

        lEmail.setError("Please fill the blank");
        lPassword.setError("Please fill the blank");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(MainActivity.this, "Email and password can't be empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Email", "Your email is : ?" + email);
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        getBalanceFirebase();
                                        Log.d("Login", "Login:success");
                                        Toast.makeText(MainActivity.this, "Login Successfull",
                                                Toast.LENGTH_LONG).show();
                                        UserDetail.setUid(task.getResult().getUser().getUid());
                                        reference = firebaseDatabase.getReference(task.getResult().getUser().getUid());
                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String balance = snapshot.child("balance").getValue().toString();
                                                int total = Integer.parseInt(balance);
                                                UserDetail user = new UserDetail();
                                                user.setBalance(total);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                        Intent intent = new Intent(MainActivity.this, MarketActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Log.w("Login", "Login:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Email or Password incorect.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private void reload() {
    }

    public void register(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
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
            String email = loginEmail.getText().toString().trim();
            String pass = loginPassword.getText().toString().trim();

            if (email.equals("")) {
                lEmail.setError("Please Fill the Blank");
            } else {
                lEmail.setError(null);
            }

            if (pass.equals("")) {
                lPassword.setError("Please fill the blank");
            } else {
                lPassword.setError(null);
            }
        }
    };

    public void getBalanceFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/");
        DatabaseReference reference = firebaseDatabase.getReference(user.getUid());
        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String x = snapshot.child("balance").getValue().toString();
                Log.d("Snapshot : ",x);
                int total = Integer.parseInt(x);
                editor.putInt("balance",total);
                editor.apply();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}