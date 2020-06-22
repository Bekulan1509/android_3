package com.twodev.android_3.presentetion.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;
import com.twodev.android_3.R;
import com.twodev.android_3.data.PreferenceHelper;
import com.twodev.android_3.presentetion.main.MainActivity;

public class IntroActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button btnSkip;
    private Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        viewPager = findViewById(R.id.viewPager);
        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next);
        WormDotsIndicator wormDotsIndicator = findViewById(R.id.worm_dots_indicator);
        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        wormDotsIndicator.setViewPager(viewPager);


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  new Prefs(IntroActivity.this).isShown(true);
                PreferenceHelper.getInstance(IntroActivity.this).setIsShow();
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()==2){
                    PreferenceHelper.getInstance(IntroActivity.this).setIsShow();
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                    finish();
                }else
                    viewPager.setCurrentItem(getItem(+1), true);

            }
        });
        pageChange();



    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void pageChange() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    btnNext.setText("START");
                    btnSkip.setVisibility(View.GONE);

//                    btnNext.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            SharedPreferences preferences = IntroActivity.this.getPreferences(MODE_PRIVATE);
////                            preferences.edit().putBoolean("isShown",true).apply();
////                           Log.d("lala", "onClick: "+preferences);
////                            new Prefs(IntroActivity.this).isShown(true);
//                            PreferenceHelper.getInstance(IntroActivity.this).setIsShow();
//                            startActivity(new Intent(IntroActivity.this, MainActivity.class));
//                            finish();
//                        }
//                    });
                } else if (position <= 1) {
                    btnNext.setText("next");
                    btnSkip.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class SectionPagerAdapter extends FragmentPagerAdapter {


        public SectionPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {


            Fragment fragment = null;
            Bundle bundle;
            switch (position) {
                case 0:
                    bundle = new Bundle();
                    fragment = new IntroFragment();
                    fragment.setArguments(bundle);
                    break;
                case 1:
                    bundle = new Bundle();
                    fragment = new IntroFragment2();
                    fragment.setArguments(bundle);
                    break;
                case 2:
                    bundle = new Bundle();
                    fragment = new IntroFragment3();
                    fragment.setArguments(bundle);
                    break;
                default:
            }

            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }
    }
}