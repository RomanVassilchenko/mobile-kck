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

    Button btnAdd, btnRead, btnClear, btnUpd, btnDel;
    EditText etIIN, etName, etSurName, etLastName, etAdress, etId;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);


        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnUpd = (Button) findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(this);

        btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etIIN = findViewById(R.id.etIIN);
        etName = findViewById(R.id.etName);
        etSurName = findViewById(R.id.etSurname);
        etLastName = findViewById(R.id.etLastname);
        etAdress = findViewById(R.id.etAdress);
        etId = (EditText) findViewById(R.id.etId);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String iin = etIIN.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurName.getText().toString();
        String lastname = etLastName.getText().toString();
        String adress = etAdress.getText().toString();
        String id = etId.getText().toString();

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
                        if (!id.isEmpty()) {
                            Integer DataId = cursor.getInt(idIndex);
                            Integer FindId = 0;
                            try {
                                FindId = Integer.valueOf(id);
                            } catch (NumberFormatException e) {
                                FindId = -1;
                            }
                            if (DataId == FindId) {
                                Log.e("DataId = FindId", "Found");
                                etIIN.setText(cursor.getString(iinIndex));
                                etName.setText(cursor.getString(nameIndex));
                                etSurName.setText(cursor.getString(surnameIndex));
                                etLastName.setText(cursor.getString(lastnameIndex));
                                etAdress.setText(cursor.getString(adressIndex));
                            }

                        }
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

            case R.id.btnUpd:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                if (iin != " " && iin != "") contentValues.put(DBHelper.KEY_IIN, iin);
                if (name != " " && name != "") contentValues.put(DBHelper.KEY_NAME, name);
                if (surname != " " && surname != "")
                    contentValues.put(DBHelper.KEY_SURNAME, surname);
                if (lastname != " " && lastname != "")
                    contentValues.put(DBHelper.KEY_LASTNAME, lastname);
                if (adress != " " && adress != "") contentValues.put(DBHelper.KEY_ADRESS, adress);
                int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + "= ?", new String[]{id});

                Log.d("mLog", "updates rows count = " + updCount);
                break;

            case R.id.btnDel:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                int delCount = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "=" + id, null);

                Log.d("mLog", "deleted rows count = " + delCount);
                break;
        }
        dbHelper.close();
    }
}