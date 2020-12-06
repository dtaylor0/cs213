package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PictureActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    String url = "";
    String newTitle = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Resources res = getResources();

        Intent intent = getIntent();
        String museum = intent.getStringExtra("selected_museum");
        setTitle(museum);

        Toast.makeText(PictureActivity.this, res.getString(R.string.ticket_limit), Toast.LENGTH_LONG).show();

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        String moma = res.getString(R.string.MoMA);
        String amonh = res.getString(R.string.AMoNH);
        String bm = res.getString(R.string.BM);
        String mmoa = res.getString(R.string.MMoA);
        if (museum.equals(moma)) {
            imageView.setImageResource(R.drawable.moma);
            textView.setText(res.getString(R.string.prices_moma));
            url = res.getString(R.string.url_moma);
            newTitle = res.getString(R.string.MoMA);
        }
        else if (museum.equals(amonh)) {
            imageView.setImageResource(R.drawable.amonh);
            textView.setText(res.getString(R.string.prices_amonh));
            url = res.getString(R.string.url_amonh);
            newTitle = res.getString(R.string.AMoNH);
        }
        else if (museum.equals(mmoa)) {
            imageView.setImageResource(R.drawable.mmoa);
            textView.setText(res.getString(R.string.prices_mmoa));
            url = res.getString(R.string.url_mmoa);
            newTitle = res.getString(R.string.MMoA);
        }
        else if (museum.equals(bm)) {
            imageView.setImageResource(R.drawable.bm);
            textView.setText(res.getString(R.string.prices_bm));
            url = res.getString(R.string.url_bm);
            newTitle = res.getString(R.string.BM);
        }
    }

    public void showBrowser(View view) {
        Intent intent = new Intent(getApplicationContext(), BrowserActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", newTitle);
        startActivity(intent);
    }
}