package com.twodev.android_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button btnSkip;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        viewPager = findViewById(R.id.viewPager);
        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next);
        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager(), 3));


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        });
        pageChange();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(getItem(+1), true);
            }
        });


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
                    btnSkip.setVisibility(View.INVISIBLE);
                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(IntroActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                } else if (position<=1) {
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


        public SectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
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