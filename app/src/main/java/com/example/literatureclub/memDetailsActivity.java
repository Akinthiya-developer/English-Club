package com.example.literatureclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

public class memDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_details);
        StatusBarUtil.setTransparent(this);
    }
}
