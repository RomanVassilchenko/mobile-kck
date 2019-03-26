package com.mobilekck;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    String IIN, NAME, SURNAME, LASTNAME, ADRESS;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle arguments = getIntent().getExtras();

        try {
            IIN = arguments.get("iin").toString();
            Log.e("IIN in MainActivity", IIN);
        } catch (Exception e) {
            Log.e("IIN in MainActivity", "Not SEND!");
        }


        dbHelper = new DBHelper(this);


        SearchInDB();


        Log.e("IIN = ", IIN);

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

    public void SearchInDB() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int iinIndex = cursor.getColumnIndex(DBHelper.KEY_IIN);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int surnameIndex = cursor.getColumnIndex(DBHelper.KEY_SURNAME);
            int lastnameIndex = cursor.getColumnIndex(DBHelper.KEY_LASTNAME);
            int adressIndex = cursor.getColumnIndex(DBHelper.KEY_ADRESS);

            do {
                String DataIIN = (cursor.getString(iinIndex));
                if (IIN.equals(DataIIN)) {
                    Log.e("DataIIN = IIN", "Found");

                    NAME = cursor.getString(nameIndex);
                    SURNAME = cursor.getString(surnameIndex);
                    LASTNAME = cursor.getString(lastnameIndex);
                    ADRESS = cursor.getString(adressIndex);

                    cursor.close();
                    return;

                }
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", iin = " + cursor.getString(iinIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", surname = " + cursor.getString(surnameIndex) +
                        ", lastname = " + cursor.getString(lastnameIndex) +
                        ", adress = " + cursor.getString(adressIndex));

            }
            while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 rows");
        }
        cursor.close();
        return;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_main:
                fragment = new MainFragment();
                break;
            case R.id.nav_bills:
                fragment = new BillsFragment();
                break;
            case R.id.nav_adboard:
                fragment = new AdBoardFragment();
                break;
            case R.id.nav_premium:
                fragment = new PremiumFragment();
                break;
            case R.id.nav_kck:
                fragment = new KCKFragment();
                break;
            case R.id.nav_info:
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                return false;

            case R.id.nav_buyer:
                Intent intent2 = new Intent(MainActivity.this, ChoseActivity.class);
                startActivity(intent2);
                return false;

            case R.id.nav_admin:
                Intent intent3 = new Intent(MainActivity.this, AdminMenu.class);
                startActivity(intent3);
                return false;
        }


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

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

    public String getIIN() {
        return IIN;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSURNAME() {
        return SURNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public String getADRESS() {
        return ADRESS;
    }
}