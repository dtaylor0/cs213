package com.example.project5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PictureActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView total;
    Spinner adults;
    Spinner seniors;
    Spinner students;
    String url = "";
    String newTitle = "";
    double[] momaPrices = {25, 18, 14};
    double[] amonhPrices = {23, 18, 18};
    double[] bmPrices = {16, 10, 10};
    double[] mmoaPrices = {25, 17, 12};
    double taxNYC = 0.08875;

    String museum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Resources res = getResources();

        Intent intent = getIntent();
        String selectedMuseum = intent.getStringExtra("selected_museum");
        setTitle(selectedMuseum);
        museum = selectedMuseum;

        Toast.makeText(PictureActivity.this, res.getString(R.string.ticket_limit), Toast.LENGTH_LONG).show();

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        adults = findViewById(R.id.adults_spinner);
        seniors = findViewById(R.id.seniors_spinner);
        students = findViewById(R.id.students_spinner);

        adults.setOnItemSelectedListener(new MyOnItemSelectedListener());
        seniors.setOnItemSelectedListener(new MyOnItemSelectedListener());
        students.setOnItemSelectedListener(new MyOnItemSelectedListener());

        total = findViewById(R.id.total);
        showPrice();
        String moma = res.getString(R.string.MoMA);
        String amonh = res.getString(R.string.AMoNH);
        String bm = res.getString(R.string.BM);
        String mmoa = res.getString(R.string.MMoA);
        if (selectedMuseum.equals(moma)) {
            imageView.setImageResource(R.drawable.moma);
            textView.setText(res.getString(R.string.prices_moma));
            url = res.getString(R.string.url_moma);
            newTitle = res.getString(R.string.MoMA);
        }
        else if (selectedMuseum.equals(amonh)) {
            imageView.setImageResource(R.drawable.amonh);
            textView.setText(res.getString(R.string.prices_amonh));
            url = res.getString(R.string.url_amonh);
            newTitle = res.getString(R.string.AMoNH);
        }
        else if (selectedMuseum.equals(mmoa)) {
            imageView.setImageResource(R.drawable.mmoa);
            textView.setText(res.getString(R.string.prices_mmoa));
            url = res.getString(R.string.url_mmoa);
            newTitle = res.getString(R.string.MMoA);
        }
        else if (selectedMuseum.equals(bm)) {
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

    public void showPrice() {
        double[] prices = null;
        Resources res = getResources();
        if (museum.equals(res.getString(R.string.MoMA)))
            prices = momaPrices;
        else if (museum.equals(res.getString(R.string.AMoNH)))
            prices = amonhPrices;
        else if (museum.equals(res.getString(R.string.MMoA)))
            prices = mmoaPrices;
        else if (museum.equals(res.getString(R.string.BM)))
            prices = bmPrices;

        if (prices == null) {
        }
        else {
            double[] amounts = {Double.parseDouble((String)adults.getSelectedItem()), Double.parseDouble((String)seniors.getSelectedItem()), Double.parseDouble((String)students.getSelectedItem())};
            double dot = 0;
            for (int i = 0; i < prices.length; i++) {
                dot += prices[i] * amounts[i];
            }
            dot = dot + dot * taxNYC;
            @SuppressLint("DefaultLocale") String strTotal = String.format("Total: $%.2f", dot);
            total.setText(strTotal);
        }
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            showPrice();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
}