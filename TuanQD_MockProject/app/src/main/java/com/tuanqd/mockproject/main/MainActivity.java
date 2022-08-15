package com.tuanqd.mockproject.main;

import static com.tuanqd.mockproject.mediaplayer.Notification_Application.ACTION_FROM_SERVICE;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.POSITION_SONG_FROM_SERVICE;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.IS_PLAYING_FROM_MAIN_NOTIFICATION;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.IS_PLAYING_FROM_SERVICE;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.LOADER_DEVICE_ID;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.START_FROM_MAIN_NOTIFICATION;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.NEXT_FROM_MAIN_NOTIFICATION;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.PLAY_FROM_ALL_SONG_AT_MAIN;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.POSITION_FROM_ALL_SONG;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.PREVIOUS_FROM_MAIN_NOTIFICATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;


import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import com.tuanqd.mockproject.mediaplayer.MusicService;
import com.tuanqd.mockproject.songs.allsongs.AllSongsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding activityMainBinding;
    AppBarConfiguration mAppBarConfiguration;
    NavController mNavController;
    BaseViewModel baseViewModel;
    AllSongsViewModel allSongsViewModel;
    Intent intentReceiveFromAllSongs;
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();
    List<SongsModel> songsModelList = new ArrayList<>();
    Intent intentMainNotificationSend;
    boolean isPlaying = false, next = false, previous = false;
    int positionSongs = 0;
    MusicService musicService;
    boolean mBound = false;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            musicService = binder.getService();
            Toast.makeText(MainActivity.this, "ServiceConnected", Toast.LENGTH_SHORT).show();
            mBound = true;
            // update progress for seekbar
            mHandlerThread = new HandlerThread("Server Handler Thread");
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
            updateData();

            // mediaPlay seek to
            updateUserActionOnSeekBar();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Toast.makeText(MainActivity.this, "ServiceDisConnected", Toast.LENGTH_SHORT).show();
        }
    };

    private void updateUserActionOnSeekBar() {
        // handle seekBar of main notification
        activityMainBinding.appBarMain.seekBarMainNotification.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        if (musicService.mediaPlayer.isPlaying()) {
                            activityMainBinding.appBarMain.seekBarMainNotification.setMax(musicService.getMaxDuration());
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (musicService.mediaPlayer.isPlaying()) {
                            Log.i("SEEK PROGRESS", "" + seekBar.getProgress());
                            musicService.mediaPlayer.seekTo(seekBar.getProgress());
                        }
                    }
                });
    }

    private void updateData() {
        //update data
        runOnUiThread(() -> {
//            Log.i("get Max Duration", "" + musicService.getMaxDuration());
            activityMainBinding.appBarMain.seekBarMainNotification.setMax(musicService.getMaxDuration());
            activityMainBinding.appBarMain.seekBarMainNotification.setProgress(musicService.getProgress());
//            Log.i("GetProgress", "" + musicService.getProgress());
        });
        mHandler.postDelayed(() -> updateData(), 999);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        setBottomNavigation();
        setAppBar();
        // register for Broadcast receiver

        registerBroadcast();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        baseViewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        intentMainNotificationSend = new Intent(this, MusicService.class);

        allSongsViewModel = new ViewModelProvider(this).get(AllSongsViewModel.class);
        activityMainBinding.appBarMain.constraintLayoutMainNotification.setAlpha((float) 0.9);

        // bind service
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

        // main notification do action
        activityMainBinding.appBarMain.imgPlayMainNotification.setOnClickListener(this);
        activityMainBinding.appBarMain.imgPrevMainNotification.setOnClickListener(this);
        activityMainBinding.appBarMain.imgNextMainNotification.setOnClickListener(this);
        baseViewModel.getFinishLoaderAllSongs().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                // all intent run service
                // observer all song fragment send action.
                songsModelList = allSongsListRepository.getAllSongsList();
                allSongsViewModel.getPositionAllSong().observe(MainActivity.this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        intentReceiveFromAllSongs = new Intent(MainActivity.this, MusicService.class);
                        intentReceiveFromAllSongs.putExtra(PLAY_FROM_ALL_SONG_AT_MAIN, 1);
                        intentReceiveFromAllSongs.putExtra(POSITION_FROM_ALL_SONG, integer);
                        activityMainBinding.appBarMain.imgSongMainNotification.setImageBitmap(
                                songsModelList.get(integer).getSongBitmap());
                        activityMainBinding.appBarMain.imgPlayMainNotification.
                                setImageResource(R.drawable.ic_pause_main_notification);
                        startService(intentReceiveFromAllSongs);
                    }
                });
            }
        });


    }

    // result permission for access media
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

    // handle broadcast receiver
    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter(ACTION_FROM_SERVICE);
        LocalBroadcastManager.getInstance(this).registerReceiver(musicBroadcastReceiver, filter);
        IntentFilter filter2 = new IntentFilter("remainTimer");
        LocalBroadcastManager.getInstance(this).registerReceiver(musicBroadcastReceiver, filter2);
    }

    private final BroadcastReceiver musicBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

// handle action with all song fragment
            // get playing from service.
            isPlaying = intent.getBooleanExtra(IS_PLAYING_FROM_SERVICE, false);
            if (isPlaying) {
                activityMainBinding.appBarMain.imgPlayMainNotification.setImageResource(
                        R.drawable.ic_pause_main_notification);
            } else {
                activityMainBinding.appBarMain.imgPlayMainNotification.setImageResource(
                        R.drawable.ic_play_main_notification);
            }
            // set image thumbs for main notification
            positionSongs = intent.getIntExtra(POSITION_SONG_FROM_SERVICE, 0);
            activityMainBinding.appBarMain.imgSongMainNotification.setImageBitmap(
                    songsModelList.get(positionSongs).getSongBitmap());
        }

// hanlde action with ...

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.img_play_main_notification):
                // play media
                if (!isPlaying) {
                    Log.i(" play", "" + false);
                    isPlaying = true;
                    activityMainBinding.appBarMain.imgPlayMainNotification.setImageResource(R.drawable.ic_pause_main_notification);
                    intentMainNotificationSend.putExtra(START_FROM_MAIN_NOTIFICATION, 1);
                    intentMainNotificationSend.putExtra(PREVIOUS_FROM_MAIN_NOTIFICATION, false);
                    intentMainNotificationSend.putExtra(NEXT_FROM_MAIN_NOTIFICATION, false);
                    intentMainNotificationSend.putExtra(IS_PLAYING_FROM_MAIN_NOTIFICATION, isPlaying);
                }
                //pause media
                else {
                    Log.i("vào pause", "" + true);
                    activityMainBinding.appBarMain.imgPlayMainNotification.setImageResource(R.drawable.ic_play_main_notification);
                    intentMainNotificationSend.putExtra(START_FROM_MAIN_NOTIFICATION, 1);
                    intentMainNotificationSend.putExtra(PREVIOUS_FROM_MAIN_NOTIFICATION, false);
                    intentMainNotificationSend.putExtra(NEXT_FROM_MAIN_NOTIFICATION, false);
                    intentMainNotificationSend.putExtra(IS_PLAYING_FROM_MAIN_NOTIFICATION, false);
                }
                startService(intentMainNotificationSend);
                break;
            case (R.id.img_next_main_notification):
                // next
                activityMainBinding.appBarMain.imgPlayMainNotification.setImageResource(R.drawable.ic_pause_main_notification);
                intentMainNotificationSend.putExtra(START_FROM_MAIN_NOTIFICATION, 1);
                intentMainNotificationSend.putExtra(PREVIOUS_FROM_MAIN_NOTIFICATION, false);
                intentMainNotificationSend.putExtra(NEXT_FROM_MAIN_NOTIFICATION, true);
                intentMainNotificationSend.putExtra(IS_PLAYING_FROM_MAIN_NOTIFICATION, false);
                startService(intentMainNotificationSend);
                break;
            case (R.id.img_prev_main_notification):
                //previous
                activityMainBinding.appBarMain.imgPlayMainNotification.setImageResource(R.drawable.ic_pause_main_notification);
                intentMainNotificationSend.putExtra(START_FROM_MAIN_NOTIFICATION, 1);
                intentMainNotificationSend.putExtra(PREVIOUS_FROM_MAIN_NOTIFICATION, true);
                intentMainNotificationSend.putExtra(NEXT_FROM_MAIN_NOTIFICATION, false);
                intentMainNotificationSend.putExtra(IS_PLAYING_FROM_MAIN_NOTIFICATION, false);
                startService(intentMainNotificationSend);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intentReceiveFromAllSongs != null) {
            stopService(intentReceiveFromAllSongs);
            intentReceiveFromAllSongs = null;
        }
        unbindService(connection);
        mBound = false;
        LocalBroadcastManager.getInstance(this).unregisterReceiver(musicBroadcastReceiver);
    }
}