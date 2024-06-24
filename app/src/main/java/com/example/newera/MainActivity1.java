package com.example.newera;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
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


public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain1Binding binding;
    //Per le icone a destra nella barra oncreateoptionmenu
    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int REWARD_FRAGMENT = 3;
    private static final int MYWISHLIST_FRAGMENT = 4;

    private static int currentFragment =-1;

    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private ImageView actionbarLogo;
    private Window window;
//    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        actionbarLogo = findViewById(R.id.actionbar_logo);
//        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(binding.appBarMain1.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


//        DrawerLayout drawer = binding.drawerLayout;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBarMain1.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main1);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment(), HOME_FRAGMENT); // setta l'inizio nella homehome
//        setFragment(new OrderDetailsFragment(), HOME_FRAGMENT); // setta l'inizio nella homehome
    }

    //fragment_home
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main_activity1, menu);
        }
        return true;
    }

       //    barra menu icone
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icon) {
            return true;
        } else if (id == R.id.main_notification_icon) {
            return true;
        } else if (id == R.id.main_cart_icon) {
            gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ////////////      cart  /////////////////
    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
//        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateMenu();
        setFragment(fragment, fragmentNo);
        if (fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(2).setChecked(true); // da menu/activity_main_drawer seleziona il cart
//            Log.d(TAG, "Button clicked!");
        }
    }
//    } private void myCart() {
//        actionbarLogo.setVisibility(View.GONE);
//        getSupportActionBar().setTitle("My cart");
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        invalidateOptionsMenu();
//        setFragment(new MyCartFragment(), CART_FRAGMENT);
//        navigationView.getMenu().getItem(2).setChecked(true);
//    }

    //activity_main_drawer  menu sinistra
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {//0
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
            actionbarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
//            setFragment(new HomeFragment(), HOME_FRAGMENT);
            gotoFragment("My Home", new HomeFragment(), HOME_FRAGMENT);
            System.out.println("nav_home");
        } else if (id == R.id.nav_my_orders) {//1
            // Handle the navigation action here
            gotoFragment("My Orders", new MyOrdersFragment(),ORDERS_FRAGMENT);
            invalidateOptionsMenu();
//            return true;
        } else if (id == R.id.nav_my_cart) {//2
//            Log.d(TAG, "Button clicked!");
            System.out.println("my cart ");
            // Handle the navigation action here
            gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            invalidateOptionsMenu();
//            return true;
        } else if (id == R.id.nav_my_rewards) {//3
            // Handle the navigation action here
            gotoFragment("My Rewards",new MyRewardsFragment(),REWARD_FRAGMENT);
//            return true;
        } else if (id == R.id.nav_my_wishlist) {//4
            // Handle the navigation action here
            gotoFragment("my wishlist",new MyWishlistFragment(),MYWISHLIST_FRAGMENT);
//            return true;
        } else if (id == R.id.nav_my_account) {//5
            // Handle the navigation action here
            return true;
        } else if (id == R.id.nav_sign_out) {//6
            // Handle the navigation action here
            return true;
        }
//        return false;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment) {
            if (fragmentNo==REWARD_FRAGMENT) {
                window.setStatusBarColor(Color.parseColor("#3006BA"));
//                toolbar.setBackgroundColor(Color.parseColor("#3006BA"));
            }else{
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//                toolbar.setBackgroundColor(Color.parseColor("#3006BA"));

            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main1);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == HOME_FRAGMENT) {
                super.onBackPressed();

            } else {
                actionbarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                gotoFragment("My Home", new HomeFragment(), HOME_FRAGMENT);
                navigationView.getMenu().getItem(0).setChecked(true);
            }
        }
    }

}