package com.shap.shap_office;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView, kategoriView, areaView, litigasiView, contactView, teamView;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
        kategoriView = itemView;
        areaView = itemView;
        litigasiView = itemView;
        contactView = itemView;
        teamView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }


    public void setKategori(Context ctx, String gambar, String kategori){

        TextView rvKategori = kategoriView.findViewById(R.id.categoryName);
        rvKategori.setText(kategori);

        ImageView gambarIv = kategoriView.findViewById(R.id.categoryThumb);
        Picasso.get().load(gambar).into(gambarIv);

    }
    public void setTeamView(Context ctx, String nama){

        TextView rvTeam = teamView.findViewById(R.id.namaTim);
        rvTeam.setText(nama);

    }

    public void setAreaView(Context ctx, String nama, String detail){
        TextView rvNama = areaView.findViewById(R.id.judul1);
        rvNama.setText(nama);

        TextView rvDetail = areaView.findViewById(R.id.detail);
        rvDetail.setText(detail);

    }
    public void setLitigasiView(Context ctx, String nama, String detail){
        TextView rvNama = litigasiView.findViewById(R.id.judul1);
        rvNama.setText(nama);

        TextView rvDetail = mView.findViewById(R.id.detail);
        rvDetail.setText(detail);
    }
    public void setContactView(Context ctx, String alamat, String fax, String mail, String telp1, String nama, String telp2){
        TextView tvAlamat = contactView.findViewById(R.id.address);
        tvAlamat.setText(alamat);

        TextView tvFax = contactView.findViewById(R.id.fax);
        tvFax.setText(fax);

        TextView tvMail = contactView.findViewById(R.id.mail);
        tvMail.setText(mail);

        TextView tvTelp1 = contactView.findViewById(R.id.telp1);
        tvTelp1.setText(telp1);

        TextView tvNama = contactView.findViewById(R.id.name);
        tvNama.setText(nama);

        TextView tvTelp2 = contactView.findViewById(R.id.telp2);
        tvTelp2.setText(telp2);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int pisition);
        void onItemLongClick(View view, int pisition);

    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}

