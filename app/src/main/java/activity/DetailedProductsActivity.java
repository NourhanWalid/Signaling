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
//    private ImageView mImage;
//    private TextView mShopName;
//    private TextView mPrice;
//    private TextView mSpecialOffers;
//    private TextView mDistance;
//    private Instant Glide;
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

//        mToolbar = findViewById(R.id.toolbar);
//        mImage = findViewById(R.id.image_view);
//        mPrice = findViewById(R.id.price);
//        mSpecialOffers = findViewById(R.id.specialoffers);
//        mDistance = findViewById(R.id.distance);
//        mShopName = findViewById(R.id.shopname);
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
        mAdapter2 = new RecyclerAdapter2(DetailedProductsActivity.this,finalentry);
        recyclerView.setAdapter(mAdapter2);
        //mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));


//        // Catching incoming intent
//        Intent intent = getIntent();
//        String description = intent.getStringExtra("description");
//        String name = intent.getStringExtra("name");
//        String image_url = intent.getStringExtra("image_url");

        for(int i=0;i<ProductsListActivity.productShops.size();i++){
            ProductShops productshop = ProductsListActivity.productShops.get(i);
            String name=RecyclerAdapter.product_name;
            if(productshop.getProduct_name().equals(name)){
                productshopresults.add(productshop);
            }

        }
//        for(int i=0;i<productshopresults.size();i++){
//            System.out.println(productshopresults.get(i).getShop_name());
//        }

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
                FinalEntry finalEntry=new FinalEntry(productshopresults.get(i).getShop_name(),productshopresults.get(i).getPrice().toString(),productshopresults.get(i).getSpecial_offers(),distance.toString());
                finalentry.add(finalEntry);
                System.out.println(finalentry.get(0).getPrice());
            }


        }
//        if (intent !=null){
//
//            mActionBar.setTitle(name);
//            mShopName.setText();
//            mDescription.setText(String.valueOf(description));
//          //  Glide.with().load(image_url).into(mImage);
//        }

    }
}