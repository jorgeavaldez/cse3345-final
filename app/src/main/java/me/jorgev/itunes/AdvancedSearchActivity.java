package me.jorgev.itunes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import hugo.weaving.DebugLog;

public class AdvancedSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
    }

    @DebugLog
    public void onBack(View target) {
        Intent resData = new Intent();
        int resCode = 1;

        int checkedId = ((RadioGroup)findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        String res;

        if (checkedId == R.id.advanced_album_button) {
            res = "entity,album";
        }
        else if (checkedId == R.id.advanced_artist_button) {
            res = "entity,musicArtist";
        }

        else if (checkedId == R.id.advanced_song_button) {
            res = "entity,song";
        }

        else if (checkedId == R.id.advanced_video_button) {
            res = "entity,musicVideo";
        }

        else {
            res = "";
            resCode = 0;
        }
        resData.putExtra("FILTER_TYPE", res);

        setResult(resCode, resData);
        finish();
    }
}
