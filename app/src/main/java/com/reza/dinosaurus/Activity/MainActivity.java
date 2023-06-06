package com.reza.dinosaurus.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.reza.dinosaurus.API.APIRequestData;
import com.reza.dinosaurus.API.RetroServer;
import com.reza.dinosaurus.Adapter.AdapterDino;
import com.reza.dinosaurus.Model.ModelDino;
import com.reza.dinosaurus.Model.ModelResponse;
import com.reza.dinosaurus.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDino;
    private FloatingActionButton fabTambah;
    private ProgressBar pbDino;
    private RecyclerView.Adapter ardDino;
    private RecyclerView.LayoutManager lmDino;
    private List<ModelDino> listDino = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvDino = findViewById(R.id.rv_dino);
        fabTambah = findViewById(R.id.fab_tambah);
        pbDino = findViewById(R.id.pb_dino);

        lmDino = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDino.setLayoutManager(lmDino);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveDino();
    }

    public void retrieveDino(){
        pbDino.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listDino = response.body().getData();

                ardDino = new AdapterDino(MainActivity.this, listDino);
                rvDino.setAdapter(ardDino);
                ardDino.notifyDataSetChanged();

                pbDino.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                pbDino.setVisibility(View.GONE);
            }
        });
    }
}