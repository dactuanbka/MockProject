package com.tuanqd.mockproject.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.view.Menu;
import android.widget.Toast;


import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.tuanqd.mockproject.songs.artist.ArtistViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    AppBarConfiguration mAppBarConfiguration;
    NavController mNavController;
    private static final int LOADER_DEVICE_ID = 1;
    BaseViewModel baseViewModel;
    ArtistViewModel artistViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        setBottomNavigation();
        setAppBar();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        baseViewModel = new ViewModelProvider(this).get(BaseViewModel.class);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                LoaderManager.getInstance(this).initLoader(LOADER_DEVICE_ID, null, baseViewModel);


            } else {
                Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setAppBar() {
        Toolbar toolbar = activityMainBinding.appBarMain.toolbar;
        setSupportActionBar(toolbar);
        NavigationView navView = activityMainBinding.navigationView;
        DrawerLayout drawer = activityMainBinding.drawerLayout;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.songsFragment, R.id.settingsFragment)
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, mNavController);
    }

    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = activityMainBinding.navBottomView;
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_search, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}