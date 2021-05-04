package com.pablotorres.ifoodist.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pablotorres.ifoodist.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void ShowDashBoard(View v) {
        Intent intent = new Intent(SignUpActivity.this, IFoodistActivity.class);
        startActivity(intent);
    }
}