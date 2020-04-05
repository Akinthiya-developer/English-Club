package com.example.literatureclub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class rightPageDataThing extends AppCompatActivity {

    DatabaseReference databaseReference;

    FloatingActionButton quploader,euploader,nuploader;
    FloatingActionMenu menu;

    EditText q;

    SimpleDateFormat formatter = new SimpleDateFormat("HHMMss");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_page_data_thing);

        menu=findViewById(R.id.menu);
        quploader=findViewById(R.id.quploader);
        euploader=findViewById(R.id.euploader);
        nuploader=findViewById(R.id.nuploader);

        databaseReference= FirebaseDatabase.getInstance().getReference("Quotes");

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

        quploader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(rightPageDataThing.this);
                builder.setTitle("Today's Quote");
                q=new EditText(rightPageDataThing.this);
                builder.setIcon(R.drawable.q);
                builder.setView(q);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Quote quote=new Quote();
                        quote.setQuote(q.getText().toString());
                        quote.setDate(formatter.format(date));
                        databaseReference.child("Quote").setValue(quote);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();

            }
        });

    }
}
