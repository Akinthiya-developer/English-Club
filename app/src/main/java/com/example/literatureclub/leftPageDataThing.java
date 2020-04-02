package com.example.literatureclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jaeger.library.StatusBarUtil;

public class leftPageDataThing extends AppCompatActivity {

    Button eventshower,notifshower;
    TextView eventext, notiftext;
    FloatingActionButton adder;
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

    }
}
