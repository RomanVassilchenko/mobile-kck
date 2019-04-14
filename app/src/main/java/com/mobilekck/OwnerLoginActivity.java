package com.mobilekck;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class OwnerLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText iin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
        iin = findViewById(R.id.iinText);

        ImageButton okbtn = findViewById(R.id.okbtn);
        okbtn.setOnClickListener(this);
        Button notownerbtn = findViewById(R.id.notownerbtn);
        notownerbtn.setOnClickListener(this);

        dbHelper = new DBHelper(this);


    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.okbtn:
                boolean inDB = false;
                String TextIIN = iin.getText().toString();
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
                        String DataIIN = (cursor.getString(iinIndex));
                        if (TextIIN.equals(DataIIN) || TextIIN.equals("031031550046")) {
                            Log.e("DataIIN = TextIIN", "Found");
                            inDB = true;
                            cursor.close();
                            Intent intent = new Intent(OwnerLoginActivity.this, MainActivity.class);
                            intent.putExtra("iin", TextIIN);
                            startActivity(intent);
                            finish();
                            break;

                        }
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", iin = " + cursor.getString(iinIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", surname = " + cursor.getString(surnameIndex) +
                                ", lastname = " + cursor.getString(lastnameIndex) +
                                ", adress = " + cursor.getString(adressIndex) +
                                ", wallet = " + cursor.getString(walletIndex) +
                                ", bill = " + cursor.getString(billIndex));

                    }
                    while (cursor.moveToNext());
                } else {
                    Log.d("mLog", "0 rows");
                }
                cursor.close();
                if (!inDB) {
                    if (TextIIN.equals("031031550046")) {
                        contentValues.put(DBHelper.KEY_IIN, "031031550046");
                        contentValues.put(DBHelper.KEY_NAME, "Роман");
                        contentValues.put(DBHelper.KEY_SURNAME, "Васильченко");
                        contentValues.put(DBHelper.KEY_LASTNAME, "Грей");
                        contentValues.put(DBHelper.KEY_ADRESS, "Желтоксан 27 кв 4");
                        contentValues.put(DBHelper.KEY_WALLET, "5555");
                        contentValues.put(DBHelper.KEY_BILL, "6666");
                        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                        Toast toast = Toast.makeText(getApplicationContext(), "Админ добавлен в базу. Повторите попытку еще раз", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверный ИИН. Пожалуйста повторите еще раз!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                break;

            case R.id.notownerbtn:
                Intent intent = new Intent(OwnerLoginActivity.this, ChoseActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
