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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.repository.Account;
import com.pablotorres.ifoodist.data.repository.RecipeRepository;
import com.pablotorres.ifoodist.iu.utils.CommonUtils;

public class SignUpActivity extends AppCompatActivity {

    private Button btSingUpRegistrarse;
    private TextInputEditText tieSingUpMail;
    private TextInputLayout tilSingUpMail;
    private TextInputEditText tieSingUpPassword;
    private TextInputLayout tilSingUpPassword;
    private TextInputEditText tieSingUpConfirmPassword;
    private TextInputLayout tilSingUpConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tieSingUpMail = findViewById(R.id.tieSingUpMail);
        tilSingUpMail = findViewById(R.id.tilSingUpMail);
        tieSingUpPassword = findViewById(R.id.tieSingUpPassword);
        tilSingUpPassword = findViewById(R.id.tilSingUpPassword);
        tieSingUpConfirmPassword = findViewById(R.id.tieSingUpConfirmPassword);
        tilSingUpConfirmPassword = findViewById(R.id.tilSingUpConfirmPassword);

        btSingUpRegistrarse = findViewById(R.id.btSingUpRegistrarse);
        btSingUpRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void registrar() {
        if(CommonUtils.isEmailValid(tieSingUpMail.getText().toString())){
            if(tieSingUpPassword.getText().toString().length() >= 7){
                if(tieSingUpPassword.getText().toString().equals(tieSingUpConfirmPassword.getText().toString())){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(tieSingUpMail.getText().toString(), tieSingUpPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Account.getInstance().setUser(tieSingUpMail.getText().toString());
                                Intent intent = new Intent(SignUpActivity.this, IFoodistActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUpActivity.this, "No se ha podido registrar el usuario...",Toast.LENGTH_SHORT ).show();
                            }
                        }
                    });
                }else{
                    tilSingUpConfirmPassword.setError("No coinciden las contraseñas");
                }
            }else{
                tilSingUpPassword.setError("La contraseña debe ser mayor a 7 caracteres");
            }
        }else{
            tilSingUpMail.setError("Email no valido");
        }
    }
}