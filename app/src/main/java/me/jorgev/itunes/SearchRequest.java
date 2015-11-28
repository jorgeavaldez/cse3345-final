package me.jorgev.itunes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jorgeavaldez on 11/27/15.
 */
public class SearchRequest
extends AsyncTask<String, Double, String> {
    public final String BASE_URL = "https://itunes.apple.com/search?";
    private HashMap<String, String> reqParams;

    public SearchRequest(HashMap<String, String> p) {
        this.reqParams = p;
    }

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

    @Override
    public String doInBackground(String... params) {
        return null;
    }
}
