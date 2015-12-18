package me.jorgev.itunes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BeginningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginning);
    }

    protected void onSearch(View target) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    // TODO: Implement the advanced view activity.
    protected void onAdvanced(View target) {
        //Intent intent = new Intent(this, )
    }

    // TODO: Implement the Favorites view activity.
    protected void onFavorites(View target) {

    }

    // TODO: Implement the Past Searches view activity.
    protected void onPastSearches(View target) {

    }
}