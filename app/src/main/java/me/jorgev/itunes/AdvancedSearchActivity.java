package me.jorgev.itunes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class AdvancedSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
    }

    protected void onBack(View target) {
        Intent resData = new Intent();
        int resCode;

        int checkedId = ((RadioGroup)findViewById(R.id.radioGroup)).getCheckedRadioButtonId();

        if (checkedId == R.id.advanced_album_button) {

        }
        else if (checkedId == R.id.advanced_artist_button) {

        }

        else if (checkedId = R.id.advanced_song_button) {

        }
        resData.putExtra("FILTER_TYPE", )
    }
}
