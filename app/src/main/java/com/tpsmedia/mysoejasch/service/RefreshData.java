package com.tpsmedia.mysoejasch.service;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.tpsmedia.mysoejasch.api.Client;
import com.tpsmedia.mysoejasch.api.Sinkronasi;
import com.tpsmedia.mysoejasch.model.Purchaseorder.Purchaseorder;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Purchaserequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RefreshData extends Service{
    private Handler handler;
    private Runnable runnable;
    private long interval = 120000;
    SQLiteHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        dbHelper = new SQLiteHelper(this);
        ServiceLogin serviceLogin = new ServiceLogin(this);
        Sinkronasi service = Client.getClient(this).create(Sinkronasi.class);
        Log.d("MyService", "Service Created");

        runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("MyService", "Doing periodic task...");

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
                        }
                    }

                    @Override
                    public void onFailure(Call<Purchaserequest> call, Throwable t) {

                    }
                });

                Call<Purchaseorder> call2 = service.postpoonlinecepat("Bearer "+ serviceLogin.getToken());
                call2.enqueue(new Callback<Purchaseorder>() {
                    @Override
                    public void onResponse(Call<Purchaseorder> call2, Response<Purchaseorder> response) {
                        if(response.body().getData() != null) {
                            for (com.tpsmedia.mysoejasch.model.Purchaseorder.Datum data : response.body().getData()) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                //db.execSQL("delete from tb_mt_SPB where UCode_SPB = ?", new Object[]{data.getUcodeSPB()});
                                db.execSQL("INSERT OR REPLACE INTO tb_mt_SPB (UCode_SPB, No_SPB, Ket, Tgl_SPB, status_approval, approval_1, no_approval_1, cek_approval_1, approval_2, no_approval_2, cek_approval_2, approval_3, no_approval_3, cek_approval_3, approval_4, no_approval_4, cek_approval_4, approval_5, no_approval_5, cek_approval_5, remarks, nama_karyawan, foto, nama_supp, jenis, lampiran) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                        new Object[]{data.getUcodeSPB(), data.getNoSPB(), data.getKet(), data.getTglSPB(), data.getStatus(), data.getApproval1(), data.getNoApproval1(), data.getCekApproval1(), data.getApproval2(), data.getNoApproval2(), data.getCekApproval2(), data.getApproval3(), data.getNoApproval3(), data.getCekApproval3(), data.getApproval4(), data.getNoApproval4(), data.getCekApproval4(), data.getApproval5(), data.getNoApproval5(), data.getCekApproval5(), data.getRemarks(), data.getNama_karyawan(), data.getFoto(), data.getNamaSupp(), data.getJenis(), data.getLampiran()});
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Purchaseorder> call2, Throwable t) {

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
                        }
                    }
                    @Override
                    public void onFailure(Call<Purchaseorder> call3, Throwable t) {

                    }
                });

                handler.postDelayed(this, interval);
            }
        };

        handler.post(runnable);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Log.d("MyService", "Service Destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
