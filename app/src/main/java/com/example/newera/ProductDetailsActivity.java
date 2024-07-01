package com.example.newera;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.newera.databinding.ActivityCategoryBinding;
import com.example.newera.databinding.ActivityMain1Binding;
import com.example.newera.databinding.ActivityProductDetailsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.newera.MainActivity1.showCart;

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

    private Button buyNowBtn;
    //tut 47 redeembutton ->connesso rewardAdapter e myrewardfragment
    private Button coupenRedeemBtn;
    public static TextView coupenTItle;
    public static TextView coupenExpiryDate;
    public static TextView coupenBody;
    private static RecyclerView coupenRecyclerView;
    private static LinearLayout selectedCoupen;
    //tut 47 redeembutton end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);

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

        buyNowBtn = findViewById(R.id.buy_now_btn);
        //tut 47 redeembutton
        coupenRedeemBtn = findViewById(R.id.coupen_redemption_btn);

        //tut 47 redeembutton end

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

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(ProductDetailsActivity.this, DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });
        //tut 47 redeembutton
        final Dialog checkCoupenPriceDialog = new Dialog(ProductDetailsActivity.this);
        checkCoupenPriceDialog.setContentView(R.layout.coupen_redeem_dialog);
        checkCoupenPriceDialog.setCancelable(true);
        checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggleRedcyclerView = checkCoupenPriceDialog.findViewById(R.id.toggle_recyclerview);
        coupenRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupens_recyclerview);
        selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupen);
        coupenTItle = checkCoupenPriceDialog.findViewById(R.id.coupen_title);
        coupenExpiryDate = checkCoupenPriceDialog.findViewById(R.id.coupen_validity);
        coupenBody = checkCoupenPriceDialog.findViewById(R.id.coupen_body);
        TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.original_price);
        TextView discountedPrice = checkCoupenPriceDialog.findViewById(R.id.discounted_price);

        coupenRecyclerView.setVisibility(View.GONE);


        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coupenRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Discount", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Discount", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Cashback", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Discount", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "till 2nd, June 2022", "GET 20% OFF on any product above € 120.00 and below € 200.00 with this item"));

        RewardAdapter myRewardAdapter = new RewardAdapter(rewardModelList, false);
        coupenRecyclerView.setAdapter(myRewardAdapter);
        myRewardAdapter.notifyDataSetChanged();
        toggleRedcyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogReciclerView();
            }
        });
        coupenRedeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCoupenPriceDialog.show();
            }
        });
        //tut 47 redeembutton end

    }

    //tut 47 redeembutton
    public static void showDialogReciclerView() {
        if (coupenRecyclerView.getVisibility() == View.GONE) {
            coupenRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);
        } else {
            coupenRecyclerView.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);
        }
    }

    //tut 47 redeembutton end
    private void setRating(int starPosition) {
        for (int i = 0; i < rateNowContainer.getChildCount(); i++) {
            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(i);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#809A9A9A")));
            if (i <= starPosition) {
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
            //tut45
            Intent cartIntent = new Intent(ProductDetailsActivity.this, MainActivity1.class);
            showCart = true;
            startActivity(cartIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}