package com.example.newera;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModels) {
        this.horizontalProductScrollModelList = horizontalProductScrollModels;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder holder, int position) {
        int resource = horizontalProductScrollModelList.get(position).getProductImage();
        String title = horizontalProductScrollModelList.get(position).getProductTitle();
        String description = horizontalProductScrollModelList.get(position).getProductDescription();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();

        holder.setProductImage(resource);
        holder.setProductTitle(title);
        holder.setProductDescritption(description);
        holder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {
        if (horizontalProductScrollModelList.size() > 8) {
            return 8;
        } else {
            return horizontalProductScrollModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescritption;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescritption = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    productDetailsIntent.putExtra("ProductName", productTitle.getText().toString());
                    itemView.getContext().startActivities(new Intent[]{productDetailsIntent});

                }
            });
        }

        public void setProductImage(int resource) {
            productImage.setImageResource(resource);
        }

        public void setProductTitle(String title) {
            productTitle.setText(title);
        }

        public void setProductDescritption(String descritption) {
            productDescritption.setText(descritption);
        }

        public void setProductPrice(String price) {
            productPrice.setText(price);
        }
    }
}
