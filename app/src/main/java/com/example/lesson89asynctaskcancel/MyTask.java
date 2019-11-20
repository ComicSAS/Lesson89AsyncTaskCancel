package com.example.lesson89asynctaskcancel;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MyTask extends AsyncTask<Void, Void, Void> {

    final String LOG_TAG = "myLogs";

    private TextView mTV;

    public MyTask(TextView mTV) {
        this.mTV = mTV;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTV.setText("Begin");
        Log.d(LOG_TAG, "Begin");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            for (int i = 0; i < 5; i++) {
                if(isCancelled())
                    return null;
                TimeUnit.SECONDS.sleep(1);
                Log.d(LOG_TAG, "isCancelled: " + isCancelled());
            }
        } catch (InterruptedException e) {
            Log.d(LOG_TAG, "Interrupted");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTV.setText("End");
        Log.d(LOG_TAG, "End");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mTV.setText("Cancel");
        Log.d(LOG_TAG, "Cancel");
    }
}
