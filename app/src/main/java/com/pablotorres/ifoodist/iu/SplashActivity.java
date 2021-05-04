package com.pablotorres.ifoodist.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pablotorres.ifoodist.R;

public class SplashActivity extends AppCompatActivity {
    private static final long WAIT_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initLogin();
            }
        }, WAIT_TIME);
    }

    private void initLogin(){
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        //Vamos a llamar de forma explicita al metodo finish que destruye la activity
        //y no se muestre cuando se pulse el boton back
        finish();
    }
}
