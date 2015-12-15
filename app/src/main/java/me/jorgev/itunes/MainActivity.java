package me.jorgev.itunes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    public boolean advancedViewShowing;
    public RelativeLayout advancedViewRoot;
    View advancedSettingsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        advancedViewShowing = false;
        advancedViewRoot = (RelativeLayout) findViewById(R.id.advanced_form_anchor);
        advancedSettingsView = getLayoutInflater().inflate(R.layout.advanced_search, null);
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

    }

    // This function is the callback for when the advanced search options button is called.
    // It should inflate the 'advanced_search' layout under the buttons and all that jazz.
    @DebugLog
    public void onAdvancedButton(View target) {
        if (!this.advancedViewShowing) {
            advancedViewRoot.addView(advancedSettingsView);
            initSpinner();

            this.advancedViewShowing = true;
        }

        else {
            advancedViewRoot.removeView(advancedSettingsView);
            this.advancedViewShowing = false;
        }
    }

    @DebugLog
    public void initSpinner() {
        String[] spinnerItems = new String[] {"Artist", "Track", "Album", "Video", "Mix", "Song"};

        Spinner s = (Spinner) findViewById(R.id.result_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        s.setAdapter(adapter);

//        String imageURL = "http://i.giphy.com/yShdlgJ3ZVdVm.gif";
//
//        ImageView imageView = (ImageView) findViewById(R.id.cheeseburger_img);
//
//        Glide.with(this).load(imageURL).into(imageView);
    }
}
