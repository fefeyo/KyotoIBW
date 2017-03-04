package com.fefeyo.kyotoibw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fefeyo.kyotoibw.store.AlcoholReaderActivity;
import com.fefeyo.kyotoibw.user.StoreReaderActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.store).setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, AlcoholReaderActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.user).setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, StoreReaderActivity.class);
            startActivity(intent);
        });
    }
}
