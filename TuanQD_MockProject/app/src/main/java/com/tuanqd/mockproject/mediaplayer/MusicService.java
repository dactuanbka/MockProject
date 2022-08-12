package com.tuanqd.mockproject.mediaplayer;

import static com.tuanqd.mockproject.mediaplayer.Notification.NOTIFICATION_CHANNEL_ID;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.baseproject.R;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import com.tuanqd.mockproject.main.SongsModel;

import java.io.IOException;

public class MusicService extends Service {
    private static final int PLAY = 0;
    private static final int PAUSE = 1;
    private static final int NEXT = 2;
    private static final int PREVIOUS = 3;
    private static final int EXIT = 4;

    private boolean mIsPlaying = false;
    private int mCurrentSongPosition = 0;
    private int positionInAllSongs = 0;
    private int allSongStart = 0;
    private static final String TAG = "Music Service";
    boolean play = false, stop = false;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            // all songs start service.
            int action = intent.getIntExtra("user_action", -1);
            allSongStart = intent.getIntExtra("AllSongsStart", 0);
            if (allSongStart == 1) {
                positionInAllSongs = intent.getIntExtra("positionAllSong", 0);
                playMusic();
            }
            if (action >= 0) {
                handlerAction(action);
            }
        }
        Log.i(TAG, "onStartCommand: ");
        return START_STICKY;
    }

    LocalBinder binder = new LocalBinder() {
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    class LocalBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    private void createNotification(SongsModel song) {
        Intent intentFragment = new Intent(this, MusicFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, intentFragment, PendingIntent.FLAG_UPDATE_CURRENT);
        // send BroadCast in application
        Intent intentSendActivity = new Intent("statusMediaPlayer");
        intentSendActivity.putExtra("id_song", mCurrentSongPosition);
        intentSendActivity.putExtra("isPlaying", mIsPlaying);
        intentSendActivity.putExtra("stop", stop);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentSendActivity);

        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.notification_music_layout);
        remoteView.setTextViewText(R.id.tv_title, song.getTitle());
        remoteView.setTextViewText(R.id.tv_artist, song.getArtist());
        remoteView.setImageViewBitmap(R.id.image_thumbs_notification, song.getBitmapSong());
        remoteView.setImageViewResource(R.id.exit_notification_button, R.drawable.ic_cancel_24);
        remoteView.setImageViewResource(R.id.img_next_notification_button, R.drawable.ic_next_24);
        remoteView.setImageViewResource(R.id.img_prev_notification_button, R.drawable.ic_previous_24);
        remoteView.setOnClickPendingIntent(R.id.exit_notification_button, getPendingIntent(EXIT));
        remoteView.setOnClickPendingIntent(R.id.img_next_notification_button, getPendingIntent(NEXT));
        remoteView.setOnClickPendingIntent(R.id.img_prev_notification_button, getPendingIntent(PREVIOUS));

        if (mIsPlaying) {
            remoteView.setOnClickPendingIntent(R.id.play_pause_notification_button, getPendingIntent(PAUSE));
            remoteView.setImageViewResource(R.id.play_pause_notification_button, R.drawable.ic_pause_music_24);
        } else {

            remoteView.setOnClickPendingIntent(R.id.play_pause_notification_button, getPendingIntent(PLAY));
            remoteView.setImageViewResource(R.id.play_pause_notification_button, R.drawable.ic_play_music);
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
        intent.putExtra("user_action", action);
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
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        try {
            String musicPath = allSongsListRepository.getAllSongsList().get(positionInAllSongs).getMusicPath();
            Log.i("REPOSITORY AT MUSIC SERVICE", allSongsListRepository.getAllSongsList().
                    get(positionInAllSongs).getMusicPath());
            createNotification(allSongsListRepository.getAllSongsList().get(positionInAllSongs));
            mediaPlayer.setDataSource(musicPath);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    private void nextMusic() {
        if (positionInAllSongs < allSongsListRepository.getAllSongsList().size() - 1) {
            positionInAllSongs = positionInAllSongs + 1;
        }
        playMusic();
    }

    private void previousMusic() {
        if (positionInAllSongs > 0) {
            positionInAllSongs = positionInAllSongs - 1;
        }
        playMusic();
    }
    private void pauseMusic() {
        mediaPlayer.pause();
        mIsPlaying = false;
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
