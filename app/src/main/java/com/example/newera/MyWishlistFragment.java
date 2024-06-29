package com.example.newera;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MyWishlistFragment extends Fragment {

    public MyWishlistFragment() {
        // Required empty public constructor
    }

    private RecyclerView wishlistRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        wishlistRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);

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

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList, true);
        wishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();

        return view;

    }
}