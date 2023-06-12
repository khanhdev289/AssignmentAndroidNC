package khanhnqph30151.fptpoly.assignment.service;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.fragment.MusicFragment;
import khanhnqph30151.fptpoly.assignment.model.Music;

public class Service extends android.app.Service {
    private MediaPlayer mediaPlayer;
    String CHANNEL_ID = "nhac phat ne";
    private String currentMusicUri;
    MusicFragment musicFragment;
    private int currentSongIndex = 0;
    private ArrayList<Music> list;
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
//        PendingIntent playPendingIntent = PendingIntent.getService(
//                this,
//                0,
//                new Intent(this, Service.class).setAction("PLAY"),
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//
//        // Tạo pending intent cho nút stop
//        PendingIntent stopPendingIntent = PendingIntent.getService(
//                this,
//                0,
//                new Intent(this, Service.class).setAction("STOP"),
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//
//        // Tạo và cấu hình notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("Your Music Service")
//                .setContentText("Music is playing...")
//                .setSmallIcon(R.drawable.music_note)
//                .addAction(R.drawable.play, "Play", playPendingIntent)
//                .addAction(R.drawable.stop, "Stop", stopPendingIntent);
//
//        // Hiển thị notification
//        startForeground(1, builder.build());

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
