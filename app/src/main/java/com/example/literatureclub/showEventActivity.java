package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showEventActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    RecyclerView lister;
    RecyclerView.Adapter adapter;

    List<UploadData> downdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        final ProgressDialog progressDialog = new ProgressDialog(showEventActivity.this);
        progressDialog.setTitle("Getting Events");
        progressDialog.show();

        lister=findViewById(R.id.lister);
        lister.setHasFixedSize(true);
        lister.setLayoutManager(new LinearLayoutManager(showEventActivity.this));

        databaseReference= FirebaseDatabase.getInstance().getReference("events");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postsnap : dataSnapshot.getChildren()){
                    UploadData data=postsnap.getValue(UploadData.class);
                    downdata.add(data);
                }
                adapter=new listadapter(getApplicationContext(),downdata);
                lister.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}
