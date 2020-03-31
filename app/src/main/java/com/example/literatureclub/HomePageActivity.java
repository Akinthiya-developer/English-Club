package com.example.literatureclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;

public class HomePageActivity extends AppCompatActivity {

    Button events,history;
    Boolean admin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        StatusBarUtil.setTransparent(this);

        events=findViewById(R.id.events);
        history=findViewById(R.id.history);

        //Basic adminCheck
        //Will be sent to the admin class and there it will continue with the getCurrentUser thing... :)
        if(new adminCheck().check()){
            history.setText("M-Data");
            events.setText("E-Update");
            admin = true;
        }

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(admin){
                    startActivity(new Intent(HomePageActivity.this,memDetailsActivity.class));
                }else{
                }
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(admin){
                    startActivity(new Intent(HomePageActivity.this,addEventActivity.class));
                }else{
                    startActivity(new Intent(HomePageActivity.this,addEventActivity.class));
                }
            }
        });

    }
}
