package com.example.literatureclub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaeger.library.StatusBarUtil;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,ddatabaseReference;
    FirebaseUser firebaseUser;

    GetUser getUser;

    TextInputLayout ed1,ed2,ed3,ed4,ed5;
    Button update;
    TextView preach;

    String Name,Department,Section,Phone,Year;
    Map<String,Object> updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        StatusBarUtil.setTransparent(this);

        ed1=findViewById(R.id.ed1);//n
        ed2=findViewById(R.id.ed2);//d
        ed3=findViewById(R.id.ed3);//s
        ed4=findViewById(R.id.ed4);//y
        ed5=findViewById(R.id.ed5);//p
        update=findViewById(R.id.update);
        preach=findViewById(R.id.preach);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        ddatabaseReference=FirebaseDatabase.getInstance().getReference("UserDetails");
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails/"+firebaseUser.getEmail().substring(0,firebaseUser.getEmail().length()-10));

        ddatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    if(snap.getKey().equals(firebaseUser.getEmail().substring(0,(firebaseUser.getEmail().length()-10)))){
                        getUser=snap.getValue(GetUser.class);
                        Name=getUser.getName();
                        Department=getUser.getDepartment();
                        Year=getUser.getYear();
                        Phone=getUser.getPhone();
                        Section=getUser.getSection();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updated=new HashMap<>();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this, Name, Toast.LENGTH_SHORT).show();
                Name=ed1.getEditText().getText().toString();
                Department=ed2.getEditText().getText().toString().toUpperCase();
                Section=ed3.getEditText().getText().toString().toUpperCase();
                Year=ed4.getEditText().getText().toString();
                Phone=ed5.getEditText().getText().toString();

                if(Name.isEmpty()){
                    getUser.setName(getUser.getName());
                }else getUser.setName(Name);
                if(Department.isEmpty()){
                    getUser.setDepartment(getUser.getDepartment());
                }else getUser.setDepartment(Department);
                if(Phone.isEmpty()){
                    getUser.setPhone(getUser.getPhone());
                }else getUser.setPhone(Phone);
                if(Section.isEmpty()){
                    getUser.setSection(getUser.getSection());
                }else getUser.setSection(Section);
                if(Year.isEmpty()){
                    getUser.setYear(getUser.getYear());
                }else getUser.setYear(Year);

                updated.put("name",getUser.getName());
                updated.put("department",getUser.getDepartment());
                updated.put("section",getUser.getSection());
                updated.put("year",getUser.getYear());
                updated.put("phone",getUser.getPhone());

                databaseReference.updateChildren(updated);
            }
        });

    }
}