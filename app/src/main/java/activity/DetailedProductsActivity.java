package activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineshop.R;

import java.time.Instant;

public class DetailedProductsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private ImageView mImage;
    private TextView mName;
    private TextView mDescription;
    private Instant Glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_products);

        mToolbar = findViewById(R.id.toolbar);
        mImage = findViewById(R.id.image_view);
        mDescription = findViewById(R.id.description);
        mName = findViewById(R.id.name);

        // Setting up action bar
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));

        // Catching incoming intent
        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        String name = intent.getStringExtra("name");
        String image_url = intent.getStringExtra("image_url");

        if (intent !=null){

            mActionBar.setTitle(name);
            mName.setText(name);
            mDescription.setText(String.valueOf(description));
          //  Glide.with().load(image_url).into(mImage);
        }

    }
}