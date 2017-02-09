package com.noteapp.Controll;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.noteapp.R;

public class MainActivity extends FragmentActivity {

    public static Context context;
    private SlidingTabsBasicFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        SlidingTabsBasicFragment.myFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            myFragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.sample_content_fragment, myFragment);
            transaction.commit();
        }
    }
}
