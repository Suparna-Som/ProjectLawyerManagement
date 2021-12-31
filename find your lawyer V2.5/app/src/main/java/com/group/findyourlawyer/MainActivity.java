package com.group.findyourlawyer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.group.findyourlawyer.activity.Incomplete;
import com.group.findyourlawyer.activity.Login;
import com.group.findyourlawyer.activity.ViewDetails;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.group.findyourlawyer.fragments.FragmentAbout;
import com.group.findyourlawyer.fragments.FragmentAdvocates;
import com.group.findyourlawyer.fragments.FragmentHome;
import com.group.findyourlawyer.fragments.FragmentQuestions;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity Debug";
    int i=1;
    ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    Button Button_Profile,Button_News,Button_Lawyer,Button_QA,Button_Fav,Button_Feedback,Button_logout,Button_about;
    ImageView nav_image;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    public static final String rateMarket = "market://details?.phonemeterr";
    public static final String pubMarket = "market://search?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  Button_Lawyer = (Button) findViewById(R.id.Button_Lawyer);
        Button_Profile = (Button) findViewById(R.id.Button_Profile);
        Button_News = (Button) findViewById(R.id.Button_News);
        Button_QA = (Button) findViewById(R.id.Button_QA);
        Button_Fav = (Button) findViewById(R.id.Button_Fav);
        Button_Feedback = (Button) findViewById(R.id.Button_Feedback);
        Button_logout = (Button) findViewById(R.id.Button_logout);
        Button_about = (Button) findViewById(R.id.Button_about);
        //Set the toolbar as the app bar


        Button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this,Login.class));

                    SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                    mEditor.clear();
                    mEditor.apply();

                    finish();
                    // signed out
                } catch (Error e) {
                    Toast.makeText(MainActivity.this, "Failed to Logout", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewDetails.class));
            }
        });
        Button_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Incomplete.class));
            }
        });
        Button_Lawyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LawyerProfile.class));
            }
        });
        Button_QA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostListActivity.class));
            }
        });
        Button_Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Incomplete.class));
            }
        });
        Button_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactus();
            }
        });
        Button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Incomplete.class));
            }
        });*/

        //Navigation View Initialize
        bottomNavigationView = findViewById(R.id.bottom_nav);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AskQuestion.class);
                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FragmentHome()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;
                switch (item.getItemId()){
                    
                    case R.id.home: temp=new FragmentHome();
                    break;
                    case R.id.lawyer: temp=new FragmentAdvocates();
                    break;

                    case R.id.ans: temp=new FragmentQuestions();
                    break;
                    case R.id.about: temp=new FragmentAbout();
                    break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,temp).addToBackStack(null).commit();
                return true;
            }
        });
        
        //Display the menu toggle icon on the app bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
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


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.nav_advocates:
                startActivity(new Intent(MainActivity.this, LawyerProfile.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this, ViewDetails.class));
                break;
            case R.id.nav_question:
                startActivity(new Intent(MainActivity.this, PostListActivity.class));
                break;

            case R.id.nav_askQuestion:
                startActivity(new Intent(MainActivity.this, AskQuestion.class));
                break;
            case R.id.nav_bookedbyme:
                startActivity(new Intent(MainActivity.this, Incomplete.class));
                break;
            case R.id.nav_rateus:
                rateApp(this, true);
                break;
            case R.id.nav_share:
               // ApplicationInfo api = getApplicationContext().getApplicationInfo();
               // String apkPath = api.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
             //   intent.setType("application/vnd.android.package-archive");
           //     intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile((new File(apkPath))));
//                startActivity(Intent.createChooser(intent,"share file using"));

                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Find Your Lawyer");
                intent.putExtra(Intent.EXTRA_TEXT, "Find your Lawyer");
               startActivity(Intent.createChooser(intent, "choose one"));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
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
            String str = sharedPreferences.getString("changer", "null");
            if (!str.equals("null")) {
                Uri uri = Uri.parse(str);
                Picasso.get().load(uri).into(nav_image);
            }
        }
    }

    private void contactus() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "170860116004@laxmi.edu.in", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding To Find Your Lawyer");
        //  emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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

    @Override
    public void onBackPressed() {

        if(i==2) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.setCanceledOnTouchOutside(false);
            alert.show();
        }
        else if (i==1){
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }
        i=i+1;
    }

}
