package com.mobilekck;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminMenu extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnRead, btnClear;
    EditText etIIN, etName, etSurName, etLastName, etAdress;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);


        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etIIN = findViewById(R.id.etIIN);
        etName = findViewById(R.id.etName);
        etSurName = findViewById(R.id.etSurname);
        etLastName = findViewById(R.id.etLastname);
        etAdress = findViewById(R.id.etAdress);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String iin = etIIN.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurName.getText().toString();
        String lastname = etLastName.getText().toString();
        String adress = etAdress.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_IIN, iin);
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_SURNAME, surname);
                contentValues.put(DBHelper.KEY_LASTNAME, lastname);
                contentValues.put(DBHelper.KEY_ADRESS, adress);


                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int iinIndex = cursor.getColumnIndex(DBHelper.KEY_IIN);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int surnameIndex = cursor.getColumnIndex(DBHelper.KEY_SURNAME);
                    int lastnameIndex = cursor.getColumnIndex(DBHelper.KEY_LASTNAME);
                    int adressIndex = cursor.getColumnIndex(DBHelper.KEY_ADRESS);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", iin = " + cursor.getString(iinIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", surname = " + cursor.getString(surnameIndex) +
                                ", lastname = " + cursor.getString(lastnameIndex) +
                                ", adress = " + cursor.getString(adressIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
        }
        dbHelper.close();
    }
}