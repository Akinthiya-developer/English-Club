package com.example.literatureclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaeger.library.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.view.View.GONE;

public class EventShower extends AppCompatActivity {

    ImageView emage;
    TextView eheader,einfo;
    static Button okie;

    List<UploadData> list;
    List<String> name;

    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    boolean adminn=rightPageDataThing.adminn;

    static int VENUMA_VENAMA;
    SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarUtil.setTransparent(this);

        databaseReference=FirebaseDatabase.getInstance().getReference("events/members");
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        //instead of adding extras I'm just lazy and set the POSITION and downdata static in the
        //listAdapter class so that i can access from here...  :P
        final int pos=listAdapter.POSITION;
        final String a=Integer.toString(pos);
        list=listAdapter.downdata;
        name=EventListActivity.name;

        emage=findViewById(R.id.emage);
        einfo=findViewById(R.id.einfo);
        eheader=findViewById(R.id.eheader);
        okie=findViewById(R.id.okie);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        String doclink=list.get(pos).getDocURL();
        String imglink=list.get(pos).getImgURL();
        if(imglink==null)emage.setVisibility(GONE);
        if(doclink==null){
            fab.setVisibility(GONE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(list.get(pos).getDocURL().toString()),"application/pdf");
                startActivity(Intent.createChooser(intent,"choose an application to view"));
                Snackbar.make(view, "Replace with your own action"+ a, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Glide.with(this).load(list.get(pos).getImgURL()).into(emage);
        einfo.setText(list.get(pos).getInfo());
        eheader.setText(list.get(pos).getName());

        if(VENUMA_VENAMA==1 || adminn)okie.setVisibility(GONE);

        okie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference memAdder=FirebaseDatabase.getInstance().getReference("UserDetails/"+(firebaseUser.getEmail().substring(0,firebaseUser.getEmail().length()-10))+"/eventList");
                databaseReference=FirebaseDatabase.getInstance().getReference("events/"+list.get(pos).getActual()+"/members");
                //saving the email part to the member list so it is accesible as before...
                databaseReference.child(firebaseUser.getEmail().substring(0,firebaseUser.getEmail().length()-10)).setValue("ullen-AIya");
                memAdder.child(name.get(pos)).setValue(formatter.format(date));
            }
        });
    }
}
