package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jaeger.library.StatusBarUtil;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout mail,pwd;
    Button logger,goin;
    TextView header;

    String Mail,Pwd;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);

        //Linking the components..
        mail=findViewById(R.id.mail);
        pwd=findViewById(R.id.pwd);
        logger=findViewById(R.id.logger);
        goin=findViewById(R.id.goin);
        header=findViewById(R.id.header);

        firebaseAuth=FirebaseAuth.getInstance();

        //To check with the FIREBASE credentials
        logger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting the texts from the edit text fields...
                Mail=mail.getEditText().getText().toString();
                Pwd=pwd.getEditText().getText().toString();

                if(!Mail.isEmpty() && !Pwd.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(Mail,Pwd)
                            .addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //sign-in success
                                        FirebaseUser user=firebaseAuth.getCurrentUser();
                                        UpdateUI(user);
                                    }else{
                                        Toast.makeText(LoginActivity.this,"Auth failed",Toast.LENGTH_LONG).show();
                                        UpdateUI(null);
                                    }
                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
                }
            }
        });

        //To visit the HomePage..
        goin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
                finish();
            }
        });


    }

    private void UpdateUI(FirebaseUser user) {
        if(user!=null && !Mail.isEmpty() && !Pwd.isEmpty()) {
            header.setText("Good to go");
            mail.setVisibility(View.GONE);
            pwd.setVisibility(View.GONE);
            logger.setVisibility(View.GONE);
            goin.setVisibility(View.VISIBLE);
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.owntoast,
                    (ViewGroup) findViewById(R.id.toaster));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("You are now logged in..");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }
}