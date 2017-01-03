package com.jacko1972.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.jacko1972.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApi = null;

    @Override
    protected String doInBackground(Void... params) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-14de6.appspot.com/_ah/api/");
            myApi = builder.build();
        }
        String response;
        try {
            response = myApi.getJoke().execute().getData();
        } catch (IOException e) {
            response = e.getMessage();
        }
        return response;
    }
}
