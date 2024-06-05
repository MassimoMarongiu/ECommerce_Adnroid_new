package com.example.newera;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newera.databinding.ActivityCategoryBinding;

import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private ActivityCategoryBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
//        setSupportActionBar(binding.appBarLayout.findViewById(R.id.app_bar_main1));
//        setContentView(binding.getRoot());

//        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
//        setSupportActionBar(binding.toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);

//        ****************************  BANNER SLIDER ****************************

        List<SliderModel>sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.home_red, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.green_email, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.red_email, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.logo, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.cart, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.home_red, "#077AE4"));


        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.green_email, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.red_email, "#077AE4"));

//        **************************** BANNER SLIDER****************************

//        **************************** HORIZONTAL PRODUCT LAYOUT ****************************

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao1, "RedMi K", "Sd 425 Processor", "€ 250,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao3, "Samsung Mr4", "Sd 625 Processor", "€ 300,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao4, "I-Phone 20", "Sd 1425 Processor", "€ 1450,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao5, "Nokia 3321", "Sd 225 Processor", "€ 200,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao1, "Oppo", "Sd 825 Processor", "€ 720,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao3, "Samsung Mr4", "Sd 625 Processor", "€ 300,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao4, "I-Phone 20", "Sd 1425 Processor", "€ 1450,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao5, "Nokia 3321", "Sd 225 Processor", "€ 200,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.logo, "Home", "", ""));

//        **************************** HORIZONTAL PRODUCT LAYOUT****************************

//        ********************************************************
//        RecyclerView testing = view.findViewById(R.id.home_page_reciclerview);
        LinearLayoutManager testingLinearLayoutManager = new LinearLayoutManager(this);
        testingLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLinearLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner1,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals of the Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the Day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner2,"#ffff00"));

        HomePageAdapter adapter= new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {
            return true;
        }else if (id== android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}