package com.jacko1972.androidjokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityAndroidJokeLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_android_joke_library);

        TextView textView = (TextView) findViewById(R.id.myTextView);
        String joke = getIntent().getStringExtra(getString(R.string.intent_joke_extra));
        if (joke != null && joke.length() != 0) {
            textView.setText(joke);
        } else {
            textView.setText(R.string.no_joke_error_string);
        }
    }
}
