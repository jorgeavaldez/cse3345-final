package me.jorgev.itunes;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

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

    public SearchRequest(HashMap<String, String> p) {
        this.reqParams = p;
        this.REQ_URL = buildSearchUrl();
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

        return url.toString();
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

            if (resCode == HttpURLConnection.HTTP_OK) {
                String json = this.convertToString(conn.getInputStream());

                Media[] m = gson.fromJson(json, Media[].class);
                return m;
            }
        }

        catch (Exception e) {
            Log.e("doInBackground", "couldn't connect");
            Log.e("doInBackground", Arrays.toString(e.getStackTrace()));
        }

        return null;
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
