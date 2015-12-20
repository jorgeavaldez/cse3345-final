package me.jorgev.itunes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

import hugo.weaving.DebugLog;

public class FavoritesActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    public Set<String> favs;
    public ArrayList<Media> favObjs;

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        this.preferences = getSharedPreferences("APP_FAVORITES", Context.MODE_PRIVATE);
        this.favs = this.preferences.getStringSet("FAVORITES_SET", null);

        if (this.favs != null) {
            Log.d("favs", "favs isn't null");
            this.favObjs = new ArrayList<>();

            Gson g = new Gson();

            for (String s : this.favs) {
                // Convert each fav json string into a media object
                // and save it so we can put it in the ListView
                this.favObjs.add(g.fromJson(s, Media.class));
            }

            // Adapter time
            FavoriteAdapter mediaAdapter = new FavoriteAdapter(getApplicationContext(),
                    this.favObjs.toArray(new Media[this.favObjs.size()]));

            Log.d("FavoritesActivity", "Populating the ListView");

            final ListView lv = (ListView) findViewById(R.id.favorites_list_view);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("onItemClick-Favs", "wow");
                    Media b = (Media)lv.getItemAtPosition(position);
                    Gson g = new Gson();
                    Intent intent = new Intent(getApplicationContext(), MusicDetailsActivity.class);
                    intent.putExtra("SELECTED_BOOK_JSON", g.toJson(b));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
            });

            lv.setAdapter(mediaAdapter);
        }
    }
    public void onBack(View target) {
        finish();
    }
}
