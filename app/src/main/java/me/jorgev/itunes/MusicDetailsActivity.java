package me.jorgev.itunes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

import hugo.weaving.DebugLog;

/**
 * Created by jorgeavaldez on 12/15/15.
 */
public class MusicDetailsActivity extends AppCompatActivity {
    public String mediaJson;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Media b = null;
        Gson g = new Gson();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            this.mediaJson = i.getExtras().getString("SELECTED_BOOK_JSON");
            b = g.fromJson(this.mediaJson, Media.class);
        }
        catch (Exception e) {
            Log.e("Creating Book Intent", e.getMessage());
        }

        Log.d("intent msg string", i.getExtras().getString("SELECTED_BOOK_JSON"));
        setContentView(R.layout.activity_music_details);

        String imageURL = b.getArtworkUrl100();

        if (!(imageURL == null || imageURL.equals("null"))) {
            ImageView imageView = (ImageView) findViewById(R.id.picture_music);
            Glide.with(this).load(imageURL).into(imageView);
        }

        TextView bviewTitle = (TextView)findViewById(R.id.title_music);
        TextView bartistTitle = (TextView)findViewById(R.id.artist_music);
        TextView bviewPrice = (TextView)findViewById(R.id.music_price);
        TextView bviewAlbum = (TextView)findViewById(R.id.album_music);
        TextView bviewGenre = (TextView)findViewById(R.id.genre_music);

        if (b.getWrapperType().equals("track")) {
            bviewTitle.setText(b.getTrackName());

            bartistTitle.setText("Artist: " + b.getArtistName());
            bviewPrice.setText("$" + b.getTrackPrice());
            bviewAlbum.setText("Album: " + b.getCollectionName());
            bviewGenre.setText("Genre: " + b.getPrimaryGenreName());
        }

        else if (b.getWrapperType().equals("collection")) {
            bviewTitle.setText(b.getCollectionName());

            bartistTitle.setText("Artist: " + b.getArtistName());
            bviewPrice.setText("$" + b.getCollectionPrice());
            ((LinearLayout)findViewById(R.id.media_info_root)).removeView(bviewAlbum);
            bviewGenre.setText("Genre: " + b.getPrimaryGenreName());
            ((LinearLayout)findViewById(R.id.media_info_root))
                    .removeView(findViewById(R.id.preview_button));

        }

        else if (b.getWrapperType().equals("artist")) {
            bviewTitle.setText(b.getArtistName());
            bviewGenre.setText("Genre: " + b.getPrimaryGenreName());
            bviewAlbum.setText(b.getArtistLinkUrl());
            ((LinearLayout)findViewById(R.id.media_info_root)).removeView(bviewPrice);
            ((LinearLayout)findViewById(R.id.media_info_root))
                    .removeView(findViewById(R.id.preview_button));
        }
    }

    public void onFavorite(View target) {
        // we about to go H . A . M

        SharedPreferences preferences = getSharedPreferences("APP_FAVORITES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> favs = preferences.getStringSet("FAVORITES_SET", null);

        if (favs == null) {
            Log.e("add to favs", "favs was null");
            favs = new HashSet<>();
        }

        favs.add(this.mediaJson);

        editor.putStringSet("FAVORITES_SET", favs);
        editor.commit();

        Snackbar.make(findViewById(android.R.id.content), "Added to favorites!",
                Snackbar.LENGTH_SHORT).show();

        Log.d("memes", "super memes");
    }

    @DebugLog
    public void onPreview(View target) {
        Media b = null;
        Gson g = new Gson();

        try {
            b = g.fromJson(this.mediaJson, Media.class);
        }
        catch (Exception e) {
            Log.e("onPreview", e.getMessage());
        }

        if (b.getPreviewUrl() != null && !b.getPreviewUrl().equals("")) {
            try {
                mediaPlayer.setDataSource(b.getPreviewUrl());
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
                mediaPlayer.start();
            }

            catch (Exception e) {
                Snackbar.make(findViewById(android.R.id.content), "Invalid preview url :(",
                        Snackbar.LENGTH_SHORT).show();
                Log.e("try to preview", e.getMessage());
            }
        }

        else
            Snackbar.make(findViewById(android.R.id.content), "No preview url :(",
                    Snackbar.LENGTH_SHORT).show();
    }

    public void onBack(View target) {
        finish();
    }

    @Override
    @DebugLog
    public void onPause() {
        if( mediaPlayer.isPlaying() ) {
            mediaPlayer.stop();
        }
        super.onPause();
    }
}
