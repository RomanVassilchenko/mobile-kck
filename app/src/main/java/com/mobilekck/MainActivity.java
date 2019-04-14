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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    String ID, IIN, NAME, SURNAME, LASTNAME, ADRESS, FULLNAME, WALLET, BILL;
    String ADBOARDNEWTEXT;
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

        try {
            FULLNAME = NAME + ' ' + SURNAME + ' ' + LASTNAME;
            TextView headerTitle = navigationView.getHeaderView(0).findViewById(R.id.nav_full_name);
            headerTitle.setText(FULLNAME);
            Log.e("FullName = ", FULLNAME);
        } catch (Exception e) {
            Log.e("FullName", "-1");
        }
    }

    public void SearchInDB() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int iinIndex = cursor.getColumnIndex(DBHelper.KEY_IIN);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int surnameIndex = cursor.getColumnIndex(DBHelper.KEY_SURNAME);
            int lastnameIndex = cursor.getColumnIndex(DBHelper.KEY_LASTNAME);
            int adressIndex = cursor.getColumnIndex(DBHelper.KEY_ADRESS);
            int walletIndex = cursor.getColumnIndex(DBHelper.KEY_WALLET);
            int billIndex = cursor.getColumnIndex(DBHelper.KEY_BILL);

            do {
                String newID, newIIN, newNAME, newSURNAME, newLASTNAME, newADRESS, newWALLET, newBILL;

                newID = cursor.getString(idIndex);
                newIIN = cursor.getString(iinIndex);
                newNAME = cursor.getString(nameIndex);
                newSURNAME = cursor.getString(surnameIndex);
                newLASTNAME = cursor.getString(lastnameIndex);
                newADRESS = cursor.getString(adressIndex);
                newWALLET = cursor.getString(walletIndex);
                newBILL = cursor.getString(billIndex);

                if (IIN.equals(newIIN)) {
                    Log.e("DataIIN = IIN", "Found");

                    ID = newID;
                    NAME = newNAME;
                    SURNAME = newSURNAME;
                    LASTNAME = newLASTNAME;
                    ADRESS = newADRESS;
                    WALLET = newWALLET;
                    BILL = newBILL;
                }

                //ADBOARD TEXT IIN = (DD-MM-YYYY)
                if (newIIN.length() == 10) {
                    String date = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                    ADBOARDNEWTEXT = newNAME + " " + newIIN + " " + date + "\n" +
                            newADRESS + "\n\n" + ADBOARDNEWTEXT;

                    try {
                        Log.e("adtext", ADBOARDNEWTEXT);
                    } catch (Exception e) {
                        Log.e("adtext", e.toString());
                    }
                }

            }
            while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 rows");
        }
        cursor.close();
    }

    public void UpdateDB(String ID, String IIN, String NAME, String SURNAME, String LASTNAME, String ADRESS, String WALLET, String BILL) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (ID.equalsIgnoreCase("")) {
            return;
        }
        if (IIN != " " && IIN != "") contentValues.put(DBHelper.KEY_IIN, IIN);
        if (NAME != " " && NAME != "") contentValues.put(DBHelper.KEY_NAME, NAME);
        if (SURNAME != " " && SURNAME != "")
            contentValues.put(DBHelper.KEY_SURNAME, SURNAME);
        if (LASTNAME != " " && LASTNAME != "")
            contentValues.put(DBHelper.KEY_LASTNAME, LASTNAME);
        if (ADRESS != " " && ADRESS != "") contentValues.put(DBHelper.KEY_ADRESS, ADRESS);
        if (WALLET != " " && WALLET != "") contentValues.put(DBHelper.KEY_WALLET, WALLET);
        if (BILL != " " && BILL != "") contentValues.put(DBHelper.KEY_BILL, BILL);
        int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + "= ?", new String[]{ID});
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

    public String getWALLET() {
        return WALLET;
    }

    public String getBILL() {
        return BILL;
    }

    public String getID() {
        return ID;
    }

    public String getADBOARDNEWTEXT() {
        return ADBOARDNEWTEXT;
    }

}