package com.example.literatureclub;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jaeger.library.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.List;

public class PersonaActivity extends AppCompatActivity {

    final int position=ListieList.POS;
    List<GetUser> user=ListieList.dataPair;
    List<String> names=ListieList.namePair;

    TextView t1,t2,t3,t4,t5;
    String Department, Section, Year, Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(names.get(position));
        setSupportActionBar(toolbar);
        StatusBarUtil.setTransparent(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);

        GetUser getUser=user.get(position);
        Department="Department : "+getUser.getDepartment();
        Section="Section : "+getUser.getSection();
        Year="Year : "+getUser.getYear();
        Phone="Phone : "+getUser.getPhone();

        t2.setText(Department);
        t3.setText(Section);
        t4.setText(Year);
        t5.setText(Phone);

    }
}
