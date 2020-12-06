package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Resources res = getResources();

        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String museum = intent.getStringExtra("selected_museum");
        textView.setText(museum);

        imageView = findViewById(R.id.imageView);
        String moma = res.getString(R.string.MoMA);
        String amonh = res.getString(R.string.AMoNH);
        String bm = res.getString(R.string.BM);
        String mmoa = res.getString(R.string.MMoA);
        if (museum.equals(moma)) {
            imageView.setImageResource(R.drawable.moma);
        }
        else if (museum.equals(amonh)) {
            imageView.setImageResource(R.drawable.amonh);
        }
        else if (museum.equals(mmoa)) {
            imageView.setImageResource(R.drawable.mmoa);
        }
        else if (museum.equals(bm)) {
            imageView.setImageResource(R.drawable.bm);
        }
    }
}