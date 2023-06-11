package com.reza.dinosaurus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.reza.dinosaurus.R;

public class DetailActivity extends AppCompatActivity {
    private String id, nama, jenis, ukuran, asal, deskripsi;
    private TextView tvId, tvNama, tvJenis, tvUkuran, tvAsal, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent ambil = getIntent();
        id = ambil.getStringExtra("xId");
        nama = ambil.getStringExtra("xNama");
        jenis = ambil.getStringExtra("xJenis");
        ukuran= ambil.getStringExtra("xUkuran");
        asal = ambil.getStringExtra("xAsal");
        deskripsi = ambil.getStringExtra("xDeskripsi");

        tvId = findViewById(R.id.tv_id);
        tvNama = findViewById(R.id.tv_nama);
        tvJenis = findViewById(R.id.tv_jenis);
        tvUkuran = findViewById(R.id.tv_ukuran);
        tvAsal = findViewById(R.id.tv_asal);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);

        tvId.setText(id);
        tvNama.setText(nama);
        tvJenis.setText(jenis);
        tvUkuran.setText(ukuran);
        tvAsal.setText(asal);
        tvDeskripsi.setText(deskripsi);

    }
}