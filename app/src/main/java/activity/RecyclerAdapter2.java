package activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder> {

    private Context mContext;
    private List<FinalEntry> finalentry = new ArrayList<>();
    private Instant Glide;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private ProgressBar progressBar;


    //private static  final String BASE_URL = "http://71.1.2.13/android_api/include/ProductShops.php";

    public RecyclerAdapter2 (Context context,List<FinalEntry> finalentry){
        this.mContext = context;
        this.finalentry = finalentry;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

//        private TextView mName, mDescription;
//        private ImageView mImageView;
        private LinearLayout mContainer;
        private TextView mShopName;
        private TextView mPrice;
        private TextView mSpecialOffers;
        private TextView mDistance;

        public MyViewHolder (View view){
            super(view);

            mShopName = view.findViewById(R.id.shopname);
            mPrice = view.findViewById(R.id.price);
            mSpecialOffers = view.findViewById(R.id.specialoffers);
            mDistance = view.findViewById(R.id.distance);
            mContainer = view.findViewById(R.id.product_container);


        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_detailed_products,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final FinalEntry finalEntry = finalentry.get(position);
        //product_name=product.getName();
        // getShops();
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
//                Intent intent = new Intent(mContext,DetailedProductsActivity.class);
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

}
