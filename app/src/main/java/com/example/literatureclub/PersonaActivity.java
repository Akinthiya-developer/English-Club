package com.example.literatureclub;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaeger.library.StatusBarUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonaActivity extends AppCompatActivity {

    final int position=ListieList.POS;
    List<GetUser> user=ListieList.dataPair;
    List<String> names=ListieList.namePair;
    List<String> events,regis;

    TextView t1,t2,t3,t4,t5;
    String Department, Section, Year, Phone;

    DatabaseReference databaseReference;

    RecyclerView eventie;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(names.get(position));
        setSupportActionBar(toolbar);
        StatusBarUtil.setTransparent(this);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);

        eventie=findViewById(R.id.eventie);
        eventie.setHasFixedSize(true);
        eventie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));
        eventie.setItemAnimator(new DefaultItemAnimator());

        GetUser getUser=user.get(position);
        Department="Department : "+getUser.getDepartment();
        Section="Section : "+getUser.getSection();
        Year="Year : "+getUser.getYear();
        Phone="Phone : "+getUser.getPhone();

        t2.setText(Department);
        t3.setText(Section);
        t4.setText(Year);
        t5.setText(Phone);

        regis=new ArrayList<>();
        events=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails/"+getUser.getCode()+"/eventList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    events.add(snap.getKey());
//                    Regis regi=snap.getValue(Regis.class);
//                    regis.add(regi.getRegis());
                }
                adapter=new eventieAdapter(getApplicationContext(),regis,events);
                eventie.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Horizontal adapter setting issue or else date retrieving issue#####


}
