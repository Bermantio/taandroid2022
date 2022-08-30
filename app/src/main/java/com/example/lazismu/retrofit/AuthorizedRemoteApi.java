package com.example.lazismu.retrofit;

import com.example.lazismu.retrofit.response.DonationListResponse;
import com.example.lazismu.retrofit.response.DonationResponse;
import com.example.lazismu.retrofit.response.ProfileUpdateResponse;
import com.example.lazismu.retrofit.response.ReportListResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AuthorizedRemoteApi {
    @Multipart
    @POST("datanontunai")
    Call<DonationResponse> postDonation(
            @Part("tanggal_transaksi") String dateOfTransaction,
            @Part("name_zakki") String name,
            @Part("alamat") String address,
            @Part("notelepon") String phoneNumber,
            @Part("profesi") String profession,
            @Part("name_program") String programName,
            @Part("keterangan") String description,
            @Part("berupa") String donationForm,
            @Part("jumlah_transaksi") String transactionAmount,
            @Part MultipartBody.Part image
    );

    @GET("datanontunai")
    Call<DonationListResponse> getDonations();

    @GET("datapenyaluran")
    Call<ReportListResponse> getReports();

    @Multipart
    @POST("profil/{id}")
    Call<ProfileUpdateResponse> updateProfile(
            @Path("id") int id,
            @Part("name") String name,
            @Part("jenis_kelamin") String gender,
            @Part("alamat") String address,
            @Part("notelepon") String phoneNumber,
            @Part("Profesi") String profession,
            @Part MultipartBody.Part image
    );
}
