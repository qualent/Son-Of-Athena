package com.example.aquality;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Qwerty extends AppCompatActivity {

    ArrayList<String> dia = new ArrayList<String>();
    ArrayList<String> reso = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView Dialogs;
    public int hp;
    public boolean trfal;
    public int i = 0;
    public int q = 0;
    TextView wer;
    TextView wek;
    Button taptapbutton;
    public boolean timerstart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        reso.addAll(Arrays.asList(getResources().getStringArray(R.array.resou)));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qwerty);
        SharedPreferences preferences = Qwerty.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        i = preferences.getInt("DialogS",0);
        hp = preferences.getInt("DialogS",0);
        if(hp>0)hp--;
        wer = findViewById(R.id.timerrr);
        taptapbutton = findViewById(R.id.tapping);
        taptapbutton.setOnClickListener((v)->{
            tap(v);
        });
    }
    public void ex(View view){
        trfal = false;
        finish();
    }

    public void tap(View view){
        SharedPreferences preferences = Qwerty.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        Dialogs = findViewById(R.id.textPlay);
        dia.add(q, reso.get(i));
        adapter = new ArrayAdapter(this
                , android.R.layout.simple_list_item_1
                , dia);
        Dialogs.setAdapter(adapter);
        q++;
        i++;
        hp++;
        if(q==6) {q = 0; dia.clear(); i--;}
        wer = findViewById(R.id.diaCol);
        wer.setText("DIALOGS:" + hp);
        preferences.edit().putInt("DialogS", hp).apply();
        if (!timerstart){
            thread.start();
        }

    }

    Thread thread = new Thread(()->{
        timerstart = true;
        wek = findViewById(R.id.timerrr);
        int seconds = 0;
        while (seconds>-1){
            try {
                Thread.sleep(1000);
                seconds++;
                int finalSeconds = seconds;
                wek.post(()->{
                    wek.setText(String.valueOf(finalSeconds));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

}
