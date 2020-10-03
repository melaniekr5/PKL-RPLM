package com.shap.shap_office;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shap.shap_office.model.about;
import com.shap.shap_office.model.kategori;
import com.shap.shap_office.model.team;
import com.shap.shap_office.model.visimisi;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView,nRecyclerView;

    TextView about,Visi,Misi,contact,team;

    LinearLayoutManager mlayoutManagerC,nlayoutManager; //sorting

    FirebaseDatabase fFirebaseDatabase;
    DatabaseReference cRef,fRef,mRef,nRef;
    FirebaseRecyclerAdapter<kategori, ViewHolder> fRecyclerAdapter;
    FirebaseRecyclerOptions<kategori> Options;

    FirebaseRecyclerAdapter<team, ViewHolder> nRecyclerAdapter;
    FirebaseRecyclerOptions<team> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        about = findViewById(R.id.detail_about);
        Visi = findViewById(R.id.detail_visi);
        Misi = findViewById(R.id.detail_misi);
        contact = findViewById(R.id.contact);
        team = findViewById(R.id.team);

        mlayoutManagerC = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        nlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView = findViewById(R.id.recyclerCategory);
        mRecyclerView.setHasFixedSize(true);

        fFirebaseDatabase = FirebaseDatabase.getInstance();
        cRef = fFirebaseDatabase.getReference("Categori");
        fRef = fFirebaseDatabase.getReference("About");
        mRef = fFirebaseDatabase.getReference("VisiMisi");
        nRef = fFirebaseDatabase.getReference("OfficeTeam");

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactUs.class));
            }
        });

        showAbout();
        showVisiMisi();
        showCategory();
        showTeam();
    }


    @Override
    protected void onStart(){
        super.onStart();

        if (fRecyclerAdapter != null){
            fRecyclerAdapter.startListening();
        }
    }

    private void showCategory() {
        Options = new FirebaseRecyclerOptions.Builder<kategori>().setQuery(cRef, kategori.class).build();

        fRecyclerAdapter = new FirebaseRecyclerAdapter<kategori, ViewHolder>(Options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull final kategori kategori) {
                viewHolder.setKategori(getApplicationContext(),kategori.getGambar(),kategori.getKategori());

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_category, parent, false);

                ViewHolder viewHolder = new ViewHolder(itemView);

                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int pisition) {
                        Intent intent = new Intent(MainActivity.this, DetailKategori.class);
                        intent.putExtra("Category",getItem(pisition).getKey());
                        intent.putExtra("Judul",getItem(pisition).getKategori());
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int pisition) {

                    }

                });

                return viewHolder;
            }
        };

        mRecyclerView.setLayoutManager(mlayoutManagerC);
        fRecyclerAdapter.startListening();
        //set adapter to firebase recycler view
        mRecyclerView.setAdapter(fRecyclerAdapter);
    }

    private void showAbout(){
        fRef.child("mAbout").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                about About = dataSnapshot.getValue(about.class);
                about.setText(About.getAbout());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showVisiMisi(){
        mRef.child("visimisi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                visimisi VisiMisi = dataSnapshot.getValue(visimisi.class);
                Visi.setText(VisiMisi.getVisi());
                Misi.setText(VisiMisi.getMisi());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showTeam(){
        nRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                team Team = dataSnapshot.getValue(team.class);
                team.setText(Team.getNama());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void showTeam() {
//        options = new FirebaseRecyclerOptions.Builder<team>().setQuery(nRef, new SnapshotParser<team>() {
//            @NonNull
//            @Override
//            public team parseSnapshot(@NonNull DataSnapshot snapshot) {
//                return new team(
//                        snapshot.child("id").getValue().toString(),
//                        snapshot.child("nama").getValue().toString());
//            }
//        }).build();
//
//        nRecyclerAdapter = new FirebaseRecyclerAdapter<team, ViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull team model) {
//                holder.setTeamView(getApplicationContext(),model.getNama());
//            }
//
//            @NonNull
//            @Override
//            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_tim, parent, false);
//
//                ViewHolder viewHolder = new ViewHolder(itemView);
//
//                viewHolder.setOnClickListener(new ViewHolder.ClickListener(){
//
//                    @Override
//                    public void onItemClick(View view, int pisition) {
//
//                    }
//
//                    @Override
//                    public void onItemLongClick(View view, int pisition) {
//
//                    }
//                });
//
//                return viewHolder;
//            }
//
//            @Override
//            public void onDataChanged() {
//                super.onDataChanged();
//            }
//        };
//        nRecyclerView.setLayoutManager(nlayoutManager);
//        nRecyclerAdapter.startListening();
//        //set adapter to firebase recycler view
//        nRecyclerView.setAdapter(nRecyclerAdapter);
//    }
}
