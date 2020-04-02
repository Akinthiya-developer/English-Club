package com.example.literatureclub;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jaeger.library.StatusBarUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class EventShower extends AppCompatActivity {

    ImageView emage;
    TextView eheader,einfo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarUtil.setTransparent(this);

        int pos=listAdapter.POSITION;
        final String a=Integer.toString(pos);
        List<UploadData> list=listAdapter.downdata;

        emage=findViewById(R.id.emage);
        einfo=findViewById(R.id.einfo);
        eheader=findViewById(R.id.eheader);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action"+ a, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Glide.with(this).load(list.get(pos).getImgURL()).into(emage);
        einfo.setText(list.get(pos).getInfo());
        eheader.setText(list.get(pos).getName());

    }
}
