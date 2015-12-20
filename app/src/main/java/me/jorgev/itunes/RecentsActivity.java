package me.jorgev.itunes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Set;

import hugo.weaving.DebugLog;

public class RecentsActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents);

        this.preferences = getSharedPreferences("ITUNES_RECENTS", Context.MODE_PRIVATE);

        Set<String> qHistory = this.preferences.getStringSet("RECENT_QUERIES", null);

        if (qHistory == null) {
            Snackbar.make(findViewById(R.id.recents_layout), "No recents :(", Snackbar.LENGTH_SHORT)
                    .show();

            return;
        }

        String[] s = qHistory.toArray(new String[qHistory.size()]);
        if (s.length > 0) {
            ((Button) findViewById(R.id.button)).setText(s[0]);
        }
        if (s.length > 1) {
            ((Button) findViewById(R.id.button2)).setText(s[1]);
        }
        if (s.length > 2) {
            ((Button) findViewById(R.id.button3)).setText(s[2]);
        }
        if (s.length > 3) {
            ((Button) findViewById(R.id.button4)).setText(s[3]);
        }
        if (s.length > 4) {
            ((Button) findViewById(R.id.button5)).setText(s[4]);
        }
    }

    public void onClickBtn1(View target) {
        Button button = ((Button) findViewById(R.id.button));

        String q = button.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FROM_RECENTS", q);

        startActivity(intent);
    }

    public void onClickBtn2(View target) {
        Button button2 = ((Button) findViewById(R.id.button2));

        String q = button2.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FROM_RECENTS", q);

        startActivity(intent);
    }

    public void onClickBtn3(View target) {
        Button button3 = ((Button) findViewById(R.id.button3));

        String q = button3.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FROM_RECENTS", q);

        startActivity(intent);

    }

    public void onClickBtn4(View target) {
        Button button4 = ((Button) findViewById(R.id.button4));

        String q = button4.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FROM_RECENTS", q);

        startActivity(intent);
    }

    public void onClickBtn5(View target) {
        Button button5 = ((Button) findViewById(R.id.button5));

        String q = button5.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("FROM_RECENTS", q);

        startActivity(intent);
    }


    @DebugLog
    public void onBack(View target) {
        finish();
    }
}
