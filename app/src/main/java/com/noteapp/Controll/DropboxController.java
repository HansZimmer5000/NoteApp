package com.noteapp.Controll;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class DropboxController {

    final public int PERM_REQUEST_CODE = 6;

    public DropboxController() {
    }

    public void askPermission(Activity activity, Context context) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(context, "Storage Permission Needed to store dropbox login data", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERM_REQUEST_CODE);
        }

    }

    public boolean checkPermission(Context context) {
        int result1 = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result1 == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isConnected() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = process.waitFor();
            return exitValue == 0;
        } catch (Exception ex) {
            return false;
        }

    }
}
