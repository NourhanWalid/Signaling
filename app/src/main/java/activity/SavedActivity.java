package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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

public class SavedActivity extends AppCompatActivity {
    private static  final String BASE_URL = "http://192.168.1.7/android_api/include/getSavedShops.php";
    public static List<SavedShop> savedShops;
    private RecyclerView.Adapter mAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    public static String useremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        savedShops=new ArrayList<SavedShop>();
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.products_recyclerView);
        manager = new GridLayoutManager(SavedActivity.this, 1);
        recyclerView.setLayoutManager(manager);
        useremail=LoginActivity.email;
        getSavedShops();


    }
    private void getSavedShops (){
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("nnnnn", "onResponse:" +response);
                        progressBar.setVisibility(View.GONE);

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String email = object.getString("email");
                                String shop_name = object.getString("shop_name");
                                String product_name = object.getString("product_name");
                                String price = object.getString("price");
                                String special_offers = object.getString("special_offers");

                                SavedShop shop = new SavedShop(email,shop_name,product_name,price,special_offers);
                                if(shop.getEmail().equals(useremail)){
                                    savedShops.add(shop);
                                };

                            }

                        }catch (Exception e){

                        }

                        RecyclerAdapter3 mAdapter = new RecyclerAdapter3(SavedActivity.this, savedShops);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(SavedActivity.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(SavedActivity.this).add(stringRequest);

    }
}
