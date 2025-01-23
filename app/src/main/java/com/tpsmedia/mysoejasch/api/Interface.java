package com.tpsmedia.mysoejasch.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.tpsmedia.mysoejasch.model.Auth.Login;
import com.tpsmedia.mysoejasch.model.Auth.Totalnotif;
import com.tpsmedia.mysoejasch.model.Auth.Notifikasi;
import com.tpsmedia.mysoejasch.model.Auth.UbahPassword;

import com.tpsmedia.mysoejasch.model.CTPlanData.Success;
import com.tpsmedia.mysoejasch.model.Divisi.Divisirequest;
import com.tpsmedia.mysoejasch.model.Gudang.Gudangrequest;
import com.tpsmedia.mysoejasch.model.Lokasi.Lokasirequest;
import com.tpsmedia.mysoejasch.model.Notifikasidetail.Notifikasidetail;
import com.tpsmedia.mysoejasch.model.Purchaseorder.Datum;
import com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Purchaseorderdetail;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Purchaserequest;
import com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Purchaserequestdetail;
import com.tpsmedia.mysoejasch.model.Purchaseorder.Purchaseorder;
import com.tpsmedia.mysoejasch.model.GetReponseSuccess;
import com.tpsmedia.mysoejasch.model.Warehouse.CTPlan;
import com.tpsmedia.mysoejasch.model.Warehouse.DetailMutasiList;
import com.tpsmedia.mysoejasch.model.Warehouse.DetailSJTBList;
import com.tpsmedia.mysoejasch.model.Warehouse.DetailSPCList;
import com.tpsmedia.mysoejasch.model.Warehouse.DetailStockinList;
import com.tpsmedia.mysoejasch.model.Warehouse.DetailStockoutList;
import com.tpsmedia.mysoejasch.model.Warehouse.DetailTTTBList;
import com.tpsmedia.mysoejasch.model.Warehouse.DetaillkList;
import com.tpsmedia.mysoejasch.model.Warehouse.Detaillkrequest;
import com.tpsmedia.mysoejasch.model.Warehouse.Getbarang;
import com.tpsmedia.mysoejasch.model.Warehouse.MutasiList;
import com.tpsmedia.mysoejasch.model.Warehouse.Nolkrequest;
import com.tpsmedia.mysoejasch.model.Warehouse.OpnameList;
import com.tpsmedia.mysoejasch.model.Warehouse.ResponData;
import com.tpsmedia.mysoejasch.model.Warehouse.ResponeKode;
import com.tpsmedia.mysoejasch.model.Warehouse.SJTBList;
import com.tpsmedia.mysoejasch.model.Warehouse.SPCList;
import com.tpsmedia.mysoejasch.model.Warehouse.StockOpnameList;
import com.tpsmedia.mysoejasch.model.Warehouse.StockinList;
import com.tpsmedia.mysoejasch.model.Warehouse.StockoutList;
import com.tpsmedia.mysoejasch.model.Warehouse.TTTBList;
import com.tpsmedia.mysoejasch.model.Warehouse.BarangList;
import com.tpsmedia.mysoejasch.requestform.RequestLocation;

public interface Interface {
    @FormUrlEncoded
    @POST("login")
    Call<Login> postLogin(@Field("username") String username,
                          @Field("password") String password,
                          @Query("tokenfb") String token);

    @POST("ubahpassword")
    Call<UbahPassword> postUbahPassword(@Query("username") String username,
                                 @Query("last_password") String last_password,
                                 @Query("new_password") String new_password,
                                        @Query("retype_password") String retype_password);

    @POST("totalnotif")
    Call<Totalnotif> postTotalNotification(@Header("Authorization") String Authorization,
                                           @Query("prlevel") String prlevel,
                                           @Query("polevel") String polevel,
                                           @Query("ucode_karyawan") String ucode_karyawan);
    @POST("notifikasi")
    Call<Notifikasidetail> postNotifikasi(@Header("Authorization") String Authorization,
                                          @Query("ucode_karyawan") String ucode_karyawan);


