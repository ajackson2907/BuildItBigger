package com.jacko1972.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.jacko1972.androidjokelibrary.MainActivityAndroidJokeLibrary;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button tellJokeButton;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.joke_progress_spinner);
        tellJokeButton = (Button) findViewById(R.id.joke_button);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                tellJokeButton.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                fetchNewInterstitialAd();
                fetchJoke();
            }
        });
        fetchNewInterstitialAd();
    }

    private void fetchNewInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);
    }

    public void tellJoke(View view) {
        if (interstitialAd.isLoaded()) {
            tellJokeButton.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            interstitialAd.show();
        } else {
            fetchJoke();
        }
    }

    private void fetchJoke() {
        tellJokeButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        new GetJokeAsyncTask() {
            @Override
            protected void onPostExecute(String s) {
                if (s != null) {
                    Intent intent = new Intent(MainActivity.this, MainActivityAndroidJokeLibrary.class);
                    intent.putExtra(getString(R.string.intent_joke_extra), s);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No Joke from Async Task!", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
                tellJokeButton.setEnabled(true);
            }
        }.execute();
    }
}
