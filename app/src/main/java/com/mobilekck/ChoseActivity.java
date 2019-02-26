package com.mobilekck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChoseActivity extends AppCompatActivity {

    private ImageButton ownerbtn;
    private ImageButton buyerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        ownerbtn = (ImageButton) findViewById(R.id.ownerbtn);
        buyerbtn = (ImageButton) findViewById(R.id.buyerbtn);

        ownerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoseActivity.this, OwnerLoginActivity.class);
                startActivity(intent);
            }
        });

    }


}