    @POST("mpurchaserequest")
    Call<Purchaserequest> postPurchaserequest(@Header("Authorization") String Authorization, @Query("prlevel") String prlevel, @Query("ucodeuser") String ucodeuser, @Query("pencarian") String pencarian, @Query("start_date") String start_date, @Query("end_date") String end_date, @Query("type_pr") String type_pr, @Query("type_tampil") String type_tampil);



    @POST("mpurchaseorder")
    Call<Purchaseorder> postPurchaseorder(@Header("Authorization") String Authorization, @Query("polevel") String polevel, @Query("ucodeuser") String ucodeuser, @Query("pencarian") String pencarian, @Query("start_date") String start_date, @Query("end_date") String end_date, @Query("type_po") String type_po, @Query("type_tampil") String type_tampil);

    @POST("mpurchaseorderget")
    Call<com.tpsmedia.mysoejasch.model.Purchaseorder.Datum> postPurchaseorderGet(@Header("Authorization") String Authorization, @Query("ucodeuser") String ucodeuser, @Query("id") String id, @Query("polevel") String polevel );


    @POST("mpurchaserequestget")
    Call<com.tpsmedia.mysoejasch.model.Purchaserequest.Datum> postPurchaserequestGet(@Header("Authorization") String Authorization, @Query("ucodeuser") String ucodeuser, @Query("id") String id, @Query("prlevel") String prlevel );


    @POST("mpurchaserequestdetail/{id}")
    Call<Purchaserequestdetail> postPurchaserequestdetail(@Header("Authorization") String Authorization, @Path(value = "id", encoded = true) String id, @Query("status") String status);


    @POST("mpurchaseorderdetail/{id}")
    Call<Purchaseorderdetail> postPurchaseorderdetail(@Header("Authorization") String Authorization, @Path(value = "id", encoded = true) String id, @Query("status") String status, @Query("polevel") String polevel);


    @FormUrlEncoded
    @POST("purchase-request-cek-mobile")
    Call<GetReponseSuccess> postPurchaserequestUpdate(@Header("Authorization") String Authorization,
                                                      @Field("module") String module,
                                                      @Field("approval_tahap") String approval_tahap,
                                                      @Field("ucode_karyawan") String ucode_karyawan,
                                                      @Field("nama_karyawan") String nama_karyawan,
                                                      @Field("status_approval") String status_approval,
                                                      @Field("agen") String agen,
                                                      @Field("ip") String ip,
                                                      @Field("latitude") String latitude,
                                                      @Field("longitude") String longitude,
                                                      @Field("remark") String remark,
                                                      @Field("barang") String barang,
                                                      @Field("id") String id );
    @FormUrlEncoded
    @POST("purchase-order-cek-mobile")
    Call<GetReponseSuccess> postPurchaseorderUpdate(@Header("Authorization") String Authorization,
                                                    @Field("module") String module,
                                                    @Field("approval_tahap") String approval_tahap,
                                                    @Field("ucode_karyawan") String ucode_karyawan,
                                                    @Field("nama_karyawan") String nama_karyawan,
                                                    @Field("status_approval") String status_approval,
                                                    @Field("agen") String agen,
                                                    @Field("ip") String ip,
                                                    @Field("latitude") String latitude,
                                                    @Field("longitude") String longitude,
                                                    @Field("remark") String remark,
                                                    @Field("barang") String barang,
                                                    @Field("id") String id );


    @POST("purchase-request-cek-mobile-reject")
    Call<GetReponseSuccess> postPurchaserequestReject(@Header("Authorization") String Authorization,
                                                      @Query("prlevel") String prlevel,
                                                      @Query("module") String module,
                                                      @Query("id") String id);

    @POST("purchase-order-cek-mobile-reject")
    Call<GetReponseSuccess> postPurchaseorderReject(@Header("Authorization") String Authorization,
                                                      @Query("polevel") String polevel,
                                                      @Query("module") String module,
                                                      @Query("id") String id);



//    WAREHOUSE

    @POST("mgudang")
    Call<Gudangrequest> postGudangrequest(@Header("Authorization") String Authorization, @Query("q") String q, @Query("ucodeuser") String ucodeuser);

    @POST("mdivarr")
    Call<Divisirequest> postDivisiArr(@Header("Authorization") String Authorization, @Query("ucodeuser") String ucodeuser);

