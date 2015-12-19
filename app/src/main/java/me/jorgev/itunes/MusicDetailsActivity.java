package me.jorgev.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

/**
 * Created by jorgeavaldez on 12/15/15.
 */
public class MusicDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Media b = null;
        Gson g = new Gson();

        try {
            b = g.fromJson(i.getExtras().getString("SELECTED_BOOK_JSON"), Media.class);
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

        bviewTitle.setText(b.getTrackName());

        bartistTitle.setText("Artist: " + b.getArtistName());
        bviewPrice.setText("$" + b.getTrackPrice());
        bviewAlbum.setText("Album: " + b.getCollectionName());
        bviewGenre.setText("Genre: " + b.getPrimaryGenreName());

        if (b.getWrapperType().equals("collection")) {
            bviewTitle.setText(b.getCollectionName());

            bartistTitle.setText("Artist: " + b.getArtistName());
            bviewPrice.setText("$" + b.getCollectionPrice());
            bviewAlbum.setText(" ");
            bviewGenre.setText("Genre: " + b.getPrimaryGenreName());
        }
    }

    public void onBack(View target) {
        finish();
    }
}
