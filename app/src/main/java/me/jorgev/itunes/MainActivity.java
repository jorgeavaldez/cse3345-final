package me.jorgev.itunes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.view.*;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    public boolean advancedViewShowing;
    public RelativeLayout advancedViewRoot;
    private String filterType;

    public Set<String> recentQueries;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefEditor;

    private String query;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //advancedSettingsView = getLayoutInflater().inflate(R.layout.advanced_search, null);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        this.sharedPreferences = getSharedPreferences("ITUNES_RECENTS", Context.MODE_PRIVATE);
        this.prefEditor = sharedPreferences.edit();

        this.recentQueries = sharedPreferences.getStringSet("RECENT_QUERIES", new HashSet<String>());
        Log.e("RECENT QUERIES", this.recentQueries.toString());

        Intent intent = getIntent();

        try {
            this.filterType = intent.getStringExtra("FILTER_TYPE");
            Log.d("FilterType", this.filterType);
        }

        catch (Exception e) {
            this.filterType = "";
            Log.e("FilterType", "Doesn't exist");
        }

        // Make it so you can see the recent search queries
        AutoCompleteTextView queryBox = (AutoCompleteTextView) findViewById(R.id.query_box);
        Log.d("We get here", "tru");
        ArrayAdapter<String> queryBoxAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                this.recentQueries.toArray(new String[this.recentQueries.size()]));

        queryBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String entity = sharedPreferences
                        .getString(parent.getItemAtPosition(position).toString(), null);

                if (entity != null) {
                    filterType = "entity," + entity;
                }

                query = parent.getItemAtPosition(position).toString();
            }
        });

        queryBox.setAdapter(queryBoxAdapter);

        if (intent.hasExtra("FROM_RECENTS")) {
            String recent = intent.getStringExtra("FROM_RECENTS");

            this.query = recent;

            this.filterType = this.sharedPreferences.getString(this.query, "");

            if (!this.filterType.equals("") && !this.filterType.contains(",")) {
                this.filterType = "entity," + this.filterType;
            }

            onSearchButton(findViewById(R.id.main_layout));
        }
    }

//    @DebugLog
//    public void onTestButtonClick(View target) {
//        Log.d("Test", "Swag");
//
//        String imageURL = "http://cfbhc.com/wiki/images/6/67/Smu_logo.png";
//
//        ImageView imageView = (ImageView) findViewById(R.id.image_view);
//
//        Glide.with(this).load(imageURL).asBitmap().into(imageView);
//    }

    @DebugLog
    public void onSearchButton(View target) {
        if (query == null)
            query = ((AutoCompleteTextView) findViewById(R.id.query_box)).getText().toString();

        HashMap<String, String> p = new HashMap<>();

        // Get form things

        p.put("term", query);
        p.put("media", "music");


        if (!this.filterType.equals("")) {
            String[] splt = this.filterType.split(",");
            p.put(splt[0], splt[1]);

            Log.d("onSearch", splt[0] + splt[1]);
        }

        // Write to the SharedPreferences
        this.recentQueries.add(query);
        this.prefEditor.putStringSet("RECENT_QUERIES", this.recentQueries);

        Log.e("add to recents", this.recentQueries.toString());

        // We're using this as a jank map, where the word query gives the entity, if any.
        if (this.filterType != null && !this.filterType.equals(""))
            this.prefEditor.putString(query, this.filterType.split(",")[1]);

        this.query = null;
        this.prefEditor.commit();

        new SearchRequest(p, this, this.getApplicationContext()).execute();
    }

    @DebugLog
    public void onBack(View target) {
        finish();
    }

    // This function is the callback for when the advanced search options button is called.
    // It should inflate the 'advanced_search' layout under the buttons and all that jazz.
//    @DebugLog
//    public void onAdvancedButton(View target) {
//        if (!this.advancedViewShowing) {
//            advancedViewRoot.addView(advancedSettingsView);
//            initSpinner();
//
//            this.advancedViewShowing = true;
//        } else {
//            advancedViewRoot.removeView(advancedSettingsView);
//            this.advancedViewShowing = false;
//        }
//    }

//    @DebugLog
//    public void initSpinner() {
//        String[] spinnerItems = new String[]{"Artist", "Track", "Album", "Video", "Mix", "Song"};
//
//        Spinner s = (Spinner) findViewById(R.id.result_type);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                R.layout.support_simple_spinner_dropdown_item, spinnerItems);
//        s.setAdapter(adapter);
//
////        String imageURL = "http://i.giphy.com/yShdlgJ3ZVdVm.gif";
////
////        ImageView imageView = (ImageView) findViewById(R.id.cheeseburger_img);
////
////        Glide.with(this).load(imageURL).into(imageView);
//    }


//    public void setAttribute(int position, View convertView, ViewGroup parent) {
//
//        Media media = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
//        }
//        // Lookup view for data population
//       //books
//        TextView title = (TextView)convertView.findViewById(R.id.title_book);
//        TextView description = (TextView)convertView.findViewById(R.id.description_book);
//        TextView artist = (TextView)convertView.findViewById(R.id.artist_book);
//        TextView price = (TextView)convertView.findViewById(R.id.price_book);
//        TextView rating =(TextView)convertView.findViewById(R.id.rating_book);
//        TextView genre =(TextView)convertView.findViewById(R.id.genre_book);
//        TextView explicit = (TextView)convertView.findViewById(R.id.expicit_book)
//        ImageView picture = (ImageView) convertView.findViewById(R.id.picture_book);
//
//        // Populate the data into the template view using the data object
//        title.setText(media.trackName);
//        artist.setText(media.artistName);
//        description.setText(media.artistName);
//        price.setText(media.collectionPrice);
//        rating.setText(media.)
//        String imageURL =media.artworkUrl60;
//        Glide.with(this.ctx).load(imageURL).into(bookPicture);
//
//        bookGenre.setText(book.primaryGenreName);
//
//    }



    public void writeToFile(Media[] arr) {
        FileOutputStream outputStream;
        File f = new File("data.txt");
        Gson g = new Gson();
        if (!f.exists()) {
            try {
                f.createNewFile();
            }
            catch(Exception e) {
                Log.e("write", "couldnt create file");
                return;
            }
        }
        try {
            outputStream = openFileOutput("data.txt",
                    Context.MODE_APPEND);
        }

        catch (FileNotFoundException e) {
            Log.e("create fail", "couldnt open file");
            return;
        }

        String contents = g.toJson(arr);

        try {
            outputStream.write(contents.getBytes());
            outputStream.close();
        }

        catch(Exception e) {
            return;
        }
    }
}


