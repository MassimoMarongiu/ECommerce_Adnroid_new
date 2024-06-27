package com.example.newera;

import static com.example.newera.DeliveryActivity.SELECT_ADDRESS;
import static com.example.newera.MyAccountFragment.MANAGE_ADDRESS;
import static com.example.newera.MyAddressesActivity.refreshItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.Viewholder> {
    private List<AddressesModel> addressesModelList;
    private int MODE; //riprende parametri da deliveryactivity e my account fragment
    private int preselectedPosition = -1;


    public AddressesAdapter(List<AddressesModel> addressesModelList, int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
//    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    public AddressesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressesAdapter.Viewholder holder, int position) {
        String name = addressesModelList.get(position).getFullname();
        String address = addressesModelList.get(position).getAddress();
        String pincode = addressesModelList.get(position).getPincode();
        Boolean selected = addressesModelList.get(position).getSelected();
        holder.setData(name, address, pincode, selected, position);

    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView fullname;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        private LinearLayout optionContainer;
        private boolean iconOnOff = false;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
            icon = itemView.findViewById(R.id.icon_view);
            optionContainer = itemView.findViewById(R.id.option_container);

        }

        private boolean isItemViewVisible = false;

        private void setData(String username, String userAddress, String userPincode, Boolean selected, int position) {
            fullname.setText(username);
            address.setText(userAddress);
            pincode.setText(userPincode);

            if (MODE == SELECT_ADDRESS) {
                icon.setImageResource(R.drawable.check);
                optionContainer.setVisibility(View.GONE);

                if (selected) {
                    icon.setVisibility(View.VISIBLE);
                    preselectedPosition = position;
                } else {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (preselectedPosition != position) {
                            addressesModelList.get(position).setSelected(true);
                            addressesModelList.get(preselectedPosition).setSelected(false);
                            refreshItem(preselectedPosition, position);
                            preselectedPosition = position;
                        }
                    }
                });
            } else if (MODE == MANAGE_ADDRESS) {
                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.mipmap.vertical_dots);

                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (preselectedPosition != -1 && preselectedPosition != position) {
                            // Nascondi il vecchio elemento selezionato
                            addressesModelList.get(preselectedPosition).setSelected(false);
                            notifyItemChanged(preselectedPosition);
                        }
                        isItemViewVisible = !isItemViewVisible;
                        if (isItemViewVisible) {
                            optionContainer.setVisibility(View.VISIBLE);
                            icon.setVisibility(View.GONE);
                        } else {
                            optionContainer.setVisibility(View.GONE);
                            icon.setVisibility(View.VISIBLE);
                        }
                        refreshItem(preselectedPosition, preselectedPosition);
                        preselectedPosition = position;
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isItemViewVisible = !isItemViewVisible;
                        if (isItemViewVisible) {
                            optionContainer.setVisibility(View.VISIBLE);
                            icon.setVisibility(View.GONE);

                        } else {
                            optionContainer.setVisibility(View.GONE);
                            icon.setVisibility(View.VISIBLE);

                        }
                        refreshItem(preselectedPosition, preselectedPosition);
                        preselectedPosition = -1;
                    }
                });
            }
        }
    }
}
