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
    com.google.android.material.floatingactionbutton.FloatingActionButton adder;
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
        adder=findViewById(R.id.adder);

        //Basic adminCheck
        //Will be sent to the admin class and there it will continue with the getCurrentUser thing... :)
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser.getEmail().equals("literaryclubpsna@gmail.com")){
            admin = true;
            notiftext.setText("You can check the pre-uploaded posts here");
            eventext.setText("You can check the pre-uploaded event details here");
        }else{
            adder.setVisibility(View.INVISIBLE);
        }

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

        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(leftPageDataThing.this,adder);
                popupMenu.getMenuInflater().inflate(R.menu.menu_scrolling, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(leftPageDataThing.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        if(menuItem.getTitle().equals("Upload Event")){
                            startActivity(new Intent(leftPageDataThing.this,addEventActivity.class));
                        }else startActivity(new Intent(leftPageDataThing.this,addNotifActivity.class));
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }
}
