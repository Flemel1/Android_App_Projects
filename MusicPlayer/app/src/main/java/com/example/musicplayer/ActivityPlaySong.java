package com.example.musicplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.example.musicplayer.Model.Music;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static com.example.musicplayer.MainActivity.musicList;

public class ActivityPlaySong extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener,
                MediaPlayer.OnCompletionListener {
    int position;
    int totalSongs;
    TextView duration_played;
    TextView duration_total;
    TextView status_palying;
    TextView song_title;
    TextView artist_name;
    ImageView btn_back;
    ImageView btn_menu;
    ImageView img_cover;
    ImageView btn_shuffle;
    ImageView btn_previous;
    FloatingActionButton btn_play;
    ImageView btn_next;
    ImageView btn_repeat;
    SeekBar seekBar;

    ArrayList<Music> music_list;
    byte[] image;
    Bitmap bitmap = null;
    static Uri uri;
    static MediaPlayer mediaPlayer;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        position = getIntent().getIntExtra("position", -1);
        music_list = musicList;
        totalSongs = musicList.size() - 1;
        initViews();
        playMusic();
        mediaPlayer.setOnCompletionListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        ActivityPlaySong.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    int mCurrentDuration = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentDuration);
                    duration_played.setText(formattedTime(mCurrentDuration));
                }
                handler.postDelayed(this, 1000);
            }
        });
        btn_play.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_previous.setOnClickListener(this);
    }

    private String formattedTime(int mCurrentDuration) {
        String seconds = String.valueOf(mCurrentDuration % 60);
        String minutes = String.valueOf(mCurrentDuration / 60);
        String totalOut = minutes + ":" + seconds;
        String totalNew = minutes + ":" + "0" + seconds;
        if (seconds.length() == 1){
            return totalNew;
        }
        else {
            return totalOut;
        }
    }

    private void playMusic() {
        btn_play.setImageResource(R.drawable.ic_baseline_pause_24);
        uri = Uri.parse(musicList.get(position).getPath());
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        else {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
    }

    private void initViews() {
        duration_played = findViewById(R.id.tv_duration_played);
        duration_total = findViewById(R.id.tv_duration_total);
        status_palying = findViewById(R.id.status_playing);
        song_title = findViewById(R.id.tv_song_name);
        artist_name = findViewById(R.id.tv_artist_name);
        btn_back = findViewById(R.id.btn_back);
        btn_menu = findViewById(R.id.btn_menu);
        img_cover = findViewById(R.id.image_cover);
        btn_shuffle = findViewById(R.id.btn_shuffle);
        btn_previous = findViewById(R.id.btn_previous);
        btn_play = findViewById(R.id.btn_play_pause);
        btn_next = findViewById(R.id.btn_next);
        btn_repeat = findViewById(R.id.btn_repeat);
        seekBar = findViewById(R.id.seekBar);

        if (musicList != null){
            song_title.setText(musicList.get(position).getTitle());
            artist_name.setText(musicList.get(position).getArtist());
            duration_total.setText(formattedDurationTotal(musicList.get(position).getDuration()));
            image = getImage(musicList.get(position).getPath());
            if (image != null){
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageAnimation(this, img_cover, bitmap);
                generateBackgroundColor();
            }
            else {
                bitmap = null;
                imageAnimation(this, img_cover, bitmap);
            }
        }

    }

    private void generateBackgroundColor() {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                Palette.Swatch swatch = palette.getDominantSwatch();
                if (swatch != null){
                    LinearLayout mContainer = findViewById(R.id.mContainer);
                    mContainer.setBackgroundResource(R.drawable.background_main);
                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP
                            , new int[] {swatch.getRgb(), swatch.getRgb()});
                    mContainer.setBackground(gradientDrawable);
                    song_title.setTextColor(swatch.getTitleTextColor());
                    artist_name.setTextColor(swatch.getBodyTextColor());
                }
                else {
                    LinearLayout mContainer = findViewById(R.id.mContainer);
                    mContainer.setBackgroundResource(R.drawable.background_main);
                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP
                            , new int[] {0xff000000, 0xff000000});
                    mContainer.setBackground(gradientDrawable);
                    song_title.setTextColor(Color.WHITE);
                    artist_name.setTextColor(Color.BLACK);
                }
            }
        });
    }

    private String formattedDurationTotal(String duration) {
        String durationTotal = "";
        int miliseconds = Integer.valueOf(duration);
        String seconds = String.valueOf((miliseconds / 1000) % 60);
        String minutes = String.valueOf((miliseconds / 1000) / 60);
        if (seconds.length() == 1){
            durationTotal = minutes + ":" + "0" + seconds;
            return durationTotal;
        }
        else {
            durationTotal = minutes + ":"  + seconds;
            return durationTotal;
        }
    }

    public byte[] getImage(String path){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(path);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

    public void imageAnimation(final Context context, final ImageView imageView, final Bitmap bitmap){
        Animation animOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        final Animation animIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        animOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (bitmap != null){
                    Glide.with(context).load(bitmap).into(imageView);
                }
                else {
                    Glide.with(context).load(R.drawable.ic_baseline_music_note_24).into(imageView);
                }
                animIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageView.startAnimation(animIn);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(animOut);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mediaPlayer != null && fromUser){
            mediaPlayer.seekTo(progress * 1000);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_play_pause:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btn_play.setImageResource(R.drawable.ic_play);
                }
                else {
                    mediaPlayer.start();
                    btn_play.setImageResource(R.drawable.ic_baseline_pause_24);
                }
                break;
            case R.id.btn_next:
                nextBtnClicked();
                break;
            case R.id.btn_previous:
                prevBtnClicked();
                break;
        }
    }

    private void prevBtnClicked() {
        mediaPlayer.stop();
        mediaPlayer.release();
        if (position != 0){
            position = position - 1;
            uri = Uri.parse(musicList.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            song_title.setText(musicList.get(position).getTitle());
            artist_name.setText(musicList.get(position).getArtist());
            duration_total.setText(formattedDurationTotal(musicList.get(position).getDuration()));
            image = getImage(musicList.get(position).getPath());
            if (image != null){
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageAnimation(this, img_cover, bitmap);
                generateBackgroundColor();
            }
            else {
                bitmap = null;
                imageAnimation(this, img_cover, bitmap);
            }
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.start();
        }
        else {
            position = totalSongs;
            uri = Uri.parse(musicList.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            song_title.setText(musicList.get(position).getTitle());
            artist_name.setText(musicList.get(position).getArtist());
            duration_total.setText(formattedDurationTotal(musicList.get(position).getDuration()));
            image = getImage(musicList.get(position).getPath());
            if (image != null) {
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageAnimation(this, img_cover, bitmap);
                generateBackgroundColor();
            } else {
                bitmap = null;
                imageAnimation(this, img_cover, bitmap);
            }
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.start();
        }
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
    }

    private void nextBtnClicked() {
        mediaPlayer.stop();
        mediaPlayer.release();
        if (position < totalSongs) {
            position = position + 1;
            uri = Uri.parse(musicList.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            song_title.setText(musicList.get(position).getTitle());
            artist_name.setText(musicList.get(position).getArtist());
            duration_total.setText(formattedDurationTotal(musicList.get(position).getDuration()));
            image = getImage(musicList.get(position).getPath());
            if (image != null) {
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageAnimation(this, img_cover, bitmap);
                generateBackgroundColor();
            } else {
                bitmap = null;
                imageAnimation(this, img_cover, bitmap);
            }
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.start();
        } else {
            position = 0;
            uri = Uri.parse(musicList.get(position).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            song_title.setText(musicList.get(position).getTitle());
            artist_name.setText(musicList.get(position).getArtist());
            duration_total.setText(formattedDurationTotal(musicList.get(position).getDuration()));
            image = getImage(musicList.get(position).getPath());
            if (image != null) {
                bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageAnimation(this, img_cover, bitmap);
                generateBackgroundColor();
            } else {
                bitmap = null;
                imageAnimation(this, img_cover, bitmap);
            }
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);
        }
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        nextBtnClicked();
        if (mediaPlayer != null){
            mediaPlayer.setOnCompletionListener(this);
        }
    }
}