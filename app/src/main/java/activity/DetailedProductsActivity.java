package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlineshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.AppConfig;

public class DetailedProductsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    public static List<ProductShops> productshopresults;
    public static List<Double> shopdistanceresults;
    public static List<FinalEntry> finalentry;
    public static List<FinalEntry> finalentry2;
    public static List<FinalEntry> finalentry3;
    private ProgressDialog pDialog;
    private Button distanceSort;
    private Button priceSort;
    public static String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list2);
        productshopresults = new ArrayList<ProductShops>();
        shopdistanceresults = new ArrayList<Double>();
        finalentry = new ArrayList<FinalEntry>();
        recyclerView = findViewById(R.id.products_recyclerView);
        manager = new GridLayoutManager(DetailedProductsActivity.this, 1);
        recyclerView.setLayoutManager(manager);
        distanceSort = (Button) findViewById(R.id.sortdistance);
        priceSort = (Button) findViewById(R.id.sortprice);


        // Setting up action bar
        //setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));

//        // Catching incoming intent
        Intent intent = getIntent();
//        String description = intent.getStringExtra("description");
        name = intent.getStringExtra("name");
//        String image_url = intent.getStringExtra("image_url");

        for (int i = 0; i < ProductsListActivity.productShops.size(); i++) {
            ProductShops productshop = ProductsListActivity.productShops.get(i);
            //System.out.println(name);
            if (productshop.getProduct_name().equals(name)) {
                productshopresults.add(productshop);
            }

        }

        for (int i = 0; i < productshopresults.size(); i++) {
            String shopname = productshopresults.get(i).getShop_name();
            Double distance = 0.0;

            for (int j = 0; j < ProductsListActivity.shops.size(); j++) {
                Shop shop = ProductsListActivity.shops.get(j);
                if (shop.getName().equals(shopname)) {
                    distance = Shop.getDistance(shop.getLatitude(), shop.getLongitude());
                    shopdistanceresults.add(distance);
                    //System.out.println(distance);

                }
            }
            FinalEntry finalEntry = new FinalEntry(productshopresults.get(i).getShop_name(), productshopresults.get(i).getPrice().toString(), productshopresults.get(i).getSpecial_offers(), distance.toString());
            finalentry.add(finalEntry);
        }
        mAdapter2 = new RecyclerAdapter2(DetailedProductsActivity.this, finalentry);
        recyclerView.setAdapter(mAdapter2);

        distanceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(finalentry);
                for (int i = 0; i < finalentry.size(); i++) {
                    Log.d("sorted list", finalentry.get(i).getShop_name());
                }
                mAdapter2.notifyDataSetChanged();
            }
        });
        priceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(finalentry, FinalEntry.myPrice);
                mAdapter2.notifyDataSetChanged();
            }
        });


//    @Override
//    public void onClick(View v) {
//      if(v.getId()==R.id.sortdistance){
//          Collections.sort(finalentry);
//          for(int i=0;i<finalentry.size();i++){
//              Log.d("sorted list",finalentry.get(i).getShop_name());
//          }
//          mAdapter2.notifyDataSetChanged();
//      }
//      else if(v.getId()==R.id.sortprice){
//          Collections.sort(finalentry,FinalEntry.myPrice);
//          mAdapter2.notifyDataSetChanged();
//      }

    }
}





