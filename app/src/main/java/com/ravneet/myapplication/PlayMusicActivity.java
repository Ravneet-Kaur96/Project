package com.ravneet.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtSongName;
    Button btnPlay, btnStop;
    MediaPlayer mediaPlayer;
    String songToPlay;
    String path;

    void initViews(){
        Intent rcv =getIntent();
        songToPlay=rcv.getStringExtra("keySong");
        path= Environment.getExternalStorageDirectory().getPath()+"/"+songToPlay;

        txtSongName = findViewById(R.id.textViewSong);
        btnPlay = findViewById(R.id.buttonPlay);
        btnStop = findViewById(R.id.buttonStop);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        txtSongName.setText(songToPlay);
        mediaPlayer= new MediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initViews();
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if (id== R.id.buttonPlay){
            try {
                mediaPlayer.setDataSource(path);
                //mediaPlayer.setDataSource(this, Uri.parse(url));
                mediaPlayer.prepare();
                mediaPlayer.start();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        else {
            mediaPlayer.stop();
            mediaPlayer.release();
            finish();

        }

    }
}
