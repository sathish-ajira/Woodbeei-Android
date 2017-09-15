package tech.ajira.woodbeei;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import tech.ajira.woodbeei.activities.ProductListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialBetterSpinner categoriesSpinner;
    private ArrayList<String> productList = new ArrayList<>();
    private AutoCompleteTextView productTxtView;
    private Button searchButton;
    private LinearLayout spinnerLayout;
    private String[] categories = {"X-Cross","Paris-Corner","Episode","Shakira","New Jaquar", "Oscar", "New"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    private void initializeViews()
    {
        productTxtView = (AutoCompleteTextView) findViewById(R.id.product);
        searchButton = (Button) findViewById(R.id.search);
        spinnerLayout = (LinearLayout) findViewById(R.id.spinner_layout);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, categories);
        categoriesSpinner = (MaterialBetterSpinner)
                findViewById(R.id.category);
        categoriesSpinner.setAdapter(arrayAdapter);

        spinnerLayout.setVisibility(View.GONE);
        searchButton.setVisibility(View.GONE);

        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Lato-Light.ttf");
        categoriesSpinner.setTypeface(tf);

        searchButton.setOnClickListener( this );

        productList.add("Sofas");
        productList.add("Cots");
        productList.add("Dining");
        productList.add("Chairs");
        productList.add("Hotel Furniture");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,productList);
        productTxtView.setAdapter(adapter);
        productTxtView.setThreshold(0);

        productTxtView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                spinnerLayout.setVisibility(View.VISIBLE);
                searchButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == searchButton)
        {
            startActivity(new Intent(MainActivity.this, ProductListActivity.class));
        }
    }
}
