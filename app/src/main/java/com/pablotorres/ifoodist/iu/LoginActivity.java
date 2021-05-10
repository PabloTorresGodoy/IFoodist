package com.pablotorres.ifoodist.iu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.repository.Account;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText tieUser;
    private TextInputEditText tiePassword;
    private Button btSingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tieUser = findViewById(R.id.tieUser);
        tiePassword = findViewById(R.id.tiePassword);
        btSingIn = findViewById(R.id.btSingIn);
        btSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tieUser.getText().toString() != "" && tiePassword.getText().toString() != "") {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(tieUser.getText().toString(), tiePassword.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Account.getInstance().setUser(tieUser.getText().toString());
                                        Intent intent = new Intent(LoginActivity.this, IFoodistActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Las credenciales no son correctas", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    public void ShowSignUp(View v) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}