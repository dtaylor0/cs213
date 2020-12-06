package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureActivity extends AppCompatActivity {

    ImageView imageView;
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Resources res = getResources();

        Intent intent = getIntent();
        String museum = intent.getStringExtra("selected_museum");
        setTitle(museum);

        imageView = findViewById(R.id.imageView);
        String moma = res.getString(R.string.MoMA);
        String amonh = res.getString(R.string.AMoNH);
        String bm = res.getString(R.string.BM);
        String mmoa = res.getString(R.string.MMoA);
        if (museum.equals(moma)) {
            imageView.setImageResource(R.drawable.moma);
            url = res.getString(R.string.url_moma);
        }
        else if (museum.equals(amonh)) {
            imageView.setImageResource(R.drawable.amonh);
            url = res.getString(R.string.url_amonh);
        }
        else if (museum.equals(mmoa)) {
            imageView.setImageResource(R.drawable.mmoa);
            url = res.getString(R.string.url_mmoa);
        }
        else if (museum.equals(bm)) {
            imageView.setImageResource(R.drawable.bm);
            url = res.getString(R.string.url_bm);
        }
    }

    public void showBrowser(View view) {
        Intent intent = new Intent(getApplicationContext(), BrowserActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}