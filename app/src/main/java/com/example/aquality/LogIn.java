package com.example.aquality;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LogIn extends AppCompatActivity {
    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> selectedUsers = new ArrayList<String>();
    Set<String> used;
    ArrayAdapter<String> adapter;
    String user;
    ListView userList;
    EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        SharedPreferences preferences = LogIn.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        used = preferences.getStringSet("ListUser", new HashSet<String>());

        users = new ArrayList<>(used);
        userList = findViewById(R.id.ListLOG);
        adapter = new ArrayAdapter(this
                , android.R.layout.simple_list_item_1
                , users);
        userList.setAdapter(adapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                user = adapter.getItem(position);
                if(userList.isItemChecked(position)) {
                    selectedUsers.add(user);
                }else {
                    selectedUsers.remove(user);
                }
                user = selectedUsers.toString();
                preferences.edit().putString("account", user).apply();
                finish();
            }
        });


    }

    public void add(View view){
        userName = findViewById(R.id.EditLog);
        String user = userName.getText().toString();
        SharedPreferences preferences = LogIn.this.getSharedPreferences("com.example.x", Context.MODE_PRIVATE);
        if(!user.isEmpty()){
            adapter.add(user);
            used.add(user);
            userName.setText("");
            adapter.notifyDataSetChanged();
        }
        preferences.edit().putStringSet("ListUser", used).apply();
    }
/*
    public void remove(View view){
        for(int i=0; i< selectedUsers.size();i++){adapter.remove(selectedUsers.get(i));}
        userList.clearChoices();
        selectedUsers.clear();
        adapter.notifyDataSetChanged();
    }
*/
}
