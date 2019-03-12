package com.mobilekck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class OwnerLoginActivity extends AppCompatActivity {

    private EditText iin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
        iin = findViewById(R.id.iinText);
        ImageButton okbtn = findViewById(R.id.okbtn);
        Button notownerbtn = findViewById(R.id.notownerbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iin.length() == 12) {
                    Intent intent = new Intent(OwnerLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Неверный ИИН. Пожалуйста повторите еще раз!", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
        notownerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OwnerLoginActivity.this, ChoseActivity.class);
                startActivity(intent);
            }
        });


    }
}
