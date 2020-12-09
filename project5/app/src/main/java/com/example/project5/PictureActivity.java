package com.example.project5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 Controls activity_picture.xml
 @author Shyam Patel, Drew Taylor
 */
public class PictureActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView price;
    TextView tax;
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

    /**
     * Overrides onCreate in AppCompatActivity, sets the picture, website link, and prices for the picture activity.
     * @param savedInstanceState saved data from previous use of this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        price = findViewById(R.id.price);
        tax = findViewById(R.id.tax);
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

    /**
     * Starts the browser activity and sends the museum name for the title of that activity.
     * @param view The view that was clicked on to open the BrowserActivity, in this case it is always the ImageView.
     */
    public void showBrowser(View view) {
        Intent intent = new Intent(getApplicationContext(), BrowserActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", newTitle);
        startActivity(intent);
    }

    /**
     * Gets current selected ticket amounts and calculates base price, tax, and total price to display.
     */
    @SuppressLint("DefaultLocale")
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

        if (!(prices == null)) {
            double[] amounts = {Double.parseDouble((String)adults.getSelectedItem()),
                    Double.parseDouble((String)seniors.getSelectedItem()),
                    Double.parseDouble((String)students.getSelectedItem())};
            double dot = 0;
            for (int i = 0; i < prices.length; i++) {
                dot += prices[i] * amounts[i];
            }
            price.setText(String.format("Base Price: $%d", (int)dot));
            tax.setText(String.format("Tax: $%.2f", dot * taxNYC));
            dot = dot + dot * taxNYC;
            @SuppressLint("DefaultLocale") String strTotal = String.format("Total: $%.2f", dot);
            total.setText(strTotal);
        }
    }

    /**
     * Custom OnItemSelectedListener to allow for real time updates to price displays.
     */
    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        /**
         * Updates price displays every time a new ticket quantity is selected.
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param pos The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            showPrice();
        }

        /**
         * Stops anything from happening when no quantity is selected.
         * @param parent The AdapterView where no selection happened.
         */
        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    /**
     * Makes the top left back arrow functional.
     * @param item The menu item that was selected.
     * @return false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}