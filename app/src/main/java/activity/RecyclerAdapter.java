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
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> products = new ArrayList<>();
    private Instant Glide;
    public static String product_name;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private ProgressBar progressBar;

    //private static  final String BASE_URL = "http://71.1.2.13/android_api/include/ProductShops.php";

    public RecyclerAdapter (Context context,List<Product> products){
        this.mContext = context;
        this.products = products;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mName, mDescription;
        private ImageView mImageView;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            mName = view.findViewById(R.id.product_title);
            mImageView = view.findViewById(R.id.product_image);
            mDescription = view.findViewById(R.id.product_description);
            mContainer = view.findViewById(R.id.product_container);


        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            final Product product = products.get(position);
            product_name=product.getName();


           // getShops();
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);



            //Glide.with(RecyclerAdapter.this).load(product.getImage_url()).into(holder.mImageView);
            holder.mDescription.setText(product.getDescription());
            holder.mName.setText(product.getName());


            holder.mContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext,DetailedProductsActivity.class);

                    intent.putExtra("name",product.getName());
                    intent.putExtra("image_url",product.getImage_url());
                    intent.putExtra("description",product.getDescription());


                    mContext.startActivity(intent);

                }
            });
    }

    //@Override
    public int getItemCount() {
        return products.size();
    }

}