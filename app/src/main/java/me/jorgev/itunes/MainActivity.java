package me.jorgev.itunes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
}
