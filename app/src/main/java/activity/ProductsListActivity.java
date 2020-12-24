package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    // Variable declarations
    private String userEmail;
    private TextView textView;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;
    private List<Product> products;
    private ProgressBar progressBar;
    private static  final String BASE_URL = "http://172.20.10.14/android_api/include/getProducts.php";
    private static final String BASE_URL2= "http://172.20.10.14/android_api/include/ProductShops.php";
    private static final String BASE_URL3="http://172.20.10.14/android_api/include/getShops.php";
    public static List<ProductShops> productShops;
    public static List<Shop> shops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        mToolbar = findViewById(R.id.dashboard_toolbar);
        progressBar = findViewById(R.id.progressbar);
       // setSupportActionBar(mToolbar);
      //  mActionBar = getSupportActionBar();

        recyclerView = findViewById(R.id.products_recyclerView);
        manager = new GridLayoutManager(ProductsListActivity.this, 1);
        recyclerView.setLayoutManager(manager);

        products = new ArrayList<Product>();
        productShops=new ArrayList<ProductShops>();
        shops=new ArrayList<Shop>();

        getProducts();
        getProductShops();
        getShops();


    }

    private void getProducts (){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String name = object.getString("name");
                                String description = object.getString("description");
                                String image_url = object.getString("image_url");

                                Product product = new Product(name,description,image_url);
                                products.add(product);
                            }

                        }catch (Exception e){

                        }

                        mAdapter = new RecyclerAdapter(ProductsListActivity.this,products);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(ProductsListActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(ProductsListActivity.this).add(stringRequest);

    }

    private void getProductShops (){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String shop_name = object.getString("shop_name");
                                String product_name = object.getString("product_name");
                                Integer price = object.getInt("price");
                                String special_offers = object.getString("special_offers");

                                ProductShops productShop = new ProductShops(shop_name,product_name,price,special_offers);
                                productShops.add(productShop);
                                //System.out.println(productShops.get(i).getProduct_name());
                            }

                        }catch (Exception e){

                        }

//                        mAdapter = new RecyclerAdapter(mContext,products);
//                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                //Toast.makeText(DetailedProductsActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(ProductsListActivity.this).add(stringRequest);
//        System.out.println(productShops.size());
//        for (int i=0;i<productShops.size();i++) {
//            System.out.println(productShops.get(i).getShop_name());
//        }

    }

    private void getShops (){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String name = object.getString("name");
                                double latitude = object.getDouble("latitude");
                                double longitude = object.getDouble("longitude");

                                Shop shop = new Shop(name,latitude,longitude);
                                shops.add(shop);
                                //System.out.println(shops.get(i).getName());
                            }

                        }catch (Exception e){

                        }

//                        mAdapter = new RecyclerAdapter(mContext,products);
//                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                //Toast.makeText(DetailedProductsActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(ProductsListActivity.this).add(stringRequest);

    }

}