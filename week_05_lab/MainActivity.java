package com.example.week_05_lab;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DBHelper mDATABASE;
    EditText nameText;
    ListView list;

    ArrayAdapter<Name> adapter;
   List<Name> names;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mDATABASE = new DBHelper(this);

        names = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        nameText = findViewById(R.id.nameText);
        findViewById(R.id.addBtn).setOnClickListener(this);
        findViewById(R.id.DisplayNamesBtn).setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        int id = v.getId();
        if (id == R.id.addBtn){
            addName();
        }else  if (id == R.id.DisplayNamesBtn){
            displayNamesBtn();
        }
    }

    private void addName() {
        //todo: add names to db
    }

    private void displayNamesBtn() {
        //todo: fetch data from db nnd show on list
    }

    private record Name(int id, String name){
        @NonNull
        @Override
        public String toString() {
            return "id "+ id +"\n" + "name" + name;
        }
    }
}