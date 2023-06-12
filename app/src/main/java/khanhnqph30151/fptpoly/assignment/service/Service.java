package khanhnqph30151.fptpoly.assignment.service;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

public class Service extends android.app.Service {
    private MediaPlayer mediaPlayer;
    private String currentMusicUri;
    private final IBinder binder = new MusicBinder();

    public class MusicBinder extends Binder {
        Service getService() {
            return Service.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String musicUri = intent.getStringExtra("linkmusic");
        if (musicUri != null) {
            playMusic(musicUri);
            Toast.makeText(this, "Bắt Đầu Phát Nhạc", Toast.LENGTH_SHORT).show();
        }else if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            Toast.makeText(this, "Dừng Nhạc", Toast.LENGTH_SHORT).show();
        } else {
            mediaPlayer.start();
            Toast.makeText(this, "Tiếp Tục Nhạc", Toast.LENGTH_SHORT).show();
        }
        return START_NOT_STICKY;
    }

    public void playMusic(String musicUri) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            mediaPlayer.setDataSource(musicUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            currentMusicUri = musicUri;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        Toast.makeText(this, "Nhạc Đã Tắt", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
