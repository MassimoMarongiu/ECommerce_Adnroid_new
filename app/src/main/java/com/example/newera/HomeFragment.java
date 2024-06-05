package com.example.newera;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //        ****************************category****************************
    private RecyclerView categoryReciclerView;
    private CategoryAdapter categoryAdapter;//
    //   ****************************category****************************

    //  ****************************banner slide****************************
    private SliderAdapter sliderAdapter;
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //        ****************************banner slide****************************

    //        ****************************strip ad layout****************************
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    //        ****************************strip ad layout****************************

    //        **************************** horizontal product layout****************************
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalReciclerView;

    private HorizontalProductScrollAdapter horizontalProductScrollAdapter;
    //        **************************** horizontal product layout****************************

    //        **************************** Grid product layout ****************************
    private TextView gridLayoutTitle;
    private Button gridLayoutViewAllBtn;
    private GridView gridView;

    //        **************************** Grid product layout ****************************

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

//        ****************************CATEGORY****************************
        categoryReciclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryReciclerView.setLayoutManager(layoutManager);

        final List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link", "Home"));
        categoryModelList.add(new CategoryModel("link", "Electronics"));
        categoryModelList.add(new CategoryModel("link", "Appliances"));
        categoryModelList.add(new CategoryModel("link", "Forniture"));
        categoryModelList.add(new CategoryModel("link", "Fashion"));
        categoryModelList.add(new CategoryModel("link", "Toys"));
        categoryModelList.add(new CategoryModel("link", "Sports"));
        categoryModelList.add(new CategoryModel("link", "Wall Arts"));
        categoryModelList.add(new CategoryModel("link", "Books"));
        categoryModelList.add(new CategoryModel("link", "Shoes"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryReciclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
//        ****************************CATEGORY****************************

//        ****************************banner slide****************************
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.home_red, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.green_email, "#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.red_email, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.logo, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.cart, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.home_red, "#077AE4"));


        sliderModelList.add(new SliderModel(R.drawable.custom_error_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.green_email, "#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.red_email, "#077AE4"));

        sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSLideShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlideShow();
                }
                return false;
            }
        });

//        ****************************banner slide****************************

//        ****************************Strip Ad****************************
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.banner2);
        stripAdContainer.setBackgroundColor(Color.parseColor("#000000"));
//        ****************************Strip Ad****************************

//        **************************** horizontal product layout****************************
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_layout_view_all_btn);
        horizontalReciclerView = view.findViewById(R.id.horizontal_scroll_layout_reciclerView);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao1, "RedMi K", "Sd 425 Processor", "€ 250,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao3, "Samsung Mr4", "Sd 625 Processor", "€ 300,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao4, "I-Phone 20", "Sd 1425 Processor", "€ 1450,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao5, "Nokia 3321", "Sd 225 Processor", "€ 200,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao1, "Oppo", "Sd 825 Processor", "€ 720,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao3, "Samsung Mr4", "Sd 625 Processor", "€ 300,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao4, "I-Phone 20", "Sd 1425 Processor", "€ 1450,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ciao5, "Nokia 3321", "Sd 225 Processor", "€ 200,00"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.logo, "Home", "", ""));

        horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        horizontalReciclerView.setLayoutManager(linearLayoutManager);
        horizontalReciclerView.setAdapter(horizontalProductScrollAdapter);

        horizontalProductScrollAdapter.notifyDataSetChanged();
//        **************************** horizontal product layout****************************

//        **************************** Grid product layout ****************************
        gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        gridLayoutViewAllBtn = view.findViewById(R.id.grid_product_layout_btn);
        gridView = view.findViewById(R.id.grid_product_layout_grid_view);

        gridView.setAdapter(new GridProductViewAdapter(horizontalProductScrollModelList));

//        **************************** Grid product layout ****************************



        return view;
    }

    //        ****************************banner slide****************************
    private void pageLooper() {
        if (currentPage == sliderModelList.size() - 2) {
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerSlideShow() {
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()) {
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSLideShow() {
        timer.cancel();
    }

    //        ****************************banner slide****************************

}