package com.reza.dinosaurus.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reza.dinosaurus.API.APIRequestData;
import com.reza.dinosaurus.API.RetroServer;
import com.reza.dinosaurus.Activity.DetailActivity;
import com.reza.dinosaurus.Activity.MainActivity;
import com.reza.dinosaurus.Activity.UbahActivity;
import com.reza.dinosaurus.Model.ModelDino;
import com.reza.dinosaurus.Model.ModelResponse;
import com.reza.dinosaurus.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDino extends RecyclerView.Adapter<AdapterDino.VHDino> {
    private Context ctx;
    private List<ModelDino> listDino;

    public AdapterDino(Context ctx, List<ModelDino> listDino){
        this.ctx = ctx;
        this.listDino = listDino;
    }
    @NonNull
    @Override
    public AdapterDino.VHDino onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dino, parent, false);
        return new VHDino(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDino.VHDino holder, int position) {
        ModelDino MD = listDino.get(position);
        holder.tvId.setText(MD.getId());
        holder.tvNama.setText(MD.getNama());
        holder.tvJenis.setText(MD.getJenis());
        holder.tvUkuran.setText(MD.getUkuran());
        holder.tvAsal.setText(MD.getAsal());
        holder.tvDeskripsi.setText(MD.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return listDino.size();
    }

    public class VHDino extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvJenis, tvUkuran, tvAsal, tvDeskripsi;

        public VHDino(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJenis = itemView.findViewById(R.id.tv_jenis);
            tvUkuran = itemView.findViewById(R.id.tv_ukuran);
            tvAsal = itemView.findViewById(R.id.tv_asal);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, DetailActivity.class);
                    pindah.putExtra("xId", tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xJenis", tvJenis.getText().toString());
                    pindah.putExtra("xUkuran", tvUkuran.getText().toString());
                    pindah.putExtra("xAsal", tvAsal.getText().toString());
                    pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                    ctx.startActivity(pindah);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Operasi apa yang akan dilakukan?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusDino(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);
                            pindah.putExtra("xId", tvId.getText().toString());
                            pindah.putExtra("xNama", tvNama.getText().toString());
                            pindah.putExtra("xJenis", tvJenis.getText().toString());
                            pindah.putExtra("xUkuran", tvUkuran.getText().toString());
                            pindah.putExtra("xAsal", tvAsal.getText().toString());
                            pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                            ctx.startActivity(pindah);
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }

        private void hapusDino(String idDino) {
            APIRequestData ARD = RetroServer.koneksiRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardDelete(idDino);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveDino();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

