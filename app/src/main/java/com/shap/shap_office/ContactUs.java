package com.shap.shap_office;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.shap.shap_office.model.contact;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactUs extends AppCompatActivity {
    TextView alamat,fax,mail,telp1,nama,telp2;
    FirebaseDatabase fFirebaseDatabase;
    DatabaseReference cRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        ImageButton Imgbtnback= (ImageButton) findViewById(R.id.btn_back);
        Imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, MainActivity.class));
            }
        });

        ImageView btntelp1= (ImageView) findViewById(R.id.btnTelp1);
        btntelp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "02152905299";
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(callIntent);
            }
        });
        ImageView btntelp2= (ImageView) findViewById(R.id.btnTelp2);
        btntelp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "+6287883003121";
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(callIntent);
            }
        });
        TextView website = (TextView) findViewById(R.id.website);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://shaplawoffice.id/";
                Intent moveLink = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
                startActivity(moveLink);
            }
        });

        fFirebaseDatabase = FirebaseDatabase.getInstance();
        cRef = fFirebaseDatabase.getReference("ContactUs");

        alamat = findViewById(R.id.address);
        fax = findViewById(R.id.fax);
        mail = findViewById(R.id.mail);
        telp1 = findViewById(R.id.telp1);
        nama = findViewById(R.id.name);
        telp2 = findViewById(R.id.telp2);

        showContact();
    }

    private void showContact(){ cRef.child("contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contact Contact = dataSnapshot.getValue(contact.class);
                alamat.setText(Contact.getAlamat());
                fax.setText(Contact.getFax());
                mail.setText(Contact.getMail());
                telp1.setText(Contact.getTelp1());
                nama.setText(Contact.getNama());
                telp2.setText(Contact.getTelp2());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClick(View v) {

    }

}