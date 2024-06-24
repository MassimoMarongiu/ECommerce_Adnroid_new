package com.example.newera;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyRewardsFragment extends Fragment {

    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);

        rewardRecyclerView = view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rewardRecyclerView.setLayoutManager(layoutManager);

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

        RewardAdapter myRewardAdapter = new RewardAdapter(rewardModelList);
        rewardRecyclerView.setAdapter(myRewardAdapter);
        myRewardAdapter.notifyDataSetChanged();
        return view;
    }
}