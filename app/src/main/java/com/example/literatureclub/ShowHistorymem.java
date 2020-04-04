package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import java.util.Set;

public class ShowHistorymem extends AppCompatActivity {

    RecyclerView perfie;
    RecyclerView.Adapter adapter;
    TextView evenfo;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    List<String> listuh;
    String news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_historymem);
        StatusBarUtil.setTransparent(this);

        listuh=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference=FirebaseDatabase.getInstance().getReference("UserDetails/"+firebaseUser.getEmail().substring(0,(firebaseUser.getEmail().length()-10))+"/eventList");

        perfie=findViewById(R.id.perfie);
        evenfo=findViewById(R.id.evenfo);

        perfie.setLayoutManager(new LinearLayoutManager(this));
        perfie.setItemAnimator(new DefaultItemAnimator());
        perfie.setHasFixedSize(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    listuh.add(snap.getKey());
                }
                news="It seems that you have enrolled in "+listuh.size()+" events in total.";
                if(listuh.size()!=0){
                    evenfo.setText(news);
                }else{
                    evenfo.setText("Currently you are not in any events!");
                }
                adapter=new HistoryAdapter(listuh,getApplicationContext());
                perfie.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
