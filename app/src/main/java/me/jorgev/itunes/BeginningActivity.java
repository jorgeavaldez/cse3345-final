package me.jorgev.itunes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import hugo.weaving.DebugLog;

public class BeginningActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    static final int ADVANCED_SETTINGS_RESULT = 1;
    private String filterType;
    ArrayList<String> favsArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning);

        preferences = getSharedPreferences("APP_FAVORITES", Context.MODE_PRIVATE);
        editor = preferences.edit();

        String data = readFromFile("__favs__.txt");
        Log.d("favs file", data);

        try {
            Gson g = new Gson();
            Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();

            this.favsArr = g.fromJson(data, collectionType);

            Set<String> favsSet = new HashSet<>(favsArr);
            editor.putStringSet("FAVORITES_SET", favsSet);
            editor.commit();
        }

        catch (Exception e) {
            Log.e("reading favs", e.getMessage());
        }
    }

    @DebugLog
    public void onSearch(View target) {
        Intent intent = new Intent(this, MainActivity.class);

        if (filterType != null && !filterType.equals("")) {
            intent.putExtra("FILTER_TYPE", filterType);
        }

        startActivity(intent);
    }

    // TODO: Implement the advanced view activity.
    public void onAdvanced(View target) {
        Intent intent = new Intent(this, AdvancedSearchActivity.class);

        startActivityForResult(intent, this.ADVANCED_SETTINGS_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != this.ADVANCED_SETTINGS_RESULT) {
            Log.d("onActivityResult", "No filter type.");
        }

        else {
            this.filterType = data.getStringExtra("FILTER_TYPE");
            Log.d("onActivityResult", "Filter Type: " + this.filterType.split(",")[1]);
        }
    }

    // TODO: Implement the Favorites view activity.
    public void onFavorites(View target) {
        Intent intent = new Intent(this, FavoritesActivity.class);

        startActivity(intent);
    }

    // TODO: Implement the Past Searches view activity.
    public void onPastSearches(View target) {
        Intent intent = new Intent(this, RecentsActivity.class);

        startActivity(intent);
    }

    @DebugLog
    private void writeToFile(String filename, String data) {
        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(getApplicationContext().openFileOutput(filename,
                            Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @DebugLog
    private String readFromFile(String filename) {

        String ret = "";

        try {
            InputStream inputStream = openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            return null;
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
            return null;
        }

        return ret;
    }

    @DebugLog
    @Override
    protected void onPause() {
        super.onPause();

        Set<String> tempSet = this.preferences.getStringSet("FAVORITES_SET", null);
        if (tempSet == null) {
            Log.e("temp set was null", "couldn't write favs");
            return;
        }

        Gson g = new Gson();
        String data = g.toJson(new ArrayList(Arrays.asList(tempSet.toArray(new String[tempSet.size()]))));
        writeToFile("__favs__.txt", data);
    }
}
