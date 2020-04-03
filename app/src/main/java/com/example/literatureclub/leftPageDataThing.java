package com.example.literatureclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jaeger.library.StatusBarUtil;

public class leftPageDataThing extends AppCompatActivity {

    Button eventshower,notifshower;
    TextView eventext, notiftext;
    private boolean admin=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_page_data_thing);
        StatusBarUtil.setTransparent(this);

        eventshower=findViewById(R.id.eventshower);
        notifshower=findViewById(R.id.notifshower);
        eventext=findViewById(R.id.eventext);
        notiftext=findViewById(R.id.notiftext);

        eventshower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(leftPageDataThing.this,EventListActivity.class));
            }
        });

        notifshower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(leftPageDataThing.this,NotifShower.class));
            }
        });

    }
}
