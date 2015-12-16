package me.jorgev.itunes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by jorgeavaldez on 12/15/15.
 */
public class AdapterView extends AppCompatActivity {
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
        //setContentView(R.layout.activity_main);

        TextView bviewTitle = (TextView)findViewById(R.id.title_podcast);
        TextView bartistTitle = (TextView)findViewById(R.id.artist_podcast);
        //TextView bviewAuthors = (TextView)findViewById(R.id.);
        //TextView bviewDescription = (TextView)findViewById(R.id.bview_description);
        TextView bviewPrice = (TextView)findViewById(R.id.price_podcast);
        //TextView bviewRating = (TextView)findViewById(R.id.bview_rating);

        bviewTitle.setText("Title: " + b.getTrackName());

        //bartistTitle.setText("Artist: " + b.getAuthors());
        //bviewDescription.setText(b.description);
        bviewPrice.setText("$" + b.getTrackPrice());
        //bviewRating.setText("Rating: " + Math.round(b.rating));
    }
}
