package com.example.serviceringtone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startBtn;
    private Button stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.btn);
        stopBtn = (Button) findViewById(R.id.btn2);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        if(view == startBtn){
            startService(new Intent(this, MyService.class));
        }

        else if(view == stopBtn){
            stopService(new Intent(this, MyService.class));
        }
    }
}
