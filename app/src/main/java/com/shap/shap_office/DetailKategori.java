package com.shap.shap_office;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shap.shap_office.model.area;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailKategori extends AppCompatActivity {
    RecyclerView mRecyclerView;

    TextView mJudul;
    LinearLayout activity;

    LinearLayoutManager mlayoutManagerC; //sorting
    FirebaseDatabase fFirebaseDatabase;
    DatabaseReference cRef;
    FirebaseRecyclerAdapter<area, ViewHolder> fRecyclerAdapter;
    FirebaseRecyclerOptions<area> Options;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori);

        mlayoutManagerC = new LinearLayoutManager(this);

        String Category = getIntent().getStringExtra("Category");
        String Judul = getIntent().getStringExtra("Judul");
        Log.d("Detail Kategori", "onCreate: "+Category);

        mJudul = findViewById(R.id.idJudul);
        activity = findViewById(R.id.detailActivity);

        mRecyclerView = findViewById(R.id.rvDetail);
        mRecyclerView.setHasFixedSize(true);

        fFirebaseDatabase = FirebaseDatabase.getInstance();
        cRef = fFirebaseDatabase.getReference().child(Category);

        ImageButton Imgbtnback= (ImageButton) findViewById(R.id.btn_back);
        Imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailKategori.this, MainActivity.class));
            }
        });

        Resources res = getResources(); //resource handle
        switch (Category){
            case "AreaPraktik":
                activity.setBackground(res.getDrawable(R.drawable.bg3));
                break;
            case "HukumLitigasi":
                activity.setBackground(res.getDrawable(R.drawable.bg2));
                break;
            case "NonLitigasi":
                activity.setBackground(res.getDrawable(R.drawable.bg1));
                break;
            case "PengalamanTim":
                activity.setBackground(res.getDrawable(R.drawable.bg4));
                break;
            default:

        }

        mJudul.setText(Judul);
        showDetail();
    }

    private void showDetail() {
        loading = ProgressDialog.show(this, "","Please Wait...", true);
        Options = new FirebaseRecyclerOptions.Builder<area>().setQuery(cRef, new SnapshotParser<area>() {
            @NonNull
            @Override
            public area parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new area(
                        snapshot.child("id").getValue().toString(),
                        snapshot.child("nama").getValue().toString(),
                        snapshot.child("detail").getValue().toString());
            }
        }).build();

        fRecyclerAdapter = new FirebaseRecyclerAdapter<area, ViewHolder>(Options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull final area area) {
                viewHolder.setAreaView(getApplicationContext(), area.getNama(), area.getDetail());

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_detail, parent, false);

                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int pisition) {

                    }

                    @Override
                    public void onItemLongClick(View view, int pisition) {

                    }

                });
                return viewHolder;
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                if (loading.isShowing()){
                    loading.dismiss();
                }
            }
        };

        mRecyclerView.setLayoutManager(mlayoutManagerC);
        //set adapter to firebase recycler view
        mRecyclerView.setAdapter(fRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fRecyclerAdapter.stopListening();
    }
}