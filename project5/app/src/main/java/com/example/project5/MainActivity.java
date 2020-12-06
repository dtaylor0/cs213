package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String main_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        listView = findViewById(R.id.listview);
        main_title = res.getString(R.string.main_title);
        TextView title = new TextView(MainActivity.this);
        title.setText(main_title);
        listView.addHeaderView(title);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String museum = (String)listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), PictureActivity.class);
                intent.putExtra("selected_museum", museum);
                startActivity(intent);
            }
        });
    }




}