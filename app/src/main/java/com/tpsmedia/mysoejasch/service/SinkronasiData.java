package com.tpsmedia.mysoejasch.service;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.tpsmedia.mysoejasch.MainActivity;
import com.tpsmedia.mysoejasch.SplashActivity;
import com.tpsmedia.mysoejasch.api.Client;
import com.tpsmedia.mysoejasch.api.Sinkronasi;
import com.tpsmedia.mysoejasch.model.Employee.EmployeeList;
import com.tpsmedia.mysoejasch.model.Purchaseorder.Purchaseorder;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Purchaserequest;
import com.tpsmedia.mysoejasch.model.Warehouse.Pallet.PalletList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SinkronasiData {

    SQLiteHelper dbHelper;

    public SinkronasiData(Context context) {}

    public boolean sinkronPPB(Context context){
        dbHelper = new SQLiteHelper(context);
        ServiceLogin serviceLogin = new ServiceLogin(context);
        Sinkronasi service = Client.getClient(context).create(Sinkronasi.class);

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

        return true;

    }

    public boolean sinkronSPB(Context context){
        dbHelper = new SQLiteHelper(context);
        ServiceLogin serviceLogin = new ServiceLogin(context);
        Sinkronasi service = Client.getClient(context).create(Sinkronasi.class);

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

        return true;

    }

    public boolean sinkronSPBSingle(Context context, String id){
        dbHelper = new SQLiteHelper(context);
        ServiceLogin serviceLogin = new ServiceLogin(context);
        Sinkronasi service = Client.getClient(context).create(Sinkronasi.class);
        Call<Purchaseorder> call2 = service.postpoonlinesingle("Bearer "+ serviceLogin.getToken(), id);
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
        return true;
    }

    public boolean sinkronSPJSingle(Context context, String id){
        dbHelper = new SQLiteHelper(context);
        ServiceLogin serviceLogin = new ServiceLogin(context);
        Sinkronasi service = Client.getClient(context).create(Sinkronasi.class);
        Call<Purchaseorder> call2 = service.postpoonlinesinglejasa("Bearer "+ serviceLogin.getToken(), id);
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
        return true;
    }

    public boolean sinkronPPBSingle(Context context, String id){
        dbHelper = new SQLiteHelper(context);
        ServiceLogin serviceLogin = new ServiceLogin(context);
        Sinkronasi service = Client.getClient(context).create(Sinkronasi.class);

        Call<Purchaserequest> call2 = service.postpronlinesingle("Bearer "+ serviceLogin.getToken(), id);
        call2.enqueue(new Callback<Purchaserequest>() {
            @Override
            public void onResponse(Call<Purchaserequest> call2, Response<Purchaserequest> response) {
                if(response.body().getData() != null) {
                    for (Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_PPB (UCode_PPB, No_PPB, Ket, Tgl_PPB, status_approval, approval_1, no_approval_1, cek_approval_1, approval_2, no_approval_2, cek_approval_2, approval_3, no_approval_3, cek_approval_3, approval_4, no_approval_4, cek_approval_4, approval_5, no_approval_5, cek_approval_5, Nama_Dept, remarks, nama_karyawan, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Object[]{data.getUcodePPB(), data.getNoPPB(), data.getKet(), data.getTglPBB(), data.getStatus(), data.getApproval1(), data.getNoApproval1(), data.getCekApproval1(), data.getApproval2(), data.getNoApproval2(), data.getCekApproval2(), data.getApproval3(), data.getNoApproval3(), data.getCekApproval3(), data.getApproval4(), data.getNoApproval4(), data.getCekApproval4(), data.getApproval5(), data.getNoApproval5(), data.getCekApproval5(), data.getNama_Dept(), data.getRemarks(), data.getNama_karyawan(), data.getFoto()});
                    }
                }
            }
            @Override
            public void onFailure(Call<Purchaserequest> call2, Throwable t) {

            }
        });


        return true;

    }

    public boolean sinkronEmployee(Context context){
        dbHelper = new SQLiteHelper(context);
        ServiceLogin serviceLogin = new ServiceLogin(context);
        Sinkronasi service = Client.getClient(context).create(Sinkronasi.class);

        Call<EmployeeList> call = service.postEmployee("Bearer "+ serviceLogin.getToken());
        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call2, Response<EmployeeList> response) {

                if(response.body().getData() != null) {
                    for (com.tpsmedia.mysoejasch.model.Employee.Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_karyawan (ucode_karyawan, nama_karyawan, jabatan, foto ) VALUES (?, ?, ?, ?)",
                                new Object[]{data.getUcodekaryawan(), data.getNamakaryawan(), data.getJabatan(), data.getPhoto()});
                    }
                }else{

                }

            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {

            }
        });

        return true;

    }

    public boolean sinkronPallet(Context context){
        dbHelper = new SQLiteHelper(context);
        ServiceLogin serviceLogin = new ServiceLogin(context);
        Sinkronasi service = Client.getClient(context).create(Sinkronasi.class);

        Call<PalletList> call = service.postPallet("Bearer "+ serviceLogin.getToken());
        call.enqueue(new Callback<PalletList>() {
            @Override
            public void onResponse(Call<PalletList> call, Response<PalletList> response) {

                if(response.body().getData() != null) {
                    for (com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum data : response.body().getData()) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("INSERT OR REPLACE INTO tb_mt_pallet (UCode_Pallet, Kode_Pallet, Nama_Pallet, Lokasi, Divisi, Stat, Ket, Capacity, TypeCapacity, Pembuat, Tgl_Jam_Buat ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",
                                new Object[]{data.getUCode_Pallet(), data.getKode_Pallet(), data.getNama_Pallet(), data.getLokasi(), data.getDivisi(),  data.getStat(), data.getKet(), data.getCapacity(), data.getTypeCapacity(), data.getPembuat(), data.getTgl_Jam_Buat()});
                    }
                }else{

                }

            }

            @Override
            public void onFailure(Call<PalletList> call, Throwable t) {

            }
        });

        return true;

    }

}
