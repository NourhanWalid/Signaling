package activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;
import com.example.onlineshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import app.AppConfig;
import helper.SQLiteHandler;
import helper.SessionManager;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder> {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Context mContext;
    private List<FinalEntry> finalentry = new ArrayList<>();
    private Instant Glide;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private ProgressBar progressBar;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;


    public RecyclerAdapter2 (Context context,List<FinalEntry> finalentry){
        this.mContext = context;
        this.finalentry = finalentry;
        System.out.println(finalentry.size());

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mContainer;
        private TextView mShopName;
        private TextView mPrice;
        private TextView mSpecialOffers;
        private TextView mDistance;
        private Button msave;

        public MyViewHolder (View view){
            super(view);

            mShopName = view.findViewById(R.id.shopname);
            mPrice = view.findViewById(R.id.price);
            mSpecialOffers = view.findViewById(R.id.specialoffers);
            mDistance = view.findViewById(R.id.distance);
            mContainer = view.findViewById(R.id.product_container);
            msave = (Button) view.findViewById(R.id.save);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout2,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final FinalEntry finalEntry = finalentry.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        //Glide.with(RecyclerAdapter.this).load(product.getImage_url()).into(holder.mImageView);


        holder.mShopName.setText(finalEntry.getShop_name());
        holder.mPrice.setText("Price: " +finalEntry.getPrice() + "LE");
        holder.mSpecialOffers.setText("Special Offer: " + finalEntry.getSpecialoffers());
        holder.mDistance.setText("Distance to Shop: " + finalEntry.getDistance() + " Km");


        //holder.mName.setText(product.getName());


//        holder.mContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(mContext,ShopActivity.class);
//
//                intent.putExtra("shopname",finalEntry.getShop_name());
//                intent.putExtra("Price",finalEntry.getPrice());
//                intent.putExtra("SpecialOffers",finalEntry.getSpecialoffers());
//                intent.putExtra("Distance",finalEntry.getDistance());
////                intent.putExtra("image_url",product.getImage_url());
////                intent.putExtra("description",product.getDescription());
//
//
//                mContext.startActivity(intent);
//
//            }
//        });
    }

    //@Override
    public int getItemCount() {
        return finalentry.size();
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
//    private void savedItem(saveditem.get(position).getProduct_name(),saveditem.get(position).getShop_name(),saveditem.get(position).getPrice(),  saveditem.get(position).getSpecialoffers(), saveditem.get(position).getDistance()) {
//        // Tag used to cancel the request
//        String tag_string_req = "req_register";
//
//        pDialog.setMessage("Saving ...");
//        showDialog();
//
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                AppConfig.URL_SAVE, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "Save Response: " + response.toString());
//                hideDialog();
//
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    boolean error = jObj.getBoolean("error");
//                    if (!error) {
//                        // User successfully stored in MySQL
//                        // Now store the user in sqlite
//
//
//                        //DIDN'T ADD ADDRESS AND PHONE YET
//
//                        // Inserting row in users table
//                        db.addUser(name, email, uid, address, phonenumber,created_at);
//
//                        Toast.makeText(mContext.getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();
//
//                        // Launch login activity
//                        Intent intent = new Intent(
//                                mContext,
//                                SavedActivity.class);
//                        startActivity();
//                        finish();
//                    } else {
//
//                        // Error occurred in registration. Get the error
//                        // message
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(mContext.getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException | JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Registration Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_LONG).show();
//                hideDialog();
//            }
//        });};
}
