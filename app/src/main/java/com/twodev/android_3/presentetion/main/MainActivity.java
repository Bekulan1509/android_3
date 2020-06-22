package com.twodev.android_3.presentetion.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.twodev.android_3.R;
import com.twodev.android_3.data.PreferenceHelper;
import com.twodev.android_3.presentetion.intro.IntroActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//        boolean isShown2 = preferences.getBoolean("isShown",false);
//        boolean isShown = new Prefs(this).isShown();


        boolean isShown= PreferenceHelper.getInstance(this).isShown();

        if (!isShown){startActivity(new Intent(this, IntroActivity.class));
            finish();
            return;
        }
    }
}