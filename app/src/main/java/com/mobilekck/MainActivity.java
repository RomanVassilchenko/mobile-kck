package com.mobilekck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_main);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment()).commit();
                break;
            case R.id.nav_bills:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BillsFragment()).commit();
                break;
            case R.id.nav_adboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AdBoardFragment()).commit();
                break;
            case R.id.nav_premium:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PremiumFragment()).commit();
                break;
            case R.id.nav_kck:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new KCKFragment()).commit();
                break;
            case R.id.nav_info:
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_buyer:
                Intent intent2 = new Intent(MainActivity.this, ChoseActivity.class);
                startActivity(intent2);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}