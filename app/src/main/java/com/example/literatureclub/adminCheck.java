package com.example.literatureclub;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class adminCheck {

    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

    public boolean check(){
        if(firebaseUser!=null){
            if(firebaseUser.getUid()=="QaiKRh8meaWJJhfCnrBPpClxHx12")return true;
        }return false;
    }

}
