package com.example.newera;

import static com.example.newera.DeliveryActivity.SELECT_ADDRESS;

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
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.newera.databinding.ActivityCategoryBinding;
import com.example.newera.databinding.ActivityMyAddressesBinding;
import com.example.newera.databinding.MyAddressesBinding;

import java.util.ArrayList;
import java.util.List;

public class MyAddressesActivity extends AppCompatActivity {
    private ActivityMyAddressesBinding binding;
    private RecyclerView myAddressesRecyclerView;
    private Button deliverHereBtn;
    private static AddressesAdapter addressesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_addresses);

        binding = ActivityMyAddressesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        String title = "My Addresses";
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        myAddressesRecyclerView = findViewById(R.id.addresses_recyclerView);
        deliverHereBtn = findViewById(R.id.deliver_here_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Massimo Marongiu", "Via Pascoli 4", "3491954725", true));
        addressesModelList.add(new AddressesModel("John Doe", "Via Pascwerwefsdfoli 4", "34534634", false));
        addressesModelList.add(new AddressesModel("Giulio Marongiu", "Via Passdferwecoli 4", "3462234234", false));
        addressesModelList.add(new AddressesModel("asd Marongiu", "Via Pascoasdali 4", "23453453", false));
        addressesModelList.add(new AddressesModel("qwer Marongiu", "Via Paasdafscoli 4", "34563456", false));
        addressesModelList.add(new AddressesModel("esc Marongiu", "Via Passdfsdcoli 4", "36745635", false));
        addressesModelList.add(new AddressesModel("bar Marongiu", "Via Passdfsdcoli 4", "34634634", false));

        int mode = getIntent().getIntExtra("MODE", -1);
//****************************da rivedere****************
        if (mode == SELECT_ADDRESS) {
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else{
            deliverHereBtn.setVisibility(View.GONE);
        }
        addressesAdapter = new AddressesAdapter(addressesModelList, mode);
        myAddressesRecyclerView.setAdapter(addressesAdapter);
        ((SimpleItemAnimator) myAddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();

    }

    public static void refreshItem(int deselect, int select) {
        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}