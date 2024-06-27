package com.example.newera;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerView;
    private Button continueBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recyclerview);
        continueBtn = view.findViewById(R.id.cart_continue_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        //cart items
        cartItemModelList.add(new CartItemModel(0, R.drawable.ciao3, "Samsung M33", 2, "€ 199.90", "€ 220.00", 1, 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.ciao3, "Samsung M33", 0, "€ 199.90", "€ 220.00", 3, 1, 1));
        cartItemModelList.add(new CartItemModel(0, R.drawable.ciao3, "Samsung M33", 2, "€ 199.90", "€ 220.00", 2, 0, 0));
        //total amount
        cartItemModelList.add(new CartItemModel(1, "Price (3 items)", "€ 400.00", "Free", "€ 400.00", "€ 199.90"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(),AddressActivity.class);
//                Intent deliveryIntent = new Intent(getContext(),DeliveryActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });

        return view;


    }
}