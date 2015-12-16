package me.jorgev.itunes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import hugo.weaving.DebugLog;

import com.google.gson.*;

/**
 * Created by jorgeavaldez on 11/27/15.
 */
public class SearchRequest
extends AsyncTask<String, Double, Media[]> {
    public final String BASE_URL = "https://itunes.apple.com/search?";
    public String REQ_URL;

    private HashMap<String, String> reqParams;
    private Activity parent;
    private Context ctx;

    public SearchRequest(HashMap<String, String> p, Activity parent, Context ctx) {
        this.reqParams = p;
        this.REQ_URL = buildSearchUrl();

        this.parent = parent;
        this.ctx = ctx;
    }

    @DebugLog
    private String buildSearchUrl() {
        StringBuilder url = new StringBuilder();

        for (String key : this.reqParams.keySet()) {
            url.append(key);
            url.append("=");
            url.append(this.reqParams.get(key));
            url.append("&");
        }

        url.deleteCharAt(url.length() - 1);

        return this.BASE_URL + url.toString();
    }

    @DebugLog
    @Override
    public Media[] doInBackground(String... params) {

        Gson gson = new Gson();
        URL url;
        HttpURLConnection conn;
        try {
            url = new URL(this.REQ_URL);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
        }

        catch (Exception e) {
            Log.e("doInBackground", "couldn't build url/open connection");
            Log.e("doInBackground", Arrays.toString(e.getStackTrace()));

            return null;
        }

        try {
            conn.connect();
            int resCode = conn.getResponseCode();

            Log.d("gets here", "" + resCode +"true");
            if (resCode == HttpURLConnection.HTTP_OK) {
                String json = this.convertToString(conn.getInputStream());
                Log.d("fuck", "dank");

                JsonParser p = new JsonParser();
                JsonObject obj = p.parse(json).getAsJsonObject();
                JsonArray arr = obj.getAsJsonArray("results");

                Log.e("kill me", arr.toString());

                Media[] m = gson.fromJson(arr.toString(), Media[].class);
                return m;
            }
        }

        catch (Exception e) {
            Log.e("doInBackground", "couldn't connect");
            Log.e("shit", e.getMessage());
            for (StackTraceElement s : e.getStackTrace())
                Log.e("doInBackground", s.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Media[] m) {
        MediaAdapter mediaAdapter = new MediaAdapter(this.ctx, m);

        final ListView lv = (ListView) this.parent.findViewById(R.id.listText);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Media b = (Media)lv.getItemAtPosition(position);
                Gson g = new Gson();
                Intent intent = new Intent(ctx, AdapterView.class);
                intent.putExtra("SELECTED_BOOK_JSON", g.toJson(b));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
    }

    public String convertToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(inputStream));

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line+"\n");
        }
        reader.close();
        return builder.toString();
    }
}
