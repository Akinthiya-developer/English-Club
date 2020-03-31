package com.example.literatureclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaeger.library.StatusBarUtil;

import java.util.HashMap;
import java.util.Map;

public class addEventActivity extends AppCompatActivity {

    EditText infoText,eventName;
    TextView imgName,docName;
    Button addImg,attachdoc,updata,makeba;

    Uri ImgPathUri=null,DocPathUri=null;
    String StoragePath="events";

    //link grabber
    String linkimg,linkdoc;

    UploadData uploadData;
    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference;

    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;
    //Doc request code..
    final static int PICK_DOC_CODE = 2342;

    ProgressDialog progressDialog ;


    //for the image picker intent..
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null &&  data.getData()!=null && requestCode==Image_Request_Code &&  resultCode==RESULT_OK){
            ImgPathUri=data.getData();
            imgName.setText("IMG_SELECTED");
        }

        if(data!=null && data.getData()!=null && requestCode==PICK_DOC_CODE && resultCode==RESULT_OK){
            DocPathUri=data.getData();
            docName.setText("DOC-SELECTED");
        }

    }


    //to get the file extension from the uri..
    public String getExtension(Uri uri){

        //MIMETYPEMAP maps the fileMimeType to the file..
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();

        //Handling the data from other apps without user interaction...
        ContentResolver contentResolver=getContentResolver();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        StatusBarUtil.setTransparent(this);

        //linking..
        infoText=findViewById(R.id.infoText);
        eventName=findViewById(R.id.eventName);
        imgName=findViewById(R.id.imgName);
        docName=findViewById(R.id.docName);
        addImg=findViewById(R.id.addImg);
        attachdoc=findViewById(R.id.attachdoc);
        updata =findViewById(R.id.updata);
        makeba=findViewById(R.id.makeba);

        uploadData=new UploadData();

        // Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(addEventActivity.this);

        storageReference= FirebaseStorage.getInstance().getReference("events");
        databaseReference= FirebaseDatabase.getInstance().getReference("events");

        //to Upload the documents..
        attachdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pick the doc"),PICK_DOC_CODE);

            }
        });

        //to Upload the images
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pick the Image"),Image_Request_Code);

            }
        });


        makeba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info=infoText.getText().toString();
                String event=eventName.getText().toString();

                uploadData.setName(event);
                uploadData.setInfo(info);

                //uploading the event name,info and other file links to the RTDB under the event name
                databaseReference.child(event).setValue(uploadData);
            }
        });

        //to Upload everything to the fireBase..
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ImgPathUri!=null){
                    StorageReference str=storageReference.child(StoragePath + System.currentTimeMillis() + "." + getExtension(ImgPathUri));

                    str.putFile(ImgPathUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    taskSnapshot.getStorage().getDownloadUrl()
                                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    String ImgLink=uri.toString();
                                                    linkimg=ImgLink;
                                                    Toast.makeText(addEventActivity.this, linkimg, Toast.LENGTH_SHORT).show();
                                                    uploadData.setImgURL(linkimg);

                                                }
                                            });

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(addEventActivity.this, "fucked ra", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                }
                            });
                }

                if(DocPathUri!=null){
                    StorageReference storageReference1=storageReference.child(StoragePath + System.currentTimeMillis() + "." + getExtension(DocPathUri));

                    storageReference1.putFile(DocPathUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    taskSnapshot.getStorage().getDownloadUrl()
                                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String DocLink=uri.toString();
                                                    linkdoc=DocLink;
                                                    uploadData.setDocURL(linkdoc);
                                                }
                                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("TAG_FOR_FAILURE LOG", "On Failure: The exception", e);
                                        }
                                    });

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(addEventActivity.this, "doc fucked", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

                progressDialog.dismiss();

            }
        });
    }

}
