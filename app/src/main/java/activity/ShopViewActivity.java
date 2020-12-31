package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onlineshop.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.AppConfig;
import app.AppController;
import helper.SQLiteHandler;
import helper.SessionManager;

public class ShopViewActivity extends AppCompatActivity {

    private static final String TAG = ShopViewActivity.class.getSimpleName();
    private TextView mshopname, mproductname, mprice, mdistance, mspecialoffers;
    private Button btnSaveShop;
    //private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    public static String email;
    private Button btnDeleteSavedShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_view);
        mshopname = findViewById(R.id.shopname);
        mproductname = findViewById(R.id.productname);
        mprice = findViewById(R.id.price);
        mdistance = findViewById(R.id.distance);
        mspecialoffers = findViewById(R.id.specialoffers);
        btnSaveShop = findViewById(R.id.btnSaveShop);
        btnDeleteSavedShop=findViewById(R.id.btnDeleteSavedShop);
        session = new SessionManager(getApplicationContext());
        email = LoginActivity.email;

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        Intent intent = getIntent();
        final String price = intent.getStringExtra("price");
        final String shopname = intent.getStringExtra("shopname");
        final String productname = intent.getStringExtra("productname");
        String distance = intent.getStringExtra("distance");
        final String specialoffers = intent.getStringExtra("specialoffers");

        if (intent != null) {

            mprice.setText("Price: " + price + " LE");
            mshopname.setText(shopname);
            mproductname.setText(productname);
            mdistance.setText("Distance to Shop: " + distance + " KM");
            mspecialoffers.setText("Special Offers: " + specialoffers);
        }

        btnSaveShop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Log.d("1", "1");
                //  Tag used to cancel the request
                String tag_string_req = "req_save";

                // pDialog.setMessage("Saving ...");
                //showDialog();
                //request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue queue = Volley.newRequestQueue(ShopViewActivity.this);

                String URL_SAVESHOP = "http://71.1.2.108/android_api/include/saveShop.php";
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        URL_SAVESHOP, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Save Response: " + response.toString());
                        // hideDialog();
                        Log.d("2", "2");
                        try {

                            JSONObject jObj = new JSONObject(response);

                            Log.d(TAG ,"SSSSSSSSSSSSSSSSSS"+response) ;
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                                Log.d("3", "3");
                                // User successfully stored in MySQL
                                // Now store the user in sqlite


                                //DIDN'T ADD ADDRESS AND PHONE YET

                                // Inserting row in users table
                                //db.addShop(email, shopname, productname, price, specialoffers);

                                Toast.makeText(getApplicationContext(), "Shop successfully saved!", Toast.LENGTH_LONG).show();
                                System.out.println();
                                // Launch login activity
//                        Intent intent = new Intent(ShopViewActivity.this,SavedActivity.class);
//                        startActivity();
//                        finish();

                                System.out.println("3");
                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                String errorMsg = jObj.getString("error_msg");
                                Log.d("4", "4");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                , new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("5", "5");
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        //hideDialog();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        params.put("shop_name", shopname);
                        params.put("product_name", productname);
                        params.put("price", price);
                        params.put("special_offers", specialoffers);


                        Log.d(TAG,"NNNNNNN"+email);
                        return params;
                    }

                };

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
            }

        });


        btnDeleteSavedShop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String tag_string_req = "req_delete";

                // pDialog.setMessage("Saving ...");
                //showDialog();
                //request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue queue = Volley.newRequestQueue(ShopViewActivity.this);

                String URL_DELETESAVEDSHOP = "http://71.1.2.108/android_api/include/deleteSavedShop.php";
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        URL_DELETESAVEDSHOP, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "DDDDDDDDD:" +response);
                        Log.d("2", "2");
                        try {

                            JSONObject jObj = new JSONObject(response);

                            Log.d(TAG ,"SSSSSSSSSSSSSSSSSS"+response) ;
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                                Log.d("3", "3");

                                Toast.makeText(getApplicationContext(), "Shop successfully deleted!", Toast.LENGTH_LONG).show();
                                System.out.println();

                                System.out.println("3");
                            } else {

                                // Error occurred in registration. Get the error
                                // message
                                String errorMsg = jObj.getString("error_msg");
                                Log.d("4", "4");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                        , new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("5", "5");
                        Log.e(TAG, "Deletion Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        //hideDialog();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);
                        params.put("shop_name", shopname);
                        params.put("product_name", productname);


                        Log.d(TAG,"NNNNNNN "+email);
                        Log.d(TAG,"SSSSSNNN "+shopname);
                        Log.d(TAG,"NNNNSSSSSNNN "+productname);
                        return params;
                    }

                };

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
            }


            });
    }
    }