    @POST("mgudangarr")
    Call<Gudangrequest> postGudangArr(@Header("Authorization") String Authorization, @Query("ucodeuser") String ucodeuser);

    @POST("mlokasiarr")
    Call<Lokasirequest> postLokasiArr(@Header("Authorization") String Authorization, @Query("ucodeuser") String ucodeuser, @Query("ucode_gdg") String ucode_gdg );

    @POST("mlokasiarr")
    Call<Lokasirequest> postLokasiArr2(@Header("Authorization") String Authorization, @Query("ucodeuser") String ucodeuser, @Query("nama_gdg") String nama_gdg );

    @FormUrlEncoded
    @POST("mdetailct")
    Call<Success> postDetailCT(@Header("Authorization") String Authorization, @Field("ucode_ct") String ucode_ct);

    @FormUrlEncoded
    @POST("mdetailctcek")
    Call<Success> cekDataTerpenuhi(@Header("Authorization") String Authorization, @Field("ucode_ct") String ucode_ct);

    @FormUrlEncoded
    @POST("mcekstokacts")
    Call<Success> cekDataStokACTS(@Header("Authorization") String Authorization, @Field("ucode_ct") String ucode_ct);


    @FormUrlEncoded
    @POST("msubmitSJTB")
    Call<GetReponseSuccess> postSubmitSJTB(@Header("Authorization") String Authorization, @Field("ucode_ct") String ucode_ct);


    @POST("mgudang")
    Call<Gudangrequest> postGudangAllrequest(@Header("Authorization") String Authorization, @Query("q") String q, @Query("ucodeuser") String ucodeuser, @Query("gudang") String gudang);

    @POST("mlokasi")
    Call<Lokasirequest> postLokasirequest(@Header("Authorization") String Authorization, @Query("gudang") String gudang);

    @POST("opnamelist")
    Call<OpnameList> postOpnameList(@Header("Authorization") String Authorization, @Query("gudang") String gudang, @Query("tg_awal") String tg_awal, @Query("tg_akhir") String tg_akhir );

    @POST("mutasilist")
    Call<StockoutList> postMutasiList(@Header("Authorization") String Authorization, @Query("gudangasal") String gudangasal, @Query("lokasiasal") String lokasiasal, @Query("gudangtujuan") String gudangtujuan, @Query("lokasitujuan") String lokasitujuan, @Query("start_date") String start_date, @Query("end_date") String end_date );

    @POST("baranglist")
    Call<BarangList> postBarangList(@Header("Authorization") String Authorization, @Query("gudang") String gudang, @Query("lokasi") String lokasi, @Query("pencarian") String pencarian  );


    @POST("stockoutlist")
    Call<StockoutList> postStockoutList(@Header("Authorization") String Authorization, @Query("gudang") String gudang,  @Query("start_date") String start_date, @Query("end_date") String end_date  );


    @POST("stockinlist")
    Call<StockinList> postStockinList(@Header("Authorization") String Authorization, @Query("gudang") String gudang, @Query("start_date") String start_date, @Query("end_date") String end_date  );

    @POST("stockopnamelist")
    Call<StockOpnameList> postStockOpnameList(@Header("Authorization") String Authorization, @Query("gudang") String gudang, @Query("lokasi") String lokasi, @Query("bulan") String bulan, @Query("tahun") String tahun  );


    @POST("detailstockin")
    Call<DetailStockinList> postDetailstockinList(@Header("Authorization") String Authorization, @Query("ucode_stock_in") String ucode_stock_in  );


    @POST("detailstockout")
    Call<DetailStockoutList> postDetailstockoutList(@Header("Authorization") String Authorization, @Query("ucode_stock_out") String ucode_stock_out  );


    @POST("simpanstockin")
    Call<ResponeKode> postStockInsave(@Header("Authorization") String Authorization,
                                      @Query("ucode_gdg_asal") String ucode_gdg_asal,
                                      @Query("ucode_lokasi_asal") String ucode_lokasi_asal,
                                      @Query("ucode_gdg_tujuan") String ucode_gdg_tujuan,
                                      @Query("ucode_lokasi_tujuan") String ucode_lokasi_tujuan,
                                      @Query("ucode_user") String ucode_user,
                                      @Query("no_referensi") String no_referensi,
                                      @Query("keterangan") String keterangan,
                                      @Query("tanggal") String tanggal,
                                      @Query("jenis") String jenis);

