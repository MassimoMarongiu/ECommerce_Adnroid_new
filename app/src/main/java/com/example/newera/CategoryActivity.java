package com.example.newera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.newera.databinding.ActivityCategoryBinding;
import com.example.newera.databinding.ActivityMain1Binding;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerView;
    private ActivityCategoryBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);


////////////////////////          Banner Slider         //////////////////////


        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.home_icon_red, "#007AE4"));
        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon, "#007AE4"));

        sliderModelList.add(new SliderModel(R.drawable.green_email, "#007AE4"));//rivedere
        sliderModelList.add(new SliderModel(R.drawable.redemail, "#007AE4"));//rivedere
//        sliderModelList.add(new SliderModel(R.drawable.logo));
//        sliderModelList.add(new SliderModel(R.drawable.ic_launcher_foreground));
        sliderModelList.add(new SliderModel(R.drawable.cart_foreground, "#007AE4"));
//        sliderModelList.add(new SliderModel(R.drawable.profile_placeholder_foreground));
        sliderModelList.add(new SliderModel(R.drawable.home_icon_red, "#007AE4"));
        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon, "#007AE4"));

        sliderModelList.add(new SliderModel(R.drawable.green_email, "#007AE4"));//rivedere
        sliderModelList.add(new SliderModel(R.drawable.redemail, "#007AE4"));//rivedere


///////////////////////          Banner Slider end         //////////////////////

///////////////////////    horizontal product Layout deals of the day         //////////////////////

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao3, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.home_icon_red, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.cart_foreground, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.custom_error_icon, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.green_email, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.redemail, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.cart_foreground, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.custom_error_icon, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.home_icon_red, "Redmi 5A", "SD 625 Processor", "RS.5999/-"));

///////////////////////      horizontal product Layout end  deals of the day    //////////////////////


////////////////////        RecyclerView testing    ///////////////////////
////////////////////       restituisce più righe del banner e delle attivta    ///////////////////////

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.banner2 ));
        homePageModelList.add(new HomePageModel(2, "Deal of the Day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deal of the Day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.banner2 ));
        homePageModelList.add(new HomePageModel(3, "Deal of the Day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2, "Deal of the Day", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.banner2));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
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
//            todo:search
            return true;
        }else if(id == android.R.id.home){
//            da category slider
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}