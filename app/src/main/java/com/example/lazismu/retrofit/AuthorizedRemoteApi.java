package com.example.lazismu.retrofit;

import com.example.lazismu.retrofit.response.DonationListResponse;
import com.example.lazismu.retrofit.response.DonationResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
            @Part("image") RequestBody proofOfPayment
    );

    @GET("datanontunai")
    Call<DonationListResponse> getDonations();
}