    @POST("simpanstockout")
    Call<ResponeKode> postStockOutsave(@Header("Authorization") String Authorization,
                                       @Query("ucode_gdg_asal") String ucode_gdg_asal,
                                       @Query("ucode_lokasi_asal") String ucode_lokasi_asal,
                                       @Query("ucode_gdg_tujuan") String ucode_gdg_tujuan,
                                       @Query("ucode_lokasi_tujuan") String ucode_lokasi_tujuan,
                                       @Query("ucode_user") String ucode_user,
                                       @Query("no_referensi") String no_referensi,
                                       @Query("keterangan") String keterangan,
                                       @Query("tanggal") String tanggal,
                                       @Query("jenis") String jenis);


    @POST("spclist")
    Call<SPCList> postSPCList(@Header("Authorization") String Authorization, @Query("gudangasal") String gudangasal, @Query("gudangtujuan") String gudangtujuan, @Query("tg_awal") String tg_awal, @Query("tg_akhir") String tg_akhir );

    @POST("sjtblist")
    Call<SJTBList> postSJTBList(@Header("Authorization") String Authorization, @Query("gudangasal") String gudangasal, @Query("gudangtujuan") String gudangtujuan, @Query("tg_awal") String tg_awal, @Query("tg_akhir") String tg_akhir );

    @POST("tttblist")
    Call<TTTBList> postTTTBList(@Header("Authorization") String Authorization, @Query("gudangasal") String gudangasal, @Query("gudangtujuan") String gudangtujuan, @Query("tg_awal") String tg_awal, @Query("tg_akhir") String tg_akhir );

    @POST("nomorlk")
    Call<Nolkrequest> postLkrequest(@Header("Authorization") String Authorization, @Query("ucodeuser") String ucodeuser);


    @POST("detaillk")
    Call<Detaillkrequest> postDetaillkrequest(@Header("Authorization") String Authorization, @Query("kode") String kode);


    @POST("mgetbarang")
    Call<Getbarang> postGetbarangrequest(@Header("Authorization") String Authorization, @Query("kode") String kode, @Query("lk") String lk);

    @POST("mgetbarangstock")
    Call<Getbarang> postGetbarangstockrequest(@Header("Authorization") String Authorization, @Query("kode") String kode, @Query("ucode") String ucode, @Query("tipe") String tipe);



    @POST("simpanlk")
    Call<GetReponseSuccess> postLksave(@Header("Authorization") String Authorization,
                                                      @Query("No_LK") String No_LK,
                                                      @Query("Tgl_LK") String Tgl_LK,
                                                      @Query("Tgl_Periode") String Tgl_Periode,
                                                      @Query("UCode_Div") String UCode_Div,
                                                      @Query("UCode_Gdg") String UCode_Gdg,
                                                      @Query("Pic") String Pic,
                                                      @Query("Stat_Dok") String Stat_Dok,
                                                      @Query("Ket") String Ket,
                                                      @Query("UCode_User") String UCode_User,
                                                      @Query("UCode_Lokasi") String UCode_Lokasi);




    @POST("simpandetaillk")
    Call<GetReponseSuccess> postSimpanlkbarangrequest(@Header("Authorization") String Authorization,
                                       @Query("UCode_LK") String UCode_LK,
                                       @Query("UCode_Brg") String UCode_Brg,
                                       @Query("UCode_Sat") String UCode_Sat,
                                       @Query("UCode_Sat_Std") String UCode_Sat_Std,
                                       @Query("UCode_Lok") String UCode_Lok,
                                       @Query("Qty") String Qty,
                                       @Query("Qty_Std") String Qty_Std,
                                       @Query("Tgl_Expired") String Tgl_Expired,
                                       @Query("Batch_No") String Batch_No,
                                                      @Query("mode") String mode);


