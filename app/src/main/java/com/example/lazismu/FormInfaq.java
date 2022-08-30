package com.example.lazismu;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lazismu.retrofit.RetrofitService;
import com.example.lazismu.retrofit.response.DonationResponse;
import com.example.lazismu.retrofit.response.User;
import com.example.lazismu.sharedpreference.SharedPreferenceHelper;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import kotlin.Unit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormInfaq extends AppCompatActivity {

    private EditText txtnominal;
    private String namalengkap, alamat, profesi, telepon;
    ImageView batalarah;
    Button confirminfaq, batalbutton;
    Spinner pilihdonasisebagai;
    ArrayAdapter<CharSequence> adapter;
    TextView txttanggaltransaksi, txtnamalengkap, txtalamat, txttelepon, txtprofesi, txtprogram, txtberupa, txtbuktiinfaq;

    private Uri uri;

    ActivityResultLauncher<Intent> pickPicture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                int resultCode = result.getResultCode();
                Intent data = result.getData();
                if (resultCode == Activity.RESULT_OK) {
                    Uri fileUri = data.getData();
                    uri = fileUri;
                    txtbuktiinfaq.setText("Bukti Transaksi Terpilih");
                } else if (resultCode == ImagePicker.RESULT_ERROR) {
                    Toast.makeText(this, "Terjadi galat", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_infaq);

        txtnamalengkap = (TextView) findViewById(R.id.txtnamalengkap);
        txtalamat = (TextView) findViewById(R.id.txtalamat);
        txttelepon = (TextView) findViewById(R.id.txttelepon);
        txtprofesi = (TextView) findViewById(R.id.txtprofesi);
        txtprogram = (TextView) findViewById(R.id.txtprogram);
        txtberupa = (TextView) findViewById(R.id.txtberupa);
        txtnominal = (EditText) findViewById(R.id.txtnominal);

        SharedPreferenceHelper sp = new SharedPreferenceHelper(this);
        User user = sp.getUser();
        String token = sp.getToken();

        if (user == null || token == null) {
            sp.clear();
            startActivity(new Intent(this, Login.class));
            finish();
        }

        showUserProfil(user);

        txttanggaltransaksi = (TextView) findViewById(R.id.txttanggaltransaksi);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateTanggal = new SimpleDateFormat("dd MMMM yyyy");
        String dateTanggal = simpleDateTanggal.format(calendar.getTime());
        txttanggaltransaksi.setText(dateTanggal);

        pilihdonasisebagai = (Spinner) findViewById(R.id.pilihdonasisebagai);
        adapter = ArrayAdapter.createFromResource(this, R.array.donasisebagai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilihdonasisebagai.setAdapter(adapter);

        txtbuktiinfaq = findViewById(R.id.buktiinfaq);
        txtbuktiinfaq.setOnClickListener(view -> {
            ImagePicker.with(this)
                    .crop()
                    .createIntent(intent -> {
                        pickPicture.launch(intent);
                        return Unit.INSTANCE;
                    });
        });

        batalbutton = (Button) findViewById(R.id.batal);
        batalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });

        confirminfaq = (Button) findViewById(R.id.confirminfaq);
        confirminfaq.setOnClickListener(v-> {
            if (!ConnectionManager.isInternetAvailable()) {
                Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
                return;
            }

            RequestBody reqBody = RequestBody.create(new File(uri.getPath()), MediaType.parse("application/image"));
            MultipartBody.Part image = MultipartBody.Part.createFormData("image", "bukti.jpg", reqBody);

            RetrofitService.getAuthorizedApiService(token).postDonation(
                    txttanggaltransaksi.getText().toString(),
                    txtnamalengkap.getText().toString(),
                    txtalamat.getText().toString(),
                    user.getPhoneNumber(),
                    txtprofesi.getText().toString(),
                    txtprogram.getText().toString(),
                    pilihdonasisebagai.getSelectedItem().toString(),
                    txtberupa.getText().toString(),
                    txtnominal.getText().toString(),
                    image
            ).enqueue(new Callback<DonationResponse>() {
                @Override
                public void onResponse(Call<DonationResponse> call, Response<DonationResponse> response) {
                    Toast.makeText(FormInfaq.this, "Transaksi Sukses, Mohon Tunggu Konfirmasi", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(FormInfaq.this, Riwayat.class));
                    finish();
                }

                @Override
                public void onFailure(Call<DonationResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(FormInfaq.this, "Transaksi Gagal", Toast.LENGTH_LONG).show();
                }
            });
        });

        batalarah = (ImageView) findViewById(R.id.kembaliinfaq);
        batalarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Donasi.class));
            }
        });
    }

    private void showUserProfil(User user){
        txtnamalengkap.setText(user.getName());
        txtalamat.setText(user.getAddress());
        txttelepon.setText(user.getPhoneNumber());
        txtprofesi.setText(user.getProfession());
    }
}