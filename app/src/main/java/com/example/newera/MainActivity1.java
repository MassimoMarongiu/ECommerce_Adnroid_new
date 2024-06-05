package com.example.newera;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newera.databinding.ActivityMain1Binding;
import com.google.android.material.navigation.NavigationBarView;

import androidx.appcompat.app.ActionBarDrawerToggle;



public class MainActivity1 extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain1Binding binding;

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain1.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


//        DrawerLayout drawer = binding.drawerLayout;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBarMain1.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main1);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        nav_header_main1
        navigationView.getMenu().getItem(0).setChecked(true);

//        fragment_home2
        frameLayout = findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment());//chiama metodo
    }
    //fragment_home
    private void  setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity1, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main1);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //main_activity1
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {
            return true;
        } else if (id == R.id.main_notification_icon) {
            return true;
        } else if (id == R.id.main_cart_icon) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //activity_main_drawer
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
        }else if (id == R.id.nav_my_orders) {
            // Handle the navigation action here
            return true;
        } else if (id == R.id.nav_my_rewards) {
            // Handle the navigation action here
            return true;
        } else if (id == R.id.nav_my_cart) {
            // Handle the navigation action here
            return true;
        } else if (id == R.id.nav_my_wishlist) {
            // Handle the navigation action here
            return true;
        } else if (id == R.id.nav_my_account) {
            // Handle the navigation action here
            return true;
        } else if (id == R.id.nav_sign_out) {
            // Handle the navigation action here
            return true;
        }
        return false;
    }


}