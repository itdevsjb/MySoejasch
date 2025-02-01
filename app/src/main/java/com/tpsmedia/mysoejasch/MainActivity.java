package com.tpsmedia.mysoejasch;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.pm.PackageManager;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import androidx.core.content.ContextCompat;

import com.tpsmedia.materialx.ui.design.intro.IntroPagerAdapter;
import com.tpsmedia.materialx.ui.design.model.Image;
import com.tpsmedia.materialx.ui.design.utils.Tools;
import com.tpsmedia.mysoejasch.api.Client;
import com.tpsmedia.mysoejasch.api.Sinkronasi;
import com.tpsmedia.mysoejasch.databinding.ActivityMainBinding;
import com.tpsmedia.mysoejasch.employee.EmployeeActivity;
import com.tpsmedia.mysoejasch.model.Purchaseorder.Purchaseorder;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Purchaserequest;
import com.tpsmedia.mysoejasch.model.VersionResponse;
import com.tpsmedia.mysoejasch.poonline.POOnlineActivity;
import com.tpsmedia.mysoejasch.pronline.PROnlineActivity;
import com.tpsmedia.mysoejasch.service.BluetoothPrinterHelper;
import com.tpsmedia.mysoejasch.service.RefreshData;
import com.tpsmedia.mysoejasch.service.SQLiteHelper;
import com.tpsmedia.mysoejasch.service.ServiceLogin;
import com.tpsmedia.mysoejasch.warehouse.WarehouseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private IntroPagerAdapter.MyViewPagerAdapter myViewPagerAdapter;
    private List<Image> items = new ArrayList<>();
    private Runnable runnable = null;
    private Handler handler = new Handler();

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private BluetoothPrinterHelper bluetoothPrinterHelper;

    private final String apkFileName = "mysoejasch.apk";
    private AlertDialog progressDialog;
    private boolean doubleBackToExitPressedOnce = false;

    private FusedLocationProviderClient fusedLocationClient;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int REQUEST_UNKNOWN_APP_SOURCES = 123;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;

    SQLiteHelper dbHelper;



    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SharedPreferences sharedPrefs = getSharedPreferences("data_mysoejasch", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        if (!sharedPrefs.contains("token")) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        ServiceLogin serviceLogin = new ServiceLogin(this);

        if (!serviceLogin.getprlevel().equals("1") && !serviceLogin.getpolevel().equals("1")){
            Intent serviceIntent = new Intent(this, RefreshData.class);
            startService(serviceIntent);
        }



        if (!isUnknownSourcesPermissionGranted()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_UNKNOWN_APP_SOURCES);
        } else {

        }



        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        bluetoothPrinterHelper = new BluetoothPrinterHelper(this);

        initToolbar();

        // Set up navigation drawer item listener
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_item_1:
                    // Handle item 1 click
                    break;
                case R.id.nav_item_2:
                    // Handle item 2 click
                    break;

                case R.id.nav_bluetoot:
                    bluetoothPrinterHelper.selectBluetoothDevice(new BluetoothDeviceCallback() {
                        @Override
                        public void onDeviceSelected(boolean isConnected) {
                            // Menangani hasil koneksi Bluetooth
                            if (isConnected) {
                                Toast.makeText(MainActivity.this, "Perangkat siap untuk mencetak", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Tidak ada perangkat terhubung", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    break;

                case R.id.nav_updater:
                    checkForUpdates();
                    break;

                case R.id.nav_logout:
                    logout();
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });

        initToolbar();
        dbHelper = new SQLiteHelper(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }


    }

    private void getLastLocation() {
        @SuppressLint("MissingPermission") Task<android.location.Location> task = fusedLocationClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    SharedPreferences sgSharedPref = getApplicationContext().getSharedPreferences("data_mysoejasch_form", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = sgSharedPref.edit();
                    editor.putString("Lat", String.valueOf(latitude));
                    editor.putString("Long", String.valueOf(longitude));
                    editor.putString("Device", String.valueOf(Build.MODEL));
                    editor.putString("itempo", "");
                    editor.apply();

                } else {
                    Toast.makeText(MainActivity.this, "Lokasi tidak tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initToolbar() {
        ServiceLogin serviceLogin = new ServiceLogin(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(serviceLogin.getLoginName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.toolbar.setNavigationIcon(R.drawable.ic_menu);
        binding.toolbar.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Mengatur warna status bar
        Tools.setSystemBarColor(this);
    }

    public interface BluetoothDeviceCallback {
        void onDeviceSelected(boolean isConnected);
    }


    private void sinkronasiDatabase(Context context){
        ServiceLogin serviceLogin = new ServiceLogin(this);
        Sinkronasi service = Client.getClient(this).create(Sinkronasi.class);
        Call<Purchaserequest> call = service.postpronline("Bearer "+ serviceLogin.getToken());
        call.enqueue(new Callback<Purchaserequest>() {
            @Override
            public void onResponse(Call<Purchaserequest> call, Response<Purchaserequest> response) {

                if(response.body().getData() != null) {
                    for (Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_PPB (UCode_PPB, No_PPB, Ket, Tgl_PPB, status_approval, approval_1, approval_2, approval_3, approval_4, Nama_Dept, remarks, nama_karyawan, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodePPB(), data.getNoPPB(), data.getKet(), data.getTglPBB(), data.getStatus(), data.getApproval1(), data.getApproval2(), data.getApproval3(), data.getApproval4(), data.getNama_Dept(), data.getRemarks(), data.getNama_karyawan(), data.getFoto()});
                    }
                }else{

                    Toast.makeText(context, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Purchaserequest> call, Throwable t) {

                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        Call<Purchaseorder> call2 = service.postpoonline("Bearer "+ serviceLogin.getToken());
        call2.enqueue(new Callback<Purchaseorder>() {
            @Override
            public void onResponse(Call<Purchaseorder> call2, Response<Purchaseorder> response) {

                if(response.body().getData() != null) {
                    for (com.tpsmedia.mysoejasch.model.Purchaseorder.Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_SPB (UCode_SPB, No_SPB, Ket, Tgl_SPB, status_approval, approval_1, approval_2, approval_3, approval_4, remarks, nama_karyawan, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodeSPB(), data.getNoSPB(), data.getKet(), data.getTglSPB(), data.getStatus(), data.getApproval1(), data.getApproval2(), data.getApproval3(), data.getApproval4(), data.getRemarks(), data.getNama_karyawan(), data.getFoto()});
                    }
                }else{

                    Toast.makeText(context, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Purchaseorder> call2, Throwable t) {

                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void logout() {

        SharedPreferences sgSharedPref = getApplicationContext().getSharedPreferences("data_mysoejasch", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sgSharedPref.edit();
        String downloaddata = sgSharedPref.getString("downloaddata", "0");
        editor.clear();
        editor.putString("downloaddata", downloaddata);
        editor.apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void showList(View view) {
        startActivity(new Intent(getApplicationContext(), PROnlineActivity.class));
    }
    public void showTab(View view) {
        startActivity(new Intent(getApplicationContext(), POOnlineActivity.class));
    }
    public void showEmployee(View view) {
        startActivity(new Intent(getApplicationContext(), EmployeeActivity.class));
    }
    public void showProgress(View view) {
        startActivity(new Intent(getApplicationContext(), WarehouseActivity.class));
    }



    private void checkForUpdates() {
        ServiceLogin serviceLogin = new ServiceLogin(this);
        Sinkronasi service = Client.getClient(this).create(Sinkronasi.class);
        Call<VersionResponse> call = service.postversion("Bearer "+ serviceLogin.getToken());
        call.enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    VersionResponse updateResponse = response.body();
                    int latestVersionCode = updateResponse.getVersionCode();
                    String apkUrl = updateResponse.getApkUrl();

                    int currentVersionCode = getCurrentVersionCode();
                    if (latestVersionCode > currentVersionCode) {
                        showProgressDialog();
                        downloadApk(apkUrl);
                    } else {
                        Toast.makeText(MainActivity.this, "Aplikasi sudah versi terbaru", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Gagal memeriksa pembaruan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal memeriksa pembaruan", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private int getCurrentVersionCode() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return pInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void showProgressDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null);
        ProgressBar progressBar = dialogView.findViewById(R.id.progressBar);

        progressDialog = new AlertDialog.Builder(this)
                .setTitle("Mengunduh Pembaruan")
                .setView(dialogView)
                .setCancelable(false)
                .create();

        progressDialog.show();
    }

    private void downloadApk(String apkUrl) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl))
                .setTitle("Mengunduh Pembaruan")
                .setDescription("Sedang mengunduh pembaruan aplikasi...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(false);

        File downloadDir = Environment.getExternalStorageDirectory();
        if (downloadDir != null) {
            File apkFile = new File(downloadDir + "/Download/", apkFileName);
            request.setDestinationUri(Uri.fromFile(apkFile));
        }

        // Mendapatkan instance DownloadManager dan memulai unduhan
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = downloadManager.enqueue(request);

        // Monitoring status unduhan dalam thread terpisah
        new Thread(() -> {
            boolean downloading = true;
            while (downloading) {
                DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
                try (Cursor cursor = downloadManager.query(query)) {
                    if (cursor != null && cursor.moveToFirst()) {
                        // Mendapatkan informasi unduhan
                        @SuppressLint("Range") int bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                        @SuppressLint("Range") int totalBytes = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                        // Menghitung progress
                        if (totalBytes > 0) {
                            final int progress = (int) ((bytesDownloaded * 100L) / totalBytes);
                            runOnUiThread(() -> {
                                if (progressDialog != null) {
                                    ProgressBar progressBar = progressDialog.findViewById(R.id.progressBar);
                                    if (progressBar != null) {
                                        progressBar.setProgress(progress);
                                    }
                                }
                            });
                        }

                        // Mengecek status unduhan
                        @SuppressLint("Range") int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            downloading = false;
                            runOnUiThread(() -> {
                                if (progressDialog != null) {
                                    progressDialog.dismiss(); // Menyembunyikan progress dialog
                                }
                                promptInstall(); // Menginstal APK setelah unduhan selesai
                            });
                        } else if (status == DownloadManager.STATUS_FAILED) {
                            downloading = false;
                            runOnUiThread(() -> {
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(MainActivity.this, "Unduhan gagal", Toast.LENGTH_SHORT).show();
                            });
                        }
                    }
                    Thread.sleep(500); // Menunggu sebelum memeriksa status unduhan lagi
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void promptInstall() {

        File downloadDir = Environment.getExternalStorageDirectory();
        File file = new File(downloadDir + "/Download/", apkFileName);
        if (file.exists() && file.length() > 0) {
            Uri apkUri = FileProvider.getUriForFile(this, "com.tpsmedia.mysoejasch.fileprovider", file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                Toast.makeText(this, "Proses installing...", Toast.LENGTH_SHORT).show();
                Intent serviceIntent = new Intent(this, RefreshData.class);
                stopService(serviceIntent);
                finish();
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Tidak dapat membuka file APK", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File APK tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }


    // Handle result of location permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isUnknownSourcesPermissionGranted() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return getPackageManager().canRequestPackageInstalls();
        } else {
            return true;
        }
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click once again to exit aplication.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 1000); // Menunggu 2 detik untuk reset status
    }

}