package com.example.newera;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.newera.databinding.ActivityCategoryBinding;
import com.example.newera.databinding.ActivityMain1Binding;
import com.example.newera.databinding.ActivityProductDetailsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ActivityProductDetailsBinding binding;
    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistBtn;

    //    product_description_layout
    private ViewPager productDetailsViepager;
    private TabLayout productDetailsTabLayout;
    //////////////////////// rating layout
        private LinearLayout rateNowContainer;
    ////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product_details_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurazione della toolbar
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        String title = getIntent().getStringExtra("ProductName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        immagine prodotto
        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.view_pager_indicator);
        addToWishlistBtn = findViewById(R.id.add_to_wishlist_btn);

//        sezione layout descrizione,specifiche, other details
        productDetailsViepager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout);

        //sezione immagini prodotto
        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.ciao1);
        productImages.add(R.drawable.ciao3);
        productImages.add(R.drawable.ciao4);
        productImages.add(R.drawable.ciao5);

        ProductImagesAdapter adapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(adapter);

        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);

//        bottone preferiti
        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishlistBtn.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                } else {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishlistBtn.setSupportBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                }
            }
        });

        //        sezione layout descrizione,specifiche, other details
        //vedi product details adapter che richiama product specification adapter
        productDetailsViepager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));
        productDetailsViepager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViepager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //////////////////////// rating layout

        rateNowContainer = findViewById(R.id.rate_now_container);
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }

        //////////////////////// rating layout


    }

    private void setRating(int starPosition) {
        for (int i = 0; i < rateNowContainer.getChildCount(); i++) {
            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(i);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#809A9A9A")));
            if(i<= starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#EC8868")));
            }
        }
    }

    //    appbar bottoni
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.main_search_icon) {
//            todo:search
            return true;
        } else if (id == R.id.main_cart_icon) {
//            todo:cart
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}