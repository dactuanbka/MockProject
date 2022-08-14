package com.tuanqd.mockproject.mediaplayer;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification_Application extends Application {

    // action for pending intent notification
    public static final int PLAY = 0;
    public static final int PAUSE = 1;
    public static final int NEXT = 2;
    public static final int PREVIOUS = 3;
    public static final int EXIT = 4;
    public static final String ACTION_FROM_SERVICE = "statusMediaPlayer";
    public static final String ACTION_FROM_PENDING = "user_action";
    public static final String POSITION_SONG_FROM_SERVICE = "IdSong";
    public static final String IS_PLAYING_FROM_SERVICE = "isPlaying";
    public static final String STOP_FROM_SERVICE = "Stop";


    // action from main notification
    public static final String START_FROM_MAIN_NOTIFICATION ="MainNotificationPlay";
    public static final String PREVIOUS_FROM_MAIN_NOTIFICATION ="Previous";
    public static final String NEXT_FROM_MAIN_NOTIFICATION ="Next";
    public static final String IS_PLAYING_FROM_MAIN_NOTIFICATION ="isPlaying";
    public static final String PLAY_FROM_ALL_SONG_AT_MAIN ="AllSongsStart";
    public static final String POSITION_FROM_ALL_SONG ="position click at All Song fragment";
    // action from All Song fragment

    // create Cursor Loader for fragments
    public static final int LOADER_DEVICE_ID = 1;


    public static final String NOTIFICATION_CHANNEL_ID = "notification_music_channel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                "notification_music_channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setSound(null, null);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }

}
