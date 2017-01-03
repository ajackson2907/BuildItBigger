package com.jacko1972.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    private static final String TAG = "JokeAsyncTaskTest";
    @Test
    public void testAsyncTask() throws Exception {
        try {
            GetJokeAsyncTask getJokeAsyncTask = new GetJokeAsyncTask();
            getJokeAsyncTask.execute();
            String result = getJokeAsyncTask.get(30, TimeUnit.SECONDS);
            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
