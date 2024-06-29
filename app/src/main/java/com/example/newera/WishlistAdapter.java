package com.example.newera;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    private List<WishlistModel> wishlistModelList;
    private Boolean wishlist;

    public WishlistAdapter(List<WishlistModel> wishlistModelList, Boolean wishlist) {
        this.wishlistModelList = wishlistModelList;
        this.wishlist = wishlist;
    }

    @NonNull
    @Override
    public WishlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent, false);
        return new ViewHolder(view);
    }

//onBindViewHolder viene chiamato quando la RecyclerView richiede di visualizzare i dati in una determinata posizione.
    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.ViewHolder holder, int position) {
        int resource = wishlistModelList.get(position).getProductImage();
        String title = wishlistModelList.get(position).getProductTile();
        int freeCoupens = wishlistModelList.get(position).getFreeCoupens();
        String rating = wishlistModelList.get(position).getRating();
        int totalRatings = wishlistModelList.get(position).getTotalRatings();
        String productPrice = wishlistModelList.get(position).getProductPrice();
        String cuttedPrice = wishlistModelList.get(position).getCuttedPrice();
        String paymentMethod = wishlistModelList.get(position).getPaymentMethod();
        holder.setData(resource,title,freeCoupens,rating,totalRatings,productPrice,cuttedPrice,paymentMethod);
    }

    //ViewHolder mantiene i riferimenti alle viste di un elemento della lista,
// evitando di doverli cercare ripetutamente ogni volta che l'elemento deve essere visualizzato.
    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupens;
        private ImageView coupenIcon;
        private TextView rating;
        private TextView totalRatings;
        private View priceCut;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;
        private ImageButton deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupens = itemView.findViewById(R.id.free_coupen);
            coupenIcon = itemView.findViewById(R.id.coupen_icon);
            rating = itemView.findViewById(R.id.tv_product_rating_miniview);
            totalRatings = itemView.findViewById(R.id.total_ratings);
            priceCut = itemView.findViewById(R.id.price_cut);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

        }

        private void setData(int resource, String title, int freeCoupensNo, String averageRate,
                             int totalRatingsNo, String price, String cuttedPriceValue, String payMethod) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCoupensNo != 0) {
//                coupenIcon.setVisibility(View.VISIBLE);
                if (freeCoupensNo == 1) {
                    freeCoupens.setText("free " + freeCoupensNo + " coupen");
                }else {
                    freeCoupens.setText("free " + freeCoupensNo + " coupens");
                }
            }else{
                coupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);
            }
            rating.setText(averageRate);
            totalRatings.setText(totalRatingsNo+" (ratings)");
            productPrice.setText(price);
            cuttedPrice.setText(cuttedPriceValue);
            paymentMethod.setText(payMethod);

            if(wishlist){
                deleteBtn.setVisibility(View.VISIBLE);
            }
            else{
                deleteBtn.setVisibility(View.GONE);
            }

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "delete",Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }
}
