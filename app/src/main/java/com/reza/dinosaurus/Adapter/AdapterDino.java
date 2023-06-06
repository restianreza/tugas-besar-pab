package com.reza.dinosaurus.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reza.dinosaurus.API.APIRequestData;
import com.reza.dinosaurus.API.RetroServer;
import com.reza.dinosaurus.Activity.MainActivity;
import com.reza.dinosaurus.Activity.UbahActivity;
import com.reza.dinosaurus.Model.ModelDino;
import com.reza.dinosaurus.Model.ModelResponse;
import com.reza.dinosaurus.R;

import java.util.List;

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


        }
    }

}

