package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.List;

public class memDetailsActivity extends AppCompatActivity {

    List<GetUser> datum;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    TextView nameBoard;
    Button My_history,Change_details,outie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_details);
        StatusBarUtil.setTransparent(this);

        firebaseAuth=FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails");

        nameBoard=findViewById(R.id.nameBoard);
        outie=findViewById(R.id.outie);
        My_history=findViewById(R.id.My_history);
        Change_details=findViewById(R.id.Change_details);
        datum=new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    GetUser uploadData=snap.getValue(GetUser.class);
                    if(uploadData.getCode().equals(firebaseUser.getEmail().substring(0,firebaseUser.getEmail().length()-10))){
                        datum.add(uploadData);
                    }
                }
                nameBoard.setText(datum.get(0).getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        My_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(memDetailsActivity.this,ShowHistorymem.class));
            }
        });

        outie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(memDetailsActivity.this,MainActivity.class));
                finish();
            }
        });

    }
}
