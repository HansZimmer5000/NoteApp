package com.noteapp.View;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxClientV2;
import com.noteapp.Controll.ConnectivityHelper;
import com.noteapp.Controll.DropboxController;
import com.noteapp.Controll.FileHelper;
import com.noteapp.Controll.PermissionHelper;
import com.noteapp.Controll.UploadFileAsyncTask;
import com.noteapp.R;

import java.io.File;

public class DropBoxFragment extends Fragment {

    final static private String DB_PREFS = "db_prefs";
    final static private String DB_ACCESS_TOKEN = "access_token";
    final static private int FILE_REQUEST_CODE = 5;
    private DropboxController mController;
    private MainActivity mainActivity;
    private Button loginButton, uploadButton;
    private boolean mLoggedIn = false;
    private String accessToken;

    public DropBoxFragment() {
    }

    public static DbxClientV2 getClient(String accessToken) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("zonk_test/1.0").build();
        DbxClientV2 client = new DbxClientV2(config, accessToken);
        return client;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        mController = new DropboxController();
        mainActivity = MainActivity.mainController.provideMainActivity();

        View rootView = inflater.inflate(R.layout.fragment_dropbox, container, false);

        loginButton = (Button) rootView.findViewById(R.id.loginButton);
        uploadButton = (Button) rootView.findViewById(R.id.uploadButton);

        PermissionHelper.askPermission(mainActivity, getContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLoggedIn()) {
                    if (!PermissionHelper.checkPermission(getContext())) {
                        Toast.makeText(getContext(), "Can't continue without permissions", Toast.LENGTH_SHORT).show();
                    } else if (!ConnectivityHelper.isConnected()) {
                        Toast.makeText(getContext(), "Check internet connection", Toast.LENGTH_SHORT).show();
                    } else {
                        startLogin();
                    }
                } else {
                    logout();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        login();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!PermissionHelper.checkPermission(getContext())) {
                    Toast.makeText(getContext(), "Can't continue without Storage permissions", Toast.LENGTH_SHORT).show();
                } else if (!ConnectivityHelper.isConnected()) {
                    Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    upload();
                }
            }
        });
    }

    private boolean isLoggedIn() {
        return mLoggedIn;
    }

    private void login() {

        SharedPreferences prefs = mainActivity.getSharedPreferences(DB_PREFS, Context.MODE_PRIVATE);
        accessToken = prefs.getString(DB_ACCESS_TOKEN, null);

        if (accessToken == null) {
            accessToken = Auth.getOAuth2Token(); //generate Access Token
        }

        if (accessToken != null) {
            prefs.edit().putString(DB_ACCESS_TOKEN, accessToken).apply();

            Toast.makeText(getContext(), "Logged In", Toast.LENGTH_LONG).show();
            mLoggedIn = true;
            uploadButton.setEnabled(true);

            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loginButton.setText("Logout");
                }
            });
        }


    }

    private void startLogin() {
        Auth.startOAuth2Authentication(getContext(), getString(R.string.DB_APP_KEY));
    }

    private void logout() {

        mLoggedIn = false;
        uploadButton.setEnabled(false);

        SharedPreferences prefs = mainActivity.getSharedPreferences(DB_PREFS, 0);
        prefs.edit().clear().apply();
        accessToken = null;

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginButton.setText("DropBox Login");
                uploadButton.setEnabled(false);
            }
        });

        Toast.makeText(getContext(), "LoggedOut", Toast.LENGTH_LONG).show();

    }

    private void upload() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        startActivityForResult(intent, FILE_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == FILE_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                File file = new File(FileHelper.getPath(getContext(), data.getData()));
                Toast.makeText(getContext(), "Starting upload", Toast.LENGTH_SHORT).show();
                new UploadFileAsyncTask(
                        getContext(),
                        getClient(accessToken),
                        file)
                        .execute();


            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermissionHelper.PERM_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(getContext(), "Can't proceed without Storage permission", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
