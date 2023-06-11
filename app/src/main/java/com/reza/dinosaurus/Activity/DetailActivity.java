package com.reza.dinosaurus.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.reza.dinosaurus.R;

public class DetailActivity extends AppCompatActivity {
    private String yId, yNama, yJenis, yUkuran, yAsal, yDeskripsi;
    private String id, nama, jenis, ukuran, asal, deskripsi;
    private TextView tvId, tvNama, tvJenis, tvUkuran, tvAsal, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yJenis = ambil.getStringExtra("xJenis");
        yUkuran= ambil.getStringExtra("xUkuran");
        yAsal = ambil.getStringExtra("xAsal");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");

        tvId = findViewById(R.id.tv_id);
        tvNama = findViewById(R.id.tv_nama);
        tvJenis = findViewById(R.id.tv_jenis);
        tvUkuran = findViewById(R.id.tv_ukuran);
        tvAsal = findViewById(R.id.tv_asal);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);

        tvId.setText(yId);
        tvNama.setText(yNama);
        tvJenis.setText(yJenis);
        tvUkuran.setText(yUkuran);
        tvAsal.setText(yAsal);
        tvDeskripsi.setText(deskripsi);

    }
}