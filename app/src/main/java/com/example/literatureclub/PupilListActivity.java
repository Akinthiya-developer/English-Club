package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PupilListActivity extends AppCompatActivity {

    RecyclerView puplist;
    RecyclerView.Adapter adapter;

    List<String> namePair;
    List<GetUser> dataPair;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupil_list);
        StatusBarUtil.setTransparent(this);

        puplist=findViewById(R.id.puplist);
        puplist.setHasFixedSize(true);
        puplist.setLayoutManager(new LinearLayoutManager(this));
        puplist.setItemAnimator(new DefaultItemAnimator());

        namePair=new ArrayList<>();
        dataPair=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails");

        //to get the names of the users alone....
        //oh crap, I also need the key of the users so below's the mess
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    GetUser getUser=snap.getValue(GetUser.class);
                    namePair.add(getUser.getName());
                    dataPair.add(getUser);
                }
                adapter=new ListieList(getApplicationContext(),namePair,dataPair);
                puplist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