    @POST("simpandetailstockin")
    Call<GetReponseSuccess> postSimpandetailstockinrequest(@Header("Authorization") String Authorization,
                                                      @Query("ucode_stock_in") String ucode_stock_in,
                                                           @Query("UCode_Brg") String UCode_Brg,
                                                           @Query("UCode_Sat") String UCode_Sat,
                                                           @Query("UCode_Sat_Std") String UCode_Sat_Std,
                                                           @Query("UCode_Lok") String UCode_Lok,
                                                           @Query("Qty") String Qty,
                                                           @Query("Qty_Std") String Qty_Std,
                                                           @Query("Tgl_Expired") String Tgl_Expired,
                                                           @Query("Batch_No") String Batch_No,
                                                           @Query("user") String user,
                                                           @Query("mode") String mode);

    @POST("simpandetailstockout")
    Call<ResponData> postSimpandetailstockoutrequest(@Header("Authorization") String Authorization,
                                                     @Query("ucode_stock_out") String ucode_stock_out,
                                                     @Query("UCode_Brg") String UCode_Brg,
                                                     @Query("UCode_Sat") String UCode_Sat,
                                                     @Query("UCode_Sat_Std") String UCode_Sat_Std,
                                                     @Query("UCode_Lok") String UCode_Lok,
                                                     @Query("Qty") String Qty,
                                                     @Query("Qty_Std") String Qty_Std,
                                                     @Query("Tgl_Expired") String Tgl_Expired,
                                                     @Query("Batch_No") String Batch_No,
                                                     @Query("user") String user,
                                                     @Query("mode") String mode,
                                                     @Query("kode_lokasi") String kodelokasi);



    @POST("detaillklist")
    Call<DetaillkList> postDetaillkList(@Header("Authorization") String Authorization, @Query("metode") String metode, @Query("lk") String lk );

    @POST("detailmutasilist")
    Call<DetailMutasiList> postDetailMutasiList(@Header("Authorization") String Authorization, @Query("metode") String metode, @Query("mtb") String mtb );

    @POST("detailspclist")
    Call<DetailSPCList> postDetailSPCList(@Header("Authorization") String Authorization, @Query("metode") String metode, @Query("spc") String spc );

    @POST("detailsjtblist")
    Call<DetailSJTBList> postDetailSJTBList(@Header("Authorization") String Authorization, @Query("metode") String metode, @Query("sjtb") String sjtb );

    @POST("detailtttblist")
    Call<DetailTTTBList> postDetailTTTBList(@Header("Authorization") String Authorization, @Query("metode") String metode, @Query("tttb") String tttb );

    @POST("hapuslklist")
    Call<GetReponseSuccess> postDetaillkhapus(@Header("Authorization") String Authorization,
                                                      @Query("No_LK") String No_LK,
                                                      @Query("Batch_No") String Batch_No,
                                                      @Query("No_Urut") String No_Urut);


    @POST("hapusstockindetail")
    Call<GetReponseSuccess> postDetailStockinhapus(@Header("Authorization") String Authorization,
                                                   @Query("ucode") String ucode,
                                                   @Query("user") String user);

    @POST("hapusstockoutdetail")
    Call<GetReponseSuccess> postDetailStockouthapus(@Header("Authorization") String Authorization,
                                                   @Query("ucode") String ucode,
                                                   @Query("user") String user);



    @PUT("barang/store")
    Call<GetReponseSuccess> postBarangStore(@Header("Authorization") String Authorization,
                                            @Field("nama_barang") String nama_barang,
                                            @Field("jumlah_barang") String jumlah_barang,
                                            @Field("keterangan") String keterangan);


    @FormUrlEncoded
    @POST("barang/update/{id}")
    Call<GetReponseSuccess> postBarangUpdate(@Header("Authorization") String Authorization,
                                             @Field("nama_barang") String nama_barang,
                                             @Field("jumlah_barang") String jumlah_barang,
                                             @Field("keterangan") String keterangan,
                                             @Path(value = "id", encoded = true) String id);

    @DELETE("barang/delete/{id}")
    Call<GetReponseSuccess> postBarangDelete(@Header("Authorization") String Authorization,
                                             @Path(value = "id", encoded = true) String id);


}
