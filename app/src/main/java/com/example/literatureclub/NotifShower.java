package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class NotifShower extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    List<UploadData> list=new ArrayList<>();

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_shower);
        StatusBarUtil.setTransparent(this);

        recyclerView=findViewById(R.id.machCity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        databaseReference= FirebaseDatabase.getInstance().getReference("notifs");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UploadData uploadData=snapshot.getValue(UploadData.class);
                    list.add(uploadData);
                }
                adapter=new listAdapter(getApplicationContext() ,list);
                recyclerView.setAdapter(adapter);
                EventShower.VENUMA_VENAMA=1;
                //after setting the adapter and also as this is a normal post and not an event
                //I there by remove the register button by making the 'okie' button static and public which is
                //in the EventShower class and set the visibility to gone here :)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
