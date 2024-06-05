package com.example.aquality;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int lvap = 1;
    public String account = "player";
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = MainActivity.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        account = preferences.getString("account", "player");
        lvap = preferences.getInt("DialogS", 1);
        login = findViewById(R.id.LogIn);
        login.setText(account + " " + lvap);

    }

    public void remove_dialogs(View view){
        SharedPreferences preferences = MainActivity.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        if(lvap>=6){
            lvap-=5;
            preferences.edit().putInt("DialogS", lvap).apply();
        }
    }

    public void Click(View view){
        Intent i = new Intent(this, Qwerty.class);
        startActivity(i);
    }
    /*
    public void levels(View view){
        Intent lev = new Intent(this, Levels.class);
        SharedPreferences preferences = MainActivity.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        lvap = preferences.getInt("lvaq", 1);
        startActivity(lev);
    }
    */
    public void Dclick(View view){
        finish();
    }

    public void logueIn(View view) {
        Intent lev = new Intent(this, LogIn.class);
        startActivity(lev);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = MainActivity.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        account = preferences.getString("account", "player");
        lvap = preferences.getInt("DialogS", 1);
        login = findViewById(R.id.LogIn);
        login.post(()->{
            login.setText(account + " " + lvap);
        });
    }

}