package com.jacko1972.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jacko1972.androidjokelibrary.MainActivityAndroidJokeLibrary;
import com.jacko1972.builditbigger.javajokeslibrary.JavaJokesClass;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button tellJokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.joke_progress_spinner);
        tellJokeButton = (Button) findViewById(R.id.joke_button);
    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void tellJoke(View view) {
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
