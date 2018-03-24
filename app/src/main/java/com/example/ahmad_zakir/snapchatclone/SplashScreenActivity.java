package com.example.ahmad_zakir.snapchatclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ahmad_zakir on 1/17/2018.
 */
// Melakukan pengecekan apakah pengguna sudah login atau belum. Jika sudah maka langsung ke main activity
public class SplashScreenActivity extends AppCompatActivity{
    public static Boolean started = false;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!= null){
            Intent intent = new Intent(getApplication(), MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;
        }else{
            Intent intent = new Intent(getApplication(), ChooseLoginRegistrationActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;
        }



    }

}
