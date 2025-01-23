package com.tpsmedia.mysoejasch.api;

import com.tpsmedia.mysoejasch.model.ApiResponse;
import com.tpsmedia.mysoejasch.model.Approval.ApprovalList;
import com.tpsmedia.mysoejasch.model.Employee.EmployeeList;
import com.tpsmedia.mysoejasch.model.GetReponseSuccess;
import com.tpsmedia.mysoejasch.model.Purchaseorder.Purchaseorder;
import com.tpsmedia.mysoejasch.model.Purchaseorderdetail.Purchaseorderdetail;
import com.tpsmedia.mysoejasch.model.Purchaserequest.Purchaserequest;
import com.tpsmedia.mysoejasch.model.Purchaserequestdetail.Purchaserequestdetail;
import com.tpsmedia.mysoejasch.model.VersionResponse;
import com.tpsmedia.mysoejasch.model.Warehouse.CTPlan;
import com.tpsmedia.mysoejasch.model.Warehouse.KartuStok.KartuStok;
import com.tpsmedia.mysoejasch.model.Warehouse.Location.LocationList;
import com.tpsmedia.mysoejasch.model.Warehouse.Pallet.PalletList;
import com.tpsmedia.mysoejasch.model.Warehouse.Produksi.Produksi;
import com.tpsmedia.mysoejasch.model.Warehouse.StokBarang.StokBarang;
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluar;
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluar.StokKeluarObject;
import com.tpsmedia.mysoejasch.model.Warehouse.StokKeluarDetail.StokKeluarDetail;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasuk;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasuk.StokMasukObject;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMasukDetail.StokMasukDetail;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMutasi.StokMutasi;
import com.tpsmedia.mysoejasch.model.Warehouse.StokMutasiDetail.StokMutasiDetail;
import com.tpsmedia.mysoejasch.requestform.RequestLocation;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Sinkronasi {

    @POST("mobile/mysj-version")
    Call<VersionResponse> postversion(@Header("Authorization") String Authorization );

    @POST("mobile/mysj-pronline")
    Call<Purchaserequest> postpronline(@Header("Authorization") String Authorization );

    @POST("mobile/mysj-poonline")
    Call<Purchaseorder> postpoonline(@Header("Authorization") String Authorization );

    @POST("mobile/mysj-pronline-cepat")
    Call<Purchaserequest> postpronlinecepat(@Header("Authorization") String Authorization );

    @POST("mobile/mysj-poonline-cepat")
    Call<Purchaseorder> postpoonlinecepat(@Header("Authorization") String Authorization );

    @POST("mobile/mysj-poonline-cepat-jasa")
    Call<Purchaseorder> postpoonlinecepatjasa(@Header("Authorization") String Authorization );


    @POST("mobile/mysj-pronline-detail")
    Call<Purchaserequestdetail> postpronlinedetail(@Header("Authorization") String Authorization, @Query("UCode_PPB")  String UCode_PPB );

    @POST("mobile/mysj-ponline-detail")
    Call<Purchaseorderdetail> postpoonlinedetail(@Header("Authorization") String Authorization, @Query("UCode_SPB")  String UCode_SPB );


    @POST("mobile/mysj-employee")
    Call<EmployeeList> postEmployee(@Header("Authorization") String Authorization );

    @POST("mobile/mysj-approval")
    Call<ApprovalList> postApproval(@Header("Authorization") String Authorization, @Query("approval") String approval );

    @POST("mobile/mysj-pallet")
    Call<PalletList> postPallet(@Header("Authorization") String Authorization );

    @FormUrlEncoded
    @POST("mobile/mysj-approve-po")
    Call<GetReponseSuccess> postApproveSPB(@Header("Authorization") String Authorization,
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
    @POST("mobile/mysj-approve-po-jasa")
    Call<GetReponseSuccess> postApproveSPJ(@Header("Authorization") String Authorization,
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
                                           @Field("bod") String bod,
                                           @Field("id") String id );

    @FormUrlEncoded
    @POST("mobile/mysj-approve-pr")
    Call<GetReponseSuccess> postApprovePPB(@Header("Authorization") String Authorization,
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


    @POST("mobile/mysj-poonline-single")
    Call<Purchaseorder> postpoonlinesingle(@Header("Authorization") String Authorization, @Query("id") String id);

    @POST("mobile/mysj-poonline-single-jasa")
    Call<Purchaseorder> postpoonlinesinglejasa(@Header("Authorization") String Authorization, @Query("id") String id);

    @POST("mobile/mysj-pronline-single")
    Call<Purchaserequest> postpronlinesingle(@Header("Authorization") String Authorization, @Query("id") String id);

    @POST("mobile/mysj-wms-produksi")
    Call<Produksi> postwmsproduksi(@Header("Authorization") String Authorization, @Query("tgl_awal") String tgl_awal, @Query("tgl_akhir") String tgl_akhir, @Query("search") String search);

    @POST("warehousev2/kartu-stok-mobile")
    Call<KartuStok> postkartustok(@Header("Authorization") String Authorization, @Query("tgl_awal") String tgl_awal, @Query("tgl_akhir") String tgl_akhir, @Query("search") String search);


    @GET("mobile/master/lokasi")
    Call<LocationList> getLocation(@Header("Authorization") String Authorization, @Query("page") String page, @Query("per_page") String per_page, @Query("search") String search );

    @GET("mobile/master/pallet")
    Call<PalletList> getPallet(@Header("Authorization") String Authorization, @Query("page") String page, @Query("per_page") String per_page, @Query("search") String search );


    @GET("mobile/inbound")
    Call<StokMasuk> getInbound(@Header("Authorization") String Authorization, @Query("page") String page, @Query("per_page") String per_page, @Query("start_date") String start_date, @Query("end_date") String end_date, @Query("search") String search  );

    @GET("mobile/inbound/{id}")
    Call<StokMasuk> getInboundId(@Header("Authorization") String Authorization, @Path(value = "id", encoded = true) String id);


    @GET("mobile/outbound")
    Call<StokKeluar> getOutbound(@Header("Authorization") String Authorization, @Query("page") String page, @Query("per_page") String per_page, @Query("start_date") String start_date, @Query("end_date") String end_date, @Query("search") String search );

    @GET("mobile/outbound/{id}")
    Call<StokKeluar> getOutboundId(@Header("Authorization") String Authorization, @Path(value = "id", encoded = true) String id);

    @GET("warehousev2/stok-berjalan-mobile")
    Call<StokBarang> getStok(@Header("Authorization") String Authorization, @Query("jenis") String jenis, @Query("ucode_lokasi") String ucode_lokasi, @Query("search") String search );

    @GET("mobile/inbound-dt-brg/{id}")
    Call<StokMasukDetail> getDetailInbound(@Header("Authorization") String Authorization, @Path(value = "id", encoded = true) String id, @Query("page") String page, @Query("per_page") String per_page);

    @GET("mobile/outbound-dt-brg/{id}")
    Call<StokKeluarDetail> getDetailOutbound(@Header("Authorization") String Authorization, @Path(value = "id", encoded = true) String id, @Query("page") String page, @Query("per_page") String per_page);

    @GET("mobile/mutasi-dt-brg/{id}")
    Call<StokMutasiDetail> getDetailMutasi(@Header("Authorization") String Authorization, @Path(value = "id", encoded = true) String id, @Query("page") String page, @Query("per_page") String per_page);


    @GET("mobile/mutasi")
    Call<StokMutasi> getMutasi(@Header("Authorization") String Authorization, @Query("page") String page, @Query("per_page") String per_page, @Query("start_date") String start_date, @Query("end_date") String end_date );

    @FormUrlEncoded
    @POST("mslug")
    Call<ApiResponse> getSlug(@Header("Authorization") String Authorization, @Field("jenis") String jenis, @Field("parameter") String parameter );

    @FormUrlEncoded
    @POST("mcekct")
    Call<CTPlan> getSlugCT(@Header("Authorization") String Authorization, @Field("jenis") String jenis, @Field("parameter") String parameter );



    @POST("mobile/master/lokasi")
    Call<GetReponseSuccess> postLokasi(
            @Header("Authorization") String authorization,
            @Body RequestBody requestData
    );

    @POST("mobile/master/pallet")
    Call<GetReponseSuccess> postPallet(
            @Header("Authorization") String authorization,
            @Body RequestBody requestData
    );

    @POST("mobile/inbound-dt-brg/{id}")
    Call<StokMasuk> postInboundDetail(
            @Header("Authorization") String authorization,
            @Path(value = "id", encoded = true) String id,
            @Body RequestBody requestData
    );

    @POST("mobile/outbound-dt-brg/{id}")
    Call<StokKeluar> postOutboundDetail(
            @Header("Authorization") String authorization,
            @Path(value = "id", encoded = true) String id,
            @Body RequestBody requestData
    );

    @POST("mobile/inbound")
    Call<StokMasuk> postInbound(
            @Header("Authorization") String authorization,
            @Body RequestBody requestData
    );

    @POST("mobile/inbound")
    Call<StokMasukObject> postInbound2(
            @Header("Authorization") String authorization,
            @Body RequestBody requestData
    );

    @POST("mobile/outbound")
    Call<StokKeluar> postOutbound(
            @Header("Authorization") String authorization,
            @Body RequestBody requestData
    );

    @POST("mobile/outbound")
    Call<StokKeluarObject> postOutbound2(
            @Header("Authorization") String authorization,
            @Body RequestBody requestData
    );

}
