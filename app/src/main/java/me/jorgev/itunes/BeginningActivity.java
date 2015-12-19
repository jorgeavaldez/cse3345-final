package me.jorgev.itunes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import hugo.weaving.DebugLog;

public class BeginningActivity extends AppCompatActivity {

    static final int ADVANCED_SETTINGS_RESULT = 1;
    private String filterType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning);
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

    }

    // TODO: Implement the Past Searches view activity.
    public void onPastSearches(View target) {
        Intent intent = new Intent(this, RecentsActivity.class);

        startActivity(intent);
    }
}
