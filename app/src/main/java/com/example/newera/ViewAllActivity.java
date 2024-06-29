package com.example.newera;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newera.databinding.ActivityCategoryBinding;
import com.example.newera.databinding.ActivityViewAllBinding;

import java.util.ArrayList;
import java.util.List;

//collegamento da wishlistadapter
public class ViewAllActivity extends AppCompatActivity {
    private ActivityViewAllBinding binding;
    private RecyclerView recyclerView;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_all);


        binding = ActivityViewAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        String title = getIntent().getStringExtra("Deal of the Day");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);

        int layout_code= getIntent().getIntExtra("layout_code",-1);//da homepageadapter

        if(layout_code == 0){
            recyclerView.setVisibility(View.VISIBLE);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            List<WishlistModel> wishlistModelList = new ArrayList<>();
            wishlistModelList.add(new WishlistModel(R.drawable.ciao1, "Pixel 2 ", 1, "3", 145, "€ 50.90", "€ 70.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao3, "Pixel 3 ", 1, "4", 20, "€ 100.90", "€ 150.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao4, "Pixel 4 ", 1, "1", 14, "€ 19.90", "€ 20.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao5, "Pixel 5 ", 1, "2", 15, "€ 99.90", "€ 100.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao1, "Pixel 6 ", 1, "3", 45, "€ 199.90", "€ 220.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao1, "Pixel 6 ", 1, "3", 45, "€ 199.90", "€ 220.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao1, "Pixel 6 ", 1, "3", 45, "€ 199.90", "€ 220.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao5, "Pixel 5 ", 1, "2", 15, "€ 99.90", "€ 100.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao1, "Pixel 6 ", 1, "3", 45, "€ 199.90", "€ 220.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao1, "Pixel 6 ", 1, "3", 45, "€ 199.90", "€ 220.00", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.ciao1, "Pixel 6 ", 1, "3", 45, "€ 199.90", "€ 220.00", "Cash on delivery"));

            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else if (layout_code == 1){
            gridView.setVisibility(View.VISIBLE);
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
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao1, "RedMi K", "Sd 425 Processor", "€ 250,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao3, "Samsung Mr4", "Sd 625 Processor", "€ 300,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao4, "I-Phone 20", "Sd 1425 Processor", "€ 1450,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao5, "Nokia 3321", "Sd 225 Processor", "€ 200,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao1, "Oppo", "Sd 825 Processor", "€ 720,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao3, "Samsung Mr4", "Sd 625 Processor", "€ 300,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao4, "I-Phone 20", "Sd 1425 Processor", "€ 1450,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao5, "Nokia 3321", "Sd 225 Processor", "€ 200,00"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.logo, "Home", "", ""));

            GridProductViewAdapter gridProductViewAdapter = new GridProductViewAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductViewAdapter);
        }




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}