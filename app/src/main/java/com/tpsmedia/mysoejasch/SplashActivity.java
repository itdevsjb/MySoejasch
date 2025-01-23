package com.tpsmedia.mysoejasch;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tpsmedia.mysoejasch.additional.CircularProgressBar;
import com.tpsmedia.mysoejasch.api.Client;
import com.tpsmedia.mysoejasch.api.Sinkronasi;
import com.tpsmedia.mysoejasch.model.Employee.EmployeeList;
import com.tpsmedia.mysoejasch.model.Purchaseorder.Purchaseorder;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Purchaserequest;
import com.tpsmedia.mysoejasch.service.RefreshData;
import com.tpsmedia.mysoejasch.service.SQLiteHelper;
import com.tpsmedia.mysoejasch.service.ServiceLogin;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private CircularProgressBar circularProgressBar;
    SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dbHelper = new SQLiteHelper(this);

        SharedPreferences sharedPrefs = getSharedPreferences("data_mysoejasch", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        if (!sharedPrefs.contains("token")) {

            Intent home = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(home);
            finish();

        }else{

            ServiceLogin serviceLogin = new ServiceLogin(this);

            if(serviceLogin.getDownloaddata().equals("0")){

                SharedPreferences sgSharedPref = getApplicationContext().getSharedPreferences("data_mysoejasch", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sgSharedPref.edit();
                editor.putString("downloaddata", "1");
                editor.apply();

                if (serviceLogin.getprlevel().equals("1") && serviceLogin.getpolevel().equals("1")){
                    circularProgressBar = findViewById(R.id.circularProgressBar);
                    animateProgress(95, 500);
                    circularProgressBar.setProgress(100);
                    Intent home = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();

                }else{
                    sinkronasiDatabase();
                    circularProgressBar = findViewById(R.id.circularProgressBar);
                    animateProgress(95, 120000);
                }



            }else{
                if (!serviceLogin.getprlevel().equals("1") || !serviceLogin.getpolevel().equals("1")){
                    sinkronasiDatabaseCepat();
                    circularProgressBar = findViewById(R.id.circularProgressBar);
                    animateProgress(95, 5000);
                }else{
                    circularProgressBar = findViewById(R.id.circularProgressBar);
                    animateProgress(95, 3000);
                    circularProgressBar.setProgress(100);
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            Intent home = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(home);
                            finish();
                        }
                    };

                    timer.schedule(task, 3000);

                }



            }

        }



    }

    private void animateProgress(int targetProgress, int targetTime) {
        ValueAnimator animator = ValueAnimator.ofInt(0, targetProgress);
        animator.setDuration(targetTime);
        animator.addUpdateListener(animation -> {
            int progress = (int) animation.getAnimatedValue();
            circularProgressBar.setProgress(progress);
        });
        animator.start();
    }

    private void sinkronasiDatabase(){
        Context context = null;
        ServiceLogin serviceLogin = new ServiceLogin(this);
        Sinkronasi service = Client.getClient().create(Sinkronasi.class);

        Call<Purchaserequest> call = service.postpronline("Bearer "+ serviceLogin.getToken());
        call.enqueue(new Callback<Purchaserequest>() {
            @Override
            public void onResponse(Call<Purchaserequest> call, Response<Purchaserequest> response) {

                if(response.body().getData() != null) {
                    for (Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_PPB (UCode_PPB, No_PPB, Ket, Tgl_PPB, status_approval, approval_1, no_approval_1, cek_approval_1, approval_2, no_approval_2, cek_approval_2, approval_3, no_approval_3, cek_approval_3, approval_4, no_approval_4, cek_approval_4, approval_5, no_approval_5, cek_approval_5, Nama_Dept, remarks, nama_karyawan, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodePPB(), data.getNoPPB(), data.getKet(), data.getTglPBB(), data.getStatus(), data.getApproval1(), data.getNoApproval1(), data.getCekApproval1(), data.getApproval2(), data.getNoApproval2(), data.getCekApproval2(), data.getApproval3(), data.getNoApproval3(), data.getCekApproval3(), data.getApproval4(), data.getNoApproval4(), data.getCekApproval4(), data.getApproval5(), data.getNoApproval5(), data.getCekApproval5(), data.getNama_Dept(), data.getRemarks(), data.getNama_karyawan(), data.getFoto()});
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Purchaserequest> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        Call<Purchaseorder> call2 = service.postpoonline("Bearer "+ serviceLogin.getToken());
        call2.enqueue(new Callback<Purchaseorder>() {
            @Override
            public void onResponse(Call<Purchaseorder> call2, Response<Purchaseorder> response) {
                if(response.body().getData() != null) {
                    for (com.tpsmedia.mysoejasch.model.Purchaseorder.Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_SPB (UCode_SPB, No_SPB, Ket, Tgl_SPB, status_approval, approval_1, no_approval_1, cek_approval_1, approval_2, no_approval_2, cek_approval_2, approval_3, no_approval_3, cek_approval_3, approval_4, no_approval_4, cek_approval_4, approval_5, no_approval_5, cek_approval_5, remarks, nama_karyawan, foto, nama_supp, jenis, lampiran) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodeSPB(), data.getNoSPB(), data.getKet(), data.getTglSPB(), data.getStatus(), data.getApproval1(), data.getNoApproval1(), data.getCekApproval1(), data.getApproval2(), data.getNoApproval2(), data.getCekApproval2(), data.getApproval3(), data.getNoApproval3(), data.getCekApproval3(), data.getApproval4(), data.getNoApproval4(), data.getCekApproval4(), data.getApproval5(), data.getNoApproval5(), data.getCekApproval5(), data.getRemarks(), data.getNama_karyawan(), data.getFoto(), data.getNamaSupp(), data.getJenis(), data.getLampiran()});
                    }
                    circularProgressBar.setProgress(100);
                    Intent home = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                }else{

                    Toast.makeText(getApplicationContext(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Purchaseorder> call2, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });



        Call<EmployeeList> call3 = service.postEmployee("Bearer "+ serviceLogin.getToken());
        call3.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call3, Response<EmployeeList> response) {

                if(response.body().getData() != null) {
                    for (com.tpsmedia.mysoejasch.model.Employee.Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_karyawan (ucode_karyawan, nama_karyawan, jabatan, foto ) VALUES (?, ?, ?, ?)",
                                new Object[]{data.getUcodekaryawan(), data.getNamakaryawan(), data.getJabatan(), data.getPhoto()});
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeeList> call3, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sinkronasiDatabaseCepat(){
        Context context = null;
        ServiceLogin serviceLogin = new ServiceLogin(this);
        Sinkronasi service = Client.getClient().create(Sinkronasi.class);

        Call<Purchaserequest> call = service.postpronlinecepat("Bearer "+ serviceLogin.getToken());
        call.enqueue(new Callback<Purchaserequest>() {
            @Override
            public void onResponse(Call<Purchaserequest> call, Response<Purchaserequest> response) {

                if(response.body().getData() != null) {
                    for (Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_PPB (UCode_PPB, No_PPB, Ket, Tgl_PPB, status_approval, approval_1, no_approval_1, cek_approval_1, approval_2, no_approval_2, cek_approval_2, approval_3, no_approval_3, cek_approval_3, approval_4, no_approval_4, cek_approval_4, approval_5, no_approval_5, cek_approval_5, Nama_Dept, remarks, nama_karyawan, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodePPB(), data.getNoPPB(), data.getKet(), data.getTglPBB(), data.getStatus(), data.getApproval1(), data.getNoApproval1(), data.getCekApproval1(), data.getApproval2(), data.getNoApproval2(), data.getCekApproval2(), data.getApproval3(), data.getNoApproval3(), data.getCekApproval3(), data.getApproval4(), data.getNoApproval4(), data.getCekApproval4(), data.getApproval5(), data.getNoApproval5(), data.getCekApproval5(), data.getNama_Dept(), data.getRemarks(), data.getNama_karyawan(), data.getFoto()});
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Purchaserequest> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        Call<Purchaseorder> call2 = service.postpoonlinecepat("Bearer "+ serviceLogin.getToken());
        call2.enqueue(new Callback<Purchaseorder>() {
            @Override
            public void onResponse(Call<Purchaseorder> call2, Response<Purchaseorder> response) {

                if(response.body().getData() != null) {
                    for (com.tpsmedia.mysoejasch.model.Purchaseorder.Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_SPB (UCode_SPB, No_SPB, Ket, Tgl_SPB, status_approval, approval_1, no_approval_1, cek_approval_1, approval_2, no_approval_2, cek_approval_2, approval_3, no_approval_3, cek_approval_3, approval_4, no_approval_4, cek_approval_4, approval_5, no_approval_5, cek_approval_5, remarks, nama_karyawan, foto, nama_supp, jenis, lampiran) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodeSPB(), data.getNoSPB(), data.getKet(), data.getTglSPB(), data.getStatus(), data.getApproval1(), data.getNoApproval1(), data.getCekApproval1(), data.getApproval2(), data.getNoApproval2(), data.getCekApproval2(), data.getApproval3(), data.getNoApproval3(), data.getCekApproval3(), data.getApproval4(), data.getNoApproval4(), data.getCekApproval4(), data.getApproval5(), data.getNoApproval5(), data.getCekApproval5(), data.getRemarks(), data.getNama_karyawan(), data.getFoto(), data.getNamaSupp(), data.getJenis(), data.getLampiran()});
                    }
                    circularProgressBar.setProgress(100);
                    Intent home = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                }else{

                    Toast.makeText(getApplicationContext(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Purchaseorder> call2, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        Call<Purchaseorder> call3 = service.postpoonlinecepatjasa("Bearer "+ serviceLogin.getToken());
        call3.enqueue(new Callback<Purchaseorder>() {
            @Override
            public void onResponse(Call<Purchaseorder> call3, Response<Purchaseorder> response) {

                if(response.body().getData() != null) {
                    for (com.tpsmedia.mysoejasch.model.Purchaseorder.Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_SPB (UCode_SPB, No_SPB, Ket, Tgl_SPB, status_approval, approval_1, no_approval_1, cek_approval_1, approval_2, no_approval_2, cek_approval_2, approval_3, no_approval_3, cek_approval_3, approval_4, no_approval_4, cek_approval_4, approval_5, no_approval_5, cek_approval_5, remarks, nama_karyawan, foto, nama_supp, jenis, lampiran) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodeSPB(), data.getNoSPB(), data.getKet(), data.getTglSPB(), data.getStatus(), data.getApproval1(), data.getNoApproval1(), data.getCekApproval1(), data.getApproval2(), data.getNoApproval2(), data.getCekApproval2(), data.getApproval3(), data.getNoApproval3(), data.getCekApproval3(), data.getApproval4(), data.getNoApproval4(), data.getCekApproval4(), data.getApproval5(), data.getNoApproval5(), data.getCekApproval5(), data.getRemarks(), data.getNama_karyawan(), data.getFoto(), data.getNamaSupp(), data.getJenis(), data.getLampiran()});
                    }
//                    circularProgressBar.setProgress(100);
//                    Intent home = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(home);
//                    finish();
                }else{

                    Toast.makeText(getApplicationContext(), "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Purchaseorder> call3, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
