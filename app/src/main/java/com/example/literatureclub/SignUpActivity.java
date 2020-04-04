package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaeger.library.StatusBarUtil;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText name,section,phone,code,email,password,year,department;
    Button signup,login;

    String Name,Section,Phone,Code,Email,Password,Year,Department;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        StatusBarUtil.setTransparent(this);

        //__init__ firebase things...
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("UserDetails");
        firebaseUser=firebaseAuth.getCurrentUser();

        //liniking the xml with java
        name=findViewById(R.id.name);
        section=findViewById(R.id.section);
        code=findViewById(R.id.code);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        year=findViewById(R.id.year);
        department=findViewById(R.id.department);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        phone=findViewById(R.id.phone);


        //On click listerner...
        /** It may contain
        * 1. text grabber and spinner value grabbers
        * 2. checking whether nothing is null and the code is correct
        * 3. creating account with the submitted mail and password
        * 4. onSuccess---adding the other details to the DB of that particular User
        * 5. Then move on :D */
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name=name.getText().toString();
                Section=section.getText().toString();
                Code=code.getText().toString();
                Email=email.getText().toString();
                Password=password.getText().toString();
                Year=year.getText().toString();
                Department=department.getText().toString();
                Phone=phone.getText().toString();

                //saving the details under the name of email without the domain name i.e. @gmail.com
                //so it can be easier to choose the user from other classes #lazyness
                final String demo=Email.substring(0,Email.length()-10);

                if(!Name.isEmpty() && !Section.isEmpty() && !Code.isEmpty() && !Email.isEmpty() &&
                        !Password.isEmpty() && !Year.isEmpty() && !Department.isEmpty() && Code.equals("reputation")){
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Code=firebaseUser.getEmail().substring(0,firebaseUser.getEmail().length()-10);
                                    Users data=new Users(Name,Section,Code,Phone,Email,Password,Department,Year);
                                    Map<String,Users> users=new HashMap<>();
                                    users.put(Name,data);
                                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                                    databaseReference.child(demo).setValue(data);
                                    Toast.makeText(SignUpActivity.this, demo, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this,HomePageActivity.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivity.this, "fucked ra", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(SignUpActivity.this, "Check the submitted values maybe wrong Code", Toast.LENGTH_SHORT).show();
                }

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                finish();
            }
        });

    }
}
