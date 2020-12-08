package com.example.onlineshop;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import activity.Product;
import activity.RecyclerAdapter;

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
    private static  final String BASE_URL = "http://192.168.1.4/android_api/getProducts.php";

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        Intent intent;
//
//        if (item.getItemId() == R.id.action_settings){
//
//            intent = new Intent(HomeActivity.this,SettingsActivity.class);
//            startActivity(intent);
//            Toast.makeText(HomeActivity.this,"Settings clicked!",Toast.LENGTH_SHORT).show();
//        }
//
//        if (item.getItemId() == R.id.action_notifications){
//
//            intent = new Intent(ProductsListActivity.this,NotificationsActivity.class);
//            startActivity(intent);
//            Toast.makeText(HomeActivity.this,"Notifications clicked!",Toast.LENGTH_SHORT).show();
//        }
//
//        return true;
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.dashboard_menu,menu);
//
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        mToolbar = findViewById(R.id.dashboard_toolbar);
        progressBar = findViewById(R.id.progressbar);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();

        recyclerView = findViewById(R.id.products_recyclerView);
        manager = new GridLayoutManager(ProductsListActivity.this, 1);
        recyclerView.setLayoutManager(manager);
        products = new ArrayList<Product>();

        getProducts();

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

}