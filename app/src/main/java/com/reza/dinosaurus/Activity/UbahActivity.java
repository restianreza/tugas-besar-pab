package com.reza.dinosaurus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.reza.dinosaurus.API.APIRequestData;
import com.reza.dinosaurus.API.RetroServer;
import com.reza.dinosaurus.Model.ModelResponse;
import com.reza.dinosaurus.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yJenis, yUkuran, yAsal, yDeskripsi;
    private EditText etNama, etJenis, etUkuran, etAsal, etDeskripsi;
    private Button btnUbah;
    private String nama, jenis, ukuran, asal, deskripsi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yJenis = ambil.getStringExtra("xJenis");
        yUkuran= ambil.getStringExtra("xUkuran");
        yAsal = ambil.getStringExtra("xAsal");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");

        etNama = findViewById(R.id.et_nama);
        etJenis = findViewById(R.id.et_jenis);
        etUkuran = findViewById(R.id.et_ukuran);
        etAsal = findViewById(R.id.et_asal);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(yNama);
        etJenis.setText(yJenis);
        etUkuran.setText(yUkuran);
        etAsal.setText(yAsal);
        etDeskripsi.setText(yDeskripsi);

        btnUbah.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                jenis = etJenis.getText().toString();
                ukuran = etUkuran.getText().toString();
                asal = etAsal.getText().toString();
                deskripsi = etDeskripsi.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama tidak boleh kosong!");
                } else if (jenis.trim().isEmpty()) {
                    etJenis.setError("Jenis tidak boleh kosong!");
                }else if (ukuran.trim().isEmpty()) {
                    etUkuran.setError("Ukuran tidak boleh kosong!");
                }else if (asal.trim().isEmpty()) {
                    etAsal.setError("Asal tidak boleh kosong!");
                }else if (deskripsi.trim().isEmpty()) {
                    etDeskripsi.setError("Deskripsi tidak boleh kosong!");
                }else {
                    ubahDino();
                }
            }
        });

    }

    private void ubahDino(){
        APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(yId, yNama, yJenis, yUkuran, yAsal, yDeskripsi);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}