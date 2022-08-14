package com.tuanqd.mockproject.mediaplayer;

import static com.tuanqd.mockproject.mediaplayer.Notification_Application.ACTION_FROM_PENDING;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.ACTION_FROM_SERVICE;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.EXIT;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.POSITION_SONG_FROM_SERVICE;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.IS_PLAYING_FROM_MAIN_NOTIFICATION;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.IS_PLAYING_FROM_SERVICE;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.NEXT;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.NEXT_FROM_MAIN_NOTIFICATION;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.NOTIFICATION_CHANNEL_ID;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.PAUSE;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.PLAY;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.PLAY_FROM_ALL_SONG_AT_MAIN;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.START_FROM_MAIN_NOTIFICATION;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.POSITION_FROM_ALL_SONG;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.PREVIOUS;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.PREVIOUS_FROM_MAIN_NOTIFICATION;
import static com.tuanqd.mockproject.mediaplayer.Notification_Application.STOP_FROM_SERVICE;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.baseproject.R;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import com.tuanqd.mockproject.main.SongsModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service {
    private boolean mIsPlaying = false;
    private int positionInAllSongs = 0;
    private int allSongClickStart = 0, mainNotificationStart = 0;
    private static final String TAG = "Music Service";
    boolean play = false, stop = false, pause = false;
    public MediaPlayer mediaPlayer = new MediaPlayer();
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();
    List<SongsModel> songsModelList = new ArrayList<>();
    int length = 0;
    private int currentProgress = 0;

    // Binder to activity
    LocalBinder binder = new LocalBinder() {
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    public class LocalBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
    /////////

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "onStartCommand: ");

        if (intent != null) {
            // all songs fragment click start service.
            allSongClickStart = intent.getIntExtra(PLAY_FROM_ALL_SONG_AT_MAIN, 0);
            if (allSongClickStart == 1) {
                positionInAllSongs = intent.getIntExtra(POSITION_FROM_ALL_SONG, 0);
                playMusic();
            }
            // action from pendingIntent notification
            int action = intent.getIntExtra(ACTION_FROM_PENDING, -1);
            if (action >= 0) {
                handlerAction(action);
            }
            // main notification start Service
            mainNotificationStart = intent.getIntExtra(START_FROM_MAIN_NOTIFICATION, 0);
            if (mainNotificationStart == 1) {
                boolean playFromMainNotification = intent.getBooleanExtra(IS_PLAYING_FROM_MAIN_NOTIFICATION,
                        false);
                boolean nextFromMainNotification = intent.getBooleanExtra(NEXT_FROM_MAIN_NOTIFICATION,
                        false);
                boolean previousFromMainNotification = intent.getBooleanExtra(PREVIOUS_FROM_MAIN_NOTIFICATION,
                        false);
                if (playFromMainNotification) {
                    playMusic();
                }
                if (nextFromMainNotification) {
                    nextMusic();
                }
                if (previousFromMainNotification) {
                    previousMusic();
                }
                if (!playFromMainNotification && !nextFromMainNotification && !previousFromMainNotification) {
                    pauseMusic();
                }
            }
        }

//         auto next song when finish
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (positionInAllSongs == (songsModelList.size() - 1)) {
                        positionInAllSongs = 0;
                    }
                    nextMusic();
                }
            });
        }
        // get progress
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {


        }
        return START_STICKY;
    }
    public int getProgress() {
        if (mediaPlayer.isPlaying()) {
            return mediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }


    private void createNotification(SongsModel song) {
        Intent intentFragment = new Intent(this, MusicFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, intentFragment, PendingIntent.FLAG_UPDATE_CURRENT);
        // send BroadCast in application
        Intent intentSendActivity = new Intent(ACTION_FROM_SERVICE);
        intentSendActivity.putExtra(POSITION_SONG_FROM_SERVICE, positionInAllSongs);
        intentSendActivity.putExtra(IS_PLAYING_FROM_SERVICE, mIsPlaying);
        intentSendActivity.putExtra(STOP_FROM_SERVICE, stop);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentSendActivity);

        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.notification_music_layout);
        remoteView.setTextViewText(R.id.tv_title, song.getTitle());
        remoteView.setTextViewText(R.id.tv_artist, song.getArtist());
        remoteView.setImageViewBitmap(R.id.image_thumbs_notification, song.getSongBitmap());
        remoteView.setImageViewResource(R.id.exit_notification_button, R.drawable.ic_cancel_24);
        remoteView.setImageViewResource(R.id.img_next_notification_button, R.drawable.ic_next_24);
        remoteView.setImageViewResource(R.id.img_prev_notification_button, R.drawable.ic_previous_24);
        remoteView.setOnClickPendingIntent(R.id.exit_notification_button, getPendingIntent(EXIT));
        remoteView.setOnClickPendingIntent(R.id.img_next_notification_button, getPendingIntent(NEXT));
        remoteView.setOnClickPendingIntent(R.id.img_prev_notification_button, getPendingIntent(PREVIOUS));

        if (mIsPlaying) {
            remoteView.setOnClickPendingIntent(R.id.play_pause_notification_button, getPendingIntent(PAUSE));
            remoteView.setImageViewResource(R.id.play_pause_notification_button, R.drawable.ic_pause_notification);
        } else {

            remoteView.setOnClickPendingIntent(R.id.play_pause_notification_button, getPendingIntent(PLAY));
            remoteView.setImageViewResource(R.id.play_pause_notification_button, R.drawable.ic_play_notification);
        }

        android.app.Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteView)
                .setSound(null)
                .build();

        startForeground(1, notification);// run forceGround service.
    }

    private PendingIntent getPendingIntent(int action) {
        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra(ACTION_FROM_PENDING, action);
        return PendingIntent.getService(getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void handlerAction(int action) {
        Log.i(TAG, "handleAction: " + action);
        switch (action) {
            case PLAY:
                play = true;
                playMusic();
                break;
            case PAUSE:
                pauseMusic();
                break;
            case EXIT:
                exitMusic();
                break;
            case NEXT:
                nextMusic();
                break;
            case PREVIOUS:
                previousMusic();
                break;
        }
    }

    private void playMusic() {
        mIsPlaying = true;
        // clear instance
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        // resume pause
        if (pause) {
            try {
                String musicPath = allSongsListRepository.getAllSongsList().get(positionInAllSongs).getMusicPath();
                createNotification(allSongsListRepository.getAllSongsList().get(positionInAllSongs));
                mediaPlayer.setDataSource(musicPath);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.seekTo(length);
            length = 0;
        } else {
            try {
                String musicPath = allSongsListRepository.getAllSongsList().get(positionInAllSongs).getMusicPath();
                Log.i("Music Path ", musicPath);
                createNotification(allSongsListRepository.getAllSongsList().get(positionInAllSongs));
                mediaPlayer.setDataSource(musicPath);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mediaPlayer.start();
    }

    private void nextMusic() {
        mediaPlayer.reset();
        if (positionInAllSongs < (allSongsListRepository.getAllSongsList().size() - 1)) {
            positionInAllSongs = positionInAllSongs + 1;

            Log.i("size list", "" + allSongsListRepository.getAllSongsList().size());
            Log.i("position", "" + positionInAllSongs);
        }else{
            positionInAllSongs=0;
        }
        playMusic();

    }

    private void previousMusic() {
        mediaPlayer.reset();
        if (positionInAllSongs > 0) {
            positionInAllSongs = positionInAllSongs - 1;
        }
        if(positionInAllSongs==0){
            positionInAllSongs=allSongsListRepository.getAllSongsList().size()-1;
        }
            playMusic();
    }

    private void pauseMusic() {
        mediaPlayer.pause();
        pause = true;
        mIsPlaying = false;
        length = mediaPlayer.getCurrentPosition();
        createNotification(allSongsListRepository.getAllSongsList().get(positionInAllSongs));
    }


    private void exitMusic() {
        stop = true;
        mIsPlaying = false;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


}
