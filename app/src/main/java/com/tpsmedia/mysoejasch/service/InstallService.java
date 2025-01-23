package com.tpsmedia.mysoejasch.service;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;

public class InstallService extends Service {

    private static final String APK_FILE_NAME = "mysoejasch.apk";
    private static final String FILE_PROVIDER_AUTHORITY = "com.tpsmedia.mysoejasch.fileprovider";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), APK_FILE_NAME);

        if (file.exists() && file.length() > 0) {
            Uri apkUri = FileProvider.getUriForFile(this, FILE_PROVIDER_AUTHORITY, file);
            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                startActivity(installIntent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Tidak dapat membuka file APK", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File APK tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

