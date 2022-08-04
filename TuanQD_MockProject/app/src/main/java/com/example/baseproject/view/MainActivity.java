package com.example.baseproject.view;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import androidx.navigation.ui.AppBarConfiguration;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding activityMainBinding;
    PopupWindow mypopupWindow;
    int height, width;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        Toolbar toolbar = activityMainBinding.layoutToolbar.toolbar;
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMainBinding.drawerLayout.open();
            }
        });

//		NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
//				findFragmentById(R.id.nav_host_fragment);
//		NavController navController = navHostFragment.getNavController();
//		NavigationView navView = activityMainBinding.navView;
//
//
//		 appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
//						.setDrawerLayout(activityMainBinding.drawerLayout)
//						.build();
//
//		NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onClick(View v) {

    }
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//		return NavigationUI.onNavDestinationSelected(item, navController)
//				|| super.onOptionsItemSelected(item);
//	}
//	@Override
//	public boolean onSupportNavigateUp() {
//		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//		return NavigationUI.navigateUp(navController, appBarConfiguration)
//				|| super.onSupportNavigateUp();
//	}
}