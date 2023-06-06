package com.reza.dinosaurus.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etJenis, etUkuran, etAsal, etDeskripsi;
    private Button btnSimpan;
    private String nama, jenis, ukuran, asal, deskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etJenis = findViewById(R.id.et_jenis);
        etUkuran = findViewById(R.id.et_ukuran);
        etAsal = findViewById(R.id.et_asal);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                jenis = etJenis.getText().toString();
                ukuran = etUkuran.getText().toString();
                asal = etAsal.getText().toString();
                deskripsi = etDeskripsi.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong");
                } else if (jenis.trim().isEmpty()) {
                    etJenis.setError("Jenis Tidak Boleh Kosong");
                } else if (ukuran.isEmpty()) {
                    etUkuran.setError("Ukuran Tidak Boleh Kosong");
                } else if (asal.isEmpty()) {
                    etAsal.setError("Asal Tidak Boleh Kosong");
                } else if (deskripsi.isEmpty()) {
                    etDeskripsi.setError("Deskripsi Tidak Boleh Kosong");
                }else {
                    tambahDino();
                }
            }
        });
    }

    private void tambahDino(){
        APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama, jenis, ukuran, asal, deskripsi);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "kode" + kode + "pesan" + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}