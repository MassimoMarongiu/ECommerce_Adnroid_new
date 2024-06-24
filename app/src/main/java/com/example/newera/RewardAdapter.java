package com.example.newera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.Viewholder> {
    //    1
    private List<RewardModel> rewardModelList;

    public RewardAdapter(List<RewardModel> rewardModelList) {
        this.rewardModelList = rewardModelList;
    }

    //3
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout,parent,false);
        return new Viewholder(view);
    }
    //   4
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String title = rewardModelList.get(position).getTitle();
        String date = rewardModelList.get(position).getExpiryDate();
        String body = rewardModelList.get(position).getCoupenBody();
        holder.setData(title,date,body);
    }
    //   5
    @Override
    public int getItemCount() {
        return rewardModelList.size();
    }
    //   3
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView coupenTitle;
        private TextView coupenExpiryDate;
        private TextView coupenBody;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            coupenTitle=itemView.findViewById(R.id.coupen_title);
            coupenExpiryDate=itemView.findViewById(R.id.coupen_validity);
            coupenBody=itemView.findViewById(R.id.coupen_body);
        }
        private void setData(String title,String date,String body){
            coupenTitle.setText(title);
            coupenExpiryDate.setText(date);
            coupenBody.setText(body);
        }
    }

}
