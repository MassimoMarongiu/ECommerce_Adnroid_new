package com.example.newera;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newera.databinding.ActivityDeliveryBinding;
import com.example.newera.databinding.ActivityMain1Binding;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {
    private ActivityDeliveryBinding binding;
    private RecyclerView deliveryRecyclerView;
    private Button changeORaddNewAddressBtn;
    public static final int SELECT_ADDRESS = 0; //connesso a addresses adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delivery);

        binding = ActivityDeliveryBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Delivery");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        deliveryRecyclerView = findViewById(R.id.delivery_recyclerView);
        changeORaddNewAddressBtn = findViewById(R.id.change_or_add_address_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        //cart items
        cartItemModelList.add(new CartItemModel(0, R.drawable.ciao3, "Samsung M33", 2, "€ 199.90", "€ 220.00", 1, 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.ciao3, "Samsung M33", 0, "€ 199.90", "€ 220.00", 3, 1, 1));
        cartItemModelList.add(new CartItemModel(0, R.drawable.ciao3, "Samsung M33", 2, "€ 199.90", "€ 220.00", 2, 0, 0));
        //total amount
        cartItemModelList.add(new CartItemModel(1, "Price (3 items)", "€ 400.00", "Free", "€ 400.00", "€ 199.90"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeORaddNewAddressBtn.setVisibility(View.VISIBLE);
        changeORaddNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressesIntent = new Intent(DeliveryActivity.this,MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(myAddressesIntent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
       if(id== android.R.id.home){
           finish();
           return true;
       }
        return super.onOptionsItemSelected(item);
    }
}