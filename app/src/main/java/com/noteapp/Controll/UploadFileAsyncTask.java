package com.noteapp.Controll;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UploadFileAsyncTask extends AsyncTask<Void, Void, String> {

    private final Context mContext;
    private final DbxClientV2 mDBClient;
    private final File file;

    public UploadFileAsyncTask(Context mContext, DbxClientV2 mDBClient, File file) {
        this.mContext = mContext;
        this.mDBClient = mDBClient;
        this.file = file;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String res;

        try {
            FileInputStream fis = new FileInputStream(file);
            mDBClient.files().uploadBuilder("/" + file.getName())
                    .withMode(WriteMode.OVERWRITE)
                    .uploadAndFinish(fis);

            Log.d("Upload", "Success");
            res = "File uploaded";
        } catch (DbxException | IOException e) {
            e.printStackTrace();
            res = e.toString();
            Log.d("Upload", "UnSuccessful");
        }
        return res;

    }

    @Override
    protected void onPostExecute(String res) {
        Toast.makeText(mContext, res, Toast.LENGTH_LONG).show();
    }
}
