package com.tpsmedia.mysoejasch.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tpsmedia.mysoejasch.model.Purchaserequest.Datum;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SoejaschDB.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS tb_mt_PPB (" +
                "UCode_PPB TEXT PRIMARY KEY, " +
                "No_PPB TEXT, " +
                "Ket TEXT, " +
                "Tgl_PPB TEXT, " +
                "status_approval TEXT, " +
                "approval_1 TEXT, " +
                "no_approval_1 TEXT, " +
                "cek_approval_1 TEXT, " +
                "approval_2 TEXT, " +
                "no_approval_2 TEXT, " +
                "cek_approval_2 TEXT, " +
                "approval_3 TEXT, " +
                "no_approval_3 TEXT, " +
                "cek_approval_3 TEXT, " +
                "approval_4 TEXT, " +
                "no_approval_4 TEXT, " +
                "cek_approval_4 TEXT, " +
                "approval_5 TEXT, " +
                "no_approval_5 TEXT, " +
                "cek_approval_5 TEXT, " +
                "Nama_Dept TEXT, " +
                "remarks TEXT, "  +
                "nama_karyawan TEXT, "  +
                "foto TEXT )";
        db.execSQL(createTable);

        String createTable2 = "CREATE TABLE IF NOT EXISTS tb_mt_SPB (" +
                "UCode_SPB TEXT PRIMARY KEY, " +
                "No_SPB TEXT, " +
                "Ket TEXT, " +
                "Tgl_SPB TEXT, " +
                "status_approval TEXT, " +
                "approval_1 TEXT, " +
                "no_approval_1 TEXT, " +
                "cek_approval_1 TEXT, " +
                "approval_2 TEXT, " +
                "no_approval_2 TEXT, " +
                "cek_approval_2 TEXT, " +
                "approval_3 TEXT, " +
                "no_approval_3 TEXT, " +
                "cek_approval_3 TEXT, " +
                "approval_4 TEXT, " +
                "no_approval_4 TEXT, " +
                "cek_approval_4 TEXT, " +
                "approval_5 TEXT, " +
                "no_approval_5 TEXT, " +
                "cek_approval_5 TEXT, " +
                "nama_supp TEXT, "  +
                "remarks TEXT, " +
                "jenis TEXT, "  +
                "lampiran TEXT, "  +
                "nama_karyawan TEXT, "  +
                "foto TEXT )";
        db.execSQL(createTable2);

        String createTable3 = "CREATE TABLE IF NOT EXISTS tb_dt_PPB_brg (" +
                "UCode_PPB TEXT, " +
                "No_Urut TEXT, " +
                "UCode_Brg TEXT, " +
                "Kode_Brg TEXT, " +
                "Nama_Brg TEXT, " +
                "Qty TEXT, " +
                "Satuan TEXT, " +
                "Qty_Std TEXT, " +
                "Satuan_Std TEXT, " +
                "Ket TEXT, " +
                "Approval_cek TEXT, " +
                "PRIMARY KEY (UCode_PPB, No_Urut))";
        db.execSQL(createTable3);

        String createTable4 = "CREATE TABLE IF NOT EXISTS tb_dt_SPB_brg (" +
                "UCode_SPB TEXT, " +
                "No_Urut TEXT, " +
                "UCode_Brg TEXT, " +
                "Kode_Brg TEXT, " +
                "Nama_Brg TEXT, " +
                "Harga_Unit TEXT, " +
                "Qty TEXT, " +
                "Satuan TEXT, " +
                "Qty_Std TEXT, " +
                "Satuan_Std TEXT, " +
                "Harga TEXT, " +
                "Discount TEXT, " +
                "Sub_Total TEXT, " +
                "Ket TEXT, " +
                "Approval_cek TEXT, "+
                "Pajak TEXT, "+
                "PRIMARY KEY (UCode_SPB, No_Urut))";
        db.execSQL(createTable4);

        String createTable5 = "CREATE TABLE IF NOT EXISTS tb_mt_karyawan (" +
                "ucode_karyawan TEXT PRIMARY KEY, " +
                "nama_karyawan TEXT, " +
                "jabatan TEXT, " +
                "foto TEXT )";
        db.execSQL(createTable5);


        String createTable6 = "CREATE TABLE IF NOT EXISTS tb_mt_approval (" +
                "no_approval TEXT PRIMARY KEY, " +
                "approval_tahap TEXT, " +
                "nama_karyawan TEXT, " +
                "status TEXT, " +
                "user_agent TEXT, " +
                "latitude TEXT, " +
                "longitude TEXT, " +
                "tanggal_jam TEXT, " +
                "remarks TEXT )";
        db.execSQL(createTable6);

        String createTable7 = "CREATE TABLE IF NOT EXISTS tb_mt_pallet (" +
                "UCode_Pallet TEXT PRIMARY KEY, " +
                "Kode_Pallet TEXT, " +
                "Nama_Pallet TEXT, " +
                "Lokasi TEXT, " +
                "Divisi TEXT, " +
                "Stat TEXT, " +
                "Ket TEXT, " +
                "Capacity TEXT, " +
                "TypeCapacity TEXT, " +
                "Pembuat TEXT, " +
                "Tgl_Jam_Buat TEXT )";
        db.execSQL(createTable7);

        String createTable8 = "CREATE TABLE IF NOT EXISTS tb_cek_SPB (" +
                "UCode_SPB TEXT, " +
                "UCode_Brg TEXT, " +
                "PRIMARY KEY (UCode_SPB, UCode_Brg))";
        db.execSQL(createTable8);

        String createTable9 = "CREATE TABLE IF NOT EXISTS tb_cek_PPB (" +
                "UCode_PPB TEXT, " +
                "UCode_Brg TEXT, " +
                "PRIMARY KEY (UCode_PPB, UCode_Brg))";
        db.execSQL(createTable9);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_mt_PPB");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_mt_SPB");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_dt_PPB_brg");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_dt_SPB_brg");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_mt_karyawan");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_mt_approval");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_mt_pallet");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_cek_SPB");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS tb_cek_PPB");
        onCreate(db);

    }


    public List<Datum> getAllData(Context context) {
        ServiceLogin serviceLogin = new ServiceLogin(context);
        List<Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(serviceLogin.getprlevel().equals("1")){
            cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_1 = '"+serviceLogin.getLoginId()+"' " +
                    " and cek_approval_1 = '1' ORDER BY Tgl_PPB DESC", null);
        } else if (serviceLogin.getprlevel().equals("2")) {
            cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_2 is null and cek_approval_1 = '1' ORDER BY Tgl_PPB DESC", null);
        } else if (serviceLogin.getprlevel().equals("3")) {
            cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_2 is null and cek_approval_1 = '1' ORDER BY Tgl_PPB DESC", null);
        } else if (serviceLogin.getprlevel().equals("4")) {
            cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_2 is null and cek_approval_1 = '1' ORDER BY Tgl_PPB DESC", null);
        }else{
            cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_3 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_3 is null and cek_approval_2 = '1' ORDER BY Tgl_PPB DESC", null);
        }

        if (cursor.moveToFirst()) {
            do {
                String UCode_PPB = cursor.getString(cursor.getColumnIndexOrThrow("UCode_PPB"));
                String No_PPB = cursor.getString(cursor.getColumnIndexOrThrow("No_PPB"));
                String Ket = cursor.getString(cursor.getColumnIndexOrThrow("Ket"));
                String Tgl_PPB = cursor.getString(cursor.getColumnIndexOrThrow("Tgl_PPB"));
                String nama_karyawan = cursor.getString(cursor.getColumnIndexOrThrow("nama_karyawan"));
                String foto = cursor.getString(cursor.getColumnIndexOrThrow("foto"));
                String Nama_Dept = cursor.getString(cursor.getColumnIndexOrThrow("Nama_Dept"));
                String remarks = cursor.getString(cursor.getColumnIndexOrThrow("remarks"));

                String approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("approval_1"));
                String approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("approval_2"));
                String approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("approval_3"));
                String approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("approval_4"));

                String no_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_1"));
                String no_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_2"));
                String no_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_3"));
                String no_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_4"));

                String cek_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_1"));
                String cek_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_2"));
                String cek_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_3"));
                String cek_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_4"));

                Datum data = new Datum(UCode_PPB, No_PPB, Tgl_PPB, Ket, foto, Nama_Dept, remarks, nama_karyawan, approval_1, approval_2, approval_3, approval_4, no_approval_1, no_approval_2, no_approval_3, no_approval_4, cek_approval_1, cek_approval_2, cek_approval_3, cek_approval_4);
                dataList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }

    public List<Datum> getAllDataHistory(Context context, String type) {
        ServiceLogin serviceLogin = new ServiceLogin(context);
        List<Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(serviceLogin.getprlevel().equals("1")){
            if(type.equals("pending")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_1 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_1 = '1' ORDER BY no_approval_1 DESC", null);
            }else if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_1 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_1 = '2' ORDER BY no_approval_1 DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_1 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_1 = '1' ORDER BY no_approval_1 DESC", null);
            }
        } else if (serviceLogin.getprlevel().equals("2")) {

            if(type.equals("pending")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '1' ORDER BY no_approval_2 DESC", null);
            }else if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '2' ORDER BY no_approval_2 DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '1' ORDER BY no_approval_2 DESC", null);
            }

        } else if (serviceLogin.getprlevel().equals("3")) {

            if(type.equals("pending")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '1' ORDER BY no_approval_2 DESC", null);
            }else if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '2' ORDER BY no_approval_2 DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '1' ORDER BY no_approval_2 DESC", null);
            }

        } else if (serviceLogin.getprlevel().equals("4")) {
            if(type.equals("pending")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '1' ORDER BY no_approval_2 DESC", null);
            }else if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '2' ORDER BY no_approval_2 DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_2 = '1' ORDER BY no_approval_2 DESC", null);
            }
        }else{

            if(type.equals("pending")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_3 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_3 = '1' ORDER BY no_approval_3 DESC", null);
            }else if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_3 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_3 = '2' ORDER BY no_approval_3 DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_PPB where approval_3 = '"+serviceLogin.getLoginId()+"' " +
                        " and cek_approval_3 = '1' ORDER BY no_approval_3 DESC", null);
            }

        }


        if (cursor.moveToFirst()) {
            do {
                String UCode_PPB = cursor.getString(cursor.getColumnIndexOrThrow("UCode_PPB"));
                String No_PPB = cursor.getString(cursor.getColumnIndexOrThrow("No_PPB"));
                String Ket = cursor.getString(cursor.getColumnIndexOrThrow("Ket"));
                String Tgl_PPB = cursor.getString(cursor.getColumnIndexOrThrow("Tgl_PPB"));
                String nama_karyawan = cursor.getString(cursor.getColumnIndexOrThrow("nama_karyawan"));
                String foto = cursor.getString(cursor.getColumnIndexOrThrow("foto"));
                String Nama_Dept = cursor.getString(cursor.getColumnIndexOrThrow("Nama_Dept"));
                String remarks = cursor.getString(cursor.getColumnIndexOrThrow("remarks"));

                String approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("approval_1"));
                String approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("approval_2"));
                String approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("approval_3"));
                String approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("approval_4"));

                String no_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_1"));
                String no_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_2"));
                String no_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_3"));
                String no_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_4"));

                String cek_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_1"));
                String cek_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_2"));
                String cek_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_3"));
                String cek_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_4"));

                Datum data = new Datum(UCode_PPB, No_PPB, Tgl_PPB, Ket, foto, Nama_Dept, remarks, nama_karyawan, approval_1, approval_2, approval_3, approval_4, no_approval_1, no_approval_2, no_approval_3, no_approval_4, cek_approval_1, cek_approval_2, cek_approval_3, cek_approval_4);
                dataList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }

    public List<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum> getAllDataSPB(Context context) {
        ServiceLogin serviceLogin = new ServiceLogin(context);
        List<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if(serviceLogin.getpolevel().equals("1")){
            cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where nama_karyawan = '"+serviceLogin.getLoginName()+"' " +
                    " ORDER BY Tgl_SPB DESC", null);
        } else if (serviceLogin.getpolevel().equals("2")) {
            cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_1 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_1 is null  ORDER BY Tgl_SPB DESC", null);
        } else if (serviceLogin.getpolevel().equals("3")) {
            cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_2 is null and cek_approval_1 = '1' ORDER BY Tgl_SPB DESC", null);
        } else if (serviceLogin.getpolevel().equals("4")) {
            cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_3 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_3 is null and cek_approval_2 = '1' ORDER BY Tgl_SPB DESC", null);
        }else{
            cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_4 = '"+serviceLogin.getLoginId()+"' " +
                    " and no_approval_4 is null and cek_approval_3 = '1' ORDER BY Tgl_SPB DESC", null);
        }

        //Cursor cursor = db.rawQuery("SELECT * FROM tb_mt_SPB  ORDER BY Tgl_SPB DESC", null);

        if (cursor.moveToFirst()) {
            do {
                String UCode_SPB = cursor.getString(cursor.getColumnIndexOrThrow("UCode_SPB"));
                String No_SPB = cursor.getString(cursor.getColumnIndexOrThrow("No_SPB"));
                String Ket = cursor.getString(cursor.getColumnIndexOrThrow("Ket"));
                String Tgl_SPB = cursor.getString(cursor.getColumnIndexOrThrow("Tgl_SPB"));
                String nama_karyawan = cursor.getString(cursor.getColumnIndexOrThrow("nama_karyawan"));
                String foto = cursor.getString(cursor.getColumnIndexOrThrow("foto"));
                String nama_supp = cursor.getString(cursor.getColumnIndexOrThrow("nama_supp"));

                String approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("approval_1"));
                String approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("approval_2"));
                String approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("approval_3"));
                String approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("approval_4"));

                String no_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_1"));
                String no_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_2"));
                String no_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_3"));
                String no_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_4"));

                String cek_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_1"));
                String cek_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_2"));
                String cek_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_3"));
                String cek_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_4"));
                String jenis = cursor.getString(cursor.getColumnIndexOrThrow("jenis"));
                String lampiran = cursor.getString(cursor.getColumnIndexOrThrow("lampiran"));


                com.tpsmedia.mysoejasch.model.Purchaseorder.Datum data = new com.tpsmedia.mysoejasch.model.Purchaseorder.Datum(UCode_SPB, No_SPB, Ket, Tgl_SPB, nama_karyawan, foto, nama_supp, approval_1, approval_2, approval_3, approval_4, no_approval_1, no_approval_2, no_approval_3, no_approval_4, cek_approval_1, cek_approval_2, cek_approval_3, cek_approval_4, jenis, lampiran);
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }

    public List<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum> getAllDataSPBHistory(Context context, String type) {
        ServiceLogin serviceLogin = new ServiceLogin(context);
        List<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(serviceLogin.getpolevel().equals("1")){
            if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where nama_karyawan = '"+serviceLogin.getLoginName()+"' " +
                        " ORDER BY Tgl_SPB DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where nama_karyawan = '"+serviceLogin.getLoginName()+"' " +
                        " ORDER BY Tgl_SPB DESC", null);
            }

        } else if (serviceLogin.getpolevel().equals("2")) {
            if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_1 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_1 is not null and cek_approval_1 = '2'  ORDER BY Tgl_SPB DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_1 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_1 is not null and cek_approval_1 = '1'  ORDER BY Tgl_SPB DESC", null);
            }
        } else if (serviceLogin.getpolevel().equals("3")) {
            if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_2 is not null and cek_approval_1 = '1' and cek_approval_2 = '2' ORDER BY Tgl_SPB DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_2 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_2 is not null and cek_approval_1 = '1' and cek_approval_2 = '1' ORDER BY Tgl_SPB DESC", null);
            }

        } else if (serviceLogin.getpolevel().equals("4")) {
            if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_3 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_3 is not null and cek_approval_2 = '1' and cek_approval_3 = '2' ORDER BY Tgl_SPB DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_3 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_3 is not null and cek_approval_2 = '1' and cek_approval_3 = '1' ORDER BY Tgl_SPB DESC", null);
            }

        }else{

            if (type.equals("cancel")){
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_4 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_4 is not null and cek_approval_3 = '1' and cek_approval_4 = '2' ORDER BY Tgl_SPB DESC", null);
            }else{
                cursor = db.rawQuery("SELECT * FROM tb_mt_SPB where approval_4 = '"+serviceLogin.getLoginId()+"' " +
                        " and no_approval_4 is not null and cek_approval_3 = '1' and cek_approval_4 = '1' ORDER BY Tgl_SPB DESC", null);
            }


        }

        if (cursor.moveToFirst()) {
            do {
                String UCode_SPB = cursor.getString(cursor.getColumnIndexOrThrow("UCode_SPB"));
                String No_SPB = cursor.getString(cursor.getColumnIndexOrThrow("No_SPB"));
                String Ket = cursor.getString(cursor.getColumnIndexOrThrow("Ket"));
                String Tgl_SPB = cursor.getString(cursor.getColumnIndexOrThrow("Tgl_SPB"));
                String nama_karyawan = cursor.getString(cursor.getColumnIndexOrThrow("nama_karyawan"));
                String foto = cursor.getString(cursor.getColumnIndexOrThrow("foto"));
                String nama_supp = cursor.getString(cursor.getColumnIndexOrThrow("nama_supp"));

                String approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("approval_1"));
                String approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("approval_2"));
                String approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("approval_3"));
                String approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("approval_4"));

                String no_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_1"));
                String no_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_2"));
                String no_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_3"));
                String no_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("no_approval_4"));

                String cek_approval_1 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_1"));
                String cek_approval_2 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_2"));
                String cek_approval_3 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_3"));
                String cek_approval_4 = cursor.getString(cursor.getColumnIndexOrThrow("cek_approval_4"));

                String jenis = cursor.getString(cursor.getColumnIndexOrThrow("jenis"));
                String lampiran = cursor.getString(cursor.getColumnIndexOrThrow("lampiran"));

                com.tpsmedia.mysoejasch.model.Purchaseorder.Datum data = new com.tpsmedia.mysoejasch.model.Purchaseorder.Datum(UCode_SPB, No_SPB, Ket, Tgl_SPB, nama_karyawan, foto, nama_supp, approval_1, approval_2, approval_3, approval_4, no_approval_1, no_approval_2, no_approval_3, no_approval_4, cek_approval_1, cek_approval_2, cek_approval_3, cek_approval_4, jenis, lampiran);

                dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }


    public List<com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum> getAllDetailPPB() {
        List<com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_dt_PPB_brg ORDER BY No_Urut DESC", null);

        if (cursor.moveToFirst()) {
            do {
                //UCode_PPB, UCode_Brg, Kode_Brg, Nama_Brg, Qty, Qty_Std, Nama_Sat, Nama_Sat_Std, Ket, Approval_cek

                String UCode_PPB = cursor.getString(cursor.getColumnIndexOrThrow("UCode_PPB"));
                String No_Urut = cursor.getString(cursor.getColumnIndexOrThrow("No_Urut"));
                String UCode_Brg = cursor.getString(cursor.getColumnIndexOrThrow("UCode_Brg"));
                String Kode_Brg = cursor.getString(cursor.getColumnIndexOrThrow("Kode_Brg"));
                String Nama_Brg = cursor.getString(cursor.getColumnIndexOrThrow("Nama_Brg"));
                String Qty = cursor.getString(cursor.getColumnIndexOrThrow("Qty"));
                String Qty_Std = cursor.getString(cursor.getColumnIndexOrThrow("Qty_Std"));
                String Nama_Sat = cursor.getString(cursor.getColumnIndexOrThrow("Satuan"));
                String Nama_Sat_Std = cursor.getString(cursor.getColumnIndexOrThrow("Satuan_Std"));
                String Ket = cursor.getString(cursor.getColumnIndexOrThrow("Ket"));
                String Approval_cek = cursor.getString(cursor.getColumnIndexOrThrow("Approval_cek"));

                com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum data = new com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Datum(UCode_PPB, No_Urut, UCode_Brg, Kode_Brg, Nama_Brg, Qty, Qty_Std, Nama_Sat, Nama_Sat_Std, Ket, Approval_cek);
                dataList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }


    public List<com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Datum> getAllDetailSPB() {
        List<com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_dt_SPB_brg ORDER BY No_Urut DESC", null);

        if (cursor.moveToFirst()) {
            do {

                String UCode_SPB = cursor.getString(cursor.getColumnIndexOrThrow("UCode_SPB"));
                String No_Urut = cursor.getString(cursor.getColumnIndexOrThrow("No_Urut"));
                String UCode_Brg = cursor.getString(cursor.getColumnIndexOrThrow("UCode_Brg"));
                String Kode_Brg = cursor.getString(cursor.getColumnIndexOrThrow("Kode_Brg"));
                String Nama_Brg = cursor.getString(cursor.getColumnIndexOrThrow("Nama_Brg"));
                String Qty = cursor.getString(cursor.getColumnIndexOrThrow("Qty"));
                String Qty_Std = cursor.getString(cursor.getColumnIndexOrThrow("Qty_Std"));
                String Nama_Sat = cursor.getString(cursor.getColumnIndexOrThrow("Satuan"));
                String Nama_Sat_Std = cursor.getString(cursor.getColumnIndexOrThrow("Satuan_Std"));
                String Ket = cursor.getString(cursor.getColumnIndexOrThrow("Ket"));
                String Approval_cek = cursor.getString(cursor.getColumnIndexOrThrow("Approval_cek"));
                String Harga = cursor.getString(cursor.getColumnIndexOrThrow("Harga"));
                String Discount = cursor.getString(cursor.getColumnIndexOrThrow("Discount"));
                String Sub_Total = cursor.getString(cursor.getColumnIndexOrThrow("Sub_Total"));
                String Pajak = cursor.getString(cursor.getColumnIndexOrThrow("Pajak"));

                com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Datum data = new com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Datum(UCode_SPB, No_Urut, UCode_Brg, Kode_Brg, Nama_Brg, Qty, Qty_Std, Nama_Sat, Nama_Sat_Std, Ket, Approval_cek, Harga, Discount, Sub_Total, Pajak);
                dataList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }


    public List<com.tpsmedia.mysoejasch.model.Employee.Datum> getAllEmployee(Context context) {
        List<com.tpsmedia.mysoejasch.model.Employee.Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_mt_karyawan ORDER BY nama_karyawan ASC", null);

        if (cursor.moveToFirst()) {
            do {
                String ucode_karyawan = cursor.getString(cursor.getColumnIndexOrThrow("ucode_karyawan"));
                String nama_karyawan = cursor.getString(cursor.getColumnIndexOrThrow("nama_karyawan"));
                String jabatan = cursor.getString(cursor.getColumnIndexOrThrow("jabatan"));
                String foto = cursor.getString(cursor.getColumnIndexOrThrow("foto"));

                com.tpsmedia.mysoejasch.model.Employee.Datum data = new com.tpsmedia.mysoejasch.model.Employee.Datum(ucode_karyawan, nama_karyawan, jabatan, foto);
                dataList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }

    public List<com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum> getAllPallet(Context context) {
        List<com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_mt_pallet ORDER BY UCode_Pallet DESC", null);

        if (cursor.moveToFirst()) {
            do {
                String UCode_Pallet = cursor.getString(cursor.getColumnIndexOrThrow("UCode_Pallet"));
                String Kode_Pallet = cursor.getString(cursor.getColumnIndexOrThrow("Kode_Pallet"));
                String Nama_Pallet = cursor.getString(cursor.getColumnIndexOrThrow("Nama_Pallet"));
                String Lokasi = cursor.getString(cursor.getColumnIndexOrThrow("Lokasi"));
                String Divisi = cursor.getString(cursor.getColumnIndexOrThrow("Divisi"));
                String Pembuat = cursor.getString(cursor.getColumnIndexOrThrow("Pembuat"));
                String Stat = cursor.getString(cursor.getColumnIndexOrThrow("Stat"));
                String Ket = cursor.getString(cursor.getColumnIndexOrThrow("Ket"));
                String Capacity = cursor.getString(cursor.getColumnIndexOrThrow("Capacity"));
                String TypeCapacity = cursor.getString(cursor.getColumnIndexOrThrow("TypeCapacity"));
                String Tgl_Jam_Buat = cursor.getString(cursor.getColumnIndexOrThrow("Tgl_Jam_Buat"));

                com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum data = new com.tpsmedia.mysoejasch.model.Warehouse.Pallet.Datum(UCode_Pallet, Kode_Pallet, Nama_Pallet, Lokasi, Divisi, Pembuat, Stat, Ket, Capacity, TypeCapacity, Tgl_Jam_Buat, 0, "0");
                dataList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        
        return dataList;
    }

    @SuppressLint("Range")
    public String getNameEmployee(Context context, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = "";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT nama_karyawan FROM tb_mt_karyawan WHERE ucode_karyawan = ?", new String[]{id});
            if (cursor != null && cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex("nama_karyawan"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            
        }
        return name;
    }

    public Boolean setSPBCheckbox(Context context, String UCode_SPB, String UCode_Brg) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT OR REPLACE INTO tb_cek_SPB (UCode_SPB, UCode_Brg) VALUES (?, ?)",
                new Object[]{UCode_SPB, UCode_Brg});
        return true;
    }

    public Boolean removesetSPBCheckbox(Context context, String UCode_SPB, String UCode_Brg) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE from tb_cek_SPB where UCode_SPB = ? and UCode_Brg = ?",
                new Object[]{UCode_SPB, UCode_Brg});
        return true;
    }

    public Boolean setPPBCheckbox(Context context, String UCode_PPB, String UCode_Brg) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT OR REPLACE INTO tb_cek_PPB (UCode_PPB, UCode_Brg) VALUES (?, ?)",
                new Object[]{UCode_PPB, UCode_Brg});
        return true;
    }

    public Boolean removesetPPBCheckbox(Context context, String UCode_PPB, String UCode_Brg) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE from tb_cek_PPB where UCode_PPB = ? and UCode_Brg = ?",
                new Object[]{UCode_PPB, UCode_Brg});
        return true;
    }

    @SuppressLint("Range")
    public String getWaktuApproval(Context context, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hasil = "";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT tanggal_jam FROM tb_mt_approval WHERE no_approval = ?", new String[]{id});
            if (cursor != null && cursor.moveToFirst()) {
                if(cursor.getString(cursor.getColumnIndex("tanggal_jam")) != null){
                    hasil = cursor.getString(cursor.getColumnIndex("tanggal_jam"));
                }else{
                    hasil = "-";
                }

            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            
        }
        return hasil;
    }


    @SuppressLint("Range")
    public String getSubTotal(Context context, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hasil = "";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT sum(Sub_Total) as Harga FROM tb_dt_SPB_brg WHERE UCode_SPB = ?", new String[]{id});
            if (cursor != null && cursor.moveToFirst()) {
                if(cursor.getString(cursor.getColumnIndex("Harga")) != null){
                    hasil = cursor.getString(cursor.getColumnIndex("Harga"));
                }else{
                    hasil = "0";
                }

            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            
        }
        return hasil;
    }

    @SuppressLint("Range")
    public String getSubPajak(Context context, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hasil = "";
        String cek = "";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT sum(Pajak) as Pajak FROM tb_dt_SPB_brg WHERE UCode_SPB = ?", new String[]{id});
            if (cursor != null && cursor.moveToFirst()) {
                if(cursor.getString(cursor.getColumnIndex("Pajak")) != null){
                    cek = cursor.getString(cursor.getColumnIndex("Pajak"));
                    if(cek.equals(".00")){
                        hasil = "0";
                    }else{
                        hasil = cek;
                    }
                }else{
                    hasil = "0";
                }

            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            
        }
        return hasil;
    }



    @SuppressLint("Range")
    public String getBarangCheckSPB(Context context, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hasilList = "";
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT UCode_Brg FROM tb_cek_SPB WHERE UCode_SPB = ?", new String[]{id});
        if (cursor != null) {
            if (cursor.moveToFirst()) { // Cek jika ada data
                do {
                    String uCodeBrg = cursor.getString(cursor.getColumnIndex("UCode_Brg"));
                    if (!hasilList.isEmpty()) {
                        hasilList += ",";
                    }
                    hasilList += uCodeBrg;
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return hasilList;
    }

    @SuppressLint("Range")
    public String getBarangCheckPPB(Context context, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hasilList = "";
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT UCode_Brg FROM tb_cek_PPB WHERE UCode_PPB = ?", new String[]{id});
        if (cursor != null) {
            if (cursor.moveToFirst()) { // Cek jika ada data
                do {
                    String uCodeBrg = cursor.getString(cursor.getColumnIndex("UCode_Brg"));
                    if (!hasilList.isEmpty()) {
                        hasilList += ",";
                    }
                    hasilList += uCodeBrg;
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return hasilList;
    }



}
