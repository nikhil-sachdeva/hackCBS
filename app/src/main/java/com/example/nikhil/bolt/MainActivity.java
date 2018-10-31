package com.example.nikhil.bolt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 2;
    PostFragment postFragment;
    FloatingActionButton fab_stats,fab;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    BottomNavigationView navigation;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //viewPager=findViewById(R.id.viewPager);
        navigation=findViewById(R.id.navigation);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        fab_stats=findViewById(R.id.fab_stats);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.rsz_b);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StatisticsActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
//            if(user!=null){
                startActivity(new Intent(MainActivity.this,CreatePost.class));
           // }
           // else{
//                Toast.makeText(MainActivity.this, "Please login to post ", Toast.LENGTH_SHORT).show();
//            }
            }
        });
        loadFragment(new PostFragment());
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.bottom_danger:
                        fab.setVisibility(View.INVISIBLE);
                        fab_stats.setVisibility(View.INVISIBLE);
                        fragment = new DangerPredictionFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.bottom_post:
                        if(fab.getVisibility()==View.INVISIBLE && fab_stats.getVisibility()==View.INVISIBLE){
                            fab.setVisibility(View.VISIBLE);
                            fab_stats.setVisibility(View.VISIBLE);
                        }
                        fragment = new PostFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.bottom_driving:
                        fab.setVisibility(View.INVISIBLE);
                        fab_stats.setVisibility(View.INVISIBLE);
                        fragment=new DriverSettingsFragment();
                        loadFragment(fragment);
                        return true;    
                }

                return false;
            }
        });

       // AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.login){
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
