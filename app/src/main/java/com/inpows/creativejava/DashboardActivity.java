package com.inpows.creativejava;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    Toolbar toolbar;
    NavController navController;

    // index to identify current nav menu item
    public static int navItemIndex = 0;
    public List<Integer> navItemIndexList;

    private boolean doubleBackToExitPressedOnce = false;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.mainToolbar);
        toolbar.setTitle(R.string.dashboard);
        mHandler = new Handler();
        navItemIndexList = new ArrayList<Integer>();
        navItemIndexList.add(0);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(DashboardActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView  = findViewById(R.id.navigation_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if (navItemIndexList.size()<=1){
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            navItemIndexList.remove(navItemIndexList.size()-1);
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Checking if the item is in checked state or not, if not make it in checked state
        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        int id = menuItem.getItemId();
        if(id != navItemIndex){
            navItemIndex = id;
            navItemIndexList.add(id);
            switch (id) {
                case R.id.navigation_dashboard:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    toolbar.setTitle(R.string.dashboard);
                    navController.navigate(R.id.dashboardFragment);
                    break;
                case R.id.navigation_explore:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    toolbar.setTitle(R.string.explore);
                    navController.navigate(R.id.explorerFragment);
                    break;
                case R.id.navigation_login:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    toolbar.setTitle(R.string.login);
                    navController.navigate(R.id.loginFragment);
                    break;
                case R.id.navigation_profile:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    toolbar.setTitle(R.string.profile);
                    navController.navigate(R.id.loginFragment);
                    break;
                case R.id.navigation_trending:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    toolbar.setTitle(R.string.profile);
                    navController.navigate(R.id.loginFragment);
                    break;
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
