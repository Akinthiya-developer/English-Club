package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaeger.library.StatusBarUtil;

import static android.view.View.GONE;

public class HomePageActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    private static final String TAG = "LOG_CAT";
    Button events,history;
    TextView infot;
    int i=0;
    String[] infotext=new String[]{"\n\nPSNA HAHAHAHAHA", "\n\nHAHAHAHA PSNA","\n\nDID WITH THE HANDLER"};
    Boolean admin=false;
    Handler handler;
    TextView qote;
    String qotie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        StatusBarUtil.setTransparent(this);

        events=findViewById(R.id.events);
        infot=findViewById(R.id.infot);
        qote=findViewById(R.id.qote);
        history=findViewById(R.id.history);

        databaseReference= FirebaseDatabase.getInstance().getReference("Quotes");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Quote quote=snap.getValue(Quote.class);
                    qotie=quote.getQuote();
                    qote.setText(qotie);
//                    Toast.makeText(HomePageActivity.this, qotie, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        handler=new Handler();
        final Runnable r=new Runnable(){
            @Override
            public void run() {
                if(i==infotext.length)i=0;
                infot.setText(infotext[i]);
                i++;
                handler.postDelayed(this,5000);
            }
        };
        handler.postDelayed(r,5000);

        //Basic adminCheck
        //Will be sent to the admin class and there it will continue with the getCurrentUser thing... :)
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser.getEmail().equals("literaryclubpsna@gmail.com")){
            history.setText("M-Data");
            events.setText("E-Update");
            admin = true;
            events.setVisibility(GONE);
        }

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(admin){
                    startActivity(new Intent(HomePageActivity.this,rightPageDataThing.class));
                }else{
                    startActivity(new Intent(HomePageActivity.this,memDetailsActivity.class));
                }
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this,leftPageDataThing.class));
            }
        });

    }
}
