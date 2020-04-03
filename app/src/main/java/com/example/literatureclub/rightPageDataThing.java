package com.example.literatureclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class rightPageDataThing extends AppCompatActivity {

    FloatingActionButton quploader,euploader,nuploader;
    FloatingActionMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_page_data_thing);

        menu=findViewById(R.id.menu);
        quploader=findViewById(R.id.quploader);
        euploader=findViewById(R.id.euploader);
        nuploader=findViewById(R.id.nuploader);

        euploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(rightPageDataThing.this,addEventActivity.class));
            }
        });

        nuploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(rightPageDataThing.this,addNotifActivity.class));
            }
        });

    }
}
