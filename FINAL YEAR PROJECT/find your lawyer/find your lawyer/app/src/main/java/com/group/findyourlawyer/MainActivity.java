package com.group.findyourlawyer;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.group.findyourlawyer.activity.Incomplete;
import com.group.findyourlawyer.activity.Login;
import com.group.findyourlawyer.activity.ViewDetails;
import com.group.findyourlawyer.adapters.PagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity Debug";
    ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    ImageView nav_image;
    public static final String rateMarket = "market://details?id=com.chetan.tools.phonemeterr";
    public static final String pubMarket = "market://search?q=pub:HNC Developers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the toolbar as the app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Display the menu toggle icon on the app bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //Registers the NavigationItemSelectedListener to the navigation view
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggler = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open_nav, R.string.close_nav);
        mDrawerLayout.addDrawerListener(toggler);
        toggler.syncState();

        //Creates the tab layout from the layout and populates it with three tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Questions"));
        tabLayout.addTab(tabLayout.newTab().setText("Advocates"));
        tabLayout.addTab(tabLayout.newTab().setText("Feeds"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        nav_image = findViewById(R.id.nav_header_image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        nav_image = findViewById(R.id.nav_header_image);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        Uri uri = Uri.parse(sharedPreferences.getString("chetanimageuri", "null"));
//        Log.d(TAG, "onResume: " + uri.getPath());
//        Picasso.get().load(uri).into(nav_image);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.nav_advocates:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this, ViewDetails.class));
                break;
            case R.id.nav_question:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_myquestions:
            case R.id.nav_askQuestion:
            case R.id.nav_bookedforme:
            case R.id.nav_bookedbyme:
                startActivity(new Intent(MainActivity.this, Incomplete.class));
                break;
            case R.id.nav_rateus:
                rateApp(this, true);
                break;
            case R.id.nav_contactus:
                contactus();
                //todo contact us
                break;
            case R.id.nav_logout:
                try {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(this, Login.class));
                    finish();
                    // signed out
                } catch (Error e) {
                    Toast.makeText(this, "Failed to Logout", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                startActivity(new Intent(this, Incomplete.class));

        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();
        if (nav_image != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String str = sharedPreferences.getString("chetanimageuri", "null");
            if (!str.equals("null")) {
                Uri uri = Uri.parse(str);
                Picasso.get().load(uri).into(nav_image);
            }
        }
    }

    private void contactus() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "chetanboraselit@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding To Find Your Lawyer");
        //  emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public static void rateApp(Context context, boolean rate) {
        String msg = "0000";
        if (rate) {
            msg = rateMarket;
        } else {
            msg = pubMarket;
        }
        try {
            Intent rateIntent = rateIntentForUrl(msg);
            context.startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Play Store Not Installed", Toast.LENGTH_LONG).show();
        }
    }

    private static Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        intent.addFlags(flags);
        return intent;
    }

}
