package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlineshop.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DetailedProductsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    public static List<ProductShops> productshopresults;
    public static List<Double> shopdistanceresults;
    public static List<FinalEntry> finalentry;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        productshopresults=new ArrayList<ProductShops>();
        shopdistanceresults=new ArrayList<Double>();
        finalentry=new ArrayList<FinalEntry>();
        recyclerView = findViewById(R.id.products_recyclerView);
        manager = new GridLayoutManager(DetailedProductsActivity.this, 1);
        recyclerView.setLayoutManager(manager);


        // Setting up action bar
        //setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));

//        // Catching incoming intent
          Intent intent = getIntent();
//        String description = intent.getStringExtra("description");
          String name = intent.getStringExtra("name");
//        String image_url = intent.getStringExtra("image_url");

        for(int i=0;i<ProductsListActivity.productShops.size();i++){
            ProductShops productshop = ProductsListActivity.productShops.get(i);
            //System.out.println(name);
            if(productshop.getProduct_name().equals(name)){
                productshopresults.add(productshop);
            }

        }

        for(int i=0;i<productshopresults.size();i++){
            String shopname=productshopresults.get(i).getShop_name();
            Double distance=0.0;
            for(int j=0;j<ProductsListActivity.shops.size();j++){
                Shop shop=ProductsListActivity.shops.get(j);
                if(shop.getName().equals(shopname)){
                    distance=Shop.getDistance(shop.getLatitude(),shop.getLongitude());
                    shopdistanceresults.add(distance);
                    //System.out.println(distance);

                }
            }
            FinalEntry finalEntry=new FinalEntry(productshopresults.get(i).getShop_name(),productshopresults.get(i).getPrice().toString(),productshopresults.get(i).getSpecial_offers(),distance.toString());
            finalentry.add(finalEntry);

        }
        mAdapter2 = new RecyclerAdapter2(DetailedProductsActivity.this,finalentry);
        recyclerView.setAdapter(mAdapter2);
//        if (intent !=null){
//
//            mActionBar.setTitle(name);
//            mShopName.setText();
//            mDescription.setText(String.valueOf(description));
//          //  Glide.with().load(image_url).into(mImage);
//        }

    }
}