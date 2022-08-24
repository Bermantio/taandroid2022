package com.example.lazismu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lazismu.retrofit.RetrofitService;
import com.example.lazismu.retrofit.response.ProfileUpdateResponse;
import com.example.lazismu.retrofit.response.User;
import com.example.lazismu.sharedpreference.SharedPreferenceHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfil extends AppCompatActivity {

    private EditText txtpassword, txtulangipassword, txtnamalengkap, txtalamat,txttelepon, txtprofesi;
    private String namalengkap,alamat,profesi,telepon,jeniskelamin;
    Button simpan, batal;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private FirebaseAuth authProfil;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

        txtnamalengkap = (EditText) findViewById(R.id.txtnamalengkap);
        txtalamat = (EditText) findViewById(R.id.txtalamat);
        txttelepon = (EditText) findViewById(R.id.txttelepon);
        txtprofesi = (EditText) findViewById(R.id.txtprofesi);

        spinner = (Spinner) findViewById(R.id.pilihjeniskelamin);
        adapter = ArrayAdapter.createFromResource(this, R.array.jeniskelamin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferenceHelper sp = new SharedPreferenceHelper(this);
        User user = sp.getUser();
        String token = sp.getToken();

        if (user == null) {
            sp.clear();
            startActivity(new Intent(this, Login.class));
            finish();
        }
        showUserProfil(user);

        simpan = (Button) findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfil(user, token, sp);
            }
            });

        batal = (Button) findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UbahProfil.this, Profil.class));
            }
        });
    }

    private void updateProfil(User user, String token, SharedPreferenceHelper sp) {
        int userId = user.getId();
        String namalengkap = txtnamalengkap.getText().toString();
        String alamat = txtalamat.getText().toString();
        String telepon = txttelepon.getText().toString();
        String profesi = txtprofesi.getText().toString();
        String jeniskelamin = spinner.getSelectedItem().toString();
        RequestBody empty = RequestBody.create(ByteString.EMPTY, MediaType.parse("application/image"));

        user.setName(namalengkap);
        user.setAlamat(alamat);
        user.setNotelepon(telepon);
        user.setProfesi(profesi);
        user.setJenisKelamin(jeniskelamin);

        if (TextUtils.isEmpty(namalengkap)){
            txtnamalengkap.setError("Nama Lengkap belum diisi");
            txtnamalengkap.requestFocus();
        }
        else if (TextUtils.isEmpty(jeniskelamin)){
            spinner.requestFocus();
        }
        else if (TextUtils.isEmpty(alamat)){
            txtalamat.setError("Alamat belum diisi");
            txtalamat.requestFocus();
        }
        else if (TextUtils.isEmpty(telepon)){
            txttelepon.setError("Telepon belum diisi");
            txttelepon.requestFocus();
        }
        else if (telepon.length() < 10){
            txttelepon.setError("Telepon minimal 10 digit");
            txttelepon.requestFocus();
        }
        else if (telepon.length() >= 14){
            txttelepon.setError("Telepon maksimal 13 digit");
            txttelepon.requestFocus();
        }
        else if (TextUtils.isEmpty(profesi)){
            txtprofesi.setError("Profesi belum diisi");
            txtprofesi.requestFocus();
        }
        else {
            Toast.makeText(UbahProfil.this,"Profil berhasil disimpan",Toast.LENGTH_SHORT).show();
            sp.setUser(user);

            Intent intent = new Intent(UbahProfil.this, Profil.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
//            RetrofitService.getAuthorizedApiService(token).updateProfile(
//                    userId,
//                    namalengkap,
//                    jeniskelamin,
//                    alamat,
//                    telepon,
//                    profesi,
//                    MultipartBody.Part.createFormData("image", "", empty)
//            ).enqueue(new Callback<ProfileUpdateResponse>() {
//                @Override
//                public void onResponse(Call<ProfileUpdateResponse> call, Response<ProfileUpdateResponse> response) {
//                    Toast.makeText(UbahProfil.this,"Profil berhasil disimpan",Toast.LENGTH_SHORT).show();
//                    sp.setUser(user);
//
//                    Intent intent = new Intent(UbahProfil.this, Profil.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<ProfileUpdateResponse> call, Throwable t) {
//                    t.printStackTrace();
//                    Toast.makeText(UbahProfil.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
        }
    }

    private void showUserProfil(User user) {
        txtnamalengkap.setText(user.getName());
        txtalamat.setText(user.getAddress());
        //spinner.setText(jeniskelamin);
        txttelepon.setText(user.getPhoneNumber());
        txtprofesi.setText(user.getProfession());
    }
}