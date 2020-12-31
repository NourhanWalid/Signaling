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

public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.MyViewHolder> {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Context mContext;
    private List<SavedShop> savedShops = new ArrayList<>();
    private Instant Glide;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private ProgressBar progressBar;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;


    public RecyclerAdapter3(Context context, List<SavedShop> savedShops) {
        this.mContext = context;
        this.savedShops = savedShops;
        System.out.println(savedShops.size());

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mContainer;
        private TextView mShopName;
        private TextView mPrice;
        private TextView mSpecialOffers;
        private TextView productname;

        public MyViewHolder(View view) {
            super(view);

            mShopName = view.findViewById(R.id.shopname);
            mPrice = view.findViewById(R.id.price);
            mSpecialOffers = view.findViewById(R.id.specialoffers);
            mContainer = view.findViewById(R.id.product_container);
            productname = view.findViewById(R.id.productname);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout3, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final SavedShop shop = savedShops.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        //Glide.with(RecyclerAdapter.this).load(product.getImage_url()).into(holder.mImageView);


        holder.mShopName.setText(shop.getShop_name());
        holder.mPrice.setText("Price: " + shop.getPrice() + "LE");
        holder.mSpecialOffers.setText("Special Offers: " + shop.getSpecial_offers());
        holder.productname.setText(shop.getProduct_name());


    }

    //@Override
    public int getItemCount() {
        return savedShops.size();
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
