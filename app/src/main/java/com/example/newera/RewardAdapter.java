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
    private Boolean useMiniLayout = false; //tut47

//    public RewardAdapter(List<RewardModel> rewardModelList) {
//        this.rewardModelList = rewardModelList;
//    }

    //tut 47->connesso productdetailsactivity e my rewardfragment
    public RewardAdapter(List<RewardModel> rewardModelList, Boolean useMiniLayout) {
        this.rewardModelList = rewardModelList;
        this.useMiniLayout = useMiniLayout;
    }
//tut 47 end

    //3
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //tut 47
        View view;
        if (useMiniLayout) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_rewards_item_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
        }
        //tut 47 end
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
        return new Viewholder(view);
    }

    //   4
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String title = rewardModelList.get(position).getTitle();
        String date = rewardModelList.get(position).getExpiryDate();
        String body = rewardModelList.get(position).getCoupenBody();
        holder.setData(title, date, body);
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
            coupenTitle = itemView.findViewById(R.id.coupen_title);
            coupenExpiryDate = itemView.findViewById(R.id.coupen_validity);
            coupenBody = itemView.findViewById(R.id.coupen_body);
        }

        private void setData(String title, String date, String body) {
            coupenTitle.setText(title);
            coupenExpiryDate.setText(date);
            coupenBody.setText(body);
            //tut 47 redeem button
           if(useMiniLayout){
               itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       ProductDetailsActivity.coupenTItle.setText(title);
                       ProductDetailsActivity.coupenExpiryDate.setText(date);
                       ProductDetailsActivity.coupenBody.setText(body);
                       ProductDetailsActivity.showDialogReciclerView();
                   }
               });
           }
            // tut 47 redeem button end
        }
    }

}
