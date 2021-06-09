package com.pablotorres.ifoodist.iu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.pablotorres.ifoodist.R;
import com.pablotorres.ifoodist.data.repository.Account;

import java.util.HashSet;
import java.util.Set;

public class IFoodistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        setSupportActionBar(toolbar);

        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.recetarioFragment);
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).setOpenableLayout(drawerLayout).build();

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration)||super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.close();
        switch (item.getItemId()){
            case R.id.action_create:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.addRecetaFragment);
                break;
            case R.id.action_recipe:
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.recetarioFragment);
                break;
            case R.id.action_about:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ifoodist.wordpress.com/"));
                startActivity(browserIntent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isOpen())
            drawerLayout.close();
        else
            super.onBackPressed();
    }
}