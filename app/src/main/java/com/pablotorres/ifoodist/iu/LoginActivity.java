package com.pablotorres.ifoodist.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pablotorres.ifoodist.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void ShowSignUp(View v){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void ShowDashBoard(View v) {
        Intent intent = new Intent(LoginActivity.this, IFoodistActivity.class);
        startActivity(intent);
    }

}