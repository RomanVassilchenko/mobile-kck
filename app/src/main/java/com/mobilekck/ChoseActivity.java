package com.mobilekck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        ImageButton ownerbtn = findViewById(R.id.ownerbtn);
        ImageButton buyerbtn = findViewById(R.id.buyerbtn);

        ownerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoseActivity.this, OwnerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buyerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Находится в разработке. Спасибо за понимание", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }


}